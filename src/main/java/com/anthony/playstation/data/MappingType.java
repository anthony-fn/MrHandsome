package com.anthony.playstation.data;

public enum MappingType
{
    Aggregates,
    BaseObject,
    CorporateActionAdjustment,
    SalesPosition,
    TaxAdjustment,
    Restated,
    Rank;
    
    public String toString(){	
    	return name();
    } 
}