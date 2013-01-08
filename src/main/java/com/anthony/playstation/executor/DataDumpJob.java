package com.anthony.playstation.executor;

import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.dataAdapter.TSDB.TSDBDataAdapter;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
import com.anthony.playstation.exceptions.DataDumpException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;

public class DataDumpJob extends AJob
{
	private ADataIOProxy m_source = null;
	private ADataIOProxy m_target = null;
	private Object m_mapping = null;
	private ADataAdapter m_adapterSrc = null;
	private ADataAdapter m_adapterTar = null;
	
	public DataDumpJob( ADataIOProxy source, ADataIOProxy target, Object mapping) throws DataProxyOperationException
	{
		m_source = source;
		m_target = target;		
		m_mapping = mapping;
		m_adapterSrc = new TSDBDataAdapter();
		m_adapterTar = new ProtoBufAdapter();
	}
	
	public DataDumpJob( ADataIOProxy source, ADataIOProxy target, Object mapping, 
			ADataAdapter adapterSrc, ADataAdapter adapterTar ) throws DataProxyOperationException
	{
		m_source = source;
		m_target = target;		
		m_mapping = mapping;
		m_adapterSrc = adapterSrc;
		m_adapterTar = adapterTar;
	}
	
	public Integer call() throws Exception
	{
		int result = 0;
		try
		{
			if( !(m_mapping instanceof com.anthony.playstation.data.mapping.MappingInfo) )
				throw new Exception( new DataDumpException("Invalid data source. Right now we only have TSDB as data source"));
			List<DataSeries> data = m_source.loadData(((MappingInfo)m_mapping).getObjectId(), m_mapping, m_adapterSrc);
			
			for( DataSeries series : data )
			{
				result = m_target.saveData(m_adapterTar, series);
			}
			
			if( result != 0 )
			{
				throw new Exception(new DataDumpException("Save data with return "+result));
			}
		} catch (DataIOException e)
		{
			throw new Exception (new DataDumpException("Dump data failed !", new Exception(e) ) );
		}
		this.hasFinished();
		return result;
	}

}
