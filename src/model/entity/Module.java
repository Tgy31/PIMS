package model.entity;

import java.util.Date;

public class Module {
	private int module_id;
	private String module_name;
	private String year;
	private Date start_date;
	private Date end_date;
	private int default_inspector_capacity;
	private int unavailability_hour_limit;
	
	public Module() {
		super();
	}

	public Module(int module_id, String module_name, String year,
			Date start_date, Date end_date, int default_inspector_capacity,
			int unavailability_hour_limit) {
		super();
		this.module_id = module_id;
		this.module_name = module_name;
		this.year = year;
		this.start_date = start_date;
		this.end_date = end_date;
		this.default_inspector_capacity = default_inspector_capacity;
		this.unavailability_hour_limit = unavailability_hour_limit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + default_inspector_capacity;
		result = prime * result
				+ ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + module_id;
		result = prime * result
				+ ((module_name == null) ? 0 : module_name.hashCode());
		result = prime * result
				+ ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + unavailability_hour_limit;
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
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (module_id != other.module_id)
			return false;
		if (module_name == null) {
			if (other.module_name != null)
				return false;
		} else if (!module_name.equals(other.module_name))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (unavailability_hour_limit != other.unavailability_hour_limit)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
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

	public int getDefault_inspector_capacity() {
		return default_inspector_capacity;
	}

	public void setDefault_inspector_capacity(int default_inspector_capacity) {
		this.default_inspector_capacity = default_inspector_capacity;
	}

	public int getUnavailability_hour_limit() {
		return unavailability_hour_limit;
	}

	public void setUnavailability_hour_limit(int unavailability_hour_limit) {
		this.unavailability_hour_limit = unavailability_hour_limit;
	}

	@Override
	public String toString() {
		return "Module [module_id=" + module_id + ", module_name="
				+ module_name + ", year=" + year + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", default_inspector_capacity="
				+ default_inspector_capacity + ", unavailability_hour_limit="
				+ unavailability_hour_limit + "]";
	}

	
	
}
