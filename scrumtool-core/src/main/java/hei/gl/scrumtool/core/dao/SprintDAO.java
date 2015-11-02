package hei.gl.scrumtool.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import hei.gl.scrumtool.core.entity.Sprint;

public interface SprintDAO extends JpaRepository<Sprint, Long> {

	@Override
	List<Sprint> findAll();

	@Override
	void delete(Long id);

	Sprint findOne(String sprint);

}
