package hei.gl.scrumtool.core.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import hei.gl.scrumtool.core.dao.SprintDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;

@Named
@Transactional
public class SprintServiceImpl implements SprintService {
	
	@Inject
	SprintDAO sprintDAO;

	@Inject
	StoryService storyService ;

	@Override
	public Sprint findById(long idSprint) {
		return sprintDAO.findOne(idSprint);
	}

	@Override
	public List<Sprint> findAll() {
		return sprintDAO.findAll();
	}

	@Override
	public void ajouterStory(long idStory, long idSprint) {
		Story story = storyService.findById(idStory);
		ajouterStory(story, idSprint);
	}

	@Override
	public void ajouterStory(Story story, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addStory(story);
		update(sprint);
	}

	@Override
	public void ajouterStory(List<Story> Stories, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addListStories(Stories);
		update(sprint);
	}

	@Override
	public void enleverStory(long idStory, long idSprint) {
		Sprint sprint= this.findById(idSprint);
		sprint.removeStory(idStory);
		update(sprint);
	}
	
	@Override
	public Sprint create(Sprint sprint) {
		return sprintDAO.save(sprint);
	}

	@Override
	public Sprint update(Sprint sprint) {
		return sprintDAO.save(sprint);
	}

	@Override
	public void supprimerSprint(long idSprint) {
		sprintDAO.delete(idSprint);

	}

}
