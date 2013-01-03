package com.anthony.playstation.exceptions;

public class JobOperationException extends Throwable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JobOperationException( String message )
	{
		super(message);
	}
	
	public JobOperationException( String message, Exception e )
	{
		super(message, e);
	}
}
