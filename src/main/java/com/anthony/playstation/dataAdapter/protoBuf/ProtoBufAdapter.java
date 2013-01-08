package com.anthony.playstation.dataAdapter.protoBuf;

import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.dataAdapter.protoBuf.ValueDataSeriesProtoBuf.ValueDataSeriesForProto;
import com.anthony.playstation.dataAdapter.protoBuf.ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto;
import com.anthony.playstation.dataAdapter.protoBuf.ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto;
import com.anthony.playstation.dataAdapter.protoBuf.ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto;
import com.anthony.playstation.exceptions.DataAdapterException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufAdapter extends ADataAdapter
{

	private static List<ADataUnit> valueFromPro( ValueDataSeriesForProto valueSeries ) throws InvalidDataUnitException
	{
		List<ADataUnit> result = new LinkedList<ADataUnit>();
		
		int length = valueSeries.getValueListCount();
		
		
		for( int i = 0; i < length; i ++ )
		{
			ValueUnitForProto unit = valueSeries.getValueList(i);
			result.add(new ValueDataUnit(unit.getDate(), unit.getValue()));
		}
		
		return result;
	}
	
	private static UniformType uniformTypeFromPro( UniformTypeForProto typeFromPro)
	{
		return new UniformType( typeFromPro.getTypeID(), typeFromPro.getTypeName(),
				DataUnitType.values()[typeFromPro.getUnitType().ordinal()]);
	}
	
	private static DataSeries proToSeries( ValueDataSeriesForProto valueSeries ) throws InvalidDataUnitException
	{
		DataSeries result = null;
		
		result = new DataSeries(valueSeries.getName());
		result.setPerformanceID(valueSeries.getId());
		result.setUniType(ProtoBufAdapter.uniformTypeFromPro(valueSeries.getUniformType()));
		result.setUnitList(ProtoBufAdapter.valueFromPro(valueSeries));
		return result;
	}
	
	private static ValueDataSeriesForProto seriesToPro( DataSeries series )
	{
		ValueDataSeriesForProto.Builder valueSeries = ValueDataSeriesForProto.newBuilder();
		valueSeries.setId(series.getPerformanceID());
		valueSeries.setName(series.getSeriesName());
		
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
			valueUnit.setValue(((ValueDataUnit)unit).getValue());
			
			valueSeries.addValueList(valueUnit.build());
		}
		
		return valueSeries.build();
	}
	
	@Override
	public byte[] serializeSeries(DataSeries series) throws DataAdapterException
	{
		return ProtoBufAdapter.seriesToPro(series).toByteArray();
	}

	@Override
	public List<DataSeries> loadSeries(Object mapping, byte[] content) throws DataAdapterException
	{
		ValueDataSeriesForProto proto = null;
		DataSeries series = null;
		try
		{
			proto = ValueDataSeriesForProto.parseFrom(content);
			series = ProtoBufAdapter.proToSeries(proto);
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
