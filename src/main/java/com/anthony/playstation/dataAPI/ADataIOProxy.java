package com.anthony.playstation.dataAPI;

import com.anthony.playstation.UniformType;
import com.anthony.playstation.data.DataSeries;
import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.exceptions.DataIOException;

public abstract class ADataIOProxy {

	public abstract byte[] loadData( MappingInfo mapping ) throws DataIOException;
	public abstract int saveData( MappingInfo mapping, byte [] content) throws DataIOException;
	public abstract int saveUniformedData( DataSeries series ) throws DataIOException;
	public abstract DataSeries loadUniformdData(String objID, UniformType type ) throws DataIOException;
}
