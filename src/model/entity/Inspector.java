package model.entity;

public class Inspector extends User {
	private int inspector_id;
	private int capacity;	

	public Inspector() {
		super();
	}

	public Inspector(int inspector_id, int capacity) {
		super();
		this.inspector_id = inspector_id;
		this.capacity = capacity;

	}

	public int getInspector_id() {
		return inspector_id;
	}

	public void setInspector_id(int inspector_id) {
		this.inspector_id = inspector_id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + capacity;
		result = prime * result + inspector_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inspector other = (Inspector) obj;
		if (inspector_id != other.inspector_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inspector [inspector_id=" + inspector_id + ", capacity="
				+ capacity + "]";
	}


	
}