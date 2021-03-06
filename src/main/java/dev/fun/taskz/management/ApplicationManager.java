package dev.fun.taskz.management;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import dev.fun.taskz.data.Repository;
import dev.fun.taskz.entities.Project;
import dev.fun.taskz.entities.Task;
import dev.fun.taskz.entities.Task.Status;
import dev.fun.taskz.entities.User;

public class ApplicationManager {
	
	private final Repository<Project> projectRepository;
	private final Repository<User> userRepository;
	private final Repository<Task> taskRepository;
	
	public ApplicationManager() {
		this.projectRepository = new Repository<>(Project.class);
		this.userRepository = new Repository<>(User.class);
		this.taskRepository = new Repository<>(Task.class);
	}
	
	public Long createProject(String title) {
		return projectRepository.create(new Project(title));
	}
	
	public List<Project> projectList() {
		return projectRepository.getAll();
	}
	
	public Project getProject(Long id) {
		return projectRepository.get(id);
	}
	
	public void deleteProject(Long projectId) {
		projectRepository.delete(projectId);
	}
	
	public Long createUser(String name) {
		return userRepository.create(new User(name));
	}
	
	public List<User> userList() {
		return userRepository.getAll();
	}
	
	public User getUser(Long userId) {
		return userRepository.get(userId);
	}
	
	public void deleteUser(Long userId) {
		userRepository.delete(userId);
	}
	
	public Long createTask(Long projectId, String deadline, String content) {
		return taskRepository.create(new Task(content, LocalDateTime.parse(deadline), projectRepository.get(projectId)));
	}
	
	public Long createSubtask(Long parentId, String deadline, String content) {
		Task parent = taskRepository.get(parentId);
		Task subtask = new Task(content, LocalDateTime.parse(deadline), parent.getProject());
		subtask.setParent(parent);
		subtask.setUser(parent.getUser());
		return taskRepository.create(subtask);
	}
	
	public List<Task> taskList(Long projectId) {
		return projectRepository.getEager("tasks_eager", projectId).getTasks();
	}
	
	public Task getTask(Long taskId) {
		return taskRepository.get(taskId);
	}
	
	public void deleteTask(Long taskId) {
		taskRepository.delete(taskId);
	}
	
	public Long getSumOfRemainingTime(Long taskId) {
		Task task = taskRepository.getEager("children_eager",taskId);
		Long sum = task
				.getChildren()
				.stream()
				.map(Task::getDeadline)
				.mapToLong(this::getTimeBeforeDeadline)
				.sum();
		sum += getTimeBeforeDeadline(task.getDeadline());
		return sum;
	}
	
	private long getTimeBeforeDeadline(LocalDateTime deadline) {
		return Duration.between(LocalDateTime.now(), deadline).toHours();
	}
	
	public void assignUserOnProject(Long projectId, Long userId) {
		Project p = projectRepository.getEager("users_eager", projectId);
		p.getUsers().add(userRepository.get(userId));
		projectRepository.update(p);
	}
	
	public void assignTaskOnUser(Long userId, Long taskId) {
		Task t = taskRepository.get(taskId);
		t.setUser(userRepository.get(userId));
		taskRepository.update(t);
	}
	
	public void setTaskStatus(Long taskId, Status status) {
		Task t = taskRepository.get(taskId);
		t.setStatus(status);
		taskRepository.update(t);
	}
	
	/**
	 * Returns user tasks in the specified project
	 * @param userId user ID
	 * @param projectId project ID
	 * @return {@code List} of {@link Task}
	 */
	public List<Task> userTasks(Long userId, Long projectId) {
		return projectRepository
				.getEager("tasks_eager", projectId)
				.getTasks()
				.stream()
				.filter(t -> t.getUser() != null && t.getUser().getId().equals(userId))
				.collect(Collectors.toList());
	}
	
}
