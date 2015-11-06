package hei.gl.scrumtool.core.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	private long number;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
	private List<Story> storyList;

	@OneToOne
	private User scrumMaster;

	@OneToOne
	private User productOwner;


	public void addStory(Story story){
		this.storyList.add(story);
	}
	
	public void addStoryList(List<Story> stories){
		this.storyList.addAll(stories);
	}
	
	public void removeStory(long idStory){
		Iterator<Story>i = this.storyList.iterator();
		while (i.hasNext()) {
			Story story = (Story) i.next();
			if(story.getId()==idStory){
				i.remove();
			}
		}
	}

	
	//Getters-Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public List<Story> getStoryList() {
		return storyList;
	}

	public void setStoryList(List<Story> storyList) {
		this.storyList = storyList;
	}
	
	public User getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(User scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

	public User getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(User productOwner) {
		this.productOwner = productOwner;
	}

}
