package hei.gl.scrumtool.core.service;

import java.util.ArrayList;
import java.util.List;

import hei.gl.scrumtool.core.entity.User;

public interface UserService {

	public User findByID(long idUser);
	public List<User> findAll();	
}
