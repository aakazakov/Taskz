package dev.fun.taskz.entities;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "projects")
@NamedQuery(name = "users_eager", query = "SELECT p FROM Project p LEFT JOIN FETCH p.users WHERE p.id=?1")
@NamedQuery(name = "tasks_eager", query = "SELECT p FROM Project p JOIN FETCH p.tasks WHERE p.id=?1")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@ManyToMany
	@JoinTable(
			name = "users_projects",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
		)
	private List<User> users;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public void ListId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Project(String title) {
		this.title = title;
	}
	
	public Project() {
		
	}
	
}
