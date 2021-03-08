package dev.fun.taskz.entities;

public interface PreRemovable<E> {
	
	void clearRefs(E entity);
	
}
