package hei.gl.scrumtool.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hei.gl.scrumtool.core.entity.Sprint;

public interface SprintDAO extends JpaRepository<Sprint, Long> {

	@Override
	List<Sprint> findAll();

	@Override
	void delete(Long id);

	Sprint findById(long id);
	
	Sprint findByCurrentSprintTrue();
	
	@Query("select s from Sprint s where s.number = (select max(ss.number) from Sprint ss)")
	Sprint findLastSprint();
}
