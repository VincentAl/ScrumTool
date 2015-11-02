package hei.gl.scrumtool.core.service;

import java.util.List;

import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;

public interface SprintService {
	
	
	Sprint findById(long idSprint);
	List<Sprint> findAll();
	
	void ajouterStory(long idStory, long idSprint);
	void ajouterStory(Story story, long idSpring);
	void ajouterStory(List<Story> Stories, long idSprint);
	
	void enleverStory(long idStory, long idSprint);
	
	void save(Sprint sprint);
	void sprimerSprint(long idSprint);
}
