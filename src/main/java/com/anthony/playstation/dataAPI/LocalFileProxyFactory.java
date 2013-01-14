/**   
* @Title: 		LocalFileProxyFactory.java 
* @Package 		com.anthony.playstation.dataAPI 
* @Description:  
* 				Contains the definition of class LocalFileProxyFactory
* @author 		Anthony Fan
* @date 		2013-1-12 
* @time 		22:51:01 
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAPI;

import java.io.File;

import com.anthony.playstation.exceptions.DataProxyOperationException;

/**
 * Class LocalFileProxyFactory, the only valid entry to get an instance of LocalFileProxy.
 * 
 * This class contains the prefix to data storage paths ( source and target ).
 * Source defines the directory prefix for reading data.
 * Target defines the directory prefix for writing data.
 */
public class LocalFileProxyFactory extends ADataIOProxyFactory{

	private String m_srcprefix = "";
	private String m_tarprefix = "";
	
	/**
	 * Method checkPrefix.
	 * Check the prefixes of reading/writing directories are valid. 
	 * If any of those directories does not exist, make it.
	 * 
	 * @throws DataProxyOperationException
	 */
	private void checkPrefix() throws DataProxyOperationException
	{
		if( m_srcprefix == null || m_srcprefix.isEmpty() )
			throw new DataProxyOperationException("Local file source proxy is not valid: "+m_srcprefix, null);
		if( m_tarprefix == null || m_tarprefix.isEmpty() )
			throw new DataProxyOperationException("Local file target proxy is not valid: "+m_tarprefix, null);
		
		this.mkdir(m_srcprefix);
		this.mkdir(m_tarprefix);
	}
	
	/**
	 * Method mkdir.
	 * Check if the directory "path" exist, and create it if not.
	 * @param path String
	 * @throws DataProxyOperationException
	 */
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
	
	/**
	 * Constructor for LocalFileProxyFactory.
	 * 
	 * @param sourcePrefix String	The directory to read data.
	 * @param targetPrefix String	The directory to write data.
	 * @throws DataProxyOperationException
	 */
	public LocalFileProxyFactory( String sourcePrefix, String targetPrefix ) throws DataProxyOperationException 
	{
		m_srcprefix = sourcePrefix;
		m_tarprefix = targetPrefix;
		checkPrefix();
	}
	
	/**
	 * Method getDataProxy.
	 * @return ADataIOProxy
	 * @throws DataProxyOperationException
	 */
	@Override
	public ADataIOProxy getDataProxy() throws DataProxyOperationException {
		
		LocalFileProxy result = null;
		try {
			result = (LocalFileProxy) Class.forName("com.anthony.playstation.dataAPI.LocalFileProxy").newInstance();
			result.setSourceDirectory(m_srcprefix);
			result.setTargetDirectory(m_tarprefix);
		} catch (InstantiationException e) {
			throw new DataProxyOperationException("Can't create new LocalFileProxy instance: "+e.getMessage(),
					e);
		} catch (IllegalAccessException e) {
			throw new DataProxyOperationException("Can't create new LocalFileProxy instance: "+e.getMessage(),
					e);
		} catch (ClassNotFoundException e) {
			throw new DataProxyOperationException("Can't create new LocalFileProxy instance: "+e.getMessage(),
					e);
		}
		
		return (ADataIOProxy)result;
	}

	/**
	 * Method closeFactory.
	 * @throws DataProxyOperationException
	 */
	@Override
	public void closeFactory() throws DataProxyOperationException {
		
	}

}
