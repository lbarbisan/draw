package fr.free.hd.servers.entities;

import junit.framework.Assert;

import org.junit.Test;

public class WordTest {

	@Test
	public void testWordGetterSetter() {
		Word word = new Word();
		
		word.setId(1);
		word.setPhonems(null);
		word.setWord("Test");
		
		Assert.assertEquals((Integer)1, word.getId());
		Assert.assertEquals("Test", word.getWord());
		
	}

}
