package hei.gl.scrumtool.core.enumeration;

public enum StoryPoint {
	ZERO(1, "0"),
	HALF(2, "0.5"),
	ONE(3, "1"),
	TWO(4, "2"),
	THREE(5, "3"),
	FIVE(6, "5"),
	EIGHT(7, "8"),
	THIRTEEN(8, "13"),
	TWENTYONE(9, "21");	
	
	private int id;
	private String label;
	
	private StoryPoint(int id, String label) {
		this.id = id;
		this.label = label;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
