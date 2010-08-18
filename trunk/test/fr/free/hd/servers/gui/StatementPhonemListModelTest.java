package fr.free.hd.servers.gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.BadLocationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;

public class StatementPhonemListModelTest {

	private StatementListModel model = null;
	private Map<String, Phonem> maps;
	
	@Before()
	public void Init()
	{
		maps = new  HashMap<String, Phonem>();
		
		Phonem phonem = new Phonem("TA", 
				HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("TA", phonem);
		phonem = new Phonem("TB",HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("TB", phonem);
		phonem = new Phonem("FA", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("FA", phonem);
		phonem = new Phonem("FB", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("FB", phonem);
		phonem = new Phonem("U", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("U", phonem);
		phonem = new Phonem("I", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("I", phonem);
		phonem = new Phonem("UI", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("UI", phonem);
		phonem = new Phonem("LUI", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		maps.put("LUI", phonem);
		
		model = new StatementListModel(maps);
	}
	
	@Test() 
	public void testSetPhonemNull() {
		
		
		Assert.assertEquals(0, model.getSize());
	}
	
	@Test()
	public void testSetPhonemEmpty() {
		Assert.assertEquals(0, model.getSize());
	}
	
	@Test
	public void testSetNew() throws BadLocationException {
		model.setString("TATB");
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetInsertMiddle() throws BadLocationException {
		model.setString("TATB");
		model.setString("TAFATB");
		Assert.assertEquals(3, model.getSize());
	}
	
	@Test
	public void testSame() throws BadLocationException {
		model.setString("TATB");
		model.setString("TATB");
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetInsertMiddle2() throws BadLocationException {
		model.setString("TATB");
		model.setString("TAFAFBTB");
		Assert.assertEquals(4, model.getSize());
	}
	
	@Test
	public void testSetRemoveMiddle() throws BadLocationException {
		model.setString("TATB");
		model.setString("TAFAFBTB");
		model.setString("TAFATB");
		Assert.assertEquals(3, model.getSize());
	}
	
	@Test
	public void testSetInsertBegin() throws BadLocationException {
		model.setString("TATB");
		model.setString("FATATB");
		Assert.assertEquals(3, model.getSize());
	}
	
	@Test
	public void testSetInsertBegin2() throws BadLocationException {
		model.setString("TATB");
		model.setString("FBFATATB");
		Assert.assertEquals(4, model.getSize());
	}
	
	@Test
	public void testSetRemoveBegin1() throws BadLocationException {
		model.setString("FBTATB");
		model.setString("TATB");
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetRemoveBegin2() throws BadLocationException {
		model.setString("FAFBTATB");
		model.setString("TATB");
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetInsertEnd() throws BadLocationException {
		model.setString("TATB");
		model.setString("TATBFA");
		Assert.assertEquals(3, model.getSize());
	}
	
	@Test
	public void testSetInsertEnd2() throws BadLocationException {
		model.setString("TATB");
		model.setString("TATBFBFA");
		Assert.assertEquals(4, model.getSize());
	}
	
	@Test
	public void testSetRemoveEnd1() throws BadLocationException {
		model.setString("TATBFB");
		model.setString("TATB");
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetRemoveEnd2() throws BadLocationException {
		model.setString("TATBFAFB");
		model.setString("TATB");
		Assert.assertEquals(2, model.getSize());
	}
	
	
	@Test
	public void testSetInvalidCharacterEnd() throws BadLocationException {
		model.setString("TATB");
		model.setString("TAsdfsds");
		Assert.assertEquals(1, model.getSize());
	}
	
	@Test
	public void testSetInvalidCharacterStart() throws BadLocationException {
		model.setString("TATB");
		model.setString("sdfsdsTA");
		Assert.assertEquals(1, model.getSize());
	}
	
	@Test
	public void testParserGourmand() throws BadLocationException {
		model.setString("LUI");
		Assert.assertEquals(1, model.getSize());
		Assert.assertEquals("LUI", ((Phonem)model.getElementAt(0)).getPhonem());
	}
}
