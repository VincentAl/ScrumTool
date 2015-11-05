package hei.gl.scrumtool.core.service;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.ColonneStory;

import java.util.List;
import java.util.Set;

public interface StoryService {
	
	
	Story findById(long idStory);
	
	List<Story> findALL();
	
	void move(long idStory, ColonneStory category);
	void move(long idStory, long idOldSprint,ColonneStory category);//pour permetre d'envelever une story du sprint en cour
	
	void delete(long idStory);
	
	public Story create(Story story);
	
	public Set<Story> findByCategory(ColonneStory category);
	
	public Story update(Story story);
	
}
