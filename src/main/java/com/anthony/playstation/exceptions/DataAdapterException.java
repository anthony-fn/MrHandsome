package com.anthony.playstation.exceptions;

public class DataAdapterException extends Throwable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataAdapterException( String message )
	{
		super(message);
	}
	
	public DataAdapterException( String message, Exception e )
	{
		super(message, e);
	}

}
