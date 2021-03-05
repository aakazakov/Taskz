package dev.fun.taskz.management;

import java.util.List;

import dev.fun.taskz.data.Repository;
import dev.fun.taskz.entities.Project;
import dev.fun.taskz.entities.Task;
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
		return null;
	}
	
	public List<String> projectList() {
		return null;
	}
	
	public String projectInfo(Long id) {
		return null;
	}
	
	public void deleteProject(Long projectId) {
		
	}
	
	public Long createUser(String name) {
		return null;
	}
	
	public List<String> userList() {
		return null;
	}
	
	public String userInfo() {
		return null;
	}
	
	public void deleteUser(Long userId) {
		
	}
	
	public Long createTask(Long projectId, String content) {
		return null;
	}
	
	public List<String> taskList(Long projectId) {
		return null;
	}
	
	public String taskInfo(Long taskId) {
		return null;
	}
	
	public void deleteTask(Long taskId) {
		
	}
	
	public void assignUserOnProject(Long projectId, Long userId) {
		
	}
	
	public void assignTaskOnUser(Long userId, Long taskId) {
		
	}
	
	public List<String> userTasks(Long userId, Long projectId) {
		return null;
	}
	
}
