package com.anthony.playstation.dataDump.Exceptions;

public class LoadDataFailure extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoadDataFailure()
	{
		super();
	}
	
	public LoadDataFailure( String message )
	{
		super(message);
	}

}
