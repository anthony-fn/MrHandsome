package com.anthony.playstation.exceptions;

public class ConfigurationException extends Throwable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfigurationException()
	{
		super();
	}
	public ConfigurationException( String message )
	{
		super(message);
	}
	
	public ConfigurationException( String message, Exception e) 
	{
		super(message, e);
	}

}
