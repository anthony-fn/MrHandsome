package com.anthony.playstation.exceptions;

public class JobBatchException extends Throwable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JobBatchException( String message )
	{
		super(message);
	}

	public JobBatchException( String message, Exception e )
	{
		super(message, e);
	}
}
