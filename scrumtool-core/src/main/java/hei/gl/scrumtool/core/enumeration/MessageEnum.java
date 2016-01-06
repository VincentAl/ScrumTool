package hei.gl.scrumtool.core.enumeration;

public enum MessageEnum {
	EMPTY_MSG(0,""),
	CLOSE_SPRINT(1,"Sprint terminé et fermé !"),
	STORY_NOT_FINISHED(2,"Attention ! Une story au moins n'est pas terminée : le sprint ne peut pas être fermé. "),
	STORY_TASK_NOT_FINISHED(3,"Impossible de fermer le sprint : une tâche au moins n'est pas terminé.");
	
	private int id;
	private String content;
	
	private MessageEnum(int id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public String displayMessage () {
		switch(id) {
		case 2: return content.concat("\nLes stories suivantes ne sont pas terminées :\n");
		case 3: return content.concat("\nLes tâches suivantes ne sont pas terminées :\n");
		default: return content;
		}
	}
}
