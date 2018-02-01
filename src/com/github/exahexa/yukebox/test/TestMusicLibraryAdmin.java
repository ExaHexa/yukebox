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
import com.github.exahexa.yukebox.data.MusicLibObj;
import com.github.exahexa.yukebox.data.MusicLibraryAdmin;

/**
 * @author exahexa
 *
 */
public class TestMusicLibraryAdmin {

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
	public void test() throws Exception{
		//MusicLibraryAdmin musicLAdm = new MusicLibraryAdmin();
		//musicLAdm.parse();
	}
	
	@Test
	public void testSpeed() throws Exception{
		MusicLibraryAdmin adm = new MusicLibraryAdmin();
		for(MusicLibObj e : adm.getMusicLibrary()) {
			System.out.println(e.getName());
			for(MusicLibObj a : e.getValues()) {
				System.out.println("	" + a.getName());
				for(MusicLibObj af : a.getValues()) {
					System.out.println("		" + af.getName());
				}
			}
		}
	}
	
	@Test
	public void testTreeStructure() throws Exception{
		MusicLibraryAdmin musicLAdm = new MusicLibraryAdmin();
		Artist artist = new Artist("test");
		musicLAdm.getMusicLibraryTree().put("test", artist);
		Album album = new Album("test", "test");
		musicLAdm.getMusicLibraryTree().get("test").add(album);
		assertTrue(musicLAdm.getMusicLibraryTree().get("test").containsKey("test"));
		assertTrue(musicLAdm.getMusicLibraryTree().get("test").containsKey("TEST"));
		assertFalse(musicLAdm.getMusicLibraryTree().get("test").containsKey("bla"));
		
	}

}
