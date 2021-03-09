package dev.fun.taskz.entities;

public interface PreRemovable<E> {
	
	/**
	 * Clears references that prevent the entity from being deleted.
	 * @param entity entity to be deleted
	 */
	void clearRefs(E entity);
	
}
