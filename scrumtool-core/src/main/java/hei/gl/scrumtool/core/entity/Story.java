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

@Entity
public class Story {

	private StoryColumn category;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.Summary.class)
	private long id;

	@JsonView(View.Summary.class)
	private long storyPoints;

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

	public long getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(long storyPoints) {
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

}
