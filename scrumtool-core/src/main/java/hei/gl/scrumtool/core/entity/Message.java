package hei.gl.scrumtool.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import hei.gl.scrumtool.core.enumeration.MessageEnum;

public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String type;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "message")
	private long idStory;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "message")
	private long idTask;
	
	private MessageEnum content;
	
	private boolean isDisplayed;
}
