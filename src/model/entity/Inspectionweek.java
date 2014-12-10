package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Inspectionweek {
	private int  inspectionweek_id;
	private int  module_id;
	private String  inspection_title;
	private Date start_date;
	
	public Inspectionweek() {
		super();
	}
	
	public Inspectionweek(int inspectionweek_id, int module_id,
			String inspection_title, Date start_date) {
		super();
		this.inspectionweek_id = inspectionweek_id;
		this.module_id = module_id;
		this.inspection_title = inspection_title;
		this.start_date = start_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((inspection_title == null) ? 0 : inspection_title.hashCode());
		result = prime * result + inspectionweek_id;
		result = prime * result + module_id;
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
		Inspectionweek other = (Inspectionweek) obj;
		if (inspection_title == null) {
			if (other.inspection_title != null)
				return false;
		} else if (!inspection_title.equals(other.inspection_title))
			return false;
		if (inspectionweek_id != other.inspectionweek_id)
			return false;
		if (module_id != other.module_id)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}
	
	public String getFormattedStartDate() {
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(this.start_date);
		return formattedDate;
	}
	
	public String getDisplayableStartDate() {
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(this.start_date);
		return formattedDate;
	}

	public int getInspectionweek_id() {
		return inspectionweek_id;
	}

	public void setInspectionweek_id(int inspectionweek_id) {
		this.inspectionweek_id = inspectionweek_id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getInspection_title() {
		return inspection_title;
	}

	public void setInspection_title(String inspection_title) {
		this.inspection_title = inspection_title;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	@Override
	public String toString() {
		return "Inspectionweek [inspectionweek_id=" + inspectionweek_id
				+ ", module_id=" + module_id + ", inspection_title="
				+ inspection_title + ", start_date=" + start_date + "]";
	}
	
	

}
