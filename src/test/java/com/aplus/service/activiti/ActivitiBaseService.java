package com.aplus.service.activiti;

import com.aplus.service.BaseTest;
import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by lifang on 2015/7/5.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class ActivitiBaseService extends BaseTest{

    @Autowired
    public IdentityService identityService;
    @Autowired
    public TaskService taskService;
    @Autowired
    public RepositoryService repositoryService;

    public User addUser(String id, String firstName, String lastName, String email, String password){
        User user = identityService.newUser(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        identityService.saveUser(user);
        return identityService.createUserQuery().userId(id).singleResult();
    }

    public Group addGroup(String id, String name, String type){
        Group group = identityService.newGroup(id);
        group.setName(name);
        group.setType(type);
        return identityService.createGroupQuery().groupId(id).singleResult();
    }

    public Task addTask(String id, String name){
        Task task = taskService.newTask(id);
        task.setName(name);
        taskService.saveTask(task);
        return taskService.createTaskQuery().taskId(id).singleResult();
    }

}
