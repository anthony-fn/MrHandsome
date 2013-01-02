package com.anthony.playstation.dataAPI;

import java.io.File;

import com.anthony.playstation.exceptions.DataProxyOperationException;

public class LocalFileProxyFactory extends ADataIOProxyFactory{

	private String m_prefix = "";
	
	private void checkPrefix() throws DataProxyOperationException
	{
		if( m_prefix == null || m_prefix.isEmpty() )
			throw new DataProxyOperationException("Local file proxy is not valid: "+m_prefix, null);
		
		File file = new File(m_prefix);
		
		if( !file.exists() && !file.mkdir())
		{
			throw new DataProxyOperationException("Can't creat local file repositry: "+m_prefix, null);
		}
		else if( !file.isDirectory() )
		{
			throw new DataProxyOperationException(m_prefix +" has already been taken, please change a prefix !", null);
		}
	}
	
	public LocalFileProxyFactory( String prefix ) throws DataProxyOperationException 
	{
		m_prefix = prefix;
		checkPrefix();
	}
	@Override
	public ADataIOProxy getDataProxy() throws DataProxyOperationException {
		return new LocalFileProxy( m_prefix);
	}

	@Override
	public void closeFactory() throws DataProxyOperationException {
		
	}

}
