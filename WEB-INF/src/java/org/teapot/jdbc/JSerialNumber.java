package org.teapot.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author DBJ
 *
 */
public class JSerialNumber {
    static final String USER = "root";
    static final String PASS = "root";
    static final String DB_URL = "jdbc:mysql://localhost/teapot?characterEncoding=utf8";

    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 9999999999999999L;

    private static String MSG_INVALID_KEYS = "Null key or number of keys less than 3 for table serial_number.";
    private static String MSG_NULL_CONN = "Null DB connection.";
    private static String MSG_INVALID_PARAM = "Invalid paramater in value, step, or max value.";
    private static String MSG_ZERO_RECORD = "Zero record inquired at serial_number ";
    /**
     * @param args
     */
    public static void main(String[] args) {
    }
    public static void init() {
    }

    public static final int getSerialNumber(String[] keys) throws Exception {
        int retSeqNum = -1;
        if(keys == null || keys.length != 3) {
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        Connection dbConn = null;
        dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        retSeqNum = getSerialNumber(keys, dbConn);

        dbConn.close();

        return retSeqNum;
    }
    public static final int getSerialNumber(String[] keys, Connection dbConn) throws Exception {
        int retSeqNum = -1;

        // 必须是3个key
        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        // 查询
        String sql = "SELECT CURRENT_VALUE FROM SERIAL_NUMBER TBL ";
        sql += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sql += "AND   TBL.KEY2='" + keys[1] + "' ";
        sql += "AND   TBL.KEY3='" + keys[2] + "' ";
        sql += "ORDER BY TBL.KEY1, TBL.KEY2, TBL.KEY3";
        PreparedStatement stmt = dbConn.prepareStatement(sql);
        ResultSet rset = stmt.executeQuery();

        if (!rset.next()) {
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_ZERO_RECORD +
                    "key1:" + keys[0] + ";key2:" + keys[1] + ";key3:" + keys[2]);
        }

        // 结果存在的话取出
        String ret = rset.getString("CURRENT_VALUE");
        retSeqNum = Integer.parseInt(ret);

        stmt.close();
        rset.close();
        return retSeqNum;
    }
    public static final int getStepSize(String[] keys) throws Exception {
        int retUnit = -100000000;
        // 必须是3个key
        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        Connection dbConn = null;
        // 访问DB
        dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }
        // 查询
        retUnit = getStepSize(keys, dbConn);
        dbConn.close();

        return retUnit;
    }
    public static final int getStepSize(String[] keys, Connection dbConn) throws Exception {
        int retUnit = -100000000;
        // 必须是3个key
        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        // 查询
        String sql = "SELECT STEP_VALUE FROM SERIAL_NUMBER TBL ";
        sql += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sql += "AND   TBL.KEY2='" + keys[1] + "' ";
        sql += "AND   TBL.KEY3='" + keys[2] + "' ";
        sql += "ORDER BY TBL.KEY1, TBL.KEY2, TBL.KEY3";
        PreparedStatement stmt = dbConn.prepareStatement(sql);
        ResultSet rset = stmt.executeQuery();

        if (!rset.next()) {
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_ZERO_RECORD +
                    "key1:" + keys[0] + ";key2:" + keys[1] + ";key3:" + keys[2]);
        }

        // データが存在する場合、値をセット
        String ret = rset.getString("UNIT");
        retUnit = Integer.parseInt(ret);

        stmt.close();
        rset.close();
        return retUnit;
    }
    public static final long getMaxValue(String[] keys) throws Exception {
        long retMax = -1;

        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        Connection dbConn = null;

        dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        retMax = getMaxValue(keys, dbConn);
        dbConn.close();

        return retMax;
    }
    public static final long getMaxValue(String[] keys, Connection dbConn) throws Exception {
        long retMax = -1;

        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }


        String sql = "SELECT MAX_VALUE FROM SERIAL_NUMBER TBL ";
        sql += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sql += "AND   TBL.KEY2='" + keys[1] + "' ";
        sql += "AND   TBL.KEY3='" + keys[2] + "' ";
        sql += "ORDER BY TBL.KEY1, TBL.KEY2, TBL.KEY3";
        PreparedStatement stmt = dbConn.prepareStatement(sql);
        ResultSet rset = stmt.executeQuery();

        if (!rset.next()) {
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_ZERO_RECORD +
                    "key1:" + keys[0] + ";key2:" + keys[1] + ";key3:" + keys[2]);
        }

        String ret = rset.getString("MAX_VALUE");
        retMax = Integer.parseInt(ret);

        stmt.close();
        rset.close();
        return retMax;
    }
    public static final int setSerialNumber(String[] keys, int value, int unit, int max) throws Exception {
        int retSeqNum = -1;
        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        Connection dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        try{
            dbConn.setAutoCommit(false);
            retSeqNum = setSerialNumber(keys, value, unit, max, dbConn);
            dbConn.commit();
        }
        catch(Exception e) {
            dbConn.rollback();
            throw e;
        } finally {
            dbConn.setAutoCommit(true);
            dbConn.close();
        }

        return retSeqNum;
    }
    public static final long updateSerialNumber(String[] keys) throws Exception{
    	long a = System.currentTimeMillis();
        long retSeqNum = -1;

        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        Connection dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }
        long b = System.currentTimeMillis();
        System.out.println("TupdateSerialNumber:sn=" + retSeqNum + "DB连接耗时：" + (b - a));

        try{
            dbConn.setAutoCommit(false);
            retSeqNum = updateSerialNumber(keys, dbConn);
            dbConn.commit();
        }
        catch(Exception e) {
            dbConn.rollback();
            throw e;
        } finally {
            dbConn.setAutoCommit(true);
            dbConn.close();
        }
        long c = System.currentTimeMillis();
        System.out.println("TupdateSerialNumber:sn=" + retSeqNum + "耗时：" + (c - b));
        System.out.println("TupdateSerialNumber:sn=" + retSeqNum + "耗时：" + (System.currentTimeMillis() - a));
        return retSeqNum;
    }

    /**
     * createSeqNumber
     * @param keys
     * @param value
     * @param unit
     * @param max
     * @return
     * @throws Exception
     */
    public static final long createSerialNumber(String[] keys, long value, long step, long max) throws Exception {
        long retSeqNum = -1;
        Connection dbConn = null;

        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        try {
            dbConn.setAutoCommit(false);

            retSeqNum = createSerialNumber(keys, value, step, max, dbConn);

            dbConn.commit();
        } catch(Exception e) {
            dbConn.rollback();
            throw e;
        } finally {
            dbConn.setAutoCommit(true);
            dbConn.close();
        }
        return retSeqNum;
    }

    /**
     *
     * @param keys
     * @param value
     * @param dbConn
     * @return
     * @throws Exception
     */
    public static final long createSerialNumber(String[] keys, long value, Connection dbConn) throws Exception {
        long retSeqNum = -1;

        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);

        }
        retSeqNum = createSerialNumber(keys,value, 1L, MAX_VALUE, dbConn);

        return retSeqNum;
    }

    /**
     *
     * @param keys
     * @param value
     * @param step
     * @param maxValue
     * @param dbConn
     * @return
     * @throws Exception
     */
    public static final long createSerialNumber(String[] keys, long value, long step, long maxValue, Connection dbConn) throws Exception {
        long retSeqNum = -1;

        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        if(!isAcceptable(value, step, maxValue)){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_PARAM);
        }

        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        String sql = "INSERT INTO SERIAL_NUMBER ";
        sql += "VALUES(";
        sql += "?, ";
        sql += "?, ";
        sql += "?, ";
        sql += "?, ";
        sql += "?, ";
        sql += "?, ";
        sql += "'" + "a" + "', ";
        sql += "'" + "b" + "', ";
        sql += "'" + "c" + "', ";
        sql += "'" + "d" + "', ";
        sql += "'" + "e" + "', ";
        sql += "'" + "f" + "' ";
        sql += ")" ;

        System.out.println("sql=" + sql);
        PreparedStatement stmt = dbConn.prepareStatement(sql);
        stmt.setString(1, keys[0]);
        stmt.setString(2, keys[1]);
        stmt.setString(3, keys[2]);
        stmt.setLong(4, value);
        stmt.setLong(5, step);
        stmt.setLong(6, maxValue);

        stmt.execute();
        retSeqNum = getSerialNumber(keys, dbConn);
        stmt.close();
        return retSeqNum;
    }
    public static final int setSerialNumber(String[] keys,
            int value,
            int unit,
            int maxValue,
            Connection dbConn) throws Exception {
        int retSeqNum = -1;

        // 必须是3个key
        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        if(!isAcceptable(value, unit, maxValue)){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_PARAM);
        }

        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        // 锁
        String sqlSel = "SELECT CURRENT_VALUE, STEP_VALUE, MAX_VALUE FROM SEQ_NUM_KANRI TBL ";
        sqlSel += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sqlSel += "AND   TBL.KEY2='" + keys[1] + "' ";
        sqlSel += "AND   TBL.KEY3='" + keys[2] + "' ";
        sqlSel += "FOR UPDATE";
        PreparedStatement stmt = dbConn.prepareStatement(sqlSel);
        ResultSet rset = stmt.executeQuery();

        if (!rset.next()) {
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_ZERO_RECORD +
                    "key1:" + keys[0] + ";key2:" + keys[1] + ";key3:" + keys[2]);
        }

        stmt.close();

        // 更新
        String sqlUps = "UPDATE SERIAL_NUMBER TBL ";
        sqlUps += "SET CURRENT_VALUE ='" + value + "', ";
        sqlUps += "    STEP_VALUE       ='" + unit  + "', ";
        sqlUps += "    MAX_VALUE  ='" + maxValue + "'";
        sqlUps += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sqlUps += "AND   TBL.KEY2='" + keys[1] + "' ";
        sqlUps += "AND   TBL.KEY3='" + keys[2] + "' ";
        stmt = dbConn.prepareStatement(sqlUps);
        stmt.executeUpdate();

        retSeqNum = value;

        stmt.close();
        rset.close();
        return retSeqNum;
    }
    public static final long updateSerialNumber(String[] keys, Connection dbConn) throws Exception {
    	long a = System.currentTimeMillis();
        long retSeqNum = -1;

        if(keys == null || keys.length != 3){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        String sqlSel = "SELECT CURRENT_VALUE, STEP_VALUE, MAX_VALUE FROM SERIAL_NUMBER TBL ";
        sqlSel += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sqlSel += "AND   TBL.KEY2='" + keys[1] + "' ";
        sqlSel += "AND   TBL.KEY3='" + keys[2] + "' ";
        sqlSel += "FOR UPDATE";
        PreparedStatement stmt = dbConn.prepareStatement(sqlSel);
        stmt.executeQuery();
        stmt.close();
        long b = System.currentTimeMillis();
        System.out.println("TupdateSerialNumber     ->>:SELECT FOR UPDATE 耗时：" + (b - a));

        sqlSel = "SELECT CURRENT_VALUE, STEP_VALUE, MAX_VALUE FROM SERIAL_NUMBER TBL ";
        sqlSel += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sqlSel += "AND   TBL.KEY2='" + keys[1] + "' ";
        sqlSel += "AND   TBL.KEY3='" + keys[2] + "' ";
        stmt = dbConn.prepareStatement(sqlSel);
        ResultSet rset = stmt.executeQuery();

        if (!rset.next()) {
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_ZERO_RECORD + "key1:" + keys[0] + ";key2:" + keys[1] + ";key3:" + keys[2]);
        }

        long setValue = 0;
        long v = Long.parseLong(rset.getString("CURRENT_VALUE"));
        long u = Long.parseLong(rset.getString("STEP_VALUE"));
        long m = Long.parseLong(rset.getString("MAX_VALUE"));
        setValue = v + u;
        if( u > 0 && (setValue > m || setValue > MAX_VALUE )) {
            setValue = 0;  // when counting up, recyle to zero
        } else if(u <= 0 && setValue < MIN_VALUE){
            setValue = m; // when counting down, recyle to max_value
        }
        stmt.close();
        long c = System.currentTimeMillis();
        System.out.println("TupdateSerialNumber     ->>:SELECT 耗时：" + (c - b));

        String sqlUps = "UPDATE SERIAL_NUMBER TBL ";
        sqlUps += "SET CURRENT_VALUE ='" + setValue + "'";
        sqlUps += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sqlUps += "AND   TBL.KEY2='" + keys[1] + "' ";
        sqlUps += "AND   TBL.KEY3='" + keys[2] + "' ";
        stmt = dbConn.prepareStatement(sqlUps);
        stmt.executeUpdate();

        retSeqNum = setValue;

        stmt.close();
        rset.close();
        long d = System.currentTimeMillis();
        System.out.println("TupdateSerialNumber     ->>:UPDATE 耗时：" + (d - c));

        System.out.println("TupdateSerialNumber     ->>:sn=" + retSeqNum + "耗时：" + (d - a));
        return retSeqNum;
    }

    /**
     *
     * @param value
     * @param unit
     * @param maxValue
     * @return
     */
    private static boolean isAcceptable(long value, long step,long maxValue) {
        if(value > MAX_VALUE || maxValue > MAX_VALUE
            || value < MIN_VALUE || maxValue <= MIN_VALUE
            || maxValue < value
            || step > MAX_VALUE || step < (-1) * MAX_VALUE){
            return false;
        }
        return true;
    }

    public static final boolean hasRecords(String[] keys) throws Exception {
        // 必须是3个key
        if(keys == null || keys.length != 3) {
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_INVALID_KEYS);
        }

        Connection dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
        if(dbConn == null){
            throw new JSerialNumberException(JSerialNumber.class.getName() + MSG_NULL_CONN);
        }

        // 查询
        String sql = "SELECT CURRENT_VALUE FROM SERIAL_NUMBER TBL ";
        sql += "WHERE TBL.KEY1='" + keys[0] + "' ";
        sql += "AND   TBL.KEY2='" + keys[1] + "' ";
        sql += "AND   TBL.KEY3='" + keys[2] + "' ";
        sql += "ORDER BY TBL.KEY1, TBL.KEY2, TBL.KEY3";
        PreparedStatement stmt = dbConn.prepareStatement(sql);
        ResultSet rset = stmt.executeQuery();

        boolean ret = false;
        if (rset.next()) {
            ret = true;
        }
        stmt.close();
        rset.close();

        dbConn.close();
        return ret;
    }
}
