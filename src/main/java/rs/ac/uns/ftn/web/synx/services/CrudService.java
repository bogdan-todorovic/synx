package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

public interface CrudService<T,U> {
	
	/*
	 * Find and return entity with passed id
	 * Return entity or null if nothing has found
	 */
	T findOne(U id);
	
	/*
	 * Return all entities or empty list
	 */
	List<T> findAll();
	
	/*
	 * Save passed entity and return saved instance
	 * Return the saved entity
	 */
	T create(T entity);
	
	/*
	 * Remove entity with passed id
	 * Throw an error if the entity doesn't exist
	 */
	void remove(U id);
}
