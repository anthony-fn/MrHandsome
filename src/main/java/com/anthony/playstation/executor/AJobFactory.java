package com.anthony.playstation.executor;

import com.anthony.playstation.exceptions.JobOperationException;

public abstract class AJobFactory
{
	public abstract void LoadFactory( Object obj );
	public abstract AJob getOneJob()throws JobOperationException;
}
