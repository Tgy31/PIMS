package model.entity;

import java.util.Date;

public class Inspection {
	private int inspection_id;
	private int inspectionweek_id;
	private int student_id;
	private int first_inspector_id;
	private int second_inspector_id;
	private Date start_date;
	private Date end_date;
	
	public Inspection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inspection(int inspection_id, int inspectionweek_id, int student_id,
			int first_inspector_id, int second_inspector_id, Date start_date,
			Date end_date) {
		super();
		this.inspection_id = inspection_id;
		this.inspectionweek_id = inspectionweek_id;
		this.student_id = student_id;
		this.first_inspector_id = first_inspector_id;
		this.second_inspector_id = second_inspector_id;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + first_inspector_id;
		result = prime * result + inspection_id;
		result = prime * result + inspectionweek_id;
		result = prime * result + second_inspector_id;
		result = prime * result
				+ ((start_date == null) ? 0 : start_date.hashCode());
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
		Inspection other = (Inspection) obj;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (first_inspector_id != other.first_inspector_id)
			return false;
		if (inspection_id != other.inspection_id)
			return false;
		if (inspectionweek_id != other.inspectionweek_id)
			return false;
		if (second_inspector_id != other.second_inspector_id)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (student_id != other.student_id)
			return false;
		return true;
	}

	public int getInspection_id() {
		return inspection_id;
	}

	public void setInspection_id(int inspection_id) {
		this.inspection_id = inspection_id;
	}

	public int getInspectionweek_id() {
		return inspectionweek_id;
	}

	public void setInspectionweek_id(int inspectionweek_id) {
		this.inspectionweek_id = inspectionweek_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getFirst_inspector_id() {
		return first_inspector_id;
	}

	public void setFirst_inspector_id(int first_inspector_id) {
		this.first_inspector_id = first_inspector_id;
	}

	public int getSecond_inspector_id() {
		return second_inspector_id;
	}

	public void setSecond_inspector_id(int second_inspector_id) {
		this.second_inspector_id = second_inspector_id;
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

	@Override
	public String toString() {
		return "Inspection [inspection_id=" + inspection_id
				+ ", inspectionweek_id=" + inspectionweek_id + ", student_id="
				+ student_id + ", first_inspector_id=" + first_inspector_id
				+ ", second_inspector_id=" + second_inspector_id
				+ ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}
	
	


}