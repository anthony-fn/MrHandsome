package com.anthony.playstation.dataAPI;

import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.exceptions.DataIOException;

public abstract class ADataIOProxy {

	public abstract byte[] loadData( MappingInfo mapping ) throws DataIOException;
	public abstract int saveData( MappingInfo mapping, byte [] content) throws DataIOException;
}
