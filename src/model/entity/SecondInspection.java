package model.entity;

import java.util.Date;

public class SecondInspection {
	private int student_id;
	private int inspector_id;
	private int module_id;
	private Date date;
	private String second_inspectioncol;
	
	public SecondInspection() {
		super();
	}

	public SecondInspection(int student_id, int inspector_id, int module_id,
			Date date, String second_inspectioncol) {
		super();
		this.student_id = student_id;
		this.inspector_id = inspector_id;
		this.module_id = module_id;
		this.date = date;
		this.second_inspectioncol = second_inspectioncol;
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

	public String getSecond_inspectioncol() {
		return second_inspectioncol;
	}

	public void setSecond_inspectioncol(String second_inspectioncol) {
		this.second_inspectioncol = second_inspectioncol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + inspector_id;
		result = prime * result + module_id;
		result = prime
				* result
				+ ((second_inspectioncol == null) ? 0 : second_inspectioncol
						.hashCode());
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
		SecondInspection other = (SecondInspection) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (inspector_id != other.inspector_id)
			return false;
		if (module_id != other.module_id)
			return false;
		if (second_inspectioncol == null) {
			if (other.second_inspectioncol != null)
				return false;
		} else if (!second_inspectioncol.equals(other.second_inspectioncol))
			return false;
		if (student_id != other.student_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SecondInspection [student_id=" + student_id + ", inspector_id="
				+ inspector_id + ", module_id=" + module_id + ", date=" + date
				+ ", second_inspectioncol=" + second_inspectioncol + "]";
	}
	
}
