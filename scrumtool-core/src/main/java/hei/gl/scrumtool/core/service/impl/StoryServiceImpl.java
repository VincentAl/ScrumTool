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
		return storyDAO.findByCategory(category);
	}
	
	@Override
	public Story update(Story story){
		return storyDAO.save(story);
	}

	@Override
	public void move(long idStory, StoryColumn category) {
		Story story=this.findById(idStory);
		
		// decrementation des priorites dans l'ancienne categorie
		Set<Story> storiesInOldCategory = this.findByCategory(story.getCategory());
		for (Story storyInOldCategory : storiesInOldCategory) {
		if (storyInOldCategory.getPriority()>=story.getPriority()){
				storyInOldCategory.setPriority(storyInOldCategory.getPriority()-1);
			}
		}
		
		//deplacement de la story
		story.setCategory(category);
		story.setPriority(this.findByCategory(category).size()+1);
		update(story);
	}
	

	@Override
	public void move(long idStory, StoryColumn category, int newPriority) {
		Story story = this.findById(idStory);
		
		// decrementation des priorites dans l'ancienne categorie
		Set<Story> storiesInOldCategory = this.findByCategory(story.getCategory());
		for (Story storyInOldCategory : storiesInOldCategory) {
			if (storyInOldCategory.getPriority()>=story.getPriority()){
				storyInOldCategory.setPriority(storyInOldCategory.getPriority()-1);
			}
		}
		
		//incrementation des priorites dans la nouvelle categorie
		Set<Story> storiesInNewCategory = this.findByCategory(category);
		for (Story storyInNewCategory : storiesInNewCategory) {
			if (storyInNewCategory.getPriority()>=newPriority) {
				storyInNewCategory.setPriority(storyInNewCategory.getPriority()+1);
			}
		}
		
		//deplacement de la story
		story.setCategory(category);
		update(story);
	}

	@Override
	public void move(long idStory, long idOldSprint, StoryColumn category) {
		Story story=this.findById(idStory);
		
		//deplacement de la story
		story.setCategory(category);
		story.setPriority(this.findByCategory(category).size()+1);
		sprintService.removeStory(idStory, idOldSprint);
		this.update(story);
	}

	
}
