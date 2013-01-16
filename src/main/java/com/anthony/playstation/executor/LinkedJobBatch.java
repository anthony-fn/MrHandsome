/**   
* @Title: 		LinkedJobBatch.java 
* @Package 		com.anthony.playstation.executor 
* @Description:  
* 				The definition of LinkedJobBatch
* @author 		Anthony Fan
* @date 		2013-1-15 
* @time 		23:44:47 
* @version 		V 1.0   
*/
package com.anthony.playstation.executor;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.anthony.playstation.exceptions.JobBatchException;

/**
 * 
 * LinkedJobBatch is a extendable list that could hold jobs and submit to executor as a whole.
 */
public class LinkedJobBatch extends AJobBatch{
	
	private BlockingQueue<AJob> m_jobs = new LinkedBlockingQueue<AJob>();
	private List<AJob> m_keptJobs = new LinkedList<AJob>();
	private Object m_lock = new Object();
	
	
	/**
	 * Method getJobNum.
	 * Job number that has been submitted to this batch.
	 * @return int
	 */
	@Override
	public int getJobNum() {
		return m_keptJobs.size();
	}

	/**
	 * Method getRemaining.
	 * The the number of jobs that has been submitted to this batch but has not started yet.
	 * @return int
	 */
	@Override
	public int getRemaining() {
		
		int result = 0;
		synchronized(m_lock)
		{
			for( AJob job : m_keptJobs )
			{
				if( job.getStatus() != JobStatus.Succeed && job.getStatus() != JobStatus.Failed )
					result ++;
			}
		}
		
		return result;
	}

	/**
	 * Method popOneJob.
	 * Get a waiting job in the queue.
	 * @return AJob
	 * @throws JobBatchException
	 */
	@Override
	public AJob popOneJob() throws JobBatchException {
		return m_jobs.poll();
	}

	/**
	 * Method isFinished.
	 * Check if all jobs in the queue has finished.
	 * @return boolean
	 */
	@Override
	public boolean isFinished() {
		
		synchronized(m_lock)
		{
			for( AJob job : m_keptJobs )
			{
				JobStatus status = job.getStatus();
				
				if( status != JobStatus.Succeed && status != JobStatus.Failed )
					return false;
			}
			
			return true;
		}
	}

	/**
	 * Method pushOneJob.
	 * Push one job into the end of the queue.
	 * @param job AJob
	 * @throws JobBatchException
	 */
	@Override
	public void pushOneJob(AJob job) throws JobBatchException {
		try {
			m_jobs.put(job);
			m_keptJobs.add(job);
		} catch (InterruptedException e) {
			throw new JobBatchException("Can't add another job with error "+e.getMessage(), e);
		}
	}

	/**
	 * Method getFailedJobs.
	 * Get a list of all the failed jobs in this batch.
	 * @return LinkedList<AJob>
	 */
	@Override
	public List<AJob> getFailedJobs()
	{
		if( !this.isFinished() )
			return null;
		
		List<AJob> result = new LinkedList<AJob>();
		
		for( AJob job : m_keptJobs )
		{	
			if( job.getStatus() == JobStatus.Failed )
			{
				result.add(job);
			}
		}
		return result;
	}

	/**
	 * Method clearBatch.
	 * Remove all jobs from the batch.
	 */
	@Override
	public void clearBatch() {
		m_jobs.clear();
		m_keptJobs.clear();
	}

}
