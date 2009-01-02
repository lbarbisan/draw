package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;

public class PhonemListModelTest {

	@Test(expected=NullPointerException.class)
	public void testPhonemListModelException() {
		PhonemListModel model = new PhonemListModel(null);
	}

	@Test
	public void testGetElementAt() {
		List<Phonem> list = new ArrayList<Phonem>();
		list.add(getPhonem("P1"));
		list.add(getPhonem("P2"));
		PhonemListModel model = new PhonemListModel(list);
		
		Phonem phonem = (Phonem)model.getElementAt(1);
		Assert.assertNotNull(phonem);
		phonem.getPhonem().equals("P2");
	}

	@Test
	public void testGetSize() {
		List<Phonem> list = new ArrayList<Phonem>();
		list.add(getPhonem("P1"));
		list.add(getPhonem("P2"));
		PhonemListModel model = new PhonemListModel(list);
		
		Assert.assertEquals(model.getSize(), 2);
		
	}
	
	private static Phonem getPhonem(String phonemString)
	{
		Phonem phonem = new Phonem();
		phonem.setHandKey(HandKeyEnum.HAND_KEY_1M);
		phonem.setHandPosition(HandPositionEnum.HAND_POSITION_BOUCHE);
		phonem.setId(1);
		phonem.setMouthVowel(MouthVowelEnum.MOUTH_VOWEL_A);
		phonem.setPhonem(phonemString);
		
		return phonem;
	}
}
