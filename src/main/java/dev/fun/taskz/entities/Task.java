package dev.fun.taskz.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
	
	public enum Status {
		TODO, DOING, DONE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Task parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<Task> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Task getParent() {
		return parent;
	}
	
	public void setParent(Task parent) {
		this.parent = parent;
	}
	
	public List<Task> getChildren() {
		return children;
	}
	
	public void setChildren(List<Task> children) {
		this.children = children;
	}
	
	public Task(String content, Status status, Project project) {
		this.content = content;
		this.status = status;
		this.project = project;
	}

	public Task() {
		
	}
	
	@Override
	public String toString() {
		return 
				String.format("[Task] id: %d, parent_id: %d, status: %s, content: %s, user: %s",
			id, (parent == null) ? null : parent.getId(), status.name(), content, (user == null) ? null : user.toString());
	}
	
}
