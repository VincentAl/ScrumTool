package hei.gl.scrumtool.core.enumeration;

public enum StoryPoint {
	ZERO(1, "0"),
	HALF(2, "0.5"),
	I(3, "1"),
	II(4, "2"),
	III(5, "3"),
	V(6, "5"),
	VIII(7, "8"),
	XIII(8, "13"),
	XXI(9, "21");	
	
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
