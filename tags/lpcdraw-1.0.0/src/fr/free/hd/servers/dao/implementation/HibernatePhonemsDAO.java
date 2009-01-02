package fr.free.hd.servers.dao.implementation;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.free.hd.servers.dao.PhonemsDAO;
import fr.free.hd.servers.entities.Phonem;


/**
 * Hibernate implementation of the Clinic interface.
 *
 * <p>The mappings are defined in "petclinic.hbm.xml", located in the root of the
 * class path.
 *
 * <p>Note that transactions are declared with annotations and that some methods
 * contain "readOnly = true" which is an optimization that is particularly
 * valuable when using Hibernate (to suppress unnecessary flush attempts for
 * read-only operations).
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Mark Fisher
 * @since 19.10.2003
 */
@Repository
@Transactional
public class HibernatePhonemsDAO implements PhonemsDAO {

	@Autowired
	@Qualifier("transactionFactory")
	private SessionFactory transactionFactory;


	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Phonem> getPhonems() {
		return transactionFactory.getCurrentSession().createQuery("from Phonem phonem order by phonem.phonem").list();
	}

	@Transactional(readOnly = true)
	public Phonem loadPhonem(int id) {
		return (Phonem) transactionFactory.getCurrentSession().load(Phonem.class, id);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Phonem> findPhonems(String phonem) {
		return transactionFactory.getCurrentSession().createQuery("from Phonem phonem where phonem.phonem like :phonem")
				.setString("phonem", phonem + "%").list();
	}
	
	public void storePhonem(Phonem phonem) {
		// Note: Hibernate3's merge operation does not reassociate the object
		// with the current Hibernate Session. Instead, it will always copy the
		// state over to a registered representation of the entity. In case of a
		// new entity, it will register a copy as well, but will not update the
		// id of the passed-in object. To still update the ids of the original
		// objects too, we need to register Spring's
		// IdTransferringMergeEventListener on our SessionFactory.
		transactionFactory.getCurrentSession().merge(phonem);
	}
}
