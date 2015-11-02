package hei.gl.scrumtool.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import hei.gl.scrumtool.core.entity.Personne;

public interface PersonneDAO extends JpaRepository<Personne, Long> {

	@Override
	void delete(Personne personne);

	Personne findOne(String nom);
	
	void savePersonne (Personne personne);

	@Override
	List<Personne> findAll();
}
