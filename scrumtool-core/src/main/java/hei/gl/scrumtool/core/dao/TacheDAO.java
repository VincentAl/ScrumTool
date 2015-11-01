package hei.gl.scrumtool.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hei.gl.scrumtool.core.entity.Tache;

public interface TacheDAO extends JpaRepository<Tache, Long> {

}
