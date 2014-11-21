package model.entity;

public class Timetable {
	private int timetable_id;
	private int slot_id;
	
	public Timetable() {
		super();
	}

	public Timetable(int timetable_id, int slot_id) {
		super();
		this.timetable_id = timetable_id;
		this.slot_id = slot_id;
	}

	public int getTimetable_id() {
		return timetable_id;
	}

	public void setTimetable_id(int timetable_id) {
		this.timetable_id = timetable_id;
	}

	public int getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + slot_id;
		result = prime * result + timetable_id;
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
		Timetable other = (Timetable) obj;
		if (slot_id != other.slot_id)
			return false;
		if (timetable_id != other.timetable_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timetable [timetable_id=" + timetable_id + ", slot_id="
				+ slot_id + "]";
	}
	
	
}
