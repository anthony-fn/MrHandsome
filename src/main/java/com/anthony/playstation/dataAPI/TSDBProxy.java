/**   
* @Title: 		TSDBProxy.java
* @Package 		com.anthony.playstation.dataAPI
* @Description: 
* 				Definition for class TSDBProxy
* @author 		Anthony Fan
* @date 		2013-1-14 
* @time 		15:39:06
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAPI;

import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.exceptions.DataAdapterException;
import com.anthony.playstation.exceptions.DataIOException;
import com.morningstar.data.tsapi.BaseObject;
import com.morningstar.data.tsapi.CorporateActionAdjustment;
import com.morningstar.data.tsapi.TSException;
import com.morningstar.data.tsapi.tscontext;

/**
 * Class TSDBProxy, which is a derived class from ADataIOProxy, handles the function read data from TSDB.
 * The write function is not and should not be implemented here. 
 */
public class TSDBProxy extends ADataIOProxy{

	/**
	 * Field ts_r_obj.
	 */
	private BaseObject ts_r_obj = null;
	/**
	 * Field ts_w_obj.
	 */
	@SuppressWarnings("unused")
	private BaseObject ts_w_obj = null;
	/**
	 * Field ts_r_cor.
	 */
	private CorporateActionAdjustment ts_r_cor = null;
	/**
	 * Field ts_w_cor.
	 */
	@SuppressWarnings("unused")
	private CorporateActionAdjustment ts_w_cor = null;
	/**
	 * Constructor for TSDBProxy.
	 * @param source tscontext
	 * @param target tscontext
	 */
	public TSDBProxy( tscontext source, tscontext target)
	{
		ts_r_obj = new BaseObject(source);
		ts_r_cor = new CorporateActionAdjustment(source);
		
		ts_w_obj = new BaseObject(target);
		ts_w_cor = new CorporateActionAdjustment(target);
	}
	
	/**
	 * Method loadData.
	 * @param mapping MappingInfo
	 * @return byte[]
	 * @throws DataIOException
	 */
	private byte[] loadData(MappingInfo mapping) throws DataIOException {
		
		byte [] result = null;
		try{
			switch( mapping.getMapping() )
			{
				case MappingAggregates:
					
					break;
				case MappingBaseObject:
					result = ts_r_obj.load_ts_content(mapping.getTsType(), mapping.getObjectId());
					break;
				case MappingCorporateActionAdjustment:
					result = ts_r_cor.load_ts_content(mapping.getTsType(), mapping.getObjectId());
					break;
				default : 
					break;	
			}
		}
		catch(TSException e )
		{
			if( !e.getErrorCode().equals("TSAPI-010"))
				throw new DataIOException("Reading data from TSDB failed for "+mapping.toString(), e);
		}
		return result;
	}

	
	/*public int saveData(MappingInfo mapping, byte[] content) throws DataIOException{
		int result = 0;
		
		if( content == null || content.length == 0 )
			return result;
		
		try{
			switch( mapping.getMapping() )
			{
			case MappingBaseObject:
				result = ts_w_obj.save_ts_content(mapping.getTsType(), mapping.getObjectId(), content);
				break;
			case MappingCorporateActionAdjustment:
				result = ts_w_cor.save_ts_content(mapping.getTsType(), mapping.getObjectId(), content);
				break;
			default:
				break;
			}
		}
		catch( TSException e)
		{
			throw new DataIOException("Saving data to TSDB failed for "+mapping.toString(), e);
		}
		return result;
	}*/

	/**
	 * Method loadData.
	 * @param objID String
	 * @param mappingObject Object
	 * @param adapter ADataAdapter
	 * @return List<DataSeries>
	 * @throws DataIOException
	 */
	@Override
	public List<DataSeries> loadData(String objID, Object mappingObject, ADataAdapter adapter) throws DataIOException
	{
		if( !(adapter instanceof com.anthony.playstation.dataAdapter.TSDB.TSDBDataAdapter) )
			throw new DataIOException("Invalid Adapter instance !");
		
		MappingInfo mapping = (MappingInfo)mappingObject;
		byte[] content = this.loadData(mapping);
		
		if( content == null || content.length == 0 )
			return null;
		
		List<DataSeries> result = null;
		try
		{
			result = adapter.loadSeries(mappingObject, content);
		} catch (DataAdapterException e)
		{
			throw new DataIOException("Failed to parse data: "+e.getMessage(), new Exception(e));
		}
		
		return result;
	}

	/**
	 * Method saveData.
	 * Should not be implemented or used !
	 * @param adapter ADataAdapter
	 * @param series DataSeries
	 * @return int
	 * @throws DataIOException
	 */
	@Override
	public int saveData(ADataAdapter adapter, DataSeries series) throws DataIOException
	{
		throw new DataIOException("Should not save data back to TSDB !");
	}



}
