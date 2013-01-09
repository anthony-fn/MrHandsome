/**   
* @Title: 		ChinaEquity.java 
* @Package 		com.anthony.playstation.configuration 
* @Description:  
* 				ChinaEquity is the representation for equities in Shanghai and Shenzhen exchange market in China. 
* @author 		Anthony Fan
* @date 		2013-1-8 
* @time 		23:29:42 
* @version 		V 1.0   
*/
package com.anthony.playstation.configuration;

import com.anthony.playstation.configuration.ASecurity;

/**
 * The Class ChinaEquity.
 */
public class ChinaEquity extends ASecurity
{
	
	/** The performance id of this equity in TSDB. */
	private String m_performanceId = "";
	
	/**
	 * Instantiates a new ChinaEquity instance.
	 * 
	 * @param name
	 *            Equity name.
	 * @param secID
	 *            Tick ID of this equity.
	 * @param performanceID
	 *            Related performance ID in TSDB.
	 */
	public ChinaEquity(String name, String secID, String performanceID)
	{
		super(name, secID);
		m_performanceId = performanceID;
	}
	
	/**
	 * Gets the performance id.
	 * 
	 * @return related performance ID in TSDB
	 */
	public String getPerformanceID ()
	{
		return m_performanceId;
	}

}
