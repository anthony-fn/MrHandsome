package com.anthony.playstation.exceptions;

public class CalculationException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5094582882964733913L;
	
	public CalculationException()
	{
		super();
	}
	
	public CalculationException( String message )
	{
		super(message);
	}
	
	public CalculationException( String message, Exception e )
	{
		super( message, e );
	}

}
