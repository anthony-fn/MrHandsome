/**   
* @Title: 		TSDBProxyFactory.java
* @Package 		com.anthony.playstation.dataAPI
* @Description: 
* 				Class TSDBProxyFactory
* @author 		Anthony Fan
* @date 		2013-1-14 
* @time 		16:51:21
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAPI;

import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.morningstar.data.tsapi.TSException;
import com.morningstar.data.tsapi.tscontext;

/**
 * Class TSDBProxyFactory, the only valid entry to get an instance of TSDBProxy.
 * 
 * This class contains the tscontext for source and target environment of TSDB, defined in OldTSAPI

 */
public class TSDBProxyFactory extends ADataIOProxyFactory{

	/**
	 * Field m_lock.
	 */
	private Object m_lock = new Object();
	
	/**
	 * Field m_context_r.
	 * Value: {@value m_context_r}
	 */
	private  static tscontext m_context_r = null;
	//private  static tscontext m_context_w = null;
	
	/*
	 * private void initTSContext( tscontext context, String setting) throws DataProxyOperationException
	{
		synchronized(m_lock){
			if(context==null){
				try {
					context = new tscontext(setting);
				} catch (TSException e) {
					throw new DataProxyOperationException( "Can't init context for TSDBProxy !", e);
				} 
			}
		}
	}*/
	/**
	 * Constructor for TSDBProxyFactory.
	 * @param from String
	 * @param to String
	 * @throws DataProxyOperationException
	 */
	public TSDBProxyFactory( String from, String to ) throws DataProxyOperationException
	{
		synchronized(m_lock){
			if(m_context_r==null){
				try {
					m_context_r = new tscontext(from);
				} catch (TSException e) {
					throw new DataProxyOperationException( "Can't init context for TSDBProxy !", e);
				} 
			}
			
			/*if(m_context_w==null){
				try {
					m_context_w = new tscontext(to);
				} catch (TSException e) {
					throw new DataProxyOperationException( "Can't init context for TSDBProxy !", e);
				} 
			}*/
		}
		//this.initTSContext(m_context_r, from);
		//this.initTSContext(m_context_w, to);
	}
	
	/**
	 * Method getDataProxy.
	 * @return ADataIOProxy
	 * @throws DataProxyOperationException
	 */
	@Override
	public ADataIOProxy getDataProxy() throws DataProxyOperationException{
		
		if( m_context_r == null )
			throw new DataProxyOperationException("TSContext is not inited properly !", null);
		
		return new TSDBProxy(m_context_r, null);
	}

	/**
	 * Method closeFactory.
	 * @throws DataProxyOperationException
	 */
	@Override
	public void closeFactory() throws DataProxyOperationException {
		
		try {
			if( m_context_r != null)
				m_context_r.dispose();
			
		} catch (TSException e) {
			throw new DataProxyOperationException( "Proxy failed to be disposed !", e);
		}
		
	}

}
