package hei.gl.scrumtool.core.service;

import java.util.List;

public interface SprintService {
	
	//TODO void cree(object.Sprint);
	
	//TODO Object.Sprint findById(long idSprint);
	//TODO List<Object.Sprint> findAll();
	
	void ajouterStory(long idStory, long idSprint);
	void ajouterStory(List<Long> idStories, long idSprint);
	//TODO void ajouterStory(object.Story, long idSpring);
	//TODO void ajouterStory(List<object.Story> Stories, long idSprint);
	
	void enleverStory(long idStory, long idSprint);
	
	//TODO void save(Object.Sprint);
	void sprimerSprint(long idSprint);
}
