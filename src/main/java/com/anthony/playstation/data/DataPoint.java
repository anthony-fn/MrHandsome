package com.anthony.playstation.data;

import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.exceptions.DataIOException;

public class DataPoint
{
	private String m_name = "DataPoint";
	private List<DataSeries> m_points = null;
	private ADataAdapter m_adapter = null;
	
	public DataPoint( String name, ADataAdapter adapter )
	{
		m_name = name;
		m_points = new LinkedList<DataSeries>();
		m_adapter = adapter;
	}
	
	public void loadData( ADataIOProxy proxy, MappingInfo mapping ) throws DataIOException
	{
		byte [] content = proxy.loadData(mapping);
		
		//m_adapter
	}
}
