package hei.gl.scrumtool.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import hei.gl.scrumtool.core.dao.UserDAO;
import hei.gl.scrumtool.core.entity.User;
import hei.gl.scrumtool.core.service.UserService;

@Named
@Transactional
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO UserDao;


	@Override
	public List<User> findAll() {
		return UserDao.findAll();
	}


	@Override
	public User findByID(long idUser) {
		return UserDao.findById(idUser);
	}
	
	
}