package com.anthony.playstation.exceptions;

public class DataProxyOperationException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5172737915405074999L;

	public DataProxyOperationException( String message, Exception e )

	{
		super(message);
		this.initCause(e);
	}
}
