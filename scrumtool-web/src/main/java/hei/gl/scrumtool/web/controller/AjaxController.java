package hei.gl.scrumtool.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.View;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.service.StoryService;

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
	@JsonView(View.Summary.class)
	@RequestMapping(value = "/story/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Story getOneStory(@PathVariable("id") long id) {
		return storyService.findById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/story/{id}/column/{id_col}", method = RequestMethod.PUT)
	public String updateOneStory(@PathVariable("id") long id, @PathVariable("id_col") long idCol) {
		Story story = storyService.findById(id);

		for (StoryColumn col : StoryColumn.values()) {
			if (col.getId() == idCol)
				story.setCategory(col);
		}

		storyService.update(story);
		return "OK";
	}

}
