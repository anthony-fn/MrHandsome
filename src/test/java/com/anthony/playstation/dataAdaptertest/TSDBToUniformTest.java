package com.anthony.playstation.dataAdaptertest;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAdapter.TSDB.TSDBToUniform;
import com.anthony.playstation.exceptions.ConfigurationException;

/**
 * The class <code>TSDBToUniformTest</code> contains tests for the class <code>{@link TSDBToUniform}</code>.
 *
 * @generatedBy CodePro at 13-1-14 上午11:01
 * @author afan
 * @version $Revision: 1.0 $
 */
public class TSDBToUniformTest
{
	/**
	 * Run the TSDBToUniform(int,String,MappingType,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testTSDBToUniform_1()
		throws Exception
	{
		int tstype = 1;
		String name = "";
		MappingType type = MappingType.MappingAggregates;
		String className = "";

		TSDBToUniform result = new TSDBToUniform(tstype, name, type, className);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getName());
		assertEquals("", result.getClassName());
	}

	/**
	 * Run the void addUniformType(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testAddUniformType_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail(e.getMessage());
		}
		int i = 1;

		try
		{
			fixture.addUniformType(i);
			fail("1 should not be able to be added again");
		} catch (ConfigurationException e)
		{
		}

		List<Integer> types = fixture.getUniformList();
		
		assertTrue( types.size() == 1 );
		assertTrue( types.get(0) == 1);
	}

	/**
	 * Run the void addUniformType(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testAddUniformType_2()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
			fixture.addUniformType(2);
			fixture.addUniformType(3);
			fixture.addUniformType(4);
		} catch (ConfigurationException e)
		{
			fail(e.getMessage());
		}
		
		List<Integer> types = fixture.getUniformList();
		
		assertTrue( types.size() == 4 );
		assertTrue( types.get(0) == 1);
		assertTrue( types.get(1) == 2);
		assertTrue( types.get(2) == 3);
		assertTrue( types.get(3) == 4);
	}

	/**
	 * Run the void addUniformTypes(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testAddUniformTypes_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
			fixture.addUniformTypes("2,3,4");
		} catch (ConfigurationException e)
		{
			fail(e.getMessage());			
		}


		List<Integer> types = fixture.getUniformList();
		
		assertTrue( types.size() == 4 );
		assertTrue( types.get(0) == 1);
		assertTrue( types.get(1) == 2);
		assertTrue( types.get(2) == 3);
		assertTrue( types.get(3) == 4);
	}

	/**
	 * Run the void addUniformTypes(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testAddUniformTypes_2()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
			fixture.addUniformTypes("1, 2,3,4");
			fail("1 should not be able to be added again");
		} catch (ConfigurationException e)
		{
		}
	}

	/**
	 * Run the void addUniformTypes(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testAddUniformTypes_3()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
			fixture.addUniformTypes("2,2,3,4");
			fail("Duplicated UniformTypes are added !");
		} catch (ConfigurationException e)
		{
		}
	}

	/**
	 * Run the void addUniformTypes(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test(expected = java.lang.NumberFormatException.class)
	public void testAddUniformTypes_4()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
			fixture.addUniformTypes("2,3,abc");
			
			fail("Invalid input for String(UniformTypes)");
		} catch (ConfigurationException e)
		{
		}
	}

	/**
	 * Run the String getClassName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testGetClassName_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "com.abc.def");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail();
		}

		String result = fixture.getClassName();

		assertTrue(result.equals("com.abc.def"));
	}

	/**
	 * Run the MappingType getMappingType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testGetMappingType_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail();
		}

		MappingType result = fixture.getMappingType();

		// add additional test code here
		assertNotNull(result);
		assertEquals("MappingAggregates", result.toString());
		assertEquals("MappingAggregates", result.name());
		assertEquals(0, result.ordinal());
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testGetName_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "tsNAV", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail();
		}

		String result = fixture.getName();

		assertTrue(result.equals("tsNAV"));
	}

	/**
	 * Run the List<Integer> getUniformList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testGetUniformList_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail();
		}

		List<Integer> result = fixture.getUniformList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(new Integer(1)));
	}

	/**
	 * Run the void setClassName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testSetClassName_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "abc");

		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail();
		}
		assertTrue(fixture.getClassName().equals("abc"));

		fixture.setClassName("def");

		assertTrue(fixture.getClassName().equals("def"));
	}

	/**
	 * Run the void setMappingType(MappingType) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testSetMappingType_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail();
		}
		assertEquals(MappingType.MappingAggregates, fixture.getMappingType());	
		fixture.setMappingType(MappingType.MappingBaseObject);
		
		assertEquals(MappingType.MappingBaseObject, fixture.getMappingType());

		// add additional test code here
	}

	/**
	 * Run the void setName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Test
	public void testSetName_1()
		throws Exception
	{
		TSDBToUniform fixture = new TSDBToUniform(1, "Mr", MappingType.MappingAggregates, "");
		fixture.setClassName("");
		fixture.setMappingType(MappingType.MappingAggregates);
		try
		{
			fixture.addUniformType(1);
		} catch (ConfigurationException e)
		{
			fail();
		}
		assertTrue(fixture.getName().equals("Mr"));
		String m_name = "Handsome";
		fixture.setName(m_name);
		assertTrue(fixture.getName().equals("Handsome"));
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@Before
	public void setUp()
		throws Exception
	{
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	@After
	public void tearDown()
		throws Exception
	{
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 13-1-14 上午11:01
	 */
	public static void main(String[] args)
	{
		new org.junit.runner.JUnitCore().run(TSDBToUniformTest.class);
	}
}