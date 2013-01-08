package com.anthony.playstation.dataAPI;

import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.exceptions.DataIOException;

public abstract class ADataIOProxy {
	
	public abstract List<DataSeries> loadData( String objID, Object mapping, ADataAdapter adapter) throws DataIOException;
	public abstract int saveData( ADataAdapter adapter, DataSeries series ) throws DataIOException;
}
