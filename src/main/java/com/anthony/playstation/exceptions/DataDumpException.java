package com.anthony.playstation.exceptions;

public class DataDumpException extends Throwable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1877867804350133947L;
	
	public DataDumpException( String message )
	{
		super(message);
	}
	
	public DataDumpException( String message, Exception e )
	{
		super( message, e );
	}

}
