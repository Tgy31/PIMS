package model.entity;

public class Student extends User {
	private int  student_id;
	private int  project_id;
	private int module_id;
	private String  project_title;
	private String  project_description;	
	private String supervisor;

	public Student() {
		super();
	}

	public Student(int student_id, int project_id, int module_id,
			String project_title, String project_description, String supervisor) {
		super();
		this.student_id = student_id;
		this.project_id = project_id;
		this.module_id = module_id;
		this.project_title = project_title;
		this.project_description = project_description;
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
		int result = super.hashCode();
		result = prime * result + module_id;
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
		Student other = (Student) obj;
		if (module_id != other.module_id)
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
		return true;
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", project_id="
				+ project_id + ", module_id=" + module_id + ", project_title="
				+ project_title + ", project_description="
				+ project_description + ", supervisor=" + supervisor + "]";
	}

	
	
}
