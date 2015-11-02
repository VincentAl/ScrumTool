package hei.gl.scrumtool.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.Client;

import hei.gl.scrumtool.core.dao.SprintDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;

@Named
@Transactional
public class SprintServiceImpl implements SprintService {
	
	@Inject
	private SprintDAO sprintDAO;
	@Inject
	private StoryService storyService;

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
		Sprint sprint=this.findById(idSprint);
		sprint.addStory(storyService.FindById(idStory));
		this.save(sprint);
	}

	@Override
	public void ajouterStory(Story story, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addStory(story);
		this.save(sprint);
	}

	@Override
	public void ajouterStory(List<Story> Stories, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addListStories(Stories);
		this.save(sprint);
	}

	@Override
	public void enleverStory(long idStory, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.removeStory(idStory);
		this.save(sprint);
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
