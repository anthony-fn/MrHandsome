package com.anthony.playstation.dataAPI;

import com.anthony.playstation.data.MappingInfo;

public interface DataOutput
{
	public int saveData( MappingInfo mapping, byte [] content );
}
