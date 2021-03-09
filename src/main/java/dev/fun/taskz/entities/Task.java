package dev.fun.taskz.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@NamedQuery(name = "children_eager", query = "SELECT t FROM Task t LEFT JOIN FETCH t.children WHERE t.id=?1")
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
	
	private LocalDateTime deadline;

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
	
	public LocalDateTime getDeadline() {
		return deadline;
	}
	
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	
	public Task(String content, LocalDateTime deadline, Project project) {
		this.content = content;
		this.deadline = deadline;
		this.project = project;
		this.status = Status.TODO;
	}

	public Task() {
		
	}
	
	@Override
	public String toString() {
		return 
				String.format("[Task] id: %d, parent_id: %d, status: %s, deadline: %s content: %s, user: %s",
			id, 
			(parent == null) ? null : parent.getId(), 
			status.name(), 
			deadline, 
			content,
			(user == null) ? null : user.toString());
	}
	
}
