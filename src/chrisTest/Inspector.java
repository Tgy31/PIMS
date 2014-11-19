package chrisTest;

import java.sql.Date;
import java.util.ArrayList;

public class Inspector {

	private String staffID, title, firstName, lastName, displayName, email;
	private Caseload caseload;
	private int capacity, currentLoad, remainingCapacity, numSupervisees, numInspector1For, numInspector2For, caseloadSize;
	private ArrayList<String> keywords;
	private Database database;
	
	public Inspector(String id, String t, String fn, String ln, String e, ArrayList<String> kw, Database db){
		
		this.staffID = id;
		this.title = t;
		this.firstName = fn;
		this.lastName = ln;
		this.displayName = (title + " " + firstName + " " + lastName).trim();
		this.keywords = kw;
		this.database = db;
		this.caseload = new Caseload(this, database);
		this.numSupervisees = caseload.getNumSupervisees();
		this.numInspector1For = caseload.getNumInspector1for();
		this.numInspector2For = caseload.getNumInspector2for();
		DatabaseInsertMethods.insertInspectorSQL(this, db);

	}
	
	public void setUnavailableForSchedule(ArrayList<TimeSlot> ts) {
		
		for (TimeSlot t : ts)
			t.getInspectorsUnavailable().add(this);		
		
	}	
	
	public String printAvailabilityForSchedule(Schedule s) {
		
String separator = "|----------";
		String name = this.displayName + "'s availability: \n";
		for (int i = 0; i < s.getLengthInDays(); i++) {
			
			separator+="|--------------";
		}
		
		separator="\n" + separator + "\n";
		String schedule = "|   time   ";
		long currentLong = ((DateSetter) s.getStartDate()).getLong(); 
		long day = 86400000;
		Date currentDate; 
		for (int i = 0; i < s.getLengthInDays(); i++) {
			if (i > 0)				
				currentLong+=day;
			currentDate = new DateSetter(currentLong);
			String header = currentDate.toString();
			schedule+=("|  " +header+ "  ");
			
		}
		
		schedule+=separator;
		for (int i = 0; i < s.getSlotsPerDay(); i++) {
			schedule+=("|          ");
			for (int j = 0; j < s.getLengthInDays(); j++) {
				
				if (!s.getSlots()[j][i].getInspectorsUnavailable().contains(this))
					schedule+=("|" + s.getSlots()[j][i]);
				else
					schedule+=("|" + "     N/A      ");
			}
			
			schedule+=(separator);

		}
		return name + schedule;
		
	}
	
	public void setUnavailableForTimeslot(TimeSlot ts) {
		
		ts.setAvailability(false);		
		
	}
	
	
	
	public void addNewSupervisee(Student student) {
		caseload.addSupervisee(student);
		remainingCapacity = capacity - caseload.getCaseloadSize();
		
	}
	
	
	public void addNewFirstInspection(Student student) throws IllegalArgumentException{
		
		if (caseload.getSupervisees().contains(student))
			throw new IllegalArgumentException(displayName + " is already supervising " + student.getDisplayName()+ "and cannot be inspector as well.");
		else {
			caseload.getInspector1for().add(student);
			
		}
	}

	//adds a student to the list of students inspector is doing first inspection for and adjusts remaining capacity
	public void inspector1AddStudent(Student student) {

	}

	public String printKeywords() {
		
		String kw = "";
		
		for (String key : this.keywords)
			kw+=key+", ";
		
		
		return kw;
	}
	
	@Override
	
	public String toString() {
		
		return 	"Inspector name: " + this.displayName + "\n" +
				"Areas of expertise: " + printKeywords() + "\n"  +
				"Supervising: " + caseload.printSupervisees() +
				"\n===================================================\n";
	}
	
	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
		this.remainingCapacity = capacity;
	}

	public int getCurrentLoad() {
		return currentLoad;
	}

	public void setCurrentLoad(int currentLoad) {
		this.currentLoad = currentLoad;
	}

	public int getRemainingCapacity() {
		return remainingCapacity;
	}

	public void setRemainingCapacity(int remainingCapacity) {
		this.remainingCapacity = remainingCapacity;
	}

	public int getNumSupervisees() {
		return numSupervisees;
	}

	public void setNumSupervisees(int numSupervisees) {
		this.numSupervisees = numSupervisees;
	}

	public int getNumInspector1For() {
		return numInspector1For;
	}

	public void setNumInspector1For(int numInspector1For) {
		this.numInspector1For = numInspector1For;
	}

	public int getNumInspector2For() {
		return numInspector2For;
	}

	public void setNumInspector2For(int numInspector2For) {
		this.numInspector2For = numInspector2For;
	}


	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	public Caseload getCaseload() {
		return this.caseload;
	}

	public int getCaseloadSize() {
	
		
		return this.caseload.getCaseloadSize();
	}
	
	
	
}
