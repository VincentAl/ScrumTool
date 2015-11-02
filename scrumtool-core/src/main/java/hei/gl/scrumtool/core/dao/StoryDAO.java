package hei.gl.scrumtool.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hei.gl.scrumtool.core.entity.Story;

public interface StoryDAO extends JpaRepository<Story, Long> {

	@Override
	void delete(Story arg0);

	@Override
	void deleteAll();

	@Override
	Story findOne(Long id);
	
	Story findOne(String titre);

	@Override
	List<Story> findAll();

}
