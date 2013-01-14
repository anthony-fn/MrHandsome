package com.anthony.playstation.dataAPItest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.exceptions.DataProxyOperationException;

public class LocalFileProxyFactoryTest {

	private static ADataIOProxyFactory m_factory = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			m_factory = new LocalFileProxyFactory("DataTest", "DataTest");
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		try {
			m_factory.closeFactory();
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetDataProxy() {
		try {
			ADataIOProxy proxy = m_factory.getDataProxy();
			assertNotNull(proxy);
			assertTrue(proxy instanceof com.anthony.playstation.dataAPI.LocalFileProxy);
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLocalFileProxyFactory() {
		String path1 = "temp1";
		String path2 = "temp2";
		File temp1 = new File(path1);
		File temp2 = new File(path2);
		
		if( temp1.exists() || temp2.exists() )
			fail("Test files exist!. Please reconfigure and try again!");
		try {
			ADataIOProxyFactory factory = new LocalFileProxyFactory("temp1", "temp2");
			
			assertNotNull(factory);
			
			if( !temp1.exists() || !temp2.exists() || !temp1.isDirectory() || !temp2.isDirectory())
				fail("Can't create folder!");
			
			factory.closeFactory();
			
			temp1.delete();
			temp2.delete();
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

}
