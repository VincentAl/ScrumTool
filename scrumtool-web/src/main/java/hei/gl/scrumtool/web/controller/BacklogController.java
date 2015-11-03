package hei.gl.scrumtool.web.controller;


import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	private final static Logger logger = LoggerFactory.getLogger(BacklogController.class);
	
	//@Inject
	private SprintService sprintService;
	
	@Inject
	private StoryService storyService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(ModelMap model){
		
		Map<String, Set<Story>> listStories = new HashMap();
		listStories.put("idea", storyService.findByCategorie(Story.IDEA));
		listStories.put("confirmed", storyService.findByCategorie(Story.CONFIRMED));
		listStories.put("next_sprint", storyService.findByCategorie(Story.NEXT_SPRINT));
		
		Map<Integer, String> categories = new HashMap();
		categories.put(1, "Idea");
		categories.put(2, "Confirmed");
		categories.put(3, "Next sprint");
		
		model.addAllAttributes(listStories);
		model.addAttribute("categories", categories);
		model.put("story", new Story());
		return "home";
	}
	
	@RequestMapping(value="/new-story", method=RequestMethod.POST)
	public String submitForm(@ModelAttribute("story") Story story){
		
		storyService.create(story);
		return "redirect:/";
	}
	
}
