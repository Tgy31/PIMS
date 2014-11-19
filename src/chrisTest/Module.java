package chrisTest;

import java.sql.Date;
import java.util.ArrayList;

public class Module {
	
	//Field variables. Corresponding database value in comments above.
	
	//unique id for use in database as primary key
	private String module_id;
	
	//Module code	char	15	Y	N
	private String moduleCode;//suggest we change this to VARCHAR in database as it does not need to act as an integer
	
	//Module name	varchar	50		N	
	private String moduleName;
	
	//Year	char	20
	private String moduleYear;
	
	//concatenation of code + name + year
	private String displayName;
	
	//Project Coordinator#1
	private Coordinator pc1, pc2;
	
	//First inspection start date	datetime	15	
	private Date firstInspectionStartDate;
	
	//First inspection end date	datetime	15	
	private Date firstInspectionEndDate;	
	
	private Schedule firstInspectionSchedule;
	
	//Second inspection start date	datetime	15
	private Date secondInspectionStartDate;
	
	//Second inspection start date	datetime	15
	private Date secondInspectionEndDate;
	
	private Schedule secondInspectionSchedule;

	
	//I know I put this in the prototypes, but not sure why. I don't think we will need it
	//Dissertation deadline	datetime	20			
	private Date disserationDeadline;
	
	//object used for connecting to the database
	private DatabaseConnection connection;
	
	//Array list of students enrolled on this module
	private ArrayList<Student> students;
	
	private int defaultCapacity, numberOfStudents, numberOfInspectors;

	private Database database;

	private Schedule firstSecondSchedule;

	private Schedule secondSecondSchedule;
	
	public Module(String code, String name, String year, Database db, Coordinator pc1, Coordinator pc2) {
		
		this.moduleCode = code;
		this.moduleName = name;
		this.moduleYear = year;
		this.displayName = (moduleName + " (" + moduleCode + ") " + moduleYear);
		this.database = db;
		this.connection = database.getConnection();
		this.module_id = connection.nextModuleID() + "";
		this.students = new ArrayList<Student>();
		this.pc1 = pc1;
		this.pc2 = pc2;
		database = db;
		DatabaseInsertMethods.insertModuleSQL(this, db);
		
	}
	

	
	
	public void setFirstInspectionSchedule() {
		
		this.firstInspectionSchedule = new Schedule(this, this.firstInspectionStartDate, this.firstInspectionEndDate, 1200000, 24, database);
		
	}
	
	
	public void setSecondInspectionSchedule() {
		
		this.secondInspectionSchedule = new Schedule(this, this.secondInspectionStartDate, this.secondInspectionEndDate, 1800000, 16, database);
		
	}	
	
	
	
	
	
	
	
	//allocates a first inspector and set
	public void matchAllocateInspectors() {
		
		
		
		
	}
	
	
	public void matchSecondInspections() {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	@Override
	
	public String toString() {
	
		String inspection1, inspection2; 
		int numStudents = students.size();
		
		if (this.firstInspectionStartDate == null || this.firstInspectionEndDate == null)
			inspection1 = "Dates of first inspection week not set.";
		else
			inspection1 = firstInspectionStartDate.toString() + " to " + firstInspectionEndDate.toString();
		
		if (this.secondInspectionStartDate == null || this.secondInspectionEndDate == null)
			inspection2 = "Dates of second inspection week not set.";
		else
			inspection2 = secondInspectionStartDate.toString() + " to " + secondInspectionEndDate.toString();
		
		
		return 			displayName + "\n" + 
						"Coordinators: " + pc1.getDisplayName() + ", " + pc2.getDisplayName() + "\n" +
						"Dates of first inspecton week: " + inspection1 + "\n" + 
						"Dates of second inspecton week: " + inspection2 + "\n" + 
						"Number of students: " + numStudents +
						"\n===================================================\n";
		
		
	}

	
	
	
	
	
	
	
	
	
	





	//Getters and setters
	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleYear() {
		return moduleYear;
	}

	public void setModuleYear(String moduleYear) {
		this.moduleYear = moduleYear;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Coordinator getPc1() {
		return pc1;
	}

	public void setPc1(Coordinator pc1) {
		this.pc1 = pc1;
	}

	public Coordinator getPc2() {
		return pc2;
	}

	public void setPc2(Coordinator pc2) {
		this.pc2 = pc2;
	}

	public Date getFirstInspectionStartDate() {
		return firstInspectionStartDate;
	}

	public void setFirstInspectionStartDate(Date firstInspectionStartDate) {
		this.firstInspectionStartDate = firstInspectionStartDate;
	}

	public Date getFirstInspectionEndDate() {
		return firstInspectionEndDate;
	}

	public void setFirstInspectionEndDate(Date firstInspectionEndDate) {
		this.firstInspectionEndDate = firstInspectionEndDate;
	}

	public Date getSecondInspectionStartDate() {
		return secondInspectionStartDate;
	}

	public void setSecondInspectionStartDate(Date secondInspectionStartDate) {
		this.secondInspectionStartDate = secondInspectionStartDate;
	}

	public Date getSecondInspectionEndDate() {
		return secondInspectionEndDate;
	}

	public void setSecondInspectionEndDate(Date secondInspectionEndDate) {
		this.secondInspectionEndDate = secondInspectionEndDate;
	}

	public Date getDisserationDeadline() {
		return disserationDeadline;
	}

	public void setDisserationDeadline(Date disserationDeadline) {
		this.disserationDeadline = disserationDeadline;
	}

	public DatabaseConnection getConnection() {
		return connection;
	}

	public void setConnection(DatabaseConnection connection) {
		this.connection = connection;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public int getDefaultCapacity() {
		return defaultCapacity;
	}

	public void setDefaultCapacity(int defaultCapacity) {
		this.defaultCapacity = defaultCapacity;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public int getNumberOfInspectors() {
		return numberOfInspectors;
	}

	public void setNumberOfInspectors(int numberOfInspectors) {
		this.numberOfInspectors = numberOfInspectors;
	}




	public Schedule getFirstInspectionSchedule() {

		return firstInspectionSchedule;
	}




	public Schedule getSecondInspectionSchedule() {
		return secondInspectionSchedule;
	}
	
	
}
