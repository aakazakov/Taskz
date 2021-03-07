package dev.fun.taskz.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "users")
	private List<Project> projects;
	
	@OneToMany(mappedBy = "user")
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public User(String name) {
		this.name = name;
	}
	
	public User() {
		
	}
	
	@Override
	public String toString() {
		return String.format("[User] id: %d, name: %s", id, name);
	}
	
}
