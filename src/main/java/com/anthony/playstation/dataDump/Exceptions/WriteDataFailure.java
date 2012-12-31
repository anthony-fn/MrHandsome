package com.anthony.playstation.dataDump.Exceptions;

public class WriteDataFailure extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WriteDataFailure()
	{
		super();
	}
	
	public WriteDataFailure( String message )
	{
		super(message);
	}
}
