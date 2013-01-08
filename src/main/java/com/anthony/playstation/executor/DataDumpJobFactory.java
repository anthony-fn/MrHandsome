package com.anthony.playstation.executor;

import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.JobOperationException;

public class DataDumpJobFactory extends AJobFactory
{
	private static ADataIOProxyFactory m_source = null;
	private static ADataIOProxyFactory m_target = null;
	private MappingInfo m_mapping = null;
	
	public DataDumpJobFactory( ADataIOProxyFactory source, ADataIOProxyFactory target )
	{
		if( m_source == null)
			m_source = source;
		if( m_target == null )
			m_target = target; 
	}
	
	
	@Override
	public AJob getOneJob( Object mapping ) throws JobOperationException
	{
		DataDumpJob job = null;
		m_mapping = (MappingInfo) mapping;
		try
		{
			job = new DataDumpJob(m_source.getDataProxy(), m_target.getDataProxy(), m_mapping);			
		} catch (DataProxyOperationException e)
		{
			throw new JobOperationException("Job creation exception. Error: "+e.getMessage(), 
					new Exception(e));
		}
		return job;
	}	
}
