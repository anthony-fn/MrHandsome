package com.anthony.playstation.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.anthony.playstation.exceptions.JobBatchException;


public class LocalExecutor
{
	private static ExecutorService m_executor = null;
	private static int MAX_WAIT_TIME = 50000;
	private static int m_threadNum = 20;

	private synchronized static void init()
	{
		if( m_executor == null )
		{
			m_executor = Executors.newFixedThreadPool(m_threadNum); 
		}
	}
	
	public void dispose()
	{
		m_executor.shutdown();
	}
	
	public LocalExecutor()
	{
		LocalExecutor.init();
	}
	
	public LocalExecutor( int number, int wait )
	{
		m_threadNum = number;
		LocalExecutor.init();
		MAX_WAIT_TIME = wait;
	}
	
	public void submit( AJobBatch jobBatch ) throws JobBatchException 
	{
		int count = 0;
		while( (count++) < jobBatch.getJobNum()  )
		{
			m_executor.submit(jobBatch.popOneJob());
		}
	}
	
	public int getResult( Future<Integer> resultFuture )
	{
		int result = -1;
		try
		{
			result = resultFuture.get(MAX_WAIT_TIME, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
	}
}
