package hei.gl.scrumtool.core.service.impl;

import hei.gl.scrumtool.core.dao.SprintDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

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
	public void addStory(long idStory, long idSprint) {
		Story story = storyService.findById(idStory);
		addStory(story, idSprint);
	}

	@Override
	public void addStory(Story story, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addStory(story);
		update(sprint);
	}

	@Override
	public void addStory(List<Story> stories, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addStoryList(stories);
		update(sprint);
	}

	@Override
	public void removeStory(long idStory, long idSprint) {
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
	public void delete(long idSprint) {
		sprintDAO.delete(idSprint);

	}

}
