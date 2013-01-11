/**   
* @Title: 		ADataAdapter.java 
* @Package 		com.anthony.playstation.dataAdapter 
* @Description:  
* 				Contains the defination of abstract class ADataApater
* @author 		Anthony Fan
* @date 		2013-1-10 
* @time 		21:15:18 
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAdapter;

import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.exceptions.DataAdapterException;


/**
 * Abstract class ADataAdapter.
 */
public abstract class ADataAdapter
{
	
	/**
	 * Method serializeSeries.
	 * 
	 * Serialize an instance of DataSeries into byte [].
	 * @param series DataSeries
	 * @return byte[]
	 * @throws DataAdapterException
	 */
	public abstract byte[] serializeSeries(DataSeries series) throws DataAdapterException;
	
	/**
	 * Method loadSeries.
	 * 
	 * Deserialize the data into an instance of DataSeries from byte [].
	 * @param mapping Object
	 * @param content byte[]
	 * @return List<DataSeries>
	 * @throws DataAdapterException
	 */
	public abstract List<DataSeries> loadSeries(Object mapping, byte[] content) throws DataAdapterException;

}
