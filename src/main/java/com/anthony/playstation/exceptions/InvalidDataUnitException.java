package com.anthony.playstation.exceptions;

public class InvalidDataUnitException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4351069035709152782L;

	public InvalidDataUnitException( String message )
	{
		super(message);
	}
	public InvalidDataUnitException( String message, Exception e )
	{
		super(message, e);
	}
}
