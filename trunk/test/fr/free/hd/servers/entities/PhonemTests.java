package fr.free.hd.servers.entities;

import junit.framework.Assert;

import org.junit.Test;

/**
 * JUnit test for the {@link Phonem} class.
 *
 * @author Laurent Barbisan
 */
public class PhonemTests {

	@Test
	public void testHasPet() {
		Phonem phonem = new Phonem("TA", HandKeyEnum.HAND_KEY_2M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A_);
		Assert.assertNotNull(phonem.getPhonem());
		Assert.assertNotNull(phonem.getHandKey());
		Assert.assertNotNull(phonem.getHandPosition());
		Assert.assertNotNull(phonem.getMouthVowel());
	}

}
