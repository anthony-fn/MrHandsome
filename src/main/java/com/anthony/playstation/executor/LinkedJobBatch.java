package com.anthony.playstation.executor;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.exceptions.JobBatchException;
import com.anthony.playstation.exceptions.JobOperationException;

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
			for( AJob job : m_keptJobs )
			{
				if( job.getStatus() == JobStatus.Submitted )
					result ++;
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
		if(this.getRemaining() == 0) 
		{
			for( AJob job : m_keptJobs )
			{
				JobStatus status = job.getStatus();
				if ( status == JobStatus.Running )
				{
					System.out.println( ((MappingInfo)(((DataDumpJob)job).getMapping())).toString());
					
				}
				if( status != JobStatus.Succeed && status != JobStatus.Failed )
					return false;
				
			}
			
			return true;
		}
		
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

	@Override
	public void getFailedJobs()
	{
		int failedNum = 0;
		for( AJob job : m_keptJobs )
		{
			JobStatus status = job.getStatus();
			
			if( status == JobStatus.Failed )
			{
				failedNum += 1;
				try
				{
					job.handleResult(job.getResult());
				} catch (JobOperationException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if( failedNum == 0 )
		{
			System.out.println("No job failed !");
		}
		
		else
		{
			System.out.println("Failed job number: "+failedNum);
		}
		
	}

}
