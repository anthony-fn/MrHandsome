/**   
* @Title: 		ASecurity.java 
* @Package 		com.anthony.playstation.configuration 
* @Description:  
* 				This is the base class for all Security types.
* @author 		Anthony Fan
* @date 		2013-1-8 
* @time 		23:16:35 
* @version 		V1.0   
*/
package com.anthony.playstation.configuration;

/**
 * The Class ASecurity.
 */
public abstract class ASecurity
{
	
	/** Name of security*/
	private String m_name = "";
	
	/** The security ID, may have different meanings in different market.
	 * 
	 *  ChinaEquityMarket	-- Tick ID
	 **/
	private String m_secID = "";
	
	/**
	 * Instantiates a new ASecurity.
	 * Should NOT be used directly.
	 * 
	 * @param name
	 *            Security name.
	 * @param secID
	 *            Security ID.
	 */
	public ASecurity( String secID )
	{
		setSecurityID(secID);
	}
	
	/**
	 * Instantiates a new ASecurity.
	 * Should NOT be used directly.
	 * 
	 * @param name
	 *            Security name.
	 * @param secID
	 *            Security ID.
	 */
	public ASecurity( String name, String secID )
	{
		setSecurityID(secID);
		setSecurityname(name);
	}

	/**
	 * Gets the security name.
	 * 
	 * @return security name.
	 */
	public String getSecurityname()
	{
		return m_name;
	}

	/**
	 * Sets the security name.
	 * 
	 * @param name
	 *            The new security name.
	 */
	public void setSecurityname(String name)
	{
		m_name = name;
	}

	/**
	 * Gets the security ID.
	 * 
	 * @return security ID
	 */
	public String getSecurityID()
	{
		return m_secID;
	}

	/**
	 * Sets the security ID.
	 * 
	 * @param secID
	 *            The new security ID
	 */
	public void setSecurityID(String secID)
	{
		m_secID = secID;
	}
}
