package model.entity;

import java.util.Date;

public class Slot {
	private int slot_id;
	private Date start_date;
	private Date end_date;
	private Boolean availbility;
	
	public Slot() {
		super();
	}

	public Slot(int slot_id, Date start_date, Date end_date, Boolean availbility) {
		super();
		this.slot_id = slot_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.availbility = availbility;
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

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Boolean getAvailbility() {
		return availbility;
	}

	public void setAvailbility(Boolean availbility) {
		this.availbility = availbility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availbility == null) ? 0 : availbility.hashCode());
		result = prime * result
				+ ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + slot_id;
		result = prime * result
				+ ((start_date == null) ? 0 : start_date.hashCode());
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
		if (availbility == null) {
			if (other.availbility != null)
				return false;
		} else if (!availbility.equals(other.availbility))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (slot_id != other.slot_id)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slot [slot_id=" + slot_id + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", availbility=" + availbility
				+ "]";
	}
	
	
}
