package org.teapot.log4j.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

public class DbjDriver implements Driver {

	private static ArrayList<Driver> drivers = new ArrayList<Driver>();
	private Driver rDriver = null;
	static {
		try {
			DriverManager.registerDriver(new DbjDriver());
		} catch (SQLException e) {
			throw (RuntimeException) new RuntimeException("could not register dbj driver!").initCause(e);
		}

		Driver driver = null;
		Enumeration<Driver> enumeration = DriverManager.getDrivers();
		while (enumeration.hasMoreElements()) {
			driver = enumeration.nextElement();
			if (!(driver instanceof DbjDriver)) {
				drivers.add(driver);
			}
		}
	}

	private Driver searchDriverByUrl(String url) throws SQLException {
//		Driver driver = null;
		System.out.println("searchDriverByUrl url=" + url);
//		Enumeration<Driver> enumeration = DriverManager.getDrivers();
//		while (enumeration.hasMoreElements()) {
//			driver = enumeration.nextElement();
//			System.out.println("before acceptsURL url=" + url);
//	        if (driver.acceptsURL(url)){
//	        	System.out.println("OK acceptsURL url=" + url);
//	        	return driver;
//	        }
//	        System.out.println("NG acceptsURL url=" + url);
//	    }
		System.out.println("before acceptsURL url=" + url);
		if (rDriver == null) {
		for(Driver driver : drivers) {
			if (driver.acceptsURL(url)){
				rDriver = driver;
				break;
			}
		}
		}
		return rDriver;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		System.out.println("DbjDriver.connect url=" + url);
		String jdbcurl = url;
		if (jdbcurl.indexOf("jdbc:") > 0) {
			jdbcurl = url.substring(4);
		}
		// get Driver
		Driver driver = searchDriverByUrl(jdbcurl);
		Connection connection = driver.connect(jdbcurl, info);
		Connection conn = connection;
		if (true) {
			conn = new DbjConnectionWrapper(connection);
		}
		System.out.println("DbjDriver.connect conn=" + conn);
		return conn;
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		System.out.println("DbjDriver.acceptsURL url=" + url);
		String jdbcurl = url;
		if (jdbcurl.indexOf("jdbc:") > 0) {
			jdbcurl = url.substring(4);
		}
		Driver driver = searchDriverByUrl(jdbcurl);
		if (driver != null) {
			System.out.println("DbjDriver.acceptsURL true");
			return true;
		}
		System.out.println("DbjDriver.acceptsURL false");
		return false;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		System.out.println("DbjDriver.getPropertyInfo");
		return null;
	}

	@Override
	public int getMajorVersion() {
		System.out.println("DbjDriver.getMajorVersion");
		return 0;
	}

	@Override
	public int getMinorVersion() {
		System.out.println("DbjDriver.getMinorVersion");
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		System.out.println("DbjDriver.jdbcCompliant");
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		System.out.println("DbjDriver.getParentLogger");
		return null;
	}

}
