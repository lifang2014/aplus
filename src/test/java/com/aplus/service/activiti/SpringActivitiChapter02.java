package com.aplus.service.activiti;

import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * Created by lifang on 2015/7/5.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringActivitiChapter02 extends ActivitiBaseService{

    @Test
    public void testRun() {

//        Task task = addTask("T0001", "任务1");
//        User user = addUser("AJ1001", "li", "ming", "liming@qq.com", "123456");
//        taskService.setOwner(task.getId(), user.getId());
//
//        List<Task> taskList = taskService.createTaskQuery().taskOwner(user.getId()).list();
//        for(Task t : taskList){
//            logger.info("task name is : {}",t.getName());
//        }
    }
}
