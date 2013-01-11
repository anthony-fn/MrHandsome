/**   
* @Title: 		ProtoBufAdapter.java 
* @Package 		com.anthony.playstation.dataAdapter.protoBuf 
* @Description:  
* 				The defination of class ProtoBufAdapter
* @author 		Anthony Fan
* @date 		2013-1-10 
* @time 		21:17:46 
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAdapter.protoBuf;

import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.data.dataunit.StringDataUnit;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.dataAdapter.protoBuf.UniformDataSeriesProtoBuf.UniformDataSeriesForProto;
import com.anthony.playstation.dataAdapter.protoBuf.UniformDataSeriesProtoBuf.UniformDataSeriesForProto.DataUnitTypeForProto;
import com.anthony.playstation.dataAdapter.protoBuf.UniformDataSeriesProtoBuf.UniformDataSeriesForProto.UniformTypeForProto;
import com.anthony.playstation.dataAdapter.protoBuf.UniformDataSeriesProtoBuf.UniformDataSeriesForProto.ValueUnitForProto;
import com.anthony.playstation.exceptions.DataAdapterException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * An implementation of ADataAdapter.
 * 
 * This class serialize/deserialize data between a ProtoBuffer formatted byte[] and an instance of DataSeries.
 */
public class ProtoBufAdapter extends ADataAdapter
{

	/**
	 * Method valueFromPro.
	 * Transfer data from a UniformDataSeriesForProtoBuffer (generated code from ProtoBuffer) into List<ADataUnit>
	 * @param valueSeries UniformDataSeriesForProtoBuffer
	 * @return List<ADataUnit>
	 * @throws InvalidDataUnitException
	 */
	private static List<ADataUnit> valueFromProtoBuffer( UniformDataSeriesForProto valueSeries ) throws InvalidDataUnitException
	{
		List<ADataUnit> result = new LinkedList<ADataUnit>();
		
		int length = valueSeries.getValueListCount();
		int type = valueSeries.getUniformType().getUnitType().ordinal();
		
		for( int i = 0; i < length; i ++ )
		{
			ValueUnitForProto unit = valueSeries.getValueList(i);
			
			switch( type )
			{
			case 2:
				result.add(new StringDataUnit(unit.getDate(), unit.getValue()));
				break;
			case 1:
				result.add(new ValueDataUnit(unit.getDate(), Float.parseFloat(unit.getValue())));
				break;
			default:
				break;
			}
			
		}
		
		return result;
	}
	
	/**
	 * Method uniformTypeFromPro.
	 * Transfer data from a UniformTypeForProtoBuffer (generated code from ProtoBuffer) into an instance of UniformType.
	 * @param typeFromPro UniformTypeForProto
	 * @return UniformType
	 */
	private static UniformType uniformTypeFromProtoBuffer( UniformTypeForProto typeFromPro)
	{
		return new UniformType( typeFromPro.getTypeID(), typeFromPro.getTypeName(),
				DataUnitType.values()[typeFromPro.getUnitType().ordinal()]);
	}
	
	/**
	 * Method proToSeries.
	 * Transfer data from a UniformDataSeriesForProto (generated code from ProtoBuffer) into an instance of DataSeries
	 * @param valueSeries UniformDataSeriesForProto
	 * @return DataSeries
	 * @throws InvalidDataUnitException
	 */
	private static DataSeries protobufferToSeries( UniformDataSeriesForProto valueSeries ) throws InvalidDataUnitException
	{
		DataSeries result = null;
		
		
		result = new DataSeries(ProtoBufAdapter.uniformTypeFromProtoBuffer(valueSeries.getUniformType()),
				valueSeries.getId());
		
		result.setUnitList(ProtoBufAdapter.valueFromProtoBuffer(valueSeries));
		return result;
	}
	
	/**
	 * Method seriesToPro.
	 * Transfer data from an instance of DataSeries into a UniformDataSeriesForProto(generated code from ProtoBuffer)
	 * @param series DataSeries
	 * @return UniformDataSeriesForProto
	 */
	private static UniformDataSeriesForProto seriesToPro( DataSeries series )
	{
		UniformDataSeriesForProto.Builder valueSeries = UniformDataSeriesForProto.newBuilder();
		valueSeries.setId(series.getPerformanceID());
		
		UniformTypeForProto.Builder uniformType = UniformTypeForProto.newBuilder();
		UniformType type = series.getUniType();
		
		uniformType.setTypeID(type.getTypeID());
		uniformType.setTypeName(type.getTypeName());
		uniformType.setUnitType(DataUnitTypeForProto.values()[type.getValueType().ordinal()]);

		valueSeries.setUniformType(uniformType.build());
		
		for (ADataUnit unit : series.getUnitList())
		{
			ValueUnitForProto.Builder valueUnit = ValueUnitForProto.newBuilder();
			
			valueUnit.setDate(unit.getDateString());
			
			switch( type.getValueType().ordinal() )
			{
			case 1:
				valueUnit.setValue( String.valueOf((Float)(unit.getValue())));
				break;
			case 2:
				valueUnit.setValue((String)unit.getValue());
				break;
			case 0:
			default:
				valueUnit.setValue("");
					
			}
			
			valueSeries.addValueList(valueUnit.build());
		}
		
		return valueSeries.build();
	}
	
	/**
	 * Method serializeSeries.
	 * @param series DataSeries
	 * @return byte[]
	 * @throws DataAdapterException
	 */
	@Override
	public byte[] serializeSeries(DataSeries series) throws DataAdapterException
	{
		return ProtoBufAdapter.seriesToPro(series).toByteArray();
	}

	/**
	 * Method loadSeries.
	 * @param mapping Object
	 * @param content byte[]
	 * @return List<DataSeries> The reason of keeping a List<> here is for TSDBDataAdapter. Other Adapters should have List<>.size() = 1
	 * @throws DataAdapterException
	 */
	@Override
	public List<DataSeries> loadSeries(Object mapping, byte[] content) throws DataAdapterException
	{
		UniformDataSeriesForProto proto = null;
		DataSeries series = null;
		try
		{
			proto = UniformDataSeriesForProto.parseFrom(content);
			series = ProtoBufAdapter.protobufferToSeries(proto);
		} catch (InvalidProtocolBufferException e)
		{
			throw new DataAdapterException("Failed to parse data from byte to DataSeries: "+e.getMessage(), e);
		} catch (InvalidDataUnitException e)
		{
			throw new DataAdapterException("Invalid data parsed from Protobuffer: "+e.getMessage(), new Exception(e));
		}		 
		
		List<DataSeries> result = new LinkedList<DataSeries>();
		result.add(series);
		
		return result;
	}

}
