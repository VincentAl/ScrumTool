package hei.gl.scrumtool.core.service;

import java.util.List;

import hei.gl.scrumtool.core.entity.Story;

public interface StoryService {
	
	
	Story FindById(long idStory);
	//TODO List<Story> FindByState(ETAT);//pour recuperer une colone de story
	List<Story> FindALL();
	
	//TODO void Deplacer(long idStory, Etat);
	//TODO void Deplacer(long idStory, idOldSprint,Etat);//pour permetre d'envelever une story du sprint en cour
	
	//TODO void save(Object.Story);
	void Supprimer(long idStory);
	
	public Story create(Story story);
	
}
