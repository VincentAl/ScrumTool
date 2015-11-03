package hei.gl.scrumtool.core.dao;

import hei.gl.scrumtool.core.entity.Story;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryDAO extends JpaRepository<Story, Long> {

	@Override
	void delete(Story arg0);

	@Override
	void deleteAll();

	Story findById(Long id);
	
	Story findByTitre(String titre);

	@Override
	List<Story> findAll();
	
	Set<Story> findByCategorie(int categorie);

}
