package hei.gl.scrumtool.core.dao;

import hei.gl.scrumtool.core.entity.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDAO extends JpaRepository<Task, Long> {

	@Override
	void delete(Long id);

	@Override
	void delete(Task id);

	@Override
	void deleteAll();

	Task findById(Long id);

	Task findByTitle(String title);

	@Override
	List<Task> findAll();

}
