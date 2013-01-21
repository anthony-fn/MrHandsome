package com.anthony.playstation.exceptions;

public class InvalidExpressionException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7730202562181366180L;
	
	public InvalidExpressionException()
	{
		super();
	}
	
	public InvalidExpressionException( String message )
	{
		super(message);
	}
	
	public InvalidExpressionException( String message, Exception e )
	{
		super(message, e);
	}

}
