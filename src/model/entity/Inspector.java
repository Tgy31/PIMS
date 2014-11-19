package model.entity;

public class Inspector extends User {
	int inspector_id;
	int timetable_id;
	int capacity;	
	String title;
	String keywords;

	public Inspector() {
		super();
	}

	public Inspector(int inspector_id, int timetable_id, int capacity,
			String username, String password, String first_name,
			String last_name, String email, String title, String keywords) {
		super();
		this.inspector_id = inspector_id;
		this.timetable_id = timetable_id;
		this.capacity = capacity;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.title = title;
		this.keywords = keywords;
	}

	public int getInspector_id() {
		return inspector_id;
	}

	public void setInspector_id(int inspector_id) {
		this.inspector_id = inspector_id;
	}

	public int getTimetable_id() {
		return timetable_id;
	}

	public void setTimetable_id(int timetable_id) {
		this.timetable_id = timetable_id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + inspector_id;
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result
				+ ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + timetable_id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Inspector other = (Inspector) obj;
		if (capacity != other.capacity)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (inspector_id != other.inspector_id)
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (timetable_id != other.timetable_id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inspector [inspector_id=" + inspector_id + ", timetable_id="
				+ timetable_id + ", capacity=" + capacity + ", username="
				+ username + ", password=" + password + ", first_name="
				+ first_name + ", last_name=" + last_name + ", email=" + email
				+ ", title=" + title + ", keywords=" + keywords + "]";
	}
	
}