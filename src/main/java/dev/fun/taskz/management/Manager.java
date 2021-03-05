package dev.fun.taskz.management;

import java.util.List;

import dev.fun.taskz.data.Repository;
import dev.fun.taskz.entities.Project;
import dev.fun.taskz.entities.Task;
import dev.fun.taskz.entities.Task.Status;
import dev.fun.taskz.entities.User;

/**
 * The Fat Manager ))
 */
public class Manager {
	
	private final Repository<Project> projectRepository;
	private final Repository<User> userRepository;
	private final Repository<Task> taskRepository;
	
	public Manager() {
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
	
	public Long createTask(Long projectId, String content) {
		return taskRepository.create(new Task(content, Status.TODO, projectRepository.get(projectId)));
	}
	
	public List<Task> taskList(Long projectId) {
		return projectRepository.get(projectId).getTasks();
	}
	
	public Task getTask(Long taskId) {
		return taskRepository.get(taskId);
	}
	
	public void deleteTask(Long taskId) {
		taskRepository.delete(taskId);
	}
	
	public void assignUserOnProject(Long projectId, Long userId) {
		
	}
	
	public void assignTaskOnUser(Long userId, Long taskId) {
		
	}
	
	public List<String> userTasks(Long userId, Long projectId) {
		return null;
	}
	
}
