/**
 * 
 */
package com.github.exahexa.yukebox.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.exahexa.yukebox.data.Album;
import com.github.exahexa.yukebox.data.AudioFile;

/**
 * @author exahexa
 *
 */
public class TestAlbum {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void testConstr2Param() {
		Album t = new Album("test", "artist");
		assertEquals(t.getName(), "test");
		assertEquals(t.getArtist(), "artist");
	}
	
	@Test 
	public void testConstr3Param() {
		Album t = new Album("test", "artist", 15);
		assertEquals(t.getName(), "test");
		assertEquals(t.getArtist(), "artist");
		assertEquals(t.getReleaseDate(), 15);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstrIllegalArgument() {
		Album t = new Album(null, null);
	}
	
	

}
