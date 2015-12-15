package hei.gl.scrumtool.core.service.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import hei.gl.scrumtool.core.dao.StoryDAO;
import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.service.SprintService;
import hei.gl.scrumtool.core.service.StoryService;
import hei.gl.scrumtool.core.service.TaskService;

@Named
@Transactional
public class StoryServiceImpl implements StoryService {

	private final static Logger log = LoggerFactory
			.getLogger(StoryServiceImpl.class);

	@Inject
	private StoryDAO storyDAO;

	@Inject
	private SprintService sprintService;
	
	@Inject
	private TaskService taskService;

	@Override
	public Story findById(long idStory) {
		return storyDAO.findOne(idStory);

	}

	@Override
	public List<Story> findALL() {
		return storyDAO.findAll();
	}

	@Override
	public void delete(long idStory) {
		storyDAO.delete(idStory);
	}

	@Override
	public Story create(Story story) {
		return storyDAO.save(story);
	}

	@Override
	public Set<Story> findByCategory(StoryColumn category) {
		return storyDAO.findByCategory(category);
	}
	
	@Override
	public List<Story> findBySprint(long idSprint) {
		return storyDAO.findBySprint(sprintService.findById(idSprint));
	}
	
	@Override
	public List<Story> findBySprint(Sprint sprint) {
		return storyDAO.findBySprint(sprint);
	}

	@Override
	public Story update(Story story) {
		return storyDAO.save(story);
	}

	@Override
	public void move(long idStory, StoryColumn category) {
		Story story = this.findById(idStory);

		// decrementation des priorites dans l'ancienne categorie
		Set<Story> storiesInOldCategory = this.findByCategory(story
				.getCategory());
		for (Story storyInOldCategory : storiesInOldCategory) {
			if (storyInOldCategory.getPriority() > story.getPriority()) {
				//log.debug(storyInOldCategory.getTitle() + " passe de " + storyInOldCategory.getPriority() + " à " + (storyInOldCategory.getPriority() - 1));
				storyInOldCategory.setPriority(storyInOldCategory.getPriority() - 1);
				update(storyInOldCategory);
			}
		}

		// deplacement de la story
		if (story.getCategory()==category){
			story.setPriority(this.findByCategory(category).size()-1);
		}
		else {
			story.setPriority(this.findByCategory(category).size());
		}
		
		//deplacement de la story
		story.setCategory(category);
		update(story);
	}

	@Override
	public void move(long idStory, StoryColumn category, int newPriority) {
		Story story = this.findById(idStory);

		// si on fait un deplacement vers le bas (ver une priorite plus basse) dans une même cathegory 
		if (story.getCategory()==category && story.getPriority()<newPriority){ 
			// decrementation des priroter entre l'ancienne et la nouvelle position
			Set<Story> storiesInCategory = this.findByCategory(category);
			for(Story storyInCategory : storiesInCategory){
				if (storyInCategory.getPriority() > story.getPriority()&& storyInCategory.getPriority()<newPriority) {
					//log.debug(storyInOldCategory.getTitle() + " passe de " + storyInOldCategory.getPriority() + " à " + (storyInOldCategory.getPriority() - 1));
					storyInCategory.setPriority(storyInCategory.getPriority() - 1);
					update(storyInCategory);
				}
			}
	
			// deplacement de la story
			story.setPriority(newPriority-1);
			update(story);
		}
		else
		{
			// decrementation des priorites dans l'ancienne categorie
			Set<Story> storiesInOldCategory = this.findByCategory(story.getCategory());
			for (Story storyInOldCategory : storiesInOldCategory) {
				if (storyInOldCategory.getPriority() > story.getPriority()) {
					//log.debug(storyInOldCategory.getTitle() + " passe de " + storyInOldCategory.getPriority() + " à " + (storyInOldCategory.getPriority() - 1));
					storyInOldCategory.setPriority(storyInOldCategory.getPriority() - 1);
					update(storyInOldCategory);
				}
			}
			
			// incrementation des priorites dans la nouvelle categorie
			Set<Story> storiesInNewCategory = this.findByCategory(category);
			for (Story storyInNewCategory : storiesInNewCategory) {
				if (storyInNewCategory.getPriority() >= newPriority) {
					//log.debug(storyInNewCategory.getTitle() + " passe de " + storyInNewCategory.getPriority() + " à " + (storyInNewCategory.getPriority() + 1));
					storyInNewCategory.setPriority(storyInNewCategory.getPriority() + 1);
					update(storyInNewCategory);
				}
			}
	
			// deplacement de la story
			story.setCategory(category);
			story.setPriority(newPriority);
			update(story);
		}
	}

	@Override
	public void move(long idStory, long idOldSprint, StoryColumn category) {
		Story story = this.findById(idStory);

		// deplacement de la story
		story.setCategory(category);
		story.setPriority(this.findByCategory(category).size() + 1);
		sprintService.removeStory(idStory, idOldSprint);
		this.update(story);
	}

	@Override
	public void changeState(long idStory, StoryColumn category) {
		Story story = this.findById(idStory);
		
		//deplacement de la story
		story.setCategory(category);
		update(story);
	}

	@Override
	public void addTask(long idTask, long idStory) {
		this.addTask(taskService.findByID(idTask), idStory);
	}

	@Override
	public void addTask(Task task, long idStory) {
		Story story=this.findById(idStory);
		story.addTask(task);
		this.update(story);
		task.setStory(story);
		taskService.update(task);
	}

	@Override
	public void addTask(List<Task> tasks, long idStory) {
		Story story=this.findById(idStory);
		story.addTaskList(tasks);
		this.update(story);
		for (Task task : tasks) {
			task.setStory(story);
			taskService.update(task);
		}
	}

	@Override
	public void removeTask(long idTask, long idStory) {
		Story story=findById(idStory);
		story.removeTask(idTask);
		this.update(story);
	}

	
}
