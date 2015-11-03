package hei.gl.scrumtool.core.service;

import hei.gl.scrumtool.core.entity.Story;

import java.util.List;
import java.util.Set;

public interface StoryService {
	
	
	Story FindById(long idStory);
	//TODO List<Story> FindByState(ETAT);//pour recuperer une colone de story
	List<Story> FindALL();
	
	//TODO void Deplacer(long idStory, Etat);
	//TODO void Deplacer(long idStory, idOldSprint,Etat);//pour permetre d'envelever une story du sprint en cour
	
	//TODO void save(Object.Story);
	void supprimer(long idStory);
	
	public Story create(Story story);
	
	public Set<Story> findByCategorie(int categorie);
	
}
