/*
 * 
 */
package com.anthony.playstation.dataAPI;

import com.anthony.playstation.exceptions.DataProxyOperationException;

public abstract class ADataIOProxyFactory {
	public abstract ADataIOProxy getDataProxy() throws DataProxyOperationException;
	public abstract void closeFactory() throws DataProxyOperationException;

}
