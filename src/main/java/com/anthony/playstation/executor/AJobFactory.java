package com.anthony.playstation.executor;

import com.anthony.playstation.exceptions.JobOperationException;

public abstract class AJobFactory
{
	public abstract AJob getOneJob( Object obj )throws JobOperationException;
}
