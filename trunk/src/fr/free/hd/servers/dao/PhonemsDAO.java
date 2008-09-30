package fr.free.hd.servers.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import fr.free.hd.servers.entities.BaseEntity;
import fr.free.hd.servers.entities.Phonem;

/**
 * The high-level Phonem business interface.
 *
 * <p>This is basically a data access object.
 * Phonem doesn't have a dedicated business facade.
 *
 * @author Laurent Barbisan
 */
public interface PhonemsDAO {

	/**
	 * Retrieve all <code>Phonems</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Phonems</code>s
	 */
	Collection<Phonem> getPhonems() throws DataAccessException;

	/**
	 * Retrieve a <code>Pet</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Phonem</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
	Phonem loadPhonem(int id) throws DataAccessException;

	/**
	 * Retrieve <code>Owner</code>s from the data store by last name,
	 * returning all owners whose last name <i>starts</i> with the given name.
	 * @param lastName Value to search for
	 * @return a <code>Collection</code> of matching <code>Owner</code>s
	 * (or an empty <code>Collection</code> if none found)
	 */
	Collection<Phonem> findPhonems(String phonem) throws DataAccessException;
	
	/**
	 * Save an <code>Phonem</code> to the data store, either inserting or updating it.
	 * @param owner the <code>Phonem</code> to save
	 * @see BaseEntity#isNew
	 */
	void storePhonem(Phonem phonem) throws DataAccessException;

}
