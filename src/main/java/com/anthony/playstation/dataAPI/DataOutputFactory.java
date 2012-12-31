package com.anthony.playstation.dataAPI;

public abstract class DataOutputFactory
{
	public static DataOutput outputToLocal()
	{
		return new DataOutputToLocalFile();
	}
}
