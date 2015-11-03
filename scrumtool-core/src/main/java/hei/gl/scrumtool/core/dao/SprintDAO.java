package hei.gl.scrumtool.core.dao;

import hei.gl.scrumtool.core.entity.Sprint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintDAO extends JpaRepository<Sprint, Long> {

	@Override
	List<Sprint> findAll();

	@Override
	void delete(Long id);

	Sprint findById(long id);

}
