package hei.gl.scrumtool.web.controller;


import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.ColonneStory;
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
		for (ColonneStory colonneStory : ColonneStory.values()) {
			listStories.put(colonneStory.getNom(), storyService.findByCategorie(colonneStory));
		}

		Map<ColonneStory, String> categories = new HashMap();
		for (ColonneStory colonneStory : ColonneStory.values()) {
			categories.put(colonneStory, colonneStory.getNom());
		}
		
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
	
	// @responseBody pour renvoyer du contenu brut 
	
}
