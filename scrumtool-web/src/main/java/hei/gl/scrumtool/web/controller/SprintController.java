package hei.gl.scrumtool.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.enumeration.StoryPoint;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;
import hei.gl.scrumtool.core.service.TaskService;
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
	
	@RequestMapping(value="/sprint", method=RequestMethod.GET)
	public String homeSprint(ModelMap model){

		Sprint currentSprint = sprintService.findCurrentSprint();
		List<Story> stroryList = storyService.findBySprint(currentSprint);
		List<Task> taskList = new ArrayList<>();
		
		for (Story story : stroryList) {
			taskList.addAll(taskService.findByStory(story.getId()));
		}
		
			// Location of the stories
			//horizontal priority
			Map<String, Map<Integer, Story>> storyList = new HashMap();
			for (StoryColumn storyColumn : StoryColumn.values()) {
				Map<Integer, Story> storyMap = new HashMap();
					for (Story story : stroryList ) {
						if(story.getCategory().equals(storyColumn)){
							System.out.println("8=========================D");
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
				storyList.put(storyColumn.toString(), sortedMap);
			}
		/*
		Map<StoryColumn, String> categories = new HashMap();
		for (StoryColumn storyColumn : StoryColumn.values()) {
			categories.put(storyColumn, storyColumn.getLabel());
		}

		Map<StoryPoint, String> storyPoints = new HashMap();
		for (StoryPoint storyPoint : StoryPoint.values()) {
			storyPoints.put(storyPoint, storyPoint.getLabel());
		}
		*/
			
		//vertical priority
		Comparator<StoryPoint> sort = new Comparator<StoryPoint>() 
		{
			@Override
			public int compare(StoryPoint arg0, StoryPoint arg1) {
				if(arg0.getId() > arg1.getId()){
					return 1;
				}else if(arg0.getId() < arg1.getId()){
					return -1;
				}else{
					return 0;
				}
			}
		};
		
		model.addAllAttributes(storyList);
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
			sprintService.closeCurrentSprint();
		}
		return "redirect:/home";
		
	}
}
