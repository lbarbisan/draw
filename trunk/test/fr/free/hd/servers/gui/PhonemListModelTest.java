package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;

public class PhonemListModelTest {

	private PhonenListModel model = null;
	private PhonemDocumentListener listener = null;
	private JTextField field;
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
		
		model = new PhonenListModel(maps);
		listener = new PhonemDocumentListener(model);
		
		field = new JTextField();
		field.getDocument().addDocumentListener(listener);
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
	public void testSetPhonemOne() throws BadLocationException {
		field.getDocument().insertString(0, "TA",null);
		Assert.assertEquals(1, model.getSize());
	}
	
	@Test
	public void testSetPhonemMore2() throws BadLocationException {
		
		List<Position> positions = new ArrayList<Position>();
		Position position = new Position();
		position.setPhonem(maps.get("TA"));
		position.setStart(0);
		position.setString("TA");
		positions.add(position);
		position = new Position();
		position.setStart(2);
		position.setString("  ,T");
		positions.add(position);
		position = new Position();
		position.setStart(6);
		position.setString("TB");
		position.setPhonem(maps.get("TB"));
		positions.add(position);
		model.updateCollection(0, 0, positions);
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetPhonemMore3() throws BadLocationException {
		
		testSetPhonemMore2();
		List<Position> positions = new ArrayList<Position>();
		Position position = new Position();
		position.setPhonem(maps.get("TA"));
		position.setStart(8);
		position.setString("TA");
		positions.add(position);
		position = new Position();
		position.setStart(10);
		position.setString("  ,T");
		positions.add(position);
		position = new Position();
		position.setStart(14);
		position.setString("TB");
		position.setPhonem(maps.get("TB"));
		positions.add(position);
		model.updateCollection(0, 0, positions);
		Assert.assertEquals(4, model.getSize());
	}
	
	@Test
	public void testSetPhonemMore4() throws BadLocationException {
		
		testSetPhonemMore2();
		List<Position> positions = new ArrayList<Position>();
		Position position = new Position();
		position.setPhonem(maps.get("TA"));
		position.setStart(0);
		position.setString("TA");
		positions.add(position);
		position = new Position();
		position.setStart(2);
		position.setString("  ,T");
		positions.add(position);
		position = new Position();
		position.setStart(6);
		position.setString("TB");
		position.setPhonem(maps.get("TB"));
		positions.add(position);
		model.updateCollection(0, 3, positions);
		Assert.assertEquals(2, model.getSize());
	}
	/*
	@Test
	public void testSetPhonemMore5() throws BadLocationException {
		
		testSetPhonemMore2();
		List<Position> positions = new ArrayList<Position>();
		Position position = new Position();
		position.setPhonem(maps.get("TA"));
		position.setStart(0);
		position.setString("TA");
		positions.add(position);
		position = new Position();
		position.setStart(2);
		position.setString("  ,T");
		positions.add(position);
		position = new Position();
		position.setStart(6);
		position.setString("TB");
		position.setPhonem(maps.get("TB"));
		positions.add(position);
		model.updateCollection(0, 0, positions);
		Assert.assertEquals(4, model.getSize());
	}*/
	@Test
	public void testSetPhonemMoreThanOne() throws BadLocationException {
		field.getDocument().insertString(0, "TA",null);
		field.getDocument().insertString(0, "TB",null);
		Assert.assertEquals(2, model.getSize());
	}
	
	@Test
	public void testSetPhonemMoreThanOneAndWhiteSpace() throws BadLocationException {
		field.getDocument().insertString(0, "TATB",null);
		field.getDocument().insertString(0, " FAFB, T",null);
		field.getDocument().insertString(0, "A TB",null);
		Assert.assertEquals(5, model.getSize());
	}
	
	@Test
	public void testSetPhonemMoreThanOneAndWhiteSpace2() throws BadLocationException {
		field.getDocument().insertString(0, "TATB",null);
		field.getDocument().insertString(4, " FAFB, T",null);
		field.getDocument().insertString(13, "A TB",null);
		Assert.assertEquals(6, model.getSize());
	}
	
	/*@Test
	public void testSetPhonemMoreThanOneAndInvalidPhonem() {
		model.setPhonem("TATB GAGB, FB");
		Assert.assertEquals(3, model.getSize());
	}*/
		
}
