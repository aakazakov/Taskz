package dev.fun.taskz;

import dev.fun.taskz.management.Manager;

public class App {
	
    public static void main( String[] args ) {
        Manager m = new Manager();
        
        Long projId = m.createProject("proj");
        Long userId = m.createUser("user");
        Long taskId = m.createTask(projId, "task");
        Long taskId1 = m.createTask(projId, "task1");
        
        m.assignUserOnProject(projId, userId);
        m.assignUserOnProject(projId, userId);
        m.assignTaskOnUser(userId, taskId);
        m.assignTaskOnUser(userId, taskId1);
        m.assignTaskOnUser(userId, taskId1);
        
        
        System.out.println(m.userTasks(userId, projId));
        
    }
    
}
