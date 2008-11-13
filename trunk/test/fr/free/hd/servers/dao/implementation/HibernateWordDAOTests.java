package fr.free.hd.servers.dao.implementation;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import fr.free.hd.servers.dao.WordDAO;
import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;
import fr.free.hd.servers.entities.Word;

@ContextConfiguration(locations={"HibernatePhonemDAOTests-context.xml"})
public class HibernateWordDAOTests extends AbstractDAOTests {

	@Autowired
	protected WordDAO wordDAO;

	/*@Test
	public void testGetWords() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindFace() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testStoreWord() {
		Phonem phonem1 = new Phonem();
		phonem1.setHandKey(HandKeyEnum.HAND_KEY_1M);
		phonem1.setHandPosition(HandPositionEnum.HAND_POSITION_BOUCHE);
		phonem1.setMouthVowel(MouthVowelEnum.MOUTH_VOWEL_A);
		phonem1.setPhonem("BR");
		phonem1.setId(1);
		
		Phonem phonem2 = new Phonem();
		phonem2.setHandKey(HandKeyEnum.HAND_KEY_1M);
		phonem2.setHandPosition(HandPositionEnum.HAND_POSITION_BOUCHE);
		phonem2.setMouthVowel(MouthVowelEnum.MOUTH_VOWEL_A);
		phonem2.setPhonem("UI");
		phonem2.setId(2);
		
		Word word = new Word();
		word.setWord("bruit");
		word.getPhonems().add(phonem1);
		word.getPhonems().add(phonem2);

		this.wordDAO.storeWord(word);
		Assert.assertEquals(2, super.countRowsInTable("PHONEM"));
		Assert.assertEquals(1, super.countRowsInTable("WORD"));
	}

}
