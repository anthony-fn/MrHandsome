/**   
* @Title: 		ASecurityType.java 
* @Package 		com.anthony.playstation.configuration 
* @Description:  
* 				Not used by now.
* @author 		Anthony Fan
* @date 		2013-1-8 
* @time 		23:19:25 
* @version 		V1.0   
*/
package com.anthony.playstation.configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class ASecurityType.
 */
public abstract class ASecurityType
{
	
	/** The m_type name. */
	private String m_typeName = "";
	
	/** The m_type. */
	private int m_type = 0;
	
	/**
	 * Instantiates a new a security type.
	 * 
	 * @param name
	 *            the name
	 */
	public ASecurityType( String name )
	{
		m_typeName = name;
	}
	
	/**
	 * Instantiates a new a security type.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 */
	public ASecurityType( String name, int type )
	{
		m_typeName = name;
		m_type = type;
	}

	/**
	 * Gets the type name.
	 * 
	 * @return the type name
	 */
	public String getTypeName()
	{
		return m_typeName;
	}
	
	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType()
	{
		return m_type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param typeName
	 *            the type name
	 * @param type
	 *            the type
	 */
	public void setType(String typeName, int type)
	{
		m_typeName = typeName;
		m_type = type;
	}

}
