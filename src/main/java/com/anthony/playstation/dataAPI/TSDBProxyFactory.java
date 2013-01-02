package com.anthony.playstation.dataAPI;

import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.morningstar.data.tsapi.TSException;
import com.morningstar.data.tsapi.tscontext;

public class TSDBProxyFactory extends ADataIOProxyFactory{

	private Object m_lock = new Object();
	
	private  static tscontext m_context_r = null;
	private  static tscontext m_context_w = null;
	
	private void initTSContext( tscontext context, String setting) throws DataProxyOperationException
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
	}
	public TSDBProxyFactory( String from, String to ) throws DataProxyOperationException
	{
		this.initTSContext(m_context_r, from);
		this.initTSContext(m_context_w, to);
	}
	
	@Override
	public ADataIOProxy getDataProxy() throws DataProxyOperationException{
		
		if( m_context_r == null || m_context_w == null )
			throw new DataProxyOperationException("TSContext is not inited properly !", null);
		
		return new TSDBProxy(m_context_r, m_context_w);
	}

	@Override
	public void closeFactory() throws DataProxyOperationException {
		
		try {
			m_context_r.dispose();
			m_context_w.dispose();
		} catch (TSException e) {
			throw new DataProxyOperationException( "Proxy failed to be disposed !", e);
		}
		
	}

}
