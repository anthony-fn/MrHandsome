package com.anthony.playstation.dataAdapter;

import java.util.List;

import com.anthony.playstation.data.DataSeries;
import com.anthony.playstation.exceptions.DataAdapterException;

public abstract class ADataAdapter
{
	public abstract List<DataSeries> parseData( byte[] content ) throws DataAdapterException;
}
