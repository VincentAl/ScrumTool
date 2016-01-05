package hei.gl.scrumtool.core.service;


import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.enumeration.TaskColumn;

import java.util.List;
import java.util.Set;

public interface TaskService {
	
	public Task findByID(long idTask);
	public List<Task> findAll();	
	public List<Task> findByStory(long idStory);
	public List<Task> findByStory(Story story);
	
	public Task save(Task task);
	public Task update(Task task);
	public void delete(long idTask);
	
	public Set<Task> findByCategory(TaskColumn column);
	
	public Integer findNewPriority();
	public void moveTask(Task task, TaskColumn newColumn, Integer newPriority);
	void moveTask(Task task, TaskColumn newColumn);
}
