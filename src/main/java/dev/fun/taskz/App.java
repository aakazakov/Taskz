package dev.fun.taskz;

import java.util.List;

import org.hibernate.Session;

import dev.fun.taskz.data.SessionMaster;
import dev.fun.taskz.entities.Task;
import dev.fun.taskz.management.Manager;

public class App {
	
    public static void main( String[] args ) {
        Manager m = new Manager();
        
        Long projId = m.createProject("proj");
        Long userId = m.createUser("user");
        Long taskId = m.createTask(projId, "task");
        Long taskId1 = m.createTask(projId, "task1");
        
        m.assignUserOnProject(projId, userId);
        m.assignTaskOnUser(userId, taskId);
        m.assignTaskOnUser(userId, taskId1);
        
        
        Session s = SessionMaster.sessionFactory.openSession();
        List<Task> list = s.createQuery("SELECT t FROM Task t JOIN User u ON u.id=1 JOIN Project p ON p.id=1", Task.class).getResultList();
        s.close();
        
        System.out.println(list);
        
    }
    
}
