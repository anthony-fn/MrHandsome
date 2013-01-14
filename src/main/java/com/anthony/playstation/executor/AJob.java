package com.anthony.playstation.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.anthony.playstation.exceptions.JobOperationException;

public abstract class AJob implements Callable<Object>
{
	private boolean m_finished = false;
	private JobStatus m_status = JobStatus.Default;
	protected Future<Object> m_result = null;
	protected String m_message = "";
	
	public void hasFinished()
	{
		m_finished = true;
	}	
	
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
	
	public Future<Object> getResult()
	{
		return m_result;
	}
	
	public boolean isFinished()
	{
		return m_finished;
	}
	
	public void setStatus( JobStatus status )
	{
		m_status = status;
	}
	
	public JobStatus getStatus()
	{
		return m_status;
	}
	
	public abstract boolean isFailed();
	public abstract void handleResult( Object result) throws JobOperationException;
}
