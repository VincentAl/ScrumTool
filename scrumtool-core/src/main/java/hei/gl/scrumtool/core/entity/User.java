package hei.gl.scrumtool.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "scrumMaster")
	private List<Sprint> sprintAsScrumMaster;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "productOwner")
	private List<Sprint> sprintAsProductOwner;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Task> task;
	
	String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Sprint> getSprintAsScrumMaster() {
		return sprintAsScrumMaster;
	}

	public void setSprintAsScrumMaster(List<Sprint> sprintAsScrumMaster) {
		this.sprintAsScrumMaster = sprintAsScrumMaster;
	}

	public List<Sprint> getSprintAsProductOwner() {
		return sprintAsProductOwner;
	}

	public void setSprintAsProductOwner(List<Sprint> sprintAsProductOwner) {
		this.sprintAsProductOwner = sprintAsProductOwner;
	}

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
