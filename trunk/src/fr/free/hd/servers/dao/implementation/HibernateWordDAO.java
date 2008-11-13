package fr.free.hd.servers.dao.implementation;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.free.hd.servers.dao.WordDAO;
import fr.free.hd.servers.entities.Phonem;
import fr.free.hd.servers.entities.Word;


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
public class HibernateWordDAO implements WordDAO {

	@Autowired
	@Qualifier("transactionFactory")
	private SessionFactory transactionFactory;


	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Word> getWords() {
		return transactionFactory.getCurrentSession().createQuery("from Word word").list();
	}

	@Transactional(readOnly = true)
	public Word findFace(String word) {
		return (Word)transactionFactory.getCurrentSession().createQuery("from Word word where word.word like :word")
				.setString("word", word).uniqueResult();
	}
	
	@Override
	public void storeWord(Word word) throws DataAccessException {
		for(Phonem phonem : word.getPhonems())
		{
			transactionFactory.getCurrentSession().merge(phonem);
		}
		transactionFactory.getCurrentSession().merge(word);
	}
}
