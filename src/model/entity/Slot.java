package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Slot {
	private int slot_id;
	private Date start_date;
	private Date end_date;
	private int student_id;
	private int inspector_id;
	
	public Slot() {
		super();
	}


	public Slot(int slot_id, Date start_date, Date end_date,
			int student_id, int inspector_id) {
		super();
		this.slot_id = slot_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.student_id = student_id;
		this.inspector_id = inspector_id;
	}
	
	public Slot(Date startDate, Date endDate) {
		super();
		this.start_date = startDate;
		this.end_date = endDate;
	}


	public int getSlot_id() {
		return slot_id;
	}


	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}


	public Date getStart_date() {
		return start_date;
	}
	
	public String getFormattedStartDate() {
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(this.start_date);
		return formattedDate;
	}
	
	public String getFormattedEndDate() {
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(this.end_date);
		return formattedDate;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + inspector_id;
		result = prime * result + slot_id;
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
		Slot other = (Slot) obj;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (inspector_id != other.inspector_id)
			return false;
		if (slot_id != other.slot_id)
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

	@Override
	public String toString() {
		return "Slot [slot_id=" + slot_id + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", student_id=" + student_id
				+ ", inspector_id=" + inspector_id + "]";
	}



	
}
