package fr.free.hd.servers.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import fr.free.hd.servers.entities.BaseEntity;
import fr.free.hd.servers.entities.Word;

/**
 * The high-level word business interface.
 *
 * <p>This is basically a data access object.
 * Phonem doesn't have a dedicated business facade.
 *
 * @author Laurent Barbisan
 */
public interface WordDAO {

	/**
	 * Retrieve all <code>Word</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Word</code>s
	 */
	Collection<Word> getWords() throws DataAccessException;


	/**
	 * Retrieve <code>Word</code>s from the data store by word,
	 * returning all word whose last name <i>starts</i> with the given name.
	 * @param lastName Value to search for
	 * @return a <code>Collection</code> of matching <code>Owner</code>s
	 * (or an empty <code>Collection</code> if none found)
	 */
	Word findFace(String word) throws DataAccessException;
	
	/**
	 * Save an <code>Face</code> to the data store, either inserting or updating it.
	 * @param owner the <code>Face</code> to save
	 * @see BaseEntity#isNew
	 */
	void storeWord(Word word) throws DataAccessException;

}
