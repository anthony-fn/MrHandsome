package com.anthony.playstation.executortest;

import com.anthony.playstation.exceptions.JobOperationException;
import com.anthony.playstation.executor.AJob;
import com.anthony.playstation.executor.JobStatus;

public class TestJob extends AJob{
	private int m_a = 0;
	private int m_b = 0;
	private String m_ope = "";
	private int m_sleep = 0;
	private int m_result = 0;
	
	public TestJob( int a, int b, String ope, int sleep, int result )
	{
		m_a = a;
		m_b = b;
		m_ope = ope;
		m_sleep = sleep;
		m_result = result;
		this.setStatus(JobStatus.Inited);
	}

	@Override
	public Object call() throws Exception {
		
		this.setStatus(JobStatus.Running);
		
		Thread.sleep(m_sleep*1000);
		
		if( m_ope.equals("add") )
		{
			this.setStatus(JobStatus.Succeed);
			return m_a+m_b;
		}
		else if ( m_ope.equals("min") )
		{
			this.setStatus(JobStatus.Succeed);
			return m_a-m_b;
		}
		
		else if ( m_ope.equals("multi"))
		{
			this.setStatus(JobStatus.Succeed);
			return m_a*m_b;
		}
		
		this.setStatus(JobStatus.Failed);
		this.setMessage("Invalid operator");
		throw new Exception("Invalid operator");
	}

	@Override
	public boolean handleResult(Object result) throws JobOperationException {
		if( !this.getMessage().isEmpty() )
		{
			System.out.println(m_message);
			throw new JobOperationException("");
		}
		if( m_result != (Integer)result )
			return false;
		
		return true;
	}
	
}