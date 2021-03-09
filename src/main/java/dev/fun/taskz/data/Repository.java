package dev.fun.taskz.data;

import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.Session;

import dev.fun.taskz.entities.PreRemovable;

/**
 * Provides data access operations.
 * @param <E> the entity type
 */
//TODO: replace <E> with common Interface ??
public class Repository<E> {
	
	private final Class<E> entityClass;
	
	public Repository(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public Long create(E entity) {
		Session session = SessionMaster.sessionFactory.openSession();
		session.beginTransaction();
		Long id = (Long) session.save(entity);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public void update(E entity) {
		Session session = SessionMaster.sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<E> getAll() {
		String query = String.format("SELECT e FROM %s e", entityClass.getSimpleName());
		Session session = SessionMaster.sessionFactory.openSession();
		List<E> entityList = session.createQuery(query, entityClass).getResultList();
		session.close();
		return entityList;
	}
	
	public E get(Long id) {
		Session session = SessionMaster.sessionFactory.openSession();
		E entity = session.get(entityClass, id);
		session.close();
		return entity;
	}
	
	/**
	 * An alternative to {@link FetchType.EAGER}
	 * @param queryName a named query with {@code JOIN FETCH}
	 * @param id the entity ID
	 * @return entity or if there is no entity with this ID, an exception is thrown, see {@link org.hibernate.query.Query.getSingleResult()}
	 */
	public E getEager(String queryName, Long id) {
		Session session = SessionMaster.sessionFactory.openSession();
		E entity = session
				.createNamedQuery(queryName, entityClass)
				.setParameter(1, id)
				.getSingleResult();
		session.close();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public void delete(Long id) {
		Session session = SessionMaster.sessionFactory.openSession();
		session.beginTransaction();
		E entity = session.load(entityClass, id);
		if (entity instanceof PreRemovable) {
			((PreRemovable<E>) entity).clearRefs(entity);
		}
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}
	
}
