package com.anthony.playstation.exceptions;

public class DataIOException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1315606605125187684L;
	
	public DataIOException( String message )
	{
		super(message);
	}
	
	public DataIOException( String message, Exception e)
	{
		super(message);
		this.initCause(e);
	}

}
