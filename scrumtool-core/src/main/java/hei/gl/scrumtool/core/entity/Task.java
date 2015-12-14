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

import hei.gl.scrumtool.core.enumeration.TaskColumn;


@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;

	private String description;

	private TaskColumn state;

	private int priority;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@ManyToOne(cascade = CascadeType.ALL)
	private Story story;

	
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

	public TaskColumn getState() {
		return state;
	}

	public void setState(TaskColumn state) {
		this.state = state;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartPoint() {
		return startTime;
	}

	public void setStartPoint(Date startTime) {
		this.startTime = startTime;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}
}
