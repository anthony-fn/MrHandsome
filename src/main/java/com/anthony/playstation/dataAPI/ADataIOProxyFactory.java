/**   
* @Title: 		ADataIOProxyFactory.java 
* @Package 		com.anthony.playstation.dataAPI 
* @Description:  
* 				Contains the definition of ADataIOProxyFactory
* @author 		Anthony Fan
* @date 		2013-1-12 
* @time 		22:32:23 
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAPI;

import com.anthony.playstation.exceptions.DataProxyOperationException;

/**
 * The abstract class ADataIOProxyFactory.
 * 
 * The factory class to get a valid ADataIOProxy instance.
 */

public abstract class ADataIOProxyFactory {
	
	/**
	 * Method getDataProxy.
	 * @return ADataIOProxy
	 * @throws DataProxyOperationException
	 */
	public abstract ADataIOProxy getDataProxy() throws DataProxyOperationException;
	
	/**
	 * Method closeFactory.
	 * 
	 * Release all resources used by this factory class instance. 
	 * Should be called when the proxy factory instance is no longer in use.
	 * @throws DataProxyOperationException
	 */
	public abstract void closeFactory() throws DataProxyOperationException;

}
