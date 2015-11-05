package hei.gl.scrumtool.core.service.impl;

import hei.gl.scrumtool.core.dao.SprintDAO;
import hei.gl.scrumtool.core.dao.StoryDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.ColonneStory;
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
	StoryDAO storyDAO;
	
	@Inject
	SprintService sprintService; 

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
	public Set<Story> findByCategory(ColonneStory category) {
		return storyDAO.findByCategory(category);
	}
	
	@Override
	public Story update(Story story){
		return storyDAO.save(story);
	}

	@Override
	public void move(long idStory, ColonneStory category) {
		Story story=this.findById(idStory);
		story.setCategory(category);
		update(story);
	}

	@Override
	public void move(long idStory, long idOldSprint, ColonneStory category) {
		Story story=this.findById(idStory);
		story.setCategory(category);
		sprintService.enleverStory(idStory, idOldSprint);
		this.update(story);
	}

	
}
