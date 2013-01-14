/**   
* @Title: 		ADataIOProxy.java 
* @Package 		com.anthony.playstation.dataAPI 
* @Description:  
* 				Contains the definition of ADataIOProxy
* @author 		Anthony Fan
* @date 		2013-1-12 
* @time 		22:32:23 
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAPI;

import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.exceptions.DataIOException;

/**
 * Abstract class ADataIOProxy.
 * 
 * This is the base class for all data read/write proxy classes.
 */
public abstract class ADataIOProxy {
	
	/**
	 * Method loadData.
	 * Get data from a data storage and parse it from the source data format into List<DataSeries>
	 * 
	 * @param objID String ObjectID of the data you want to read. 
	 * @param mapping Object The mapping information of the data. Could be different kind of class.
	 * @param adapter ADataAdapter	The data adapter which transfer data between different data formats.
	 * @return List<DataSeries>	Normally, this should be with size = 1
	 * @throws DataIOException
	 */
	public abstract List<DataSeries> loadData( String objID, Object mapping, ADataAdapter adapter) throws DataIOException;
	
	/**
	 * Method saveData.
	 * Save data from DataSeries into a defined data storage.
	 * @param adapter ADataAdapter	The data adapter which transfer data between different data formats.
	 * @param series DataSeries	Data source
	 * @return int	0 if success.
	 * @throws DataIOException
	 */
	public abstract int saveData( ADataAdapter adapter, DataSeries series ) throws DataIOException;
}
