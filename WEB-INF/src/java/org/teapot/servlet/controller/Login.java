package org.teapot.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.teapot.db.TeapotDb;
import org.teapot.db.orm.beetlsql.pojo.TblUser;

public class Login extends AbstractController {

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        VelocityContext context = new VelocityContext();

        TblUser user = null;
        String userNnm = "";
        String event = (String) req.getAttribute("login");
        if (event == null || event.length() <= 0) {
            user = new TblUser();
        } else {
            user = new TblUser();
            user.setUserAcnt((String)req.getAttribute("email"));
            // Single item check 单项检查
            // 必须输入项目
            // 类型
            // 长度

            // Related checks 相关检查
            // 项目间的比如开始日期，结束日期

            // System check 系统检查
            // 与数据库等相关的检查
            String pwda = (String) req.getAttribute("passworda");


            // check
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                conn = TeapotDb.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT USER_ID,USER_NNM FROM tbl_user WHERE USER_ACNT='" + req.getAttribute("email") + "' ORDER BY APPLI_ST_DT DESC");
                while (rs.next()) {
                    user.setUserId(rs.getString("USER_ID"));
                    resp.getWriter().write("ID=" + rs.getString("USER_ID") + ", ");
                    userNnm = rs.getString("USER_NNM");
                    resp.getWriter().write("昵称=" + userNnm + "\n");
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
                if (stmt != null ) { try {stmt.close(); } catch (SQLException e) {e.printStackTrace();} }
                if (conn != null ) { try {TeapotDb.releaseConnection(conn); } catch (SQLException e) {e.printStackTrace();} }
            }
        }

        if (userNnm == null || userNnm.length() <= 0) {
            // Login failure失败
            context.put("user", user);
            Template tmplate = Velocity.getTemplate("Login.vm", "UTF8");
            PrintWriter writer = resp.getWriter();
            tmplate.merge(context, writer);
        } else {
            // Login success成功
            // write cookie
            Cookie userCookie = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                System.out.println("----------------------------------");
                for (int i = 0; i < cookies.length; i++) {
                    userCookie = cookies[i];
                    System.out.println("Cookie.Name      :" + userCookie.getName());
                    System.out.println("Cookie.Value     :" + userCookie.getValue());
                    System.out.println("Cookie.Domain    :" + userCookie.getDomain());
                    System.out.println("Cookie.Path      :" + userCookie.getPath());
                    System.out.println("Cookie.Comment   :" + userCookie.getComment());
                    System.out.println("Cookie.Secure    :" + userCookie.getSecure());
                    System.out.println("Cookie.MaxAge    :" + userCookie.getMaxAge());
                    System.out.println("Cookie.Version   :" + userCookie.getVersion());
                    System.out.println("Cookie.isHttpOnly:" + userCookie.isHttpOnly());
                    System.out.println("");
                    if ( "user".equalsIgnoreCase( URLDecoder.decode( userCookie.getName(), "UTF-8") ) ) {
                        userCookie.setValue(URLEncoder.encode(userNnm, "UTF-8"));
                        break;
                    }
                }
                System.out.println("----------------------------------");
            }
            if (userCookie == null) {
                userCookie = new Cookie("user", URLEncoder.encode(userNnm, "UTF-8"));
            }
            userCookie.setComment("Comment");
            userCookie.setDomain("192.168.11.32");
            userCookie.setPath("/teapot/");
            userCookie.setHttpOnly(false);
            userCookie.setSecure(false);
            userCookie.setVersion(1);
            userCookie.setMaxAge(60 * 60 * 24 * 365);
            System.out.println("Cookie.Name      :" + userCookie.getName());
            System.out.println("Cookie.Value     :" + userCookie.getValue());
            System.out.println("Cookie.Domain    :" + userCookie.getDomain());
            System.out.println("Cookie.Path      :" + userCookie.getPath());
            System.out.println("Cookie.Comment   :" + userCookie.getComment());
            System.out.println("Cookie.Secure    :" + userCookie.getSecure());
            System.out.println("Cookie.MaxAge    :" + userCookie.getMaxAge());
            System.out.println("Cookie.Version   :" + userCookie.getVersion());
            System.out.println("Cookie.isHttpOnly:" + userCookie.isHttpOnly());
            resp.addCookie(userCookie);

            context.put("user", user);
            context.put("userid", user.getUserId());
            context.put("userNnm", userNnm);
            context.put("link", "http://192.168.11.32:8080/teapot/t/");

            Template tmplate = Velocity.getTemplate("Index.vm", "UTF8");
            PrintWriter writer = resp.getWriter();
            tmplate.merge(context, writer);
            Object[] keys = context.getKeys();
            System.out.println("------------------");
            for (Object key : keys) {
                System.out.println(key + "=" + context.get((String) key));
            }
            System.out.println("------------------");
        }


    }

}
