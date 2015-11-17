package hei.gl.scrumtool.web.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.View;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.service.StoryService;

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AjaxController {
	private final static Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Inject
	private StoryService storyService;

	@ResponseBody
	@RequestMapping(value = "/story/{id}", method = RequestMethod.DELETE)
	public String deleteOneStory(@PathVariable("id") long id) {
		storyService.delete(id);
		return "{}";
	}

	@ResponseBody
	@RequestMapping(value = "/story/{id}", method = RequestMethod.PATCH)
	public String updateStory(@PathVariable("id") long id ,@RequestBody Story story) {
		Story oldStory = storyService.findById(id);
		// pour ne pas écraser les anciennes valeurs à null
		story.setCategory(oldStory.getCategory());
		story.setPriority(oldStory.getPriority());
		story.setSprint(oldStory.getSprint());
		story.setTasksList(oldStory.getTasksList());
		// update la story avec les nouvelles valeurs
		storyService.update(story);
		return "{}";
	}

	@ResponseBody
	@JsonView(View.Summary.class)
	@RequestMapping(value = "/story/{id}", method = RequestMethod.GET)
	public Story getOneStory(@PathVariable("id") long id) {
		return storyService.findById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/story/{id}/column/{id_col}/previous/{id_previous}", method = RequestMethod.PUT)
	public String updateOneStory(@PathVariable("id") long id, @PathVariable("id_col") long idCol, @PathVariable("id_previous") long idPrev) {
		
		
		if(idPrev == -1){
			storyService.move(id, StoryColumn.getStoryColumnById(idCol));
		}else{
			storyService.move(id, StoryColumn.getStoryColumnById(idCol), 
					storyService.findById(idPrev).getPriority());
		}
		return "{}";
	}

}
