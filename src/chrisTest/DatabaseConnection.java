package chrisTest;

public class DatabaseConnection {

	//variable storing the next available id to be used as primary key in the database
	int nextModuleID;
	int nextScheduleID;
	int nextInspection1ID;
	int nextInspection2ID;
	
	
	public DatabaseConnection() {
		
		nextModuleID 		= 100000;
		nextScheduleID		= 200000;
		nextInspection1ID	= 500000;;
		nextInspection2ID	= 600000;;
		
	}
	
	//real method will need exception handling
	public void connectToDatabase() {
		
		System.out.println("Attempting to connect to database server . . . ");
		
		if (true)
			System.out.println("Successfully connected to database.");
		else
			//throw exception here
			System.out.println("Error: Unable to connect to database.");	
	
		System.out.println("\n===============================================\n");	
	}
	
	//method to return the next available id to be used as primary key in database. Increments nextID by one when used.
	public int nextModuleID() {
		
		int currentID = nextModuleID;
		nextModuleID = nextModuleID+1;
		return currentID;
		
	}
	
	public int nextScheduleID() {
		
		int currentID = nextScheduleID;
		nextScheduleID = nextScheduleID+1;
		return currentID;
		
	}
	
}
