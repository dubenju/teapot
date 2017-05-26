package org.teapot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeapotDb {
    public static void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    }
    public static TeapotDBConnection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/teapot?user=root&password=root&useUnicode=true&characterEncoding=utf8");
        return new TeapotDBConnection(conn);
    }
    public static void releaseConnection(Object dbConn) throws SQLException {
        if (dbConn instanceof Connection) {
            ((Connection) dbConn).close();
        }
    }
}
