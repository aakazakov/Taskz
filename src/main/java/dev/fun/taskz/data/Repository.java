package dev.fun.taskz.data;

import java.util.List;

import org.hibernate.Session;

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
	
	public E getEager(String queryName, Long id) {
		Session session = SessionMaster.sessionFactory.openSession();
		E entity = session
				.createNamedQuery(queryName, entityClass)
				.setParameter(1, id)
				.getSingleResult();
		session.close();
		return entity;
	}
	
	public void delete(Long id) {
		Session session = SessionMaster.sessionFactory.openSession();
		session.beginTransaction();
//		session
//			.createQuery("DELETE " + entityClass.getSimpleName() + " WHERE id=?1")
//			.setParameter(1, id)
//			.executeUpdate();
		session.delete(session.load(entityClass, id));
		session.getTransaction().commit();
		session.close();
	}
	
}
