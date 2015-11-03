package hei.gl.scrumtool.core.enumeration;

public enum ColonneStory {
	IDEA(1,"Idea"),
	CONFIRMED(2,"Confirmed"),
	NEXT_SPRINT(3, "Next sprint");
	
	private int id;
	private String nom;
	
	private ColonneStory(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	
	
}
