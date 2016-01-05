package hei.gl.scrumtool.core.enumeration;

public enum MessageEnum {
	CLOSESPRINT(1,"Sprint terminé et fermé !"),
	STORYNOTFINISHED(2,"Attention ! Une story n'est pas terminée ; le sprint ne peut pas être fermé. "),
	STORYTASKNOTFINISHED(3,"Impossible de fermer le sprint : une tâche n'est pas terminé.");
	
	private int id;
	private String content;
	
	private MessageEnum(int id, String content) {
		this.id = id;
		this.content = content;
	}
}
