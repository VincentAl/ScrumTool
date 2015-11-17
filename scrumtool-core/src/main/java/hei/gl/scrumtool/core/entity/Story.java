package hei.gl.scrumtool.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import hei.gl.scrumtool.core.enumeration.StoryColumn;
import hei.gl.scrumtool.core.enumeration.StoryPoint;

@Entity
public class Story {

	// Properties
	private StoryColumn category;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.Summary.class)
	private long id;

	@JsonView(View.Summary.class)
	private StoryPoint storyPoints;

	private int priority;

	@JsonView(View.Summary.class)
	private String description;

	@ManyToOne
	private Sprint sprint;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "story")
	private Set<Task> tasksList;

	@JsonView(View.Summary.class)
	private String title;

	// Getters-Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public StoryPoint getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(StoryPoint storyPoints) {
		this.storyPoints = storyPoints;
	}

	public Set<Task> getTasksList() {
		return tasksList;
	}

	public void setTasksList(Set<Task> tasksList) {
		this.tasksList = tasksList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StoryColumn getCategory() {
		return category;
	}

	public void setCategory(StoryColumn storyColumn) {
		this.category = storyColumn;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Story [category=" + category + ", id=" + id + ", storyPoints=" + storyPoints + ", priority=" + priority
				+ ", description=" + description + ", sprint=" + sprint + ", tasksList=" + tasksList + ", title="
				+ title + "]";
	}
	

}
