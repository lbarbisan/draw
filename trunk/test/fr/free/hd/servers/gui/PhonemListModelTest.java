package fr.free.hd.servers.gui;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.free.hd.servers.entities.Phonem;

public class PhonemListModelTest {

	private PhonemListModel model = null;
	
	@Before()
	public void Init()
	{
		Map<String, Phonem> maps = new  HashMap<String, Phonem>();
		
		Phonem phonem = new Phonem("TA", "C:\\Phonems");
		maps.put("TA", phonem);
		phonem = new Phonem("TB", "C:\\Phonems");
		maps.put("TB", phonem);
		phonem = new Phonem("FA", "C:\\Phonems");
		maps.put("FA", phonem);
		phonem = new Phonem("FB", "C:\\Phonems");
		maps.put("FB", phonem);
		
		model = new PhonemListModel(maps);
	}
	
	@Test() 
	public void testSetPhonemNull() {
		model.setPhonem(null);
		Assert.assertEquals(0, model.getSize());
	}
	
	@Test()
	public void testSetPhonemEmpty() {
		model.setPhonem("");
		Assert.assertEquals(0, model.getSize());
	}
	
	@Test
	public void testSetPhonemOne() {
		model.setPhonem("TA");
		Assert.assertEquals(1, model.getSize());
	}
	
	@Test
	public void testSetPhonemMoreThanOne() {
		model.setPhonem("TATB");
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetPhonemMoreThanOneAndWhiteSpace() {
		model.setPhonem("TATB FAFB, FC FD");
		Assert.assertEquals(4, model.getSize());
	}
	
	@Test
	public void testSetPhonemMoreThanOneAndInvalidPhonem() {
		model.setPhonem("TATB GAGB, FB");
		Assert.assertEquals(3, model.getSize());
	}
		
}
