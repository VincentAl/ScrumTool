package hei.gl.scrumtool.web.controller;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.ColonneStory;
import hei.gl.scrumtool.core.service.StoryService;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	private final static Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Inject
	private StoryService storyService;

	@ResponseBody
	@RequestMapping(value = "/story/{id}", method = RequestMethod.DELETE)
	public String deleteOneStory(@PathVariable("id") long id) {
		storyService.delete(id);
		return "OK";
	}
	
	@ResponseBody    
	@RequestMapping(value = "/story/{id}/column/{id_col}", method = RequestMethod.PUT)
	public String updateOneStory(@PathVariable("id") long id, @PathVariable("id_col") long idCol) {
		Story story = storyService.findById(id);
		
		for (ColonneStory col : ColonneStory.values()) {
			if(col.getId() == idCol)
				story.setCategory(col);
		}
		
		storyService.update(story);
		return "OK";
	}

}
