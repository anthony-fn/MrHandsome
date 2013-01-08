package com.anthony.playstation.dataAdapter;

import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.exceptions.DataAdapterException;


public abstract class ADataAdapter
{
	//public abstract List<DataSeries> parseData( byte[] content ) throws DataAdapterException;
	
	public abstract byte[] serializeSeries(DataSeries series) throws DataAdapterException;
	
	public abstract List<DataSeries> loadSeries(Object mapping, byte[] content) throws DataAdapterException;

}
