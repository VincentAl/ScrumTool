package hei.gl.scrumtool.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import hei.gl.scrumtool.core.entity.Personne;

public interface PersonneDAO extends JpaRepository<Personne, Long> {

}
