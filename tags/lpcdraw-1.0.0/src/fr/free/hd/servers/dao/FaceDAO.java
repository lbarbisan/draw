package fr.free.hd.servers.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import fr.free.hd.servers.entities.BaseEntity;
import fr.free.hd.servers.entities.Face;

/**
 * The high-level Phonem business interface.
 *
 * <p>This is basically a data access object.
 * Phonem doesn't have a dedicated business facade.
 *
 * @author Laurent Barbisan
 */
public interface FaceDAO {

	/**
	 * Retrieve all <code>Face</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Face</code>s
	 */
	Collection<Face> getFaces() throws DataAccessException;


	/**
	 * Retrieve <code>Owner</code>s from the data store by last name,
	 * returning all owners whose last name <i>starts</i> with the given name.
	 * @param lastName Value to search for
	 * @return a <code>Collection</code> of matching <code>Owner</code>s
	 * (or an empty <code>Collection</code> if none found)
	 */
	Face findFace(String picture) throws DataAccessException;
	
	/**
	 * Save an <code>Face</code> to the data store, either inserting or updating it.
	 * @param owner the <code>Face</code> to save
	 * @see BaseEntity#isNew
	 */
	void storeFace(Face face) throws DataAccessException;

}
