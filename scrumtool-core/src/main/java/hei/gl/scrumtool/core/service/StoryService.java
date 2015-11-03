package hei.gl.scrumtool.core.service;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.ColonneStory;

import java.util.List;
import java.util.Set;

public interface StoryService {
	
	
	Story findById(long idStory);
	//TODO List<Story> FindByState(ETAT);//pour recuperer une colone de story
	List<Story> findALL();
	
	//TODO void Deplacer(long idStory, Etat);
	//TODO void Deplacer(long idStory, idOldSprint,Etat);//pour permetre d'envelever une story du sprint en cour
	
	//TODO void save(Object.Story);
	void delete(long idStory);
	
	public Story create(Story story);
	
	public Set<Story> findByCategory(ColonneStory category);
	
	public Story update(Story story);
	
}
