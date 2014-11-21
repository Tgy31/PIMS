package model.entity;

public class Course {
	private int course_id;
	private String course_name;
	private String coures_description;
	
	public Course() {
		super();
	}

	public Course(int course_id, String course_name, String coures_description) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.coures_description = coures_description;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCoures_description() {
		return coures_description;
	}

	public void setCoures_description(String coures_description) {
		this.coures_description = coures_description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((coures_description == null) ? 0 : coures_description
						.hashCode());
		result = prime * result + course_id;
		result = prime * result
				+ ((course_name == null) ? 0 : course_name.hashCode());
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
		Course other = (Course) obj;
		if (coures_description == null) {
			if (other.coures_description != null)
				return false;
		} else if (!coures_description.equals(other.coures_description))
			return false;
		if (course_id != other.course_id)
			return false;
		if (course_name == null) {
			if (other.course_name != null)
				return false;
		} else if (!course_name.equals(other.course_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", course_name="
				+ course_name + ", coures_description=" + coures_description
				+ "]";
	}
	
	
}
