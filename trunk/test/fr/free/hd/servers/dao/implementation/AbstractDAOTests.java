package fr.free.hd.servers.dao.implementation;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.Clinic;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import fr.free.hd.servers.dao.PhonemsDAO;
import fr.free.hd.servers.entities.Phonem;

/**
 * <p>
 * Base class for {@link Clinic} integration tests.
 * </p>
 * <p>
 * &quot;AbstractClinicTests-context.xml&quot; declares a common
 * {@link javax.sql.DataSource DataSource}. Subclasses should specify
 * additional context locations which declare a
 * {@link org.springframework.transaction.PlatformTransactionManager PlatformTransactionManager}
 * and a concrete implementation of {@link Clinic}.
 * </p>
 * <p>
 * This class extends {@link AbstractTransactionalJUnit4SpringContextTests},
 * one of the valuable testing support classes provided by the
 * <em>Spring TestContext Framework</em> found in the
 * <code>org.springframework.test.context</code> package. The
 * annotation-driven configuration used here represents best practice for
 * integration tests with Spring. Note, however, that
 * AbstractTransactionalJUnit4SpringContextTests serves only as a convenience
 * for extension. For example, if you do not wish for your test classes to be
 * tied to a Spring-specific class hierarchy, you may configure your tests with
 * annotations such as {@link ContextConfiguration @ContextConfiguration},
 * {@link org.springframework.test.context.TestExecutionListeners @TestExecutionListeners},
 * {@link org.springframework.transaction.annotation.Transactional @Transactional},
 * etc.
 * </p>
 * <p>
 * AbstractClinicTests and its subclasses benefit from the following services
 * provided by the Spring TestContext Framework:
 * </p>
 * <ul>
 * <li><strong>Spring IoC container caching</strong> which spares us
 * unnecessary set up time between test execution.</li>
 * <li><strong>Dependency Injection</strong> of test fixture instances,
 * meaning that we don't need to perform application context lookups. See the
 * use of {@link Autowired @Autowired} on the <code>clinic</code> instance
 * variable, which uses autowiring <em>by type</em>. As an alternative, we
 * could annotate <code>clinic</code> with
 * {@link javax.annotation.Resource @Resource} to achieve dependency injection
 * <em>by name</em>.
 * <em>(see: {@link ContextConfiguration @ContextConfiguration},
 * {@link org.springframework.test.context.support.DependencyInjectionTestExecutionListener DependencyInjectionTestExecutionListener})</em></li>
 * <li><strong>Transaction management</strong>, meaning each test method is
 * executed in its own transaction, which is automatically rolled back by
 * default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script.
 * <em>(see: {@link org.springframework.test.context.transaction.TransactionConfiguration @TransactionConfiguration},
 * {@link org.springframework.transaction.annotation.Transactional @Transactional},
 * {@link org.springframework.test.context.transaction.TransactionalTestExecutionListener TransactionalTestExecutionListener})</em></li>
 * <li><strong>Useful inherited protected fields</strong>, such as a
 * {@link org.springframework.jdbc.core.simple.SimpleJdbcTemplate SimpleJdbcTemplate}
 * that can be used to verify database state after test operations or to verify
 * the results of queries performed by application code. An
 * {@link org.springframework.context.ApplicationContext ApplicationContext} is
 * also inherited and can be used for explicit bean lookup if necessary.
 * <em>(see: {@link org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests AbstractJUnit4SpringContextTests},
 * {@link AbstractTransactionalJUnit4SpringContextTests})</em></li>
 * </ul>
 * <p>
 * The Spring TestContext Framework and related unit and integration testing
 * support classes are shipped in <code>spring-test.jar</code>.
 * </p>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
@ContextConfiguration
public abstract class AbstractDAOTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	protected PhonemsDAO phonemsDAO;

	@Test
	public void insertPhonem() {
		Collection<Phonem> phonems = this.phonemsDAO.findPhonems("ta");
		int found = phonems.size();
		Phonem phonem = new Phonem("ta", "C:\\TA-est");
		this.phonemsDAO.storePhonem(phonem);
		// assertTrue(!owner.isNew()); -- NOT TRUE FOR TOPLINK (before commit)
		phonems = this.phonemsDAO.findPhonems("ta");
		assertEquals("Verifying number of owners after inserting a new one.", found + 1, phonems.size());
	}

	@Test
	public void getPhonems() {
		Phonem phonem = new Phonem("TB", "C:\\TB-Test");
		this.phonemsDAO.storePhonem(phonem);
		phonem = new Phonem("TC", "C:\\TC-Test");
		this.phonemsDAO.storePhonem(phonem);
		Collection<Phonem> vets = this.phonemsDAO.getPhonems();
		// Use the inherited countRowsInTable() convenience method (from
		// AbstractTransactionalJUnit4SpringContextTests) to verify the results.
		assertEquals("JDBC query must show the same number of vets", super.countRowsInTable("PHONEM"), vets.size());
		Phonem v1 = phonemsDAO.loadPhonem(2);
		assertEquals("tb", v1.getPhonem());
		assertEquals("C:\\TB-Test", v1.getPicture());
		Phonem v2 = phonemsDAO.loadPhonem(3);
		assertEquals("tc", v2.getPhonem());
		assertEquals("C:\\TC-Test", v2.getPicture());
	}
	
	@Test
	public void updatePhonem() throws Exception {
		insertPhonem();
		Phonem p1 = this.phonemsDAO.loadPhonem(4);
		int id = p1.getId();
		String old = p1.getPhonem();
		p1.setPhonem(old + "x");
		this.phonemsDAO.storePhonem(p1);
		p1 = this.phonemsDAO.loadPhonem(id);
		assertEquals(old + "x", p1.getPhonem());
	}
}
