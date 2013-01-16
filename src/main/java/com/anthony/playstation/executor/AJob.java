package com.anthony.playstation.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.anthony.playstation.exceptions.JobOperationException;

public abstract class AJob implements Callable<Object>
{
	private JobStatus m_status = JobStatus.Default;
	protected Future<Object> m_result = null;
	protected String m_message = "";
	
	public void setMessage( String message )
	{
		m_message = message;
	}
	
	public String getMessage( )
	{
		return m_message;
	}
	
	public void setResult( Future<Object> result )
	{
		m_result = result;
	}
	
	public Object getResult() throws JobOperationException
	{
		Object result = null;
		try {
			result = m_result.get();
		} catch (InterruptedException e) {
			throw new JobOperationException("Get job result failed "+e.getMessage(), e);
		} catch (ExecutionException e) {
			throw new JobOperationException("Get job result failed "+e.getMessage(), e);
		}
		
		return result;
	}
	
	public boolean isFinished()
	{
		if( this.m_status == JobStatus.Failed || this.m_status == JobStatus.Succeed )
			return true;
		
		return false;
	}
	
	public void setStatus( JobStatus status )
	{
		m_status = status;
	}
	
	public JobStatus getStatus()
	{
		return m_status;
	}
	
	public abstract boolean handleResult( Object result) throws JobOperationException;
}
