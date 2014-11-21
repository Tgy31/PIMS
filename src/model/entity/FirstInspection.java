package model.entity;

import java.util.Date;

public class FirstInspection {
	private int student_id;
	private int inspector_id;
	private int module_id;
	private Date date;
	private String first_inspectioncol;
	
	public FirstInspection() {
		super();
	}

	

	public FirstInspection(int student_id, int inspector_id, int module_id,
			Date date, String first_inspectioncol) {
		super();
		this.student_id = student_id;
		this.inspector_id = inspector_id;
		this.module_id = module_id;
		this.date = date;
		this.first_inspectioncol = first_inspectioncol;
	}


	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getInspector_id() {
		return inspector_id;
	}

	public void setInspector_id(int inspector_id) {
		this.inspector_id = inspector_id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public String getFirst_inspectioncol() {
		return first_inspectioncol;
	}

	public void setFirst_inspectioncol(String first_inspectioncol) {
		this.first_inspectioncol = first_inspectioncol;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime
				* result
				+ ((first_inspectioncol == null) ? 0 : first_inspectioncol
						.hashCode());
		result = prime * result + inspector_id;
		result = prime * result + module_id;
		result = prime * result + student_id;
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
		FirstInspection other = (FirstInspection) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (first_inspectioncol == null) {
			if (other.first_inspectioncol != null)
				return false;
		} else if (!first_inspectioncol.equals(other.first_inspectioncol))
			return false;
		if (inspector_id != other.inspector_id)
			return false;
		if (module_id != other.module_id)
			return false;
		if (student_id != other.student_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FirstInspection [student_id=" + student_id + ", inspector_id="
				+ inspector_id + ", module_id=" + module_id + ", date=" + date
				+ ", first_inspectioncol=" + first_inspectioncol + "]";
	}
	
	
}
