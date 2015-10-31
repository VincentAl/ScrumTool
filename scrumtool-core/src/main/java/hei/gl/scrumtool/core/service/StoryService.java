package hei.gl.scrumtool.core.service;

public interface StoryService {
	
	//TODO void creeStory(object.Strory);
	
	//TODO object.Story FindById(long idStory);
	//TODO List<object.Story> FindByState(ETAT);//pour recuperer une colone de story
	//TODO List<Object.Story> FindALL();
	
	
	//TODO void Deplacer(long idStory, Etat);
	//TODO void Deplacer(long idStory, idOldSprint,Etat);//pour permetre d'envelever une story du sprint en cour
	
	//TODO void save(Object.Story);
	void Supprimer(long idStory);
}
