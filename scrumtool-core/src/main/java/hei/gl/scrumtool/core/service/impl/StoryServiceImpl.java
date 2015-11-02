package hei.gl.scrumtool.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import hei.gl.scrumtool.core.dao.StoryDAO;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.service.StoryService;

@Named
@Transactional
public class StoryServiceImpl implements StoryService {
	
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
	public void save(Story story) {
		storyDAO.save(story);
	}

	@Override
	public void Supprimer(long idStory) {
		storyDAO.delete(idStory);
	}

}
