package hei.gl.scrumtool.core.enumeration;

public enum StoryColumn {
	IDEA(1,"Idea"),
	CONFIRMED(2,"Confirmed"),
	NEXT_SPRINT(3, "Next sprint");
	
	private int id;
	private String label;
	
	private StoryColumn(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
}
