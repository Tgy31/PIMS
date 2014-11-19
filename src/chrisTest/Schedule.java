package chrisTest;

import java.sql.Date;
import java.util.ArrayList;

public class Schedule {

	private Module module;
	private String timeTableID;
	private Date startDate, endDate;
	private long slotLength;
	private int lengthInDays;
	private TimeSlot[][] slots;
	private int numSlots; 
	private int slotsPerDay;
	private Database database;
	
	public Schedule(Module m, Date start, Date end, long sl, int spd, Database db) {
		this.module = m;
		this.database = db;
		this.timeTableID = db.getConnection().nextScheduleID+"";

		this.startDate = start;
		this.endDate = end;
		this.slotLength = sl;
		this.lengthInDays = ((DateSetter) start).getDifferenceBetweenTwoDates((DateSetter) end)+1;
		this.slotsPerDay = spd;
		numSlots = slotsPerDay * lengthInDays;
		if (numSlots < (module.getNumberOfStudents() * 1.2))
			throw new IllegalArgumentException("Number of slots available should be at least 20% larger than number of students enrolled. Number of Students: "  +module.getNumberOfStudents());
		slots = new TimeSlot[lengthInDays][slotsPerDay];
		this.populateSlots();
		this.uploadToDatabase();
		
	}

	public boolean uploadToDatabase() {
		
		
		
		return true;
	}
	
	
	private void populateSlots() {
		long currentSlot;
		for (int i = 0; i < this.lengthInDays; i++) {
			long daysPassed = (i * 8640000);
			currentSlot = (((DateSetter) startDate).getLong()) + daysPassed + 540000;
			for (int j = 0; j < this.slotsPerDay; j++) {
				Date slotStart = new DateSetter(currentSlot);
				this.slots[i][j] = new TimeSlot(slotStart, this.slotLength);
				
			}
			
			
		}
		
	}
	
	
	public void setRandomAvailability(Inspector ins) {
		
		ArrayList<TimeSlot> unavailable = new ArrayList<TimeSlot>();

		for (int i = 0; i < this.lengthInDays; i++) {
			for (int j = 0; j < this.slotsPerDay; j++) {
				
				double random = Math.random();
				if (random < 0.15)	
					unavailable.add(slots[i][j]);

			}
		}
		
		
		
		
		ins.setUnavailableForSchedule(unavailable);
		
		
	}
	
	
	@Override
	public String toString() {
		
		String separator = "|----------";
		
		for (int i = 0; i < lengthInDays; i++) {
			
			separator+="|--------------";
		}
		
		separator="\n" + separator + "\n";
		String schedule = "|   time   ";
		long currentLong = ((DateSetter) this.startDate).getLong(); 
		long day = 86400000;
		Date currentDate; 
		for (int i = 0; i < lengthInDays; i++) {
			if (i > 0)				
				currentLong+=day;
			currentDate = new DateSetter(currentLong);
			String header = currentDate.toString();
			schedule+=("|  " +header+ "  ");
			
		}
		
		schedule+=separator;
		for (int i = 0; i < slotsPerDay; i++) {
			schedule+=("|          ");
			for (int j = 0; j < lengthInDays; j++) {
				
				schedule+=("|" + slots[j][i]);
				
			}
			
			schedule+=(separator);

		}
		return schedule;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getTimeTableID() {
		return timeTableID;
	}

	public void setTimeTableID(String timeTableID) {
		this.timeTableID = timeTableID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getSlotLength() {
		return slotLength;
	}

	public void setSlotLength(long slotLength) {
		this.slotLength = slotLength;
	}

	public int getLengthInDays() {
		return lengthInDays;
	}

	public void setLengthInDays(int lengthInDays) {
		this.lengthInDays = lengthInDays;
	}

	public TimeSlot[][] getSlots() {
		return slots;
	}

	public void setSlots(TimeSlot[][] slots) {
		this.slots = slots;
	}

	public int getNumSlots() {
		return numSlots;
	}

	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}

	public int getSlotsPerDay() {
		return slotsPerDay;
	}

	public void setSlotsPerDay(int slotsPerDay) {
		this.slotsPerDay = slotsPerDay;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
	
	
	
}
