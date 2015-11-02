package hei.gl.scrumtool.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hei.gl.scrumtool.core.entity.Tache;

public interface TacheDAO extends JpaRepository<Tache, Long> {

	@Override
	void delete(Long arg0);

	@Override
	void delete(Tache arg0);

	@Override
	void deleteAll();

	@Override
	Tache findOne(Long id);

	Tache findOne(String titre);

	@Override
	List<Tache> findAll();

}
