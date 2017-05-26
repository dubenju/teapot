/**
 *
 */
package org.teapot.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.Velocity;
import org.teapot.db.TeapotDb;
import org.teapot.servlet.controller.Controllsers;

/**
 * @author DBJ
 *
 */
public class TeapotServlet extends HttpServlet {


    /**
     * 1.init(ServletConfig config)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        log("□1.Servlet init(ServletConfig) --- begin" + Thread.currentThread().getId());

    	ServletContext context = config.getServletContext();
        if (context == null) {
            context = new TeapotServletContext();
        }

        log("□1.Servlet init(ServletConfig) config=" + config); // ServletConfig

        log("□1.Servlet init(ServletConfig) config=" + this.getServletConfig()); // null
        super.init(config);
        try {
            TeapotDb.init();
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            e.printStackTrace();
            log(e.toString());
        }

        Velocity.setProperty("file.resource.loader.path", context.getRealPath("/") + "./template/velocity");
        Velocity.init();

        log("□1.Servlet init(ServletConfig) config=" + this.getServletConfig()); // ServletConfig

        log("□1.Servlet init(ServletConfig) ServletInfo=" + this.getServletInfo()); // ""

        log("□1.Servlet init(ServletConfig) --- end" + Thread.currentThread().getId());
    }

    /**
     * 2.init()
     * GenericServlet->Servlet
     */
    @Override
    public void init() throws ServletException {
        log("□2.Servlet init() --- begin" + Thread.currentThread().getId());
        super.init(); // NOOP
        ServletConfig conf = this.getServletConfig();
        ServletContext context = conf.getServletContext();


        log("□2.Servlet init() --- end" + Thread.currentThread().getId());
    }

    /**
     * Servlet
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log("Servlet service() --- begin" + Thread.currentThread().getId());
        super.service(req, res); // service(HttpServlet)

        log("Servlet service() --- end" + Thread.currentThread().getId());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log("Servlet(Http) service() --- begin" + Thread.currentThread().getId());
        super.service(req, res);

        log("Servlet(Http) service() --- end" + Thread.currentThread().getId());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Servlet(Http) doDelete() --- begin" + Thread.currentThread().getId());
        super.doDelete(req, resp);
        log("Servlet(Http) doGet() --- end" + Thread.currentThread().getId());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("◆Servlet(Http) doGet() --- begin" + Thread.currentThread().getId());
        Cookie[] cks        = req.getCookies();
        HttpSession session = req.getSession();
        String authType     = req.getAuthType();
        String remoteUser   = req.getRemoteUser();
        int contentLength   = req.getContentLength();
        String contentType  = req.getContentType();
        // String contentType = req.getHeaders(arg0);
        String method       = req.getMethod();
        String requestURI   = req.getRequestURI();
        String queryString  = req.getQueryString();
        String protocol     = req.getProtocol();
        log("◆Servlet(Http) doGet() cks             = " + cks);
        log("◆Servlet(Http) doGet() AUTH_TYPE       = " + authType);
        log("◆Servlet(Http) doGet() REMOTE_USER     = " + remoteUser);
        log("◆Servlet(Http) doGet() CONTENT_LENGTH  = " + contentLength);
        log("◆Servlet(Http) doGet() CONTENT_TYPE    = " + contentType);
        log("◆Servlet(Http) doGet() REQUEST_METHOD  = " + method);
        log("◆Servlet(Http) doGet() requestURI      = " + requestURI);
        log("◆Servlet(Http) doGet() QUERY_STRING    = " + queryString);
        log("◆Servlet(Http) doGet() SERVER_PROTOCOL = " + protocol);
        String userAgent = req.getHeader("User-Agent");
        log("◆Servlet(Http) doGet() userAgent       = " + userAgent);
        log("◆Servlet(Http) doGet() DOCUMENT_ROOT   = " + this.getServletContext().getRealPath("/"));
        log("◆Servlet(Http) doGet() PATH_INFO       = " + req.getPathInfo());
        log("◆Servlet(Http) doGet() PATH_TRANSLATED = " + req.getPathTranslated());
        log("◆Servlet(Http) doGet() REMOTE_ADDR     = " + req.getRemoteAddr());
        log("◆Servlet(Http) doGet() REMOTE_HOST     = " + req.getRemoteHost());
        log("◆Servlet(Http) doGet() SCRIPT_NAME     = " + req.getServletPath());
        log("◆Servlet(Http) doGet() SERVER_NAME     = " + req.getServerName());
        log("◆Servlet(Http) doGet() SERVER_PORT     = " + req.getServerPort());
        log("◆Servlet(Http) doGet() SERVER_SOFTWARE = " + this.getServletContext().getServerInfo());
        log("◆Servlet(Http) doGet() -----------------------------------");
        Enumeration<String> paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String param = paramNames.nextElement();
            log("◆Servlet(Http) doGet() " + param + " = " +req.getParameter(param));
        }
        log("◆Servlet(Http) doGet() -----------------------------------");
//        ServletInputStream sis = req.getInputStream();
//        sis.close();


        String action = req.getParameter("_a");
        log("◆Servlet(Http) doGet() action = " + action);
        int ia = 0;
        if (action != null) {
            ia = Integer.parseInt(action);
        }
        log("◆Servlet(Http) doGet() ia = " + ia);
        Controllsers.getInstance().getControllser(ia).action(req, resp);

        log("◆Servlet(Http) doGet() --- end" + Thread.currentThread().getId());

//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//          Class.forName("com.mysql.jdbc.Driver").newInstance();
//          conn = DriverManager.getConnection("jdbc:mysql://localhost/sampledb?user=root&password=root");
//          stmt = conn.createStatement();
//          rs = stmt.executeQuery("SELECT userid,status FROM userinfo");
//
////          Class.forName("org.sqlite.JDBC");
////          conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/testdb.sqlite");
////          stmt = conn.createStatement();
////          rs = stmt.executeQuery("SELECT userid,status FROM userinfo");
//
//          resp.setContentType("text/plain");
//          while (rs.next()) {
//              resp.getWriter().write("userid=" + rs.getString("userid") + ", ");
//              resp.getWriter().write("status=" + rs.getString("status") + "\n");
//          }
//        } catch(Exception e) {
//          e.printStackTrace();
//        } finally {
//          if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
//          if (stmt != null ) { try {stmt.close(); } catch (SQLException e) {e.printStackTrace();} }
//          if (conn != null ) { try {conn.close(); } catch (SQLException e) {e.printStackTrace();} }
//        }
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("★Servlet(Http) doHead() --- begin" + Thread.currentThread().getId());
        super.doHead(req, resp);
        log("★Servlet(Http) doHead() --- end" + Thread.currentThread().getId());
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("■Servlet(Http) doOptions() --- begin" + Thread.currentThread().getId());
        super.doOptions(req, resp);
        log("■Servlet(Http) doOptions() --- end" + Thread.currentThread().getId());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        log("●Servlet(Http) doPost() --- begin" + Thread.currentThread().getId());
        //super.doPost(req, resp);
        request.setCharacterEncoding("utf-8");
//
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
////        ServletContext servletContext = this.getServletConfig().getServletContext();
////        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
////        factory.setRepository(repository);
//
//        //获取绝对路径
//        String path  = request.getRealPath("/upload");
//        //
//        factory.setRepository(new File(path));
//        factory.setSizeThreshold(1024*1024);
//        //
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        try {
//            RequestContext ctx = new ServletRequestContext(request);
//            List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
//            System.out.println(list.size());
//            for (FileItem fileItem : list) {
//                System.out.println(fileItem.getFieldName());
//            }
//        } catch (Exception e) {
//        // TODO: handle exception
//        e.printStackTrace();
//        }

//      //备份HttpServletRequest
//        HttpServletRequest httpRequest = (HttpServletRequest)request;
//        httpRequest = new BufferedServletRequestWrapper( httpRequest );
//        //使用流
//        InputStream is = request.getInputStream();
//        //其他业务逻辑

        this.doGet(request, resp);
        log("●Servlet(Http) doPost() --- end" + Thread.currentThread().getId());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("▲Servlet(Http) doPut() --- begin" + Thread.currentThread().getId());
        super.doPut(req, resp);
        log("▲Servlet(Http) doPut() --- end" + Thread.currentThread().getId());
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("▼Servlet(Http) doTrace() --- begin" + Thread.currentThread().getId());
        super.doTrace(req, resp);
        log("▼Servlet(Http) doTrace() --- end" + Thread.currentThread().getId());
    }

    /**
     * on destroy
     * Servlet
     */
    @Override
    public void destroy() {
        log("◎Servlet destroy() --- begin" + Thread.currentThread().getId());
        super.destroy(); // NOOP

        log("◎Servlet destroy() --- end" + Thread.currentThread().getId());
    }

    /**
     * @see javax.servlet.GenericServlet#log(java.lang.String)
     */
    @Override
    public void log(String msg) {
        System.out.println(msg);
    }

}
