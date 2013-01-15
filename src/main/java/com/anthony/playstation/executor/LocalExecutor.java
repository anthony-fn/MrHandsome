/**   
* @Title: 		LocalExecutor.java 
* @Package 		com.anthony.playstation.executor 
* @Description:  
* 				Contains the definition of LocalExecutor.
* @author 		Anthony Fan
* @date 		2013-1-13 
* @time 		17:39:24 
* @version 		V 1.0   
*/
package com.anthony.playstation.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.anthony.playstation.exceptions.JobBatchException;
import com.anthony.playstation.exceptions.JobOperationException;


/**
 * Class LocalExecutor contains an ExecutorService instance. 
 * This instance is a FiexedThreadPool, whose number could be set via constructor.
 * @author Anthony
 * @version $Revision: 1.0 $
 */
public class LocalExecutor
{
	private static ExecutorService m_executor = null;
	private static int m_threadNum = 20;

	private synchronized static void init()
	{
		if( m_executor == null )
		{
			m_executor = Executors.newFixedThreadPool(m_threadNum);
		}
	}
	
	/**
	* Method dispose.
	* Close the ExecutorService, should be called while all jobs are finished.
	*/
	public void dispose()
	{
		m_executor.shutdown();
	}
	
	/**
	 * Class constructor.
	 * Initialize the instance with default pool size and wait time.
	 */
	public LocalExecutor()
	{
		LocalExecutor.init();
	}
	
	/**
	 * Constructor for LocalExecutor.
	 * @param number int	The thread number within the thread pool.
	 * @param wait int		The max wait time for each job.
	 */
	public LocalExecutor( int number)
	{
		m_threadNum = number;
		LocalExecutor.init();
	}
	
	public void submit( AJob job) throws JobOperationException
	{
		if( job == null )
			throw new JobOperationException("Trying to submit a null job into executor!");
		
		job.setStatus(JobStatus.Submitted);
		job.setResult(m_executor.submit(job));
	}
	
	/**
	 * Method submit.
	 * Add one instance of AJobBatch into the thread pool and start to execute.
	 * @param jobBatch AJobBatch
	
	 * @throws JobBatchException */
	public void submit( AJobBatch jobBatch ) throws JobBatchException 
	{
		int count = 0;
		while( (count++) < jobBatch.getJobNum()  )
		{
			AJob job = jobBatch.popOneJob();
			if( job != null )
			{
				job.setStatus(JobStatus.Submitted);
				job.setResult(m_executor.submit(job));
			}
		}
	}
}
