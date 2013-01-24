package com.anthony.playstation;

import java.util.Calendar;

public abstract class PlaystationUtility
{
	public static Calendar unifyCalendar( Calendar src)
	{
		if( src == null )
			src = Calendar.getInstance();
		src.set(Calendar.HOUR_OF_DAY, 0);
		src.set(Calendar.MINUTE, 0);
		src.set(Calendar.SECOND, 0);
		src.set(Calendar.MILLISECOND, 0);	
		
		return src;
	}
	
	public static Calendar copyCalendar( Calendar src )
	{
		if( src == null )
			return unifyCalendar(null);
		else{
			Calendar result = Calendar.getInstance();
			
			result.set(Calendar.YEAR, src.get(Calendar.YEAR));
			result.set(Calendar.MONTH, src.get(Calendar.MONTH));
			result.set(Calendar.DAY_OF_MONTH, src.get(Calendar.DAY_OF_MONTH));
			
			result.set(Calendar.HOUR_OF_DAY, 0);
			result.set(Calendar.MINUTE, 0);
			result.set(Calendar.SECOND, 0);
			result.set(Calendar.MILLISECOND, 0);	
			
			return result;
		}
	}
	
	public static Calendar getStart( Calendar front, Calendar end)
	{
		return front.compareTo(end) <= 0 ? front : end;
	}
	
	public static Calendar getEnd( Calendar front, Calendar end)
	{
		return front.compareTo(end) >= 0 ? front : end;
	}
}
