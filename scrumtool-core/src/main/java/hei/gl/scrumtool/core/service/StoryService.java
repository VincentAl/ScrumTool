package hei.gl.scrumtool.core.service;

import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
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
	public void changeState(long idStory, StoryColumn category);
	
	public void delete(long idStory);
	
	public Story create(Story story);
	
	public Set<Story> findByCategory(StoryColumn category);
	public List<Story> findBySprint(long idSprint);
	public List<Story> findBySprint(Sprint Sprint);
	
	public void addTask(long idTask, long idStory);
	public void addTask(Task task, long idStory);
	public void addTasks(List<Task> tasks, long idStory);
	
	public void removeTask(long idTask, long idStory);
	
	public Story update(Story story);
	
}
