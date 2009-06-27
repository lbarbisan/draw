package fr.free.hd.servers.dao.implementation;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import fr.free.hd.servers.dao.PhonemsDAO;
import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;

/**
 * <p>
 * Integration tests for the {@link HibernateClinic} implementation.
 * </p>
 * <p>
 * "HibernateClinicTests-context.xml" determines the actual beans to test.
 * </p>
 *
 * @author Laurent Barbisan
 */
@ContextConfiguration(locations={"HibernatePhonemDAOTests-context.xml"})
public class HibernatePhonemDAOTests extends AbstractDAOTests {

	private int id;
	
	@Autowired
	protected PhonemsDAO phonemsDAO;

	@Test
	public void insertPhonem() {
		Collection<Phonem> phonems = this.phonemsDAO.findPhonems("ta");
		int found = phonems.size();
		Phonem phonem = new Phonem("ta",HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		this.phonemsDAO.storePhonem(phonem);
		id = phonem.getId();
		// assertTrue(!owner.isNew()); -- NOT TRUE FOR TOPLINK (before commit)
		phonems = this.phonemsDAO.findPhonems("ta");
		assertEquals("Verifying number of owners after inserting a new one.", found + 1, phonems.size());
	}

	@Test
	public void getPhonems() {
		int a1 = 0;
		int a2 = 0;
		Phonem phonem = new Phonem("TB", HandKeyEnum.HAND_KEY_1M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		this.phonemsDAO.storePhonem(phonem);
		a1 = phonem.getId();
		phonem = new Phonem("TC", HandKeyEnum.HAND_KEY_2M, 
				HandPositionEnum.HAND_POSITION_BOUCHE, 
				MouthVowelEnum.MOUTH_VOWEL_A);
		this.phonemsDAO.storePhonem(phonem);
		a2 = phonem.getId();
		Collection<Phonem> vets = this.phonemsDAO.getPhonems();
		// Use the inherited countRowsInTable() convenience method (from
		// AbstractTransactionalJUnit4SpringContextTests) to verify the results.
		assertEquals("JDBC query must show the same number of vets", super.countRowsInTable("PHONEM"), vets.size());
		Phonem v1 = phonemsDAO.loadPhonem(a1);
		assertEquals("tb", v1.getPhonem());
		assertEquals(HandKeyEnum.HAND_KEY_1M, v1.getHandKey());
		Phonem v2 = phonemsDAO.loadPhonem(a2);
		assertEquals("tc", v2.getPhonem());
		assertEquals(HandKeyEnum.HAND_KEY_2M, v2.getHandKey());
	}
	
	@Test
	public void updatePhonem() throws Exception {
		insertPhonem();
		Phonem p1 = this.phonemsDAO.loadPhonem(id);
		int id = p1.getId();
		String old = p1.getPhonem();
		p1.setPhonem(old + "x");
		this.phonemsDAO.storePhonem(p1);
		p1 = this.phonemsDAO.loadPhonem(id);
		assertEquals(old + "x", p1.getPhonem());
	}
}
