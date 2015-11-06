package hei.gl.scrumtool.core.service;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.StoryColumn;

import java.util.List;
import java.util.Set;

public interface StoryService {
	
	
	Story findById(long idStory);
	
	List<Story> findALL();
	
	void move(long idStory, StoryColumn category);
	void move(long idStory, long idOldSprint, StoryColumn category);//pour permetre d'envelever une story du sprint en cour
	
	void delete(long idStory);
	
	public Story create(Story story);
	
	public Set<Story> findByCategory(StoryColumn category);
	
	public Story update(Story story);
	
}
