package hei.gl.scrumtool.core.dao;

import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.entity.Task;
import hei.gl.scrumtool.core.enumeration.TaskColumn;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskDAO extends JpaRepository<Task, Long> {

	@Override
	void delete(Long id);

	@Override
	void delete(Task id);

	@Override
	void deleteAll();

	Task findById(Long id);

	Task findByTitle(String title);
	
	List<Task> findByStory(Story story);

	Set<Task> findByCategory(TaskColumn column);

	@Override
	List<Task> findAll();
	
	@Query("select t from Task t where t.priority = (select max(tt.priority) from Task tt)")
	Task findByLowestPriority();

}
