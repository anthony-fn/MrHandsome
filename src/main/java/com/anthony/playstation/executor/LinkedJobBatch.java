package com.anthony.playstation.executor;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.anthony.playstation.exceptions.JobBatchException;

public class LinkedJobBatch extends AJobBatch{
	
	private BlockingQueue<AJob> m_jobs = new LinkedBlockingQueue<AJob>();
	private List<AJob> m_keptJobs = new LinkedList<AJob>();
	private Object m_lock = new Object();
	
	@Override
	public int getJobNum() {
		return m_keptJobs.size();
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
		return m_jobs.poll();
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pushOneJob(AJob job) throws JobBatchException {
		try {
			m_jobs.put(job);
			m_keptJobs.add(job);
		} catch (InterruptedException e) {
			throw new JobBatchException("Can't add another job with error "+e.getMessage(), e);
		}
	}

}
