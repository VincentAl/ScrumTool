package hei.gl.scrumtool.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
	public void changeState(long idTask, TaskColumn category) {
		Task task = this.findByID(idTask);

		// deplacement
		task.setState(category);
		this.update(task);
	}

	@Override
	public Task create(Task task) {
		return taskDao.save(task);
	}

	@Override
	public Task update(Task task) {
		return taskDao.save(task);
	}

	@Override
	public void delete(Task task) {
		taskDao.delete(task);
	}

}
