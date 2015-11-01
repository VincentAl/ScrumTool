package hei.gl.scrumtool.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hei.gl.scrumtool.core.entity.Sprint;

public interface SprintDAO extends JpaRepository<Sprint, Long> {

}
