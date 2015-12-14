package hei.gl.scrumtool.core.service;

import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;

import java.util.List;

public interface SprintService {
	
	
	Sprint findById(long idSprint);
	List<Sprint> findAll();
	
	void addStory(long idStory, long idSprint);
	void addStory(Story story, long idSpring);
	void addStory(List<Story> stories, long idSprint);
	
	void removeStory(long idStory, long idSprint);
	
	public Sprint create(Sprint sprint);
	public Sprint update(Sprint sprint);
	void delete(long idSprint);
	
	public Sprint findCurrentSprint();
	public boolean areAllSprintClosed();
	public Sprint findLastSprint();
	public void closeCurrentSprint();
	
}
