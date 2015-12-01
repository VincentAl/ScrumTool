package hei.gl.scrumtool.core.dao;

import hei.gl.scrumtool.core.entity.Sprint;
import hei.gl.scrumtool.core.entity.Story;
import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.enumeration.StoryPoint;

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
	List<Story> findBySprint(Sprint sprint); 
	
	Set<Story> findByCategory(StoryColumn category);
	Set<Story> findByCategory(StoryPoint category);
	

}
