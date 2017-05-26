package org.teapot.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class Index extends AbstractController {

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

//    if (GzipUtilities.isGzipSupported(req) &&
//        !GzipUtilities.isGzipDisabled(req)) {
//
//    }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = TeapotDb.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT USER_ID,USER_NNM FROM tbl_user");

  //        Class.forName("org.sqlite.JDBC");
  //        conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/testdb.sqlite");
  //        stmt = conn.createStatement();
  //        rs = stmt.executeQuery("SELECT userid,status FROM userinfo");

//            resp.setContentType("text/plain");
//            while (rs.next()) {
//                resp.getWriter().write("ID=" + rs.getString("USER_ID") + ", ");
//                resp.getWriter().write("昵称=" + rs.getString("USER_NNM") + "\n");
//            }
//            System.out.println("ResultSet复位：" + rs.first());
//            do {
//                resp.getWriter().write("ID=" + rs.getString("USER_ID") + ", ");
//                resp.getWriter().write("昵称=" + rs.getString("USER_NNM") + "\n");
//            } while (rs.next());
//            rs.beforeFirst();
//            while (rs.next()) {
//                resp.getWriter().write("ID=" + rs.getString("USER_ID") + ", ");
//                resp.getWriter().write("昵称=" + rs.getString("USER_NNM") + "\n");
//            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
            if (stmt != null ) { try {stmt.close(); } catch (SQLException e) {e.printStackTrace();} }
            if (conn != null ) { try {TeapotDb.releaseConnection(conn); } catch (SQLException e) {e.printStackTrace();} }
        }


        Cookie userCookie = new Cookie("user", "user1234");
        userCookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(userCookie);

//        String docType = "<!DOCTYPE HTML>\n";
//        writer.println(docType +
//            "<HTML>\n" +
//            "<HEAD><TITLE>Hello</TITLE></HEAD>\n" +
//            "<BODY BGCOLOR=\"#FDF5E6\">\n" +
//            "<H1>Hello</H1>\n" +
//            "<FORM ACTION=\"/teapot/t/AAA\">" +
//            "First Parameter:<INPUT TYPE=\"TEXT\" NAME=\"param1\">\n" +
//            "<INPUT TYPE=\"SUBMIT\">\n" +
//            "</FORM>\n" +
//            "</BODY>\n</HTML>");


        VelocityContext context = new VelocityContext();
        context.put("msgObj", "test");
        context.put("link", "http://192.168.11.32:8080/teapot/t/");

        Template tmplate = Velocity.getTemplate("Index.vm", "UTF8");

        PrintWriter writer = resp.getWriter();
        tmplate.merge(context, writer);
    }

}
