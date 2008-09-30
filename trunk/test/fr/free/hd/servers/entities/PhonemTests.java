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
		Phonem phonem = new Phonem("TA", "C:\\Test");
		Assert.assertNotNull(phonem.getPhonem());
		Assert.assertNotNull(phonem.getPicture());
	}

}
