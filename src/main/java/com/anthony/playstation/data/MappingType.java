package com.anthony.playstation.data;

public enum MappingType
{
    MappingAggregates,
    MappingBaseObject,
    MappingCorporateActionAdjustment,
    MappingSalesPosition,
    MappingTaxAdjustment,
    MappingRestated,
    MappingRank;
    
    public String toString(){	
    	return name();
    } 
}