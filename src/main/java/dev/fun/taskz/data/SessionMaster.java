package dev.fun.taskz.data;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dev.fun.taskz.entities.Project;
import dev.fun.taskz.entities.Task;
import dev.fun.taskz.entities.User;

public final class SessionMaster {
	
	public static final SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration()
				.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
				.setProperty("hibernate.connection.driver_class", "org.h2.Driver")
				.setProperty("hibernate.connection.url", "jdbc:h2:mem:test")
				.setProperty("hibernate.connection.username", "sa")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.show_sql", "false")
				.setProperty("hibernate.hbm2ddl.auto", "create-drop")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Project.class)
				.addAnnotatedClass(Task.class)
				.buildSessionFactory();
	}
	
	private SessionMaster () {
		
	}
	
}
