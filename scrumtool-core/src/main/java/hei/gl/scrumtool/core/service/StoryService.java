package hei.gl.scrumtool.core.service;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.enumeration.StoryPoint;

import java.util.List;
import java.util.Set;

public interface StoryService {
	
	
	public Story findById(long idStory);
	
	public List<Story> findALL();
	
	public void move(long idStory, StoryColumn category);
	public void move(long idStory, StoryColumn category, int priority);
	public void move(long idStory, long idOldSprint, StoryColumn category);//pour permettre d'enlever une story du sprint en cours
	
	public void delete(long idStory);
	
	public Story create(Story story);
	
	public Set<Story> findByCategory(StoryColumn category);
	
	public Story update(Story story);
	
}
