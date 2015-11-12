package hei.gl.scrumtool.core.service.impl;

import hei.gl.scrumtool.core.dao.SprintDAO;
import hei.gl.scrumtool.core.dao.StoryDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;


@Named
@Transactional
public class StoryServiceImpl implements StoryService{
	
	@Inject 
	private StoryDAO storyDAO;
	
	@Inject
	private SprintService sprintService; 

	@Override
	public Story findById(long idStory) {
		return storyDAO.findOne(idStory);
	}

	@Override
	public List<Story> findALL() {
		return storyDAO.findAll();
	}

	@Override
	public void delete(long idStory) {
		storyDAO.delete(idStory);
	}
	
	@Override
	public Story create(Story story){
		return storyDAO.save(story);
	}

	@Override
	public Set<Story> findByCategory(StoryColumn category) {
		Set<Story> stories = storyDAO.findByCategory(category);
		return storyDAO.findByCategory(category);
	}
	
	@Override
	public Story update(Story story){
		return storyDAO.save(story);
	}

	@Override
	public void move(long idStory, StoryColumn category) {
		Story story=this.findById(idStory);
		story.setCategory(category);
		update(story);
	}

	@Override
	public void move(long idStory, long idOldSprint, StoryColumn category) {
		Story story=this.findById(idStory);
		story.setCategory(category);
		sprintService.removeStory(idStory, idOldSprint);
		this.update(story);
	}

	
}
