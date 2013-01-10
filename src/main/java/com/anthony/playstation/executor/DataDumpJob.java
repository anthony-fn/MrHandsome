package com.anthony.playstation.executor;

import java.util.List;

import org.apache.log4j.Logger;

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
	private boolean m_failed = false;
	private static final Logger logger = Logger.getLogger(DataDumpJob.class);
	
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
	
	public MappingInfo getMapping()
	{
		return (MappingInfo)m_mapping;
	}
	
	@Override
	public boolean isFailed()
	{
		if( !this.isFinished())
		{
			return false;
		}
		
		return m_failed;
	}
	
	public Integer call() throws Exception
	{
		int result = 0;
		try
		{
			//if( !(m_mapping instanceof com.anthony.playstation.data.mapping.MappingInfo) )
			//	throw new Exception( new DataDumpException("Invalid data source. Right now we only have TSDB as data source"));
			List<DataSeries> data = m_source.loadData(((MappingInfo)m_mapping).getObjectId(), m_mapping, m_adapterSrc);
			
			for( DataSeries series : data )
			{
				result = m_target.saveData(m_adapterTar, series);
			}
			
			if( result != 0 )
			{
				m_failed = true;
				throw new Exception(new DataDumpException("Save data with return "+result));
			}
		} catch (DataIOException e)
		{
			m_failed = true;
			logger.error(e.getMessage());
			throw new Exception (new DataDumpException("Dump data failed !", new Exception(e) ) );
		}
		finally{
			this.hasFinished();
		}
		return result;
	}

}
