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
import com.anthony.playstation.exceptions.JobOperationException;

public class DataDumpJob extends AJob
{
	private ADataIOProxy m_source = null;
	private ADataIOProxy m_target = null;
	private Object m_mapping = null;
	private ADataAdapter m_adapterSrc = null;
	private ADataAdapter m_adapterTar = null;
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
	
	/*@Override
	public boolean isFailed()
	{
		if( !this.isFinished())
		{
			return false;
		}
		
		return m_failed;
	}*/
	
	public Object call() throws Exception
	{
		JobStatus status = JobStatus.Running;
		this.setStatus(status);
		try
		{
			List<DataSeries> data = m_source.loadData(((MappingInfo)m_mapping).getObjectId(), m_mapping, m_adapterSrc);
			
			if( data == null ||  data.size() == 0 )
			{
				status = JobStatus.Succeed;
				return status;
			}
			for( DataSeries series : data )
			{
				int result = m_target.saveData(m_adapterTar, series);
				if( result != 0 )
					status = JobStatus.Failed;
			}
			status = JobStatus.Succeed;
		} catch (DataIOException e)
		{
			status = JobStatus.Failed;
			String message = "Data dump failed : " + e.getMessage();
			this.setMessage(message);
			logger.error(e.getMessage());
			throw new Exception (new DataDumpException(message, new Exception(e) ) );
		}
		finally{
			this.setStatus(status);
		}
		return status;
	}

	@Override
	public boolean handleResult(Object result) throws JobOperationException {

		JobStatus status = (JobStatus)this.getResult();
			
		if( status == JobStatus.Succeed )
			return true;
		else if( status == JobStatus.Failed)
		{
			logger.error("The data dump job for "+ ((MappingInfo)m_mapping).getObjectId()+" "+((MappingInfo)m_mapping).getTsType()
						+" has failed with message " + this.getMessage() );
				
			return false;
		}
			
		return false;
	}

}
