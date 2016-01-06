package hei.gl.scrumtool.core.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import hei.gl.scrumtool.core.enumeration.TaskColumn;


@Entity
public class Task {

	@Id
	@JsonView(View.Summary.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonView(View.Summary.class)
	private String title;

	@JsonView(View.Summary.class)
	private String description;

	private TaskColumn category;

	private int priority;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date duration;

	@ManyToOne(cascade = CascadeType.ALL)
	private Story story;
	
	@JsonView(View.Summary.class)
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	
public Task(String title, String description, TaskColumn category) {
		this.title = title;
		this.description = description;
		this.category = category;
		this.user = user;
	}

public Task() {}



//Getters-Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskColumn getCategory() {
		return category;
	}

	public void setCategory(TaskColumn category) {
		this.category = category;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartPoint() {
		return duration;
	}

	public void setStartPoint(Date duration) {
		this.duration = duration;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}
}
