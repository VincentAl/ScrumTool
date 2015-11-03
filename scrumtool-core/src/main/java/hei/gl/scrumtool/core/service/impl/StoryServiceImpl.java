package hei.gl.scrumtool.core.service.impl;

import hei.gl.scrumtool.core.dao.StoryDAO;
import hei.gl.scrumtool.core.entity.Story;
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

	@Override
	public Story FindById(long idStory) {
		return storyDAO.findOne(idStory);
	}

	@Override
	public List<Story> FindALL() {
		return storyDAO.findAll();
	}

	@Override
	public void supprimer(long idStory) {
		storyDAO.delete(idStory);
	}
	
	@Override
	public Story create(Story story){
		return storyDAO.save(story);
	}

	@Override
	public Set<Story> findByCategorie(int categorie) {
		return storyDAO.findByCategorie(categorie);
	}

}
