package hei.gl.scrumtool.core.service.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import hei.gl.scrumtool.core.dao.TaskDAO;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.enumeration.TaskColumn;
import hei.gl.scrumtool.core.service.StoryService;
import hei.gl.scrumtool.core.service.TaskService;

@Named
@Transactional
public class TaskServiceImpl implements TaskService {

	private final static Logger log = LoggerFactory.getLogger(StoryServiceImpl.class);
	
	@Inject
	private TaskDAO taskDao;

	@Inject
	private StoryService storyService;

	@Override
	public Task findByID(long idTask) {
		return taskDao.findById(idTask);
	}

	@Override
	public List<Task> findAll() {
		return taskDao.findAll();
	}

	@Override
	public List<Task> findByStory(long idStory) {
		return taskDao.findByStory(storyService.findById(idStory));
	}

	@Override
	public List<Task> findByStory(Story story) {
		return taskDao.findByStory(story);
	}

	@Override
	public Task save(Task task) {
		return taskDao.save(task);
	}

	@Override
	public Task update(Task task) {
		return taskDao.save(task);
	}

	@Override
	public void delete(long idTask) {
		taskDao.delete(idTask);
	}
	
	public Integer findNewPriority(){
		return taskDao.findByLowestPriority().getPriority() + 1;
	}

	@Override
	public void moveTask(Task task, TaskColumn newColumn) {
		
		// decrementation des priorites dans l'ancienne categorie
		Set<Task> tasksInOldCategory = this.findByCategory(task.getCategory());
		for (Task taskInOldCategory : tasksInOldCategory) {
			if (taskInOldCategory.getPriority() > task.getPriority()) {
				//log.debug(taskInOldCategory.getTitle() + " passe de " + taskInOldCategory.getPriority() + " à " + (taskInOldCategory.getPriority() - 1));
				taskInOldCategory.setPriority(taskInOldCategory.getPriority() - 1);
				update(taskInOldCategory);
			}
		}

		// deplacement de la task
		if (task.getCategory()==newColumn){
			task.setPriority(this.findByCategory(newColumn).size()-1);
		}
		else {
			task.setPriority(this.findByCategory(newColumn).size());
		}
		
		//deplacement de la task
		task.setCategory(newColumn);
		update(task);
	}
	
	@Override
	public void moveTask(Task task, TaskColumn newColumn, Integer newPriority) {

		// si on fait un deplacement vers le bas (vers une priorite plus basse) dans une même categorie 
		if (task.getCategory() == newColumn && task.getPriority() < newPriority){ 
			// decrementation des prirotés entre l'ancienne et la nouvelle position
			Set<Task> tasksInCategory = this.findByCategory(newColumn);
			for(Task taskInCategory : tasksInCategory){
				if (taskInCategory.getPriority() > task.getPriority()&& taskInCategory.getPriority()<newPriority) {
					log.debug(taskInCategory.getTitle() + " passe de " + taskInCategory.getPriority() + " à " + (taskInCategory.getPriority() - 1));
					taskInCategory.setPriority(taskInCategory.getPriority() - 1);
					this.update(taskInCategory);
				}
			}
	
			// deplacement de la task
			task.setPriority(newPriority-1);
			update(task);
		}
		else
		{
			// decrementation des priorites dans l'ancienne categorie
			Set<Task> tasksInOldCategory = this.findByCategory(task.getCategory());
			for (Task taskInOldCategory : tasksInOldCategory) {
				if (taskInOldCategory.getPriority() > task.getPriority()) {
					log.debug(taskInOldCategory.getTitle() + " passe de " + taskInOldCategory.getPriority() + " à " + (taskInOldCategory.getPriority() - 1));
					taskInOldCategory.setPriority(taskInOldCategory.getPriority() - 1);
					update(taskInOldCategory);
				}
			}
			
			// incrementation des priorites dans la nouvelle categorie
			Set<Task> tasksInNewCategory = this.findByCategory(newColumn);
			for (Task taskInNewCategory : tasksInNewCategory) {
				if (taskInNewCategory.getPriority() >= newPriority) {
					log.debug(taskInNewCategory.getTitle() + " passe de " + taskInNewCategory.getPriority() + " à " + (taskInNewCategory.getPriority() + 1));
					taskInNewCategory.setPriority(taskInNewCategory.getPriority() + 1);
					update(taskInNewCategory);
				}
			}
	
			// deplacement de la task
			task.setCategory(newColumn);
			task.setPriority(newPriority);
			update(task);
		}
	}

	@Override
	public Set<Task> findByCategory(TaskColumn column) {
		return taskDao.findByCategory(column);
	}


}
