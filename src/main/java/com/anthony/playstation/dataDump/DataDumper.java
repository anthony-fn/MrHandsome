package com.anthony.playstation.dataDump;

import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.exceptions.DataDumpException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;

public class DataDumper
{
	private static ADataIOProxy m_source = null;
	private static ADataIOProxy m_target = null;
	
	public DataDumper( ADataIOProxyFactory source, ADataIOProxyFactory target ) throws DataProxyOperationException
	{
		m_source = source.getDataProxy();
		m_target = target.getDataProxy();
	}
	
	public void dump( MappingInfo mapping ) throws DataDumpException
	{
		try
		{
			byte [] content = m_source.loadData(mapping);
			int result = m_target.saveData(mapping, content);
			
			if( result != 0 )
			{
				throw new DataDumpException("Save data with return "+result);
			}
		} catch (DataIOException e)
		{
			throw new DataDumpException("Dump data failed !", new Exception(e) );
		}
		
	}
}
