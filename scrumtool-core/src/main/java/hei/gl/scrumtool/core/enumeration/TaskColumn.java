package hei.gl.scrumtool.core.enumeration;

public enum TaskColumn {
	TODO(4,"Todo"),
	DOING(5,"Doing"),
	DONE(6,"Done");
	
	
	private int id;
	private String label;
	
	private TaskColumn(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public static TaskColumn getTaskColumnById(long id){
		for (TaskColumn tc : TaskColumn.values()) {
			if(tc.getId() == id)
				return tc;
		}
		return null;
	}

}
