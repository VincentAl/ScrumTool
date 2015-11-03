package hei.gl.scrumtool.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hei.gl.scrumtool.core.service.StoryService;

@Controller
public class AjaxController {
	private final static Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Inject
	private StoryService storyService;
	
	@RequestMapping(value="/story/{id}", method=RequestMethod.GET)
	public String deleteOneStory(@PathVariable("id") long id){
		storyService.supprimer(id);
		return "redirect:/";
	}
	

}
