package com.jdroid.javaweb.datasource;

import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ComboPooledDataSource implements DataSource {
	
	private com.mchange.v2.c3p0.ComboPooledDataSource wrappedDataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
	
	public synchronized void close() throws SQLException {
		wrappedDataSource.close();
		DriverManager.deregisterDriver(DriverManager.getDriver(wrappedDataSource.getJdbcUrl()));
	}
	
	/**
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return wrappedDataSource.getLogWriter();
	}
	
	/**
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if (iface == null) {
			throw new IllegalArgumentException("Interface argument must not be null");
		}
		if (!DataSource.class.equals(iface)) {
			throw new SQLException("DataSource of type [" + getClass().getName()
					+ "] can only be unwrapped as [javax.sql.DataSource], not as [" + iface.getName());
		}
		return (T)this;
	}
	
	/**
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return DataSource.class.equals(iface);
	}
	
	/**
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		wrappedDataSource.setLogWriter(out);
	}
	
	/**
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		wrappedDataSource.setLoginTimeout(seconds);
	}
	
	/**
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return wrappedDataSource.getConnection();
	}
	
	/**
	 * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return wrappedDataSource.getConnection(username, password);
	}
	
	/**
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		return wrappedDataSource.getLoginTimeout();
	}
	
	/**
	 * @param driverClass
	 * @throws PropertyVetoException
	 */
	public void setDriverClass(String driverClass) throws PropertyVetoException {
		wrappedDataSource.setDriverClass(driverClass);
	}
	
	/**
	 * @param jdbcUrl
	 * @see com.mchange.v2.c3p0.ComboPooledDataSource#setJdbcUrl(java.lang.String)
	 */
	public void setJdbcUrl(String jdbcUrl) {
		wrappedDataSource.setJdbcUrl(jdbcUrl);
	}
	
	/**
	 * @param password
	 * @see com.mchange.v2.c3p0.ComboPooledDataSource#setPassword(java.lang.String)
	 */
	public void setPassword(String password) {
		wrappedDataSource.setPassword(password);
	}
	
	/**
	 * @param initialPoolSize
	 * @see com.mchange.v2.c3p0.ComboPooledDataSource#setInitialPoolSize(int)
	 */
	public void setInitialPoolSize(int initialPoolSize) {
		wrappedDataSource.setInitialPoolSize(initialPoolSize);
	}
	
	/**
	 * @param maxPoolSize
	 * @see com.mchange.v2.c3p0.ComboPooledDataSource#setMaxPoolSize(int)
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		wrappedDataSource.setMaxPoolSize(maxPoolSize);
	}
	
	/**
	 * @param minPoolSize
	 * @see com.mchange.v2.c3p0.ComboPooledDataSource#setMinPoolSize(int)
	 */
	public void setMinPoolSize(int minPoolSize) {
		wrappedDataSource.setMinPoolSize(minPoolSize);
	}
	
	/**
	 * @param user
	 * @see com.mchange.v2.c3p0.ComboPooledDataSource#setUser(java.lang.String)
	 */
	public void setUser(String user) {
		wrappedDataSource.setUser(user);
	}
}
