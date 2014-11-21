package model.entity;

import java.util.Date;

public class Module {
	private int module_id;
	private String module_name;
	private String year;
	private Date start_date;
	private Date end_date;
	private int students_enrolled;
	private int inspector_available;
	private int default_inspector_capacity;
	private Date first_inspection_start_date;
	private Date first_inspection_end_date;
	private Date second_inspection_start_date;
	private Date second_inspection_end_date;
	private Date disseration_deadline;
	private int pc_id;
	
	public Module() {
		super();
	}

	public Module(int module_id, String module_name, String year,
			Date start_date, Date end_date, int students_enrolled,
			int inspector_available, int default_inspector_capacity,
			Date first_inspection_start_date, Date first_inspection_end_date,
			Date second_inspection_start_date, Date second_inspection_end_date,
			Date disseration_deadline, int pc_id) {
		super();
		this.module_id = module_id;
		this.module_name = module_name;
		this.year = year;
		this.start_date = start_date;
		this.end_date = end_date;
		this.students_enrolled = students_enrolled;
		this.inspector_available = inspector_available;
		this.default_inspector_capacity = default_inspector_capacity;
		this.first_inspection_start_date = first_inspection_start_date;
		this.first_inspection_end_date = first_inspection_end_date;
		this.second_inspection_start_date = second_inspection_start_date;
		this.second_inspection_end_date = second_inspection_end_date;
		this.disseration_deadline = disseration_deadline;
		this.pc_id = pc_id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getStudents_enrolled() {
		return students_enrolled;
	}

	public void setStudents_enrolled(int students_enrolled) {
		this.students_enrolled = students_enrolled;
	}

	public int getInspector_available() {
		return inspector_available;
	}

	public void setInspector_available(int inspector_available) {
		this.inspector_available = inspector_available;
	}

	public int getDefault_inspector_capacity() {
		return default_inspector_capacity;
	}

	public void setDefault_inspector_capacity(int default_inspector_capacity) {
		this.default_inspector_capacity = default_inspector_capacity;
	}

	public Date getFirst_inspection_start_date() {
		return first_inspection_start_date;
	}

	public void setFirst_inspection_start_date(Date first_inspection_start_date) {
		this.first_inspection_start_date = first_inspection_start_date;
	}

	public Date getFirst_inspection_end_date() {
		return first_inspection_end_date;
	}

	public void setFirst_inspection_end_date(Date first_inspection_end_date) {
		this.first_inspection_end_date = first_inspection_end_date;
	}

	public Date getSecond_inspection_start_date() {
		return second_inspection_start_date;
	}

	public void setSecond_inspection_start_date(Date second_inspection_start_date) {
		this.second_inspection_start_date = second_inspection_start_date;
	}

	public Date getSecond_inspection_end_date() {
		return second_inspection_end_date;
	}

	public void setSecond_inspection_end_date(Date second_inspection_end_date) {
		this.second_inspection_end_date = second_inspection_end_date;
	}

	public Date getDisseration_deadline() {
		return disseration_deadline;
	}

	public void setDisseration_deadline(Date disseration_deadline) {
		this.disseration_deadline = disseration_deadline;
	}

	public int getPc_id() {
		return pc_id;
	}

	public void setPc_id(int pc_id) {
		this.pc_id = pc_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + default_inspector_capacity;
		result = prime
				* result
				+ ((disseration_deadline == null) ? 0 : disseration_deadline
						.hashCode());
		result = prime * result
				+ ((end_date == null) ? 0 : end_date.hashCode());
		result = prime
				* result
				+ ((first_inspection_end_date == null) ? 0
						: first_inspection_end_date.hashCode());
		result = prime
				* result
				+ ((first_inspection_start_date == null) ? 0
						: first_inspection_start_date.hashCode());
		result = prime * result + inspector_available;
		result = prime * result + module_id;
		result = prime * result
				+ ((module_name == null) ? 0 : module_name.hashCode());
		result = prime * result + pc_id;
		result = prime
				* result
				+ ((second_inspection_end_date == null) ? 0
						: second_inspection_end_date.hashCode());
		result = prime
				* result
				+ ((second_inspection_start_date == null) ? 0
						: second_inspection_start_date.hashCode());
		result = prime * result
				+ ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + students_enrolled;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Module other = (Module) obj;
		if (default_inspector_capacity != other.default_inspector_capacity)
			return false;
		if (disseration_deadline == null) {
			if (other.disseration_deadline != null)
				return false;
		} else if (!disseration_deadline.equals(other.disseration_deadline))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (first_inspection_end_date == null) {
			if (other.first_inspection_end_date != null)
				return false;
		} else if (!first_inspection_end_date
				.equals(other.first_inspection_end_date))
			return false;
		if (first_inspection_start_date == null) {
			if (other.first_inspection_start_date != null)
				return false;
		} else if (!first_inspection_start_date
				.equals(other.first_inspection_start_date))
			return false;
		if (inspector_available != other.inspector_available)
			return false;
		if (module_id != other.module_id)
			return false;
		if (module_name == null) {
			if (other.module_name != null)
				return false;
		} else if (!module_name.equals(other.module_name))
			return false;
		if (pc_id != other.pc_id)
			return false;
		if (second_inspection_end_date == null) {
			if (other.second_inspection_end_date != null)
				return false;
		} else if (!second_inspection_end_date
				.equals(other.second_inspection_end_date))
			return false;
		if (second_inspection_start_date == null) {
			if (other.second_inspection_start_date != null)
				return false;
		} else if (!second_inspection_start_date
				.equals(other.second_inspection_start_date))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (students_enrolled != other.students_enrolled)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Module [module_id=" + module_id + ", module_name="
				+ module_name + ", year=" + year + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", students_enrolled="
				+ students_enrolled + ", inspector_available="
				+ inspector_available + ", default_inspector_capacity="
				+ default_inspector_capacity + ", first_inspection_start_date="
				+ first_inspection_start_date + ", first_inspection_end_date="
				+ first_inspection_end_date + ", second_inspection_start_date="
				+ second_inspection_start_date
				+ ", second_inspection_end_date=" + second_inspection_end_date
				+ ", disseration_deadline=" + disseration_deadline + ", pc_id="
				+ pc_id + "]";
	}
	
	
}
