package com.anthony.playstation.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.anthony.playstation.exceptions.JobBatchException;

public class FixedJobBatch extends AJobBatch
{
	
	private List<AJob> m_jobs = null;
	private List<Future<Object>> m_results = null;
	private int m_maxJobNum = 0;
	private int m_currentJobNum = 0;
	private int m_popPos = 0;
	
	public FixedJobBatch( int num ) throws JobBatchException
	{
		if( num > AJobBatch.MAX_JOB_NUMBER )
			throw new JobBatchException("We have a job number limitation in batch "+ AJobBatch.MAX_JOB_NUMBER+" !");
		
		m_maxJobNum = num;
		m_jobs = new ArrayList<AJob>(num);
		m_results = new ArrayList<Future<Object>>(num);
	}
	
	@Override
	public void pushOneJob( AJob job ) throws JobBatchException
	{
		if( m_currentJobNum >= m_maxJobNum )
			throw new JobBatchException("Current job batch has already met the limitation "+ m_maxJobNum);
		else if ( job == null )
		{
			throw new JobBatchException("Adding a null job into the batch!");
		}
		m_jobs.add(m_currentJobNum++, job);

	}
	
	
	@Override
	public AJob popOneJob() throws JobBatchException
	{
		if ( (m_currentJobNum - m_popPos)<0 )
			throw new JobBatchException("No jobs waiting !");
		
		return  m_jobs.get(m_popPos++);
	}
	
	public void SetResult( Future<Object> result, int pos )
	{
		m_results.add(pos, result);
	}
	
	@Override
	public int getJobNum()
	{
		return m_currentJobNum;
	}

	@Override
	public int getRemaining()
	{
		int result = m_currentJobNum;
		synchronized(this){
			for( AJob job : m_jobs)
			{
				if( job.isFinished() )
					result --;
			}
		}
		
		return result;
	}
	
	/*public int checkForFailed()
	{
		int count = 0;
		for( AJob job : m_jobs )
		{
			if( job.isFailed() )
			{
				count ++;
				System.out.println(((DataDumpJob)job).getMapping().getObjectId());
			}
		}
		
		return count;
	}*/

	@Override
	public boolean isFinished()
	{
		synchronized(this){
			for( AJob job : m_jobs)
			{
				if( !job.isFinished() )
					return false;
			}
		}
		return true;
	}

	@Override
	public List<AJob> getFailedJobs()
	{
		return null;
	}

	@Override
	public void clearBatch() {
		// TODO Auto-generated method stub
		
	}
}
