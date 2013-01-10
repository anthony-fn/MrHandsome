package com.anthony.playstation.executor;

import java.util.concurrent.Callable;

public abstract class AJob implements Callable<Integer>
{
	private boolean m_finished = false;
	
	public void hasFinished()
	{
		m_finished = true;
	}	
	
	public boolean isFinished()
	{
		return m_finished;
	}
	
	public abstract boolean isFailed();
}
