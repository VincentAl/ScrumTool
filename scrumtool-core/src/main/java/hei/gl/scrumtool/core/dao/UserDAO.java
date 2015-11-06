package hei.gl.scrumtool.core.dao;

import hei.gl.scrumtool.core.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

	@Override
	void delete(User user);

	User findById(Long id);
	
	User save (User user);

	@Override
	List<User> findAll();
}
