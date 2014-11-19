package chrisTest;

public class Coordinator {

	private String firstName, lastName, displayName, staffID;
	private Database database;
	
	
	public Coordinator(String fn, String ln, String id, Database db) {
		
		this.firstName = fn;
		this.lastName = ln;
		this.displayName = fn + " " + ln;
		this.staffID = id;
		this.database = db;
		
		DatabaseInsertMethods.insertCoordinatorSQL(this, database);
	}
	
	@Override
	
	public String toString() {
		
		
		return this.displayName + "\n===================================================\n";
		
	}

	public String getStaffID() {
		return this.staffID;
	}

	public String getDisplayName() {
		return this.displayName;
	}
	
	

}
