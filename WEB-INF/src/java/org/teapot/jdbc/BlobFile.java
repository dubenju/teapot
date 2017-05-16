package org.teapot.jdbc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BlobFile {
    public static byte[] selectBLOB(MastCriteria criteria, Connection dbConn) throws Exception {

            Blob blob = selectAsBLOB(criteria, dbConn);

            InputStream is = blob.getBinaryStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            do {
                int data = is.read();
                if(data == -1) {
                    break;
                }
                baos.write(data);
            } while(true);

            byte data[] = baos.toByteArray();
            return data;
        }

        public static Blob selectAsBLOB(MastCriteria criteria, Connection dbConn) throws Exception {
            if(criteria.getSelectColumns().empty()) {
                throw new Exception("Selected Columns is empty!!");
            }
            if(criteria.getSelectColumns().size() != 1) {
                throw new Exception("Selected Columns should have only blob field!");
            }
            Map<String, Object> prep = MastBasePeer.prepareQueryString(criteria);

            String sql = (String) prep.get("sql");
            List<Object> params = (List<Object>) prep.get("params");
            PreparedStatement stmt = dbConn.prepareStatement(sql);
            stmt.setFetchSize(1);
            if(params != null) {
                MastBasePeer.bindParameterValues(stmt, params);
            }

            ResultSet rset = stmt.executeQuery();

            int numOfFetch = 0;
            Blob blob;
            // (Blob) rset.getObject(1))
            for(blob = null; rset.next(); blob = rset.getBlob(1)) {
                if(numOfFetch != 0) {
                    throw new Exception("Multiple rows hit! Specify a complete primary key in criteria");
                }
            }

            rset.close();
            stmt.close();
            return blob;
        }

        public static void saveAsBLOB(String tableName, String primaryKeys[][], String blobFieldName, byte data[], Connection dbConn) throws Exception {
            MastCriteria criteria = new MastCriteria();
            for(int i = 0; i < primaryKeys.length; i++) {
                String columnInNeat = null;
                if(primaryKeys[i][0].indexOf(".") == -1) {
                    columnInNeat = tableName + "." + primaryKeys[i][0];
                } else {
                    columnInNeat = primaryKeys[i][0];
                }
                criteria.add(columnInNeat, primaryKeys[i][1]);
            }

            String selectColumnInNeat = null;
            if(blobFieldName.indexOf(".") == -1) {
                selectColumnInNeat = tableName + "." + blobFieldName;
            } else {
                selectColumnInNeat = blobFieldName;
            }
            criteria.addSelectColumn(selectColumnInNeat);
            Blob currentData = selectAsBLOBWithPK(criteria, dbConn);
            if(currentData == null) {
                insertAsBLOB(tableName, primaryKeys, blobFieldName, data, dbConn);
                return;
            }
            StringBuffer sb = new StringBuffer();
            sb.append("BEGIN ");
            sb.append("UPDATE ");
            sb.append(tableName);
            sb.append(" SET ");
            sb.append(blobFieldName);
            sb.append(" = ");
            sb.append(" EMPTY_BLOB() ");
            sb.append(" WHERE ");
            for(int i = 0; i < primaryKeys.length; i++) {
                if(i != 0) {
                    sb.append(" AND ");
                }
                sb.append(primaryKeys[i][0]);
                sb.append(" = ?");
            }

            sb.append(" RETURNING ");
            sb.append(blobFieldName);
            sb.append(" INTO ?;");
            sb.append(" END;");
            String sql = sb.toString();
            CallableStatement cstmt = null;
            try {
                dbConn.setAutoCommit(false);
                cstmt = dbConn.prepareCall(sql);
                int columnIndex;
                for(columnIndex = 1; columnIndex <= primaryKeys.length; columnIndex++) {
                    cstmt.setString(columnIndex, primaryKeys[columnIndex - 1][1]);
                }

                cstmt.registerOutParameter(columnIndex, 2004);

                try {
                    cstmt.executeQuery();
                }
                catch(SQLException se) {
                    System.out.println("[MastLOB] Failed to execute a query by " + sql);

                    throw se;
                }
                Blob blob = cstmt.getBlob(columnIndex);
                putBytes(blob, data);

                dbConn.commit();
            } finally {
                dbConn.setAutoCommit(true);
                if(cstmt != null) {
                    cstmt.close();
                }
            }
        }

        public static void insertAsBLOB(String tableName, String primaryKeys[][], String blobFieldName, byte data[], Connection dbConn) {
            StringBuffer sb = new StringBuffer();
            StringBuffer sbu = new StringBuffer();
//            sb.append("BEGIN ");
            sb.append("INSERT INTO ");
            sb.append(tableName);
            sb.append(" (");
            for(int i = 0; i < primaryKeys.length; i++) {
                if(i != 0) {
                    sb.append(",");
                }
                sb.append(primaryKeys[i][0]);
            }

            sb.append(",");
            sb.append(blobFieldName);
            sb.append(") ");
            sb.append(" VALUES ");
            sb.append("(");
            for(int i = 0; i < primaryKeys.length; i++) {
                if(i != 0) {
                    sb.append(",");
                }
                sb.append("?");
            }

            sb.append(",");
//            sb.append(" EMPTY_BLOB() ");
            sb.append(" NULL");
            sb.append(") ");
//            sb.append(" RETURNING ");
//            sb.append(blobFieldName);
//            sb.append(" INTO ?;");
//            sb.append(" END;");
            sb.append(";");

            sbu.append("UPDATE ");
            sbu.append(tableName);
            sbu.append(" SET ");
            sbu.append(blobFieldName);
            sbu.append("=? WHERE ");
            for(int i = 0; i < primaryKeys.length; i++) {
                if(i != 0) {
                    sbu.append(" AND ");
                }
                sbu.append(primaryKeys[i][0] + "='" + primaryKeys[i][1] + "'");
            }
            sbu.append(";");

            String sql = sb.toString();
            String updSql = sbu.toString();

            //CallableStatement cstmt = null;
            PreparedStatement cstmt = null;
            PreparedStatement updStmt = null;
            try {
                dbConn.setAutoCommit(false);
//                cstmt = dbConn.prepareCall(sql);
                cstmt = dbConn.prepareStatement(sql);
                updStmt  = dbConn.prepareStatement(updSql);

                int columnIndex;
                for(columnIndex = 1; columnIndex <= primaryKeys.length; columnIndex++) {
                    cstmt.setString(columnIndex, primaryKeys[columnIndex - 1][1]);
                }
                updStmt.setBytes(1, data);
                //InputStream strm = new BufferedInputStream(new ByteArrayInputStream(data));
                //updStmt.setBinaryStream(1, strm, data.length);

//                cstmt.registerOutParameter(columnIndex, 2004);
                try {
                    cstmt.executeUpdate();
                    updStmt.executeUpdate();
                } catch(SQLException se) {
                    System.out.println("[MastLOB] Failed to execute an update query by " + sql);
                    System.out.println("[MastLOB] Failed to execute an update query by " + updSql);
                    throw se;
                }
//                Blob blob = cstmt.getBlob(columnIndex);
//                putBytes(blob, data);

                dbConn.commit();
            } finally {
//                dbConn.setAutoCommit(true);
                if(cstmt != null) {
                    cstmt.clearParameters();
                    cstmt.close();
                }
                if(updStmt != null) {
                    updStmt.clearParameters();
                    updStmt.close();
                }
            }
        }

        private static void putBytes(Blob blob, byte data[]) {
            try {
                Method m = blob.getClass().getMethod("putBytes", new Class[] {
                    Long.TYPE, byte[].class
                });
                m.invoke(blob, new Object[] {
                    new Long(1L), data
                });
            }
            catch(Exception e) {
//                Log.error(e);
            }
        }
}
