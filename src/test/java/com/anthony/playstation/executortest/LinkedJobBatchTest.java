package com.anthony.playstation.executortest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.exceptions.JobBatchException;
import com.anthony.playstation.exceptions.JobOperationException;
import com.anthony.playstation.executor.AJob;
import com.anthony.playstation.executor.JobStatus;
import com.anthony.playstation.executor.LinkedJobBatch;
import com.anthony.playstation.executor.LocalExecutor;

public class LinkedJobBatchTest {
	
	private static LinkedJobBatch m_batch = null;
	private static LocalExecutor m_executor = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		m_batch = new LinkedJobBatch();
		m_executor = new LocalExecutor(1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		m_executor.dispose();
	}

	private void init()
	{
		m_batch.clearBatch();
		AJob job1 = new TestJob(1,2,"add",5, 3);
		AJob job2 = new TestJob(1,2,"add2",5, 12);
		
		try {
			m_batch.pushOneJob(job1);
			m_batch.pushOneJob(job2);
		} catch (JobBatchException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetJobNum() {
		this.init();
		assertEquals( m_batch.getJobNum(), 2);
	}

	@Test
	public void testGetRemaining() {
		this.init();
		assertEquals(m_batch.getRemaining(), 2);
		AJob job = null;
		
		try {
			job = m_batch.popOneJob();
			m_executor.submit(job);
		} catch (JobOperationException e) {
			fail(e.getMessage());
		} catch (JobBatchException e) {
			fail(e.getMessage());
		}
		
		while( !job.isFinished() )
		{
			try {
				Thread.sleep(1*1000);
			} catch (InterruptedException e) {
				fail();
			}
		}
		assertEquals(m_batch.getRemaining(), 1);
	}

	@Test
	public void testPopOneJob() {
		this.init();
		
		AJob job = null;
		try {
			job = m_batch.popOneJob();
		} catch (JobBatchException e) {
			fail(e.getMessage());
		}
		assertNotNull(job);
		assertEquals( job.getStatus(), JobStatus.Inited);
	}

	@Test
	public void testClearBatch()
	{
		this.init();
		assertEquals( m_batch.getRemaining(), 2 );
		m_batch.clearBatch();
		assertEquals( m_batch.getRemaining(), 0 );
	}
	
	@Test
	public void testIsFinished() {
		m_batch.clearBatch();
		AJob job1 = new TestJob(1,2,"add",5, 3);
		AJob job2 = new TestJob(1,2,"add2",5, 12);
		
		try {
			m_batch.pushOneJob(job1);
			m_batch.pushOneJob(job2);
		} catch (JobBatchException e) {
			fail(e.getMessage());
		}
		
		assertTrue(!m_batch.isFinished());
		
		try {
			m_executor.submit(m_batch);
		} catch (JobBatchException e) {
			fail(e.getMessage());
		}
		
		while( !job1.isFinished() )
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				fail(e.getMessage());
			}
		}
		
		while( !job2.isFinished() )
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				fail(e.getMessage());
			}
		}
		
		assertTrue(m_batch.isFinished());
	}

	@Test
	public void testPushOneJob() {
		this.init();
		
		assertEquals( 2, m_batch.getRemaining() );
		
		AJob job = new TestJob(0, 0, null, 0, 0);
		try {
			m_batch.pushOneJob(job);
		} catch (JobBatchException e) {
			fail(e.getMessage());
		}
		
		assertEquals(3, m_batch.getRemaining());
	}

	@Test
	public void testGetFailedJobs() {
		this.init();
		
		try {
			m_executor.submit(m_batch);
		} catch (JobBatchException e) {
			fail(e.getMessage());
		}
		
		while( !m_batch.isFinished() )
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				fail();
			}
		}
		
		List<AJob> failed = m_batch.getFailedJobs();
		
		assertNotNull(failed);
		assertEquals( 1, failed.size());
		
		AJob failedJob = failed.get(0);
		assertNotNull(failedJob);
		assertTrue(failedJob.getMessage().equals("Invalid operator"));
	}

}
