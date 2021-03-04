package dev.fun.taskz.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public class EntityRepository<E, ID extends Serializable> {
	
	private final Class<E> entityClass;
	
	public EntityRepository(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void create(E entity) {
		Session session = SessionMaster.sessionFactory.openSession();
		session.beginTransaction();
		session.save(entity);
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
	
	public E get(ID id) {
		Session session = SessionMaster.sessionFactory.openSession();
		E entity = session.get(entityClass, id);
		session.close();
		return entity;
	}
	
	public void delete(E entity) {
		Session session = SessionMaster.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}
	
}