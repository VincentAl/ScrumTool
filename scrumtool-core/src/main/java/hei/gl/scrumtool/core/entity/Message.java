package hei.gl.scrumtool.core.entity;

import hei.gl.scrumtool.core.enumeration.MessageEnum;

public class Message {

	private long id;
	
	private String type;
	
	private long idStory;
	
	private long idTask;
	
	private MessageEnum content;
	
	private boolean isDisplayed;


//Constructors

	public Message(long id, String type, long idStory, long idTask, MessageEnum content, boolean isDisplayed) {
		super();
		this.id = id;
		this.type = type;
		this.idStory = idStory;
		this.idTask = idTask;
		this.content = content;
		this.isDisplayed = isDisplayed;
	}

	
//Getters-Setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getIdStory() {
		return idStory;
	}

	public void setIdStory(long idStory) {
		this.idStory = idStory;
	}

	public long getIdTask() {
		return idTask;
	}

	public void setIdTask(long idTask) {
		this.idTask = idTask;
	}

	public MessageEnum getContent() {
		return content;
	}

	public void setContent(MessageEnum content) {
		this.content = content;
	}

	public boolean isDisplayed() {
		return isDisplayed;
	}

	public void setDisplayed(boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

}
