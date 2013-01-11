package com.anthony.playstation.executor;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.anthony.playstation.exceptions.JobBatchException;

public class LinkedJobBatch extends AJobBatch{
	
	private BlockingQueue<AJob> m_jobs = new LinkedBlockingQueue<AJob>();
	private int m_number = 0;
	private Object m_lock = new Object();

	public LinkedJobBatch( int max )
	{
		m_number = max;
	}
	@Override
	public int getJobNum() {
		return m_jobs.size();
	}

	@Override
	public int getRemaining() {
		
		int result = 0;
		synchronized(m_lock)
		{
			for( AJob job : m_jobs )
			{
				if( job.isFinished())
					continue;
				else
					result++;
			}
		}
		
		return result;
	}

	@Override
	public AJob popOneJob() throws JobBatchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pushOneJob(AJob job) throws JobBatchException {
		// 
		
	}

}
