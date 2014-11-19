package model.entity;

public class Student extends User {
	int  student_id;
	int  project_id;
	int module_id;
	int course_id;
	int timetable_id;
	String  project_title;
	String  project_description;	
	String supervisor;

	public Student() {
		super();
	}

	public Student(int student_id, int project_id, int module_id,
			int course_id, int timetable_id, String username, String password,
			String project_title, String project_description,
			String first_name, String last_name, String email, String supervisor) {
		super();
		this.student_id = student_id;
		this.project_id = project_id;
		this.module_id = module_id;
		this.course_id = course_id;
		this.timetable_id = timetable_id;
		this.username = username;
		this.password = password;
		this.project_title = project_title;
		this.project_description = project_description;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.supervisor = supervisor;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getTimetable_id() {
		return timetable_id;
	}

	public void setTimetable_id(int timetable_id) {
		this.timetable_id = timetable_id;
	}

	public String getProject_title() {
		return project_title;
	}

	public void setProject_title(String project_title) {
		this.project_title = project_title;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}
	
	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + course_id;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result
				+ ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + module_id;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime
				* result
				+ ((project_description == null) ? 0 : project_description
						.hashCode());
		result = prime * result + project_id;
		result = prime * result
				+ ((project_title == null) ? 0 : project_title.hashCode());
		result = prime * result + student_id;
		result = prime * result
				+ ((supervisor == null) ? 0 : supervisor.hashCode());
		result = prime * result + timetable_id;
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
		Student other = (Student) obj;
		if (course_id != other.course_id)
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
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (module_id != other.module_id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (project_description == null) {
			if (other.project_description != null)
				return false;
		} else if (!project_description.equals(other.project_description))
			return false;
		if (project_id != other.project_id)
			return false;
		if (project_title == null) {
			if (other.project_title != null)
				return false;
		} else if (!project_title.equals(other.project_title))
			return false;
		if (student_id != other.student_id)
			return false;
		if (supervisor == null) {
			if (other.supervisor != null)
				return false;
		} else if (!supervisor.equals(other.supervisor))
			return false;
		if (timetable_id != other.timetable_id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	
}
