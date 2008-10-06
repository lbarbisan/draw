package fr.free.hd.servers.gui;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;

public class PhonemListModelTest {

	private PhonemListModel model = null;
	
	@Before()
	public void Init()
	{
		Map<String, Phonem> maps = new  HashMap<String, Phonem>();
		
		Phonem phonem = new Phonem("TA", 
				HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A_);
		maps.put("TA", phonem);
		phonem = new Phonem("TB",HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A_);
		maps.put("TB", phonem);
		phonem = new Phonem("FA", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A_);
		maps.put("FA", phonem);
		phonem = new Phonem("FB", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A_);
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
