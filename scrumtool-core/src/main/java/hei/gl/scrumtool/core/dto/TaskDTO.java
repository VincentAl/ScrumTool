package hei.gl.scrumtool.core.dto;

public class TaskDTO {
	private Integer idStory;
    private String taskTitle;
    private String taskDescription;
    
	public Integer getIdStory() {
		return idStory;
	}
	public void setIdStory(Integer idStory) {
		this.idStory = idStory;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
}
