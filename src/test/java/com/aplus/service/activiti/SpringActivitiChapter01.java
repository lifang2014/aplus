package com.aplus.service.activiti;

import com.aplus.entity.MenuEntity;
import com.aplus.service.BaseTest;
import com.aplus.utils.DateUtils;
import junit.framework.Assert;
import net.sf.cglib.core.CollectionUtils;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.core.Transformer;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lifang on 2015/5/31.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringActivitiChapter01 extends BaseTest{

    @Autowired
    public RepositoryService repositoryService;
    @Autowired
    public FormService formService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private TaskService taskService;

    @Test
//    @Deployment(resources = "bpmn/chapter03.bpmn")
    public void testDeployProcess(){
        logger.info("deployment Process....");
//        InputStream in = getClass().getClassLoader().getResourceAsStream("activiti/leave-dynamic-from.zip");
//        repositoryService.createDeployment().addZipInputStream(new ZipInputStream(in)).deploy();
//
        identityService.setAuthenticatedUserId("admin");

        logger.info("get process definition....");
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("leave-dynamic-from").singleResult();

        Assert.assertNotNull("process definition is null", processDefinition);

        logger.info("process definition id : {}", processDefinition.getId());
        logger.info("process definition key : {}", processDefinition.getKey());
        logger.info("process definition name : {}", processDefinition.getName());

        String startDate = DateUtils.getStrDate(new Date());
        String endDate = DateUtils.getStrDate(DateUtils.getDate(new Date(), 3));
        logger.info("start date : {}", startDate);
        logger.info("end date : {}", endDate);

        Map<String, String> variables = new HashMap<String, String>();
        variables.put("startDate", startDate);
        variables.put("endDate", endDate);
        variables.put("reason", "生病,请假两天");

        ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), variables);

        Assert.assertNotNull("process instance is null", processInstance);

        logger.info("process instance name : {}", processInstance.getName());


        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();

        if(!tasks.isEmpty()){
            logger.info("There are currently no task");
        }

        for(Task task : tasks){
            logger.info("task name : {}", task.getName());
            variables = new HashMap<String, String>();
            variables.put("deptLeaderPass", "true");
            formService.submitTaskFormData(task.getId(), variables);
        }

        List<Task> hrTask = taskService.createTaskQuery().taskCandidateGroup("hr").list();
        for(Task task : hrTask){
            logger.info("task name : {}", task.getName());
            variables = new HashMap<String, String>();
            variables.put("hrPass", "true");
            formService.submitTaskFormData(task.getId(), variables);
        }

        List<Task> assignedTasks = taskService.createTaskQuery().taskCandidateOrAssigned("admin").list();

        for(Task task : assignedTasks){
            task.getProcessInstanceId();
            logger.info("task name : {}", task.getName());
            logger.info("task id : {}", task.getId());
            logger.info("task key : {}", task.getProcessDefinitionId());
            variables = new HashMap<String, String>();
            variables.put("reportBackDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            formService.submitTaskFormData(task.getId(), variables);
        }

    }


    @Test
    public void testCollFilter(){
        List<MenuEntity> menuEntities = new LinkedList<MenuEntity>();
        for(int i = 0; i < 100; i++){
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setName("menu:" + i);
            menuEntities.add(menuEntity);
        }

        Collection<MenuEntity> menuEntities1 = CollectionUtils.filter(menuEntities, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                MenuEntity menuEntity = (MenuEntity)o;
                return menuEntity.getName().indexOf("2") != -1;
            }
        });
        logger.info("coll size : {}", menuEntities1.size());

        List<String> strLists = new LinkedList<String>();
        strLists = CollectionUtils.transform(menuEntities1, new Transformer() {
            @Override
            public Object transform(Object o) {
                MenuEntity menuEntity = (MenuEntity)o;
                return menuEntity.getName();
            }
        });
        logger.info("str size : {}", menuEntities1.size());
        for(String str : strLists){
            logger.info("menu name : {}", str);
        }
    }

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Test
    public void destory(){
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processEngine.close();
    }





}
