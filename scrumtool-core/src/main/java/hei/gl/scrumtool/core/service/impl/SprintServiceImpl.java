package hei.gl.scrumtool.core.service.impl;

import hei.gl.scrumtool.core.dao.SprintDAO;
import hei.gl.scrumtool.core.dao.StoryDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.service.SprintService;

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
	StoryDAO storyDAO;

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

	}

	@Override
	public void ajouterStory(Story story, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addStory(story);
	}

	@Override
	public void ajouterStory(List<Story> Stories, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addListStories(Stories);
	}

	@Override
	public void enleverStory(long idStory, long idSprint) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Sprint sprint) {
		sprintDAO.save(sprint);

	}

	@Override
	public void sprimerSprint(long idSprint) {
		sprintDAO.delete(idSprint);

	}

}
