package chrisTest;
import java.sql.Date;
import java.util.ArrayList;


public class TimeSlot {

	private Date startTime, endTime;
	private long duration;
	private boolean availability;
	private ArrayList<Inspector> inspectorsUnavailable;
	private ArrayList<Student>   studentsUnavailable;	
	private Inspection1 inspection1;
	private Inspection2 inspection2;
	
	
	public TimeSlot(Date start, long d) {
		
		this.startTime = start;
		this.duration = d;
		this.endTime = ((DateSetter) start).getEndTime(duration);
		this.availability = true;
		this.inspectorsUnavailable = new ArrayList<Inspector>();
		this.studentsUnavailable = new ArrayList<Student>();
		
		
	}

	@Override
	public String toString() {
		
		if (availability)
			return"     FREE     ";
		else
			return"     N/A      ";
		
		

	}
	
	
	//getters and setters
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public ArrayList<Inspector> getInspectorsUnavailable() {
		return inspectorsUnavailable;
	}

	public void setInspectorsUnavailable(ArrayList<Inspector> inspectorsUnavailable) {
		this.inspectorsUnavailable = inspectorsUnavailable;
	}

	public ArrayList<Student> getStudentsUnavailable() {
		return studentsUnavailable;
	}

	public void setStudentsUnavailable(ArrayList<Student> studentsUnavailable) {
		this.studentsUnavailable = studentsUnavailable;
	}

	public Inspection1 getInspection1() {
		return inspection1;
	}

	public void setInspection1(Inspection1 inspection1) {
		this.inspection1 = inspection1;
	}

	public Inspection2 getInspection2() {
		return inspection2;
	}

	public void setInspection2(Inspection2 inspection2) {
		this.inspection2 = inspection2;
	}
	
	
	
	
	
}
