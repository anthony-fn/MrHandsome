package com.anthony.playstation.exceptions;

public class InvalidOperationException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5363597664632644029L;
	
	public InvalidOperationException()
	{
		super();
	}
	
	public InvalidOperationException( String message )
	{
		super(message);
	}
	
	public InvalidOperationException( String message, Exception e )
	{
		super(message, e);
	}

}
