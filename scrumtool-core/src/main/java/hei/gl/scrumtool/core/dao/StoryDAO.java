package hei.gl.scrumtool.core.dao;

import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.StoryColumn;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryDAO extends JpaRepository<Story, Long> {

	@Override
	void delete(Story id);

	@Override
	void deleteAll();

	Story findById(Long id);
	
	Story findByTitle(String title);

	@Override
	List<Story> findAll();
	
	Set<Story> findByCategory(StoryColumn category);
	
	

}
