package hei.gl.scrumtool.core.service;


import java.util.List;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.enumeration.TaskColumn;

public interface TaskService {
	
	public Task findByID(long idTask);
	public List<Task> findAll();	
	public List<Task> findByStory(long idStory);
	public List<Task> findByStory(Story story);
	
	public void changeState(long idTask, TaskColumn category);
	
	public Task create(Task task);
	public Task update(Task task);
	public void delete(Task task);
	
}
