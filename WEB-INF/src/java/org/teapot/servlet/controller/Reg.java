package org.teapot.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.teapot.db.TeapotDb;
import org.teapot.db.jdbc.JSerialNumber;
import org.teapot.db.orm.beetlsql.pojo.TblAuth;
import org.teapot.db.orm.beetlsql.pojo.TblUser;

public class Reg extends AbstractController {

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        VelocityContext context = new VelocityContext();

        TblUser user = null;
        TblAuth auth = null;
        String event = (String) req.getAttribute("register");
        if (event == null || event.length() <= 0) {
            user = new TblUser();
        } else {
            user = new TblUser();
            String[] keys = {
                    "business",
                    "user",
                    "id"
            };
            try {
                user.setUserId("" + JSerialNumber.updateSerialNumber(keys));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            user.setAppliStDt("20170518");
            user.setAppliEdDt("99991231");
            user.setUserAcnt((String)req.getAttribute("email"));
            // Single item check 单项检查
            // 必须输入项目
            // 类型
            // 长度

            // Related checks 相关检查
            // 项目间的比如开始日期，结束日期

            // System check 系统检查
            // 与数据库等相关的检查
            user.setUserNnm((String)req.getAttribute("nickname"));
            user.setUserEml((String)req.getAttribute("email"));
            String pwda = (String) req.getAttribute("passworda");
            String pwdb = (String) req.getAttribute("passwordb");
            if (!StringUtils.equals(pwda, pwdb)) {
                // Error
            }
            user.setFstCrtDt("20170518");
            user.setFstCrtTm("121314");
            user.setRntUpdDt("20170518");
            user.setRntUpdTm("121314");
            user.setUpdUserId("none");
            user.setUpdVwId("reg");

            auth = new TblAuth();
            auth.setUserId(user.getUserId());
            auth.setAppliStDt(user.getAppliStDt());
            auth.setAppliEdDt(user.getAppliEdDt());
            auth.setUserPwd(pwda);
            auth.setFstCrtDt(user.getFstCrtDt());
            auth.setFstCrtTm(user.getFstCrtTm());
            auth.setRntUpdDt(user.getRntUpdDt());
            auth.setRntUpdTm(user.getRntUpdTm());
            auth.setUpdUserId(user.getUpdUserId());
            auth.setUpdVwId(user.getUpdVwId());

            // check
            // getId
            // Insert
            Connection conn = null;
            Statement stmt = null;
            PreparedStatement ps = null;
            PreparedStatement ps2 = null;
            ResultSet rs = null;
            String sql  = "INSERT INTO tbl_user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO tbl_auth values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                // Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = TeapotDb.getConnection();
                conn.setAutoCommit(false);
//                stmt = conn.createStatement();
                ps = conn.prepareStatement(sql);
//                rs = stmt.executeQuery("SELECT USER_ID,USER_NNM FROM tbl_user");
                ps.setString(1, user.getUserId()); // USER_ID
                ps.setString(2, user.getAppliStDt()); // APPLI_ST_DT
                ps.setString(3, user.getAppliEdDt()); // APPLI_ED_DT
                ps.setString(4, user.getUserAcnt()); // USER_ACNT
                ps.setString(5, user.getUserNnm());// USER_NNM
                ps.setString(6, user.getUserEml()); // USER_EML
                ps.setString(7, user.getPstCd()); //PST_CD
                ps.setString(8, user.getCntctAddr()); // CNTCT_ADDR
                ps.setString(9, user.getUserFnm()); // USER_FNM
                ps.setString(10, user.getCntctPhn()); // CNTCT_PHN
                ps.setString(11, user.getIdcrdNo()); // IDCRD_NO
                ps.setString(12, user.getFstCrtDt()); // FST_CRT_DT
                ps.setString(13, user.getFstCrtTm()); // FST_CRT_TM
                ps.setString(14, user.getRntUpdDt()); // RNT_UPD_DT
                ps.setString(15, user.getRntUpdTm()); // RNT_UPD_TM
                ps.setString(16, user.getUpdUserId()); // UPD_USER_ID
                ps.setString(17, user.getUpdVwId()); // UPD_VW_ID
                ps.addBatch();
                int i = ps.executeUpdate();
                System.out.println("結果：" + i);

                ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, auth.getUserId()); // USER_ID
                ps2.setString(2, auth.getAppliStDt()); // APPLI_ST_DT
                ps2.setString(3, auth.getAppliEdDt()); // APPLI_ED_DT
                ps2.setString(4, auth.getUserPwd()); // USER_PWD
                ps2.setString(5, auth.getFstCrtDt()); // FST_CRT_DT
                ps2.setString(6, auth.getFstCrtTm()); // FST_CRT_TM
                ps2.setString(7, auth.getRntUpdDt()); // RNT_UPD_DT
                ps2.setString(8, auth.getRntUpdTm()); // RNT_UPD_TM
                ps2.setString(9, auth.getUpdUserId()); // UPD_USER_ID
                ps2.setString(10, auth.getUpdVwId()); // UPD_VW_ID
                i = ps2.executeUpdate();
                System.out.println("結果：" + i);

                conn.commit();

            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
                if (stmt != null ) { try {stmt.close(); } catch (SQLException e) {e.printStackTrace();} }
                if (conn != null ) { try {TeapotDb.releaseConnection(conn); } catch (SQLException e) {e.printStackTrace();} }
            }
        }

        context.put("user", user);
        Template tmplate = Velocity.getTemplate("Reg.vm", "UTF8");

        PrintWriter writer = resp.getWriter();
        tmplate.merge(context, writer);
    }
}
