package com.anthony.playstation.dataAPI;

import java.io.File;

import com.anthony.playstation.exceptions.DataProxyOperationException;

public class LocalFileProxyFactory extends ADataIOProxyFactory{

	private String m_srcprefix = "";
	private String m_tarprefix = "";
	
	private void checkPrefix() throws DataProxyOperationException
	{
		if( m_srcprefix == null || m_srcprefix.isEmpty() )
			throw new DataProxyOperationException("Local file source proxy is not valid: "+m_srcprefix, null);
		if( m_tarprefix == null || m_tarprefix.isEmpty() )
			throw new DataProxyOperationException("Local file target proxy is not valid: "+m_tarprefix, null);
		
		this.mkdir(m_srcprefix);
		this.mkdir(m_tarprefix);
	}
	
	private void mkdir( String path ) throws DataProxyOperationException
	{
		File file = new File(path);
		
		if( !file.exists() && !file.mkdir())
		{
			throw new DataProxyOperationException("Can't creat local file repositry: "+path, null);
		}
		else if( !file.isDirectory() )
		{
			throw new DataProxyOperationException(path +" has already been taken, please change a prefix !", null);
		}
	}
	
	public LocalFileProxyFactory( String sourcePrefix, String targetPrefix ) throws DataProxyOperationException 
	{
		m_srcprefix = sourcePrefix;
		m_tarprefix = targetPrefix;
		checkPrefix();
	}
	@Override
	public ADataIOProxy getDataProxy() throws DataProxyOperationException {
		return new LocalFileProxy( m_srcprefix, m_tarprefix);
	}

	@Override
	public void closeFactory() throws DataProxyOperationException {
		
	}

}
