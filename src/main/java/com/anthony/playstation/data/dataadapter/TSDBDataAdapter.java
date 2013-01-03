package com.anthony.playstation.data.dataadapter;

import com.anthony.playstation.dataAPI.ADataIOProxy;

public class TSDBDataAdapter extends ADataAdapter
{
	private ADataIOProxy m_proxy = null;
	
	public TSDBDataAdapter( ADataIOProxy proxy )
	{
		m_proxy = proxy;
	}
}
