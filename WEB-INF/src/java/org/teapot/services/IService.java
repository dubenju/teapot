package org.teapot.services;

import java.util.Properties;

public interface IService {
	public boolean isRegistered( String serviceName );
	public void initService( String name, Object data ) throws InitializationException;
	public void initServices( Object data );
	public void shutdownService( String name );
	public void shutdownServices( );
	public IService getService( String name ) throws InstantiationException;
	public Properties getProperties( String name );
	//public Configuration getConfiguration( String name );
}
