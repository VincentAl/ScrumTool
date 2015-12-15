package hei.gl.scrumtool.core.service.impl;

import hei.gl.scrumtool.core.dao.SprintDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

@Named
@Transactional
public class SprintServiceImpl implements SprintService {
	
	private String messageHelper = "";
	private String messageHelperType = "";
	
	
	
	@Inject
	private SprintDAO sprintDAO;

	@Inject
	private StoryService storyService ;

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
		story.setSprint(sprint);
		storyService.update(story);
	}

	@Override
	public void addStory(List<Story> stories, long idSprint) {
		Sprint sprint=this.findById(idSprint);
		sprint.addStoryList(stories);
		update(sprint);
		for (Story story : stories) {
			story.setSprint(sprint);
			storyService.update(story);
		}
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

	@Override
	public Sprint findCurrentSprint() {
		return sprintDAO.findByCurrentSprintTrue();
	}

	@Override
	public boolean areAllSprintClosed() {
		if(sprintDAO.findByCurrentSprintTrue() != null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public Sprint findLastSprint() {
		return sprintDAO.findLastSprint();
	}

	@Override
	public void closeCurrentSprint() {
		Sprint s = sprintDAO.findByCurrentSprintTrue();
		s.setCurrentSprint(false);
		sprintDAO.save(s);
	}
	
	public void clearMessageHelper(){
		this.messageHelper = "";
		this.messageHelperType = "";
	}

	public String getMessageHelper() {
		return messageHelper;
	}

	public void setMessageHelper(String messageHelper) {
		this.messageHelper = messageHelper;
	}

	public String getMessageHelperType() {
		return messageHelperType;
	}

	public void setMessageHelperType(String messageHelperType) {
		this.messageHelperType = messageHelperType;
	}

}
