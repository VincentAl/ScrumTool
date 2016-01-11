package hei.gl.scrumtool.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.mapping.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.entity.User;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.enumeration.StoryPoint;
import hei.gl.scrumtool.core.enumeration.TaskColumn;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;
import hei.gl.scrumtool.core.service.TaskService;
import hei.gl.scrumtool.core.service.UserService;
import hei.gl.scrumtool.core.service.impl.SprintServiceImpl;

@Controller
public class SprintController {
private final static Logger logger = LoggerFactory.getLogger(BacklogController.class);
	
	@Inject
	private SprintService sprintService;
	
	@Inject
	private StoryService storyService;
	
	@Inject
	private TaskService taskService;
	
	@Inject
	private UserService UserService;
	 
	@RequestMapping(value="/sprint", method=RequestMethod.GET)
	public String homeSprint(ModelMap model){

		Sprint currentSprint = sprintService.findCurrentSprint();
		List<Story> storyList = storyService.findBySprint(currentSprint);
		List<Task> taskList = new ArrayList<>();
		
		List<User> userList = UserService.findAll();
		
		Map<String, Map<String, User>> usersList = new HashMap();	
		Map<String, User> userMap = new HashMap();
		for (User user :userList) {
		userMap.put(String.valueOf(user.getId()), user);
		usersList.put("usersList", userMap);
		}
				
		for (Story story : storyList) {
			taskList.addAll(taskService.findByStory(story.getId()));
		}
		
		// Location of the stories
		//horizontal priority
		Map<String, Map<Integer, Story>> storiesList = new HashMap();
		for (StoryColumn storyColumn : StoryColumn.values()) {
			Map<Integer, Story> storyMap = new HashMap();
				for (Story story : storyList ) {
					if(story.getCategory().equals(storyColumn)){
						storyMap.put(story.getPriority(), story);
					}
				}

			List<Integer> keys = new LinkedList<Integer>(storyMap.keySet());
	        Collections.sort(keys);
	     
	        //LinkedHashMap will keep the keys in the order they are inserted
	        //which is currently sorted on natural ordering
	        Map<Integer,Story> sortedMap = new LinkedHashMap<Integer,Story>();
		        for(Integer key: keys){
		            sortedMap.put(key, storyMap.get(key));
		        }
			storiesList.put(storyColumn.toString()+"_story", sortedMap);
		}
		
		// Location of the stories
		//horizontal priority
		Map<String, Map<Integer, Task>> tasksList = new HashMap();
		for (TaskColumn taskColumn : TaskColumn.values()) {
			Map<Integer, Task> taskMap = new HashMap();
				for (Task task : taskList ) {
					if(task.getCategory().equals(taskColumn)){
						taskMap.put(task.getPriority(), task);
					}
				}

			List<Integer> keys = new LinkedList<Integer>(taskMap.keySet());
	        Collections.sort(keys);
	     
	        //LinkedHashMap will keep the keys in the order they are inserted
	        //which is currently sorted on natural ordering
	        Map<Integer,Task> sortedMapTask = new LinkedHashMap<Integer,Task>();
		        for(Integer key: keys){
		            sortedMapTask.put(key, taskMap.get(key));
		        }
			tasksList.put(taskColumn.toString()+"_task", sortedMapTask);
		}
		
		
		model.addAllAttributes(storiesList);
		model.addAllAttributes(tasksList);
		model.addAllAttributes(usersList);
		model.put("task", new Task());
		return "sprint";
	}
	
	@RequestMapping(value="/close-sprint", method=RequestMethod.GET)
	public String closeSprint(ModelMap model){
		if( sprintService.areAllSprintClosed()){
			((SprintServiceImpl) sprintService).setMessageHelper("No Sprint to close, you can create a new one from the Backlog");
			((SprintServiceImpl) sprintService).setMessageHelperType("danger");
		}else{
			long numSprint = sprintService.findLastSprint().getNumber();
			((SprintServiceImpl) sprintService).setMessageHelper("The Sprint "+ numSprint +" is now closed, it can be found in the archive tab.");
			((SprintServiceImpl) sprintService).setMessageHelperType("success");
			
			
			Sprint sprint=sprintService.findCurrentSprint();
			for(Story story : storyService.findBySprint(sprint)){
				 if(story.getCategory()!=StoryColumn.DONE){
					story.setCategory(StoryColumn.NEXT_SPRINT);
					storyService.update(story);
				 }
			}
			sprintService.closeCurrentSprint();
		}
		
		return "redirect:/home";
	}
}
