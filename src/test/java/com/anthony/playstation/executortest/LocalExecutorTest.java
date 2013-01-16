package com.anthony.playstation.executortest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.exceptions.JobOperationException;
import com.anthony.playstation.executor.AJob;
import com.anthony.playstation.executor.JobStatus;
import com.anthony.playstation.executor.LocalExecutor;


public class LocalExecutorTest {

	private static LocalExecutor m_executor = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		m_executor = new LocalExecutor(2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testJobStatus() {
		AJob job1 = new TestJob(1,2,"add",5, 3);
		AJob job2 = new TestJob(1,2,"min",5, -1);
		AJob job3 = new TestJob(1,2,"multi",5, 2);
		AJob job4 = new TestJob(1,2,"add",5, 2);
		AJob job5 = new TestJob(1,2,"add2",5, 12);
		
		assertEquals( job1.getStatus(), JobStatus.Inited);
		assertEquals( job2.getStatus(), JobStatus.Inited);
		assertEquals( job3.getStatus(), JobStatus.Inited);
		assertEquals( job4.getStatus(), JobStatus.Inited);
		assertEquals( job5.getStatus(), JobStatus.Inited);
		
		try {
			m_executor.submit(job1);
			m_executor.submit(job2);
			m_executor.submit(job3);
			m_executor.submit(job4);
			m_executor.submit(job5);
		} catch (JobOperationException e) {
			fail(e.getMessage());
		}
		try {
			Thread.sleep(2*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(job1.getStatus(), JobStatus.Running);
		assertEquals(job2.getStatus(), JobStatus.Running);
		assertEquals(job3.getStatus(), JobStatus.Submitted);
		assertEquals(job4.getStatus(), JobStatus.Submitted);
		assertEquals(job5.getStatus(), JobStatus.Submitted);
		
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(job1.getStatus(), JobStatus.Succeed);
		assertEquals(job2.getStatus(), JobStatus.Succeed);
		assertEquals(job3.getStatus(), JobStatus.Succeed);
		assertEquals(job4.getStatus(), JobStatus.Succeed);
		assertEquals(job5.getStatus(), JobStatus.Failed);
		
		try {
			assertTrue(job1.handleResult(job1.getResult()));
			assertTrue(job2.handleResult(job2.getResult()));
			assertTrue(job3.handleResult(job3.getResult()));
			assertTrue(!job4.handleResult(job4.getResult()));
		} catch (JobOperationException e) {
			fail(e.getMessage());
		}

		try {
			assertTrue(job5.handleResult(job5.getResult()));
			fail("Job5 should fail!");
		} catch (JobOperationException e) {
		}
	}

	@Test
	public void testLocalExecutor() {
		assertNotNull(m_executor);
	}

}
