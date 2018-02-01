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
import com.github.exahexa.yukebox.data.Artist;
import com.github.exahexa.yukebox.data.AudioFile;

/**
 * @author exahexa
 *
 */
public class TestArtist {

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
	public void test() {
		Artist a = new Artist("test");
		Album album = new Album("test", "test");
		a.add(album);
		assertTrue(a.contains(album));
		assertTrue(a.containsKey(album.getName()));
		//AudioFile af = new AudioFile("name", "1", 1, "test", "test", "test", "test");
		//album.add(af);
		//assertTrue(album.containsKey("name"));
	}

}
