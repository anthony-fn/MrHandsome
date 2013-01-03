package com.anthony.playstation.executor;

import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.exceptions.DataDumpException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;

public class DataDumpJob extends AJob
{
	private ADataIOProxy m_source = null;
	private ADataIOProxy m_target = null;
	private MappingInfo m_mapping = null;
	private boolean m_finished = false;
	
	public DataDumpJob( ADataIOProxy source, ADataIOProxy target, MappingInfo mapping ) throws DataProxyOperationException
	{
		m_source = source;
		m_target = target;		
		m_mapping = mapping;
	}
	public int execute()
	{
		return 0;
	}
	
	@Override
	public boolean isFinished()
	{
		return m_finished;
	}
	
	public Integer call() throws Exception
	{
		int result = 0;
		try
		{
			byte [] content = m_source.loadData(m_mapping);
			result = m_target.saveData(m_mapping, content);
			
			if( result != 0 )
			{
				throw new Exception(new DataDumpException("Save data with return "+result));
			}
		} catch (DataIOException e)
		{
			throw new Exception (new DataDumpException("Dump data failed !", new Exception(e) ) );
		}
		m_finished = true;
		return result;
	}
}
