package com.anthony.playstation.executor;

import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.JobOperationException;

public class DataDumpJobFactory extends AJobFactory
{
	private static ADataIOProxyFactory m_source = null;
	private static ADataIOProxyFactory m_target = null;
	private MappingInfo m_mapping = null;
	private boolean m_loaded = false;
	
	public DataDumpJobFactory( ADataIOProxyFactory source, ADataIOProxyFactory target )
	{
		if( m_source == null)
			m_source = source;
		if( m_target == null )
			m_target = target; 
	}
	
	
	@Override
	public AJob getOneJob() throws JobOperationException
	{
		if( !m_loaded )
		{
			throw new JobOperationException("Factory is not loaded. No job could be created!");
		}
		DataDumpJob job = null;
		try
		{
			job = new DataDumpJob(m_source.getDataProxy(), m_target.getDataProxy(), m_mapping);
			m_loaded = false;
			
		} catch (DataProxyOperationException e)
		{
			throw new JobOperationException("Job creation exception. Error: "+e.getMessage(), 
					new Exception(e));
		}
		return job;
	}


	@Override
	public void LoadFactory(Object obj)
	{
		m_mapping = (MappingInfo)obj;	
		m_loaded = true;
	}
	
}
