package hei.gl.scrumtool.web.controller;


import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.enumeration.StoryPoint;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;
import hei.gl.scrumtool.core.service.TaskService;
import hei.gl.scrumtool.core.service.impl.SprintServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BacklogController {
	

	
	@Inject
	private SprintService sprintService;
	
	@Inject
	private StoryService storyService;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(ModelMap model){
		Map<String, Map<Integer, Story>> storyList = new HashMap();
		for (StoryColumn storyColumn : StoryColumn.values()) {
			Map<Integer, Story> storyMap = new HashMap();
			for (Story story : storyService.findByCategory(storyColumn)) {
				storyMap.put(story.getPriority(), story);
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
		
		Map<StoryColumn, String> categories = new HashMap();
		for (StoryColumn storyColumn : StoryColumn.values()) {
			categories.put(storyColumn, storyColumn.getLabel());
		}

		Map<StoryPoint, String> storyPoints = new HashMap();
		for (StoryPoint storyPoint : StoryPoint.values()) {
			storyPoints.put(storyPoint, storyPoint.getLabel());
		}
		
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
		
		Map<StoryPoint, String> sortedStoryPointsMap = new TreeMap<StoryPoint, String>(sort);
		sortedStoryPointsMap.putAll(storyPoints);

		model.addAllAttributes(storyList);
		model.addAttribute("messageHelper", ((SprintServiceImpl) sprintService).getMessageHelper());
		model.addAttribute("messageHelperType", ((SprintServiceImpl) sprintService).getMessageHelperType());
		model.addAttribute("categories", categories);
		model.addAttribute("storyPoints", sortedStoryPointsMap);
		model.put("story", new Story());
		return "home";
	}
	
	
	@RequestMapping(value="/new-story", method=RequestMethod.POST)
	public String submitForm(@ModelAttribute("story") Story story){
		((SprintServiceImpl) sprintService).clearMessageHelper();
		story.setPriority(storyService.findByCategory(story.getCategory()).size());
		storyService.create(story);
		return "redirect:/home";
	}

	//Request to create the new spring
	@RequestMapping(value="/launch-new-spring", method=RequestMethod.GET)
	public String launchNewSpring(){
		if(sprintService.areAllSprintClosed()){
			Sprint newSprint = new Sprint();
			if(sprintService.findLastSprint() != null){
				newSprint.setNumber(sprintService.findLastSprint().getNumber()+1);
			}
			
			else{
				newSprint.setNumber(Long.valueOf(1));
			}
			
				newSprint.setNumber(sprintService.findLastSprint().getNumber()+1);
			}else{
				newSprint.setNumber(Long.valueOf(1));
			}
			
			newSprint.setCurrentSprint(true);
			newSprint = sprintService.create(newSprint);
			
			Set<Story> storyListNextSpring = new HashSet<>();
			storyListNextSpring = storyService.findByCategory(StoryColumn.NEXT_SPRINT);
			
			for (Story story : storyListNextSpring) {
				story.setCategory(StoryColumn.TODO);
				story.setSprint(newSprint);
				storyService.create(story);
			}
			((SprintServiceImpl) sprintService).clearMessageHelper();
			return "redirect:/sprint";
		}else{
			long numSprint = sprintService.findLastSprint().getNumber();
			((SprintServiceImpl) sprintService).setMessageHelper("Please, close the Sprint "+ numSprint +" before creating a new one. :)");
			((SprintServiceImpl) sprintService).setMessageHelperType("danger");
			return "redirect:/home";
		}
	}
}
