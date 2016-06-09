/**
 *
 */
package org.teapot.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        log("Servlet init(ServletConfig) --- begin");

    	ServletContext context = config.getServletContext();
        if (context == null) {
            context = new TeapotServletContext();
        }

        log("Servlet init(ServletConfig) config=" + config); // ServletConfig

        log("Servlet init(ServletConfig) config=" + this.getServletConfig()); // null
        super.init(config);
        log("Servlet init(ServletConfig) config=" + this.getServletConfig()); // ServletConfig

        log("Servlet init(ServletConfig) ServletInfo=" + this.getServletInfo()); // ""

        log("Servlet init(ServletConfig) --- end");
    }

    /**
     * 2.init()
     * GenericServlet->Servlet
     */
    @Override
    public void init() throws ServletException {
        log("Servlet init() --- begin");
        super.init(); // NOOP
        ServletConfig conf = this.getServletConfig();
        ServletContext context = conf.getServletContext();

        log("Servlet init() --- end");
    }

    /**
     * Servlet
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log("Servlet service() --- begin");
        super.service(req, res); // service(HttpServlet)

        log("Servlet service() --- end");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log("Servlet(Http) service() --- begin");
        super.service(req, res);

        log("Servlet(Http) service() --- end");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Servlet(Http) doDelete() --- begin");
        super.doDelete(req, resp);
        log("Servlet(Http) doGet() --- end");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Servlet(Http) doGet() --- begin");
        Cookie[] cks        = req.getCookies();
        HttpSession session = req.getSession();
        String authType     = req.getAuthType();
        String remoteUser   = req.getRemoteUser();
        int contentLength   = req.getContentLength();
        String contentType  = req.getContentType();
        // String contentType = req.getHeaders(arg0);
        String method = req.getMethod();
        String requestURI   = req.getRequestURI();
        String queryString  = req.getQueryString();
        String protocol     = req.getProtocol();
        log("Servlet(Http) doGet() cks             = " + cks);
        log("Servlet(Http) doGet() AUTH_TYPE       = " + authType);
        log("Servlet(Http) doGet() REMOTE_USER     = " + remoteUser);
        log("Servlet(Http) doGet() CONTENT_LENGTH  = " + contentLength);
        log("Servlet(Http) doGet() CONTENT_TYPE    = " + contentType);
        log("Servlet(Http) doGet() REQUEST_METHOD  = " + method);
        log("Servlet(Http) doGet() requestURI      = " + requestURI);
        log("Servlet(Http) doGet() QUERY_STRING    = " + queryString);
        log("Servlet(Http) doGet() SERVER_PROTOCOL = " + protocol);
        String userAgent = req.getHeader("User-Agent");
        log("Servlet(Http) doGet() userAgent       = " + userAgent);
        log("Servlet(Http) doGet() DOCUMENT_ROOT   = " + this.getServletContext().getRealPath("/"));
        log("Servlet(Http) doGet() PATH_INFO       = " + req.getPathInfo());
        log("Servlet(Http) doGet() PATH_TRANSLATED = " + req.getPathTranslated());
        log("Servlet(Http) doGet() REMOTE_ADDR     = " + req.getRemoteAddr());
        log("Servlet(Http) doGet() REMOTE_HOST     = " + req.getRemoteHost());
        log("Servlet(Http) doGet() SCRIPT_NAME     = " + req.getServletPath());
        log("Servlet(Http) doGet() SERVER_NAME     = " + req.getServerName());
        log("Servlet(Http) doGet() SERVER_PORT     = " + req.getServerPort());
        log("Servlet(Http) doGet() SERVER_SOFTWARE = " + this.getServletContext().getServerInfo());

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
//        if (GzipUtilities.isGzipSupported(req) &&
//            !GzipUtilities.isGzipDisabled(req)) {
//
//        }
        Cookie userCookie = new Cookie("user", "user1234");
        userCookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(userCookie);

        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
        out.println(docType +
            "<HTML>\n" +
            "<HEAD><TITLE>Hello</TITLE></HEAD>\n" +
            "<BODY BGCOLOR=\"#FDF5E6\">\n" +
            "<H1>Hello</H1>\n" +
            "<FORM ACTION=\"/teapot/t/AAA\">" +
            "First Parameter:<INPUT TYPE=\"TEXT\" NAME=\"param1\">\n" +
            "<INPUT TYPE=\"SUBMIT\">\n" +
            "</FORM>\n" +
            "</BODY>\n</HTML>");

        log("Servlet(Http) doGet() --- end");

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
        log("Servlet(Http) doHead() --- begin");
        super.doHead(req, resp);
        log("Servlet(Http) doHead() --- end");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Servlet(Http) doOptions() --- begin");
        super.doOptions(req, resp);
        log("Servlet(Http) doOptions() --- end");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        log("Servlet(Http) doPost() --- begin");
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
        log("Servlet(Http) doPost() --- end");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Servlet(Http) doPut() --- begin");
        super.doPut(req, resp);
        log("Servlet(Http) doPut() --- end");
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Servlet(Http) doTrace() --- begin");
        super.doTrace(req, resp);
        log("Servlet(Http) doTrace() --- end");
    }

    /**
     * on destroy
     * Servlet
     */
    @Override
    public void destroy() {
        log("Servlet destroy() --- begin");
        super.destroy(); // NOOP

        log("Servlet destroy() --- end");
    }

    /**
     * @see javax.servlet.GenericServlet#log(java.lang.String)
     */
    @Override
    public void log(String msg) {
        System.out.println(msg);
    }

}
