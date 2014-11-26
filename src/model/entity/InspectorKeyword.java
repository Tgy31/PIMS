package model.entity;

public class InspectorKeyword {
	private int inspector_keyword_id;
	private int inspector_id;
	private int keyword_id;
	
	public InspectorKeyword() {
		super();
	}
	public InspectorKeyword(int inspector_keyword_id, int inspector_id,
			int keyword_id) {
		super();
		this.inspector_keyword_id = inspector_keyword_id;
		this.inspector_id = inspector_id;
		this.keyword_id = keyword_id;
	}
	public int getInspector_keyword_id() {
		return inspector_keyword_id;
	}
	public void setInspector_keyword_id(int inspector_keyword_id) {
		this.inspector_keyword_id = inspector_keyword_id;
	}
	public int getInspector_id() {
		return inspector_id;
	}
	public void setInspector_id(int inspector_id) {
		this.inspector_id = inspector_id;
	}
	public int getKeyword_id() {
		return keyword_id;
	}
	public void setKeyword_id(int keyword_id) {
		this.keyword_id = keyword_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inspector_id;
		result = prime * result + inspector_keyword_id;
		result = prime * result + keyword_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InspectorKeyword other = (InspectorKeyword) obj;
		if (inspector_id != other.inspector_id)
			return false;
		if (inspector_keyword_id != other.inspector_keyword_id)
			return false;
		if (keyword_id != other.keyword_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InspectorKeyword [inspector_keyword_id=" + inspector_keyword_id
				+ ", inspector_id=" + inspector_id + ", keyword_id="
				+ keyword_id + "]";
	}
	
	
}
