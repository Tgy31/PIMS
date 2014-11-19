package chrisTest;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student {

	private String studentID; //I think this should be the student's ID number which is guaranteed to be unique
	private String userLogIn;//substring of e-mail, nice and easy
	private String password;//randomly set when object is created
	private String title;
	private String firstName; 
	private String lastName; 
	private String displayName;//concatenation of first and last name
	private String bhamEmail;//test to check if it ends in "bham.ac.uk"
	private String secondEmail;//optional. Not sure if this is really needed
	private String mobile;//optional. Not sure if this is really needed
	private String module_id;
	private Module module;
	private String course_id;
	private String project_ID;
	private String projectTitle;
	private Project project;
	private String supervisorID;
	private Inspector supervisor;
	private String inspector1ID;
	private Inspector inspector1;
	private String inspector2ID;
	private Inspector inspector2;
	private Date firstInspection;
	private Date secondInspection;
	private Database database;
	
	public Student(String id, String fName, String lName, Module m, String email, String supID, String project, Database db) throws IllegalArgumentException {
		this.database = db;

		this.studentID = id;
		this.firstName = fName;
		this.lastName = lName;
		this.displayName = firstName + " " + lastName;
		this.module = m;
		this.bhamEmail = email;
		this.supervisorID = supID;
		this.supervisor = getSupervisorByID(supervisorID);
		this.projectTitle = project;
		
		if (!checkValidEmail(bhamEmail))
			throw new IllegalArgumentException("Please enter a valid University of Birmingham e-mail address ending in 'bham.ac.uk'");
		
		this.userLogIn = getUserLogIn();
		this.project = new Project(this, supervisor, projectTitle, database);
		DatabaseInsertMethods.insertStudentSQL(this,db);
		supervisor.addNewSupervisee(this);
	}
	
	
	
	
	
	//checks last ten characters of e-mail are 'bham.ac.uk'
	private boolean checkValidEmail(String email) {
	
		return true;
	}
	
	//looks up inspector in database. Needs exception handling for SQL errors when implemented for real ("throws SQLException")
	private Inspector getSupervisorByID(String id) {
		return DatabaseRetrieveMethods.retrieveInspectorByIDSQL(id, database);
	}
	
	
	
	public ArrayList<Inspector> findBestMatchForKeywords() {
		
		ArrayList<Inspector> matches = new ArrayList<Inspector>();
		ArrayList<Inspector> inspectors = DatabaseRetrieveMethods.retrieveAllInspectors(database);
		int numMatches = 0;
		
		for (Inspector i : inspectors) {
			
			numMatches = compareKeywords(this.project.getKeywords(), i.getKeywords());
			System.out.println(i.getDisplayName() + "'s keywords:" + i.printKeywords());
			System.out.println(i.getDisplayName() + " has " + numMatches + " keyword in common with the project.");
			System.out.println("=======================================\n");
		}

		return matches;
	}
	
	private int compareKeywords(ArrayList<String> projectKW, ArrayList<String> inspectorKW) {
		
		int matches = 0;
		
		for (String kw : projectKW) {
			
			if (inspectorKW.contains(kw))
				matches++;
			
		}		
		
		
		return matches;
	}
	
	public boolean arrangeFirstInspection(Date fi, Inspector i1) {
		
		this.inspector1 = i1;
		this.inspector1.inspector1AddStudent(this);
		this.firstInspection = fi;
		Inspection1 firstInspection = new Inspection1(fi, this, i1, module);
		firstInspection.uploadToDatabase();
		DatabaseUpdateMethods.updateStudentInspector1(this, this.inspector1);
		return true;
	}
	
	public boolean arrangeSecondInspection(Date si, Inspector i1, Inspector i2) {
		
		this.inspector1 = i1;
		this.inspector2 = i2;		
		this.inspector1.inspector1AddStudent(this);
		this.secondInspection = si;
		Inspection2 secondInspection = new Inspection2(si, this, i1, i2, module);
		secondInspection.uploadToDatabase();
		DatabaseUpdateMethods.updateStudentInspector1(this, this.inspector1);
		return true;
	}	
	
	//needs to set date of first inspection and to include SQL for updating database ("throws SQLException")
	public boolean setDateFirstInspection(Date fi) {
		
		this.firstInspection = fi;
		
		//SQL to go here need try / catch declaration
		DatabaseUpdateMethods.setDateFirstInspectionSQL(this, inspector1, module, fi);
		
		return true;
	}
	
	//needs to set date of first inspection and to include SQL for updating database ("throws SQLException")
	public void setDateSecondInspection(Date si) {
		
		this.secondInspection = si;
		
		//SQL method to go here need try / catch declaration
		DatabaseUpdateMethods.setDateSecondInspectionSQL(this, inspector1, inspector2, module, si);

		
	}

	
	//removes student from students database
	public void archiveStudent() {
		
		
		
	}
	
	@Override
	
	public String toString() {
		
		String dateFirst, dateSecond,ins1,ins2,date1st;
		
		if (firstInspection==null)
			dateFirst = "First inspection not set yet";
		else
			dateFirst = firstInspection.toString();
		
		if (secondInspection==null)
			dateSecond = "Second inspection not set yet";
		else
			dateSecond = secondInspection.toString();		
		
		
		if (inspector1==null)
			ins1 =  "Not allocated";
		else 
			ins1 = inspector1.getDisplayName();
			
		if (inspector2==null)
			ins2 =  "Not allocated";
		else 
			ins2 = inspector2.getDisplayName();		
	
		
		
		
		return 	"Student ID: " + studentID +"\n" +
				"Student login: " + userLogIn +"\n" +		
				"Student Name: " + displayName + "\n" +	
				"Email: " + bhamEmail + "\n" +
				"Module:" + module.getDisplayName() + "\n" +
				"Project Title: " + projectTitle + "\n" +
				"Supervisor: " + supervisor.getDisplayName() + "\n" +		
				"Inspector 1:" +ins1 + "\n" +
				"Inspector 2:" +ins2 + "\n" +
				"Date 1st Inspection: " + dateFirst + "\n" +
				"Date 2nd Inspection: " + dateSecond +
				"\n===================================================\n";
	}
	
	
	
	//standard getters and setters
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getUserLogIn() {
	
		String[] parts = this.bhamEmail.split("@");
		
		
		return parts[0];
	}

	public void setUserLogIn(String userLogIn) {
		this.userLogIn = userLogIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getBhamEmail() {
		return bhamEmail;
	}

	public void setBhamEmail(String bhamEmail) {
		this.bhamEmail = bhamEmail;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(String project_ID) {
		this.project_ID = project_ID;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	public Inspector getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Inspector supervisor) {
		this.supervisor = supervisor;
	}

	public String getInspector1ID() {
		return inspector1ID;
	}

	public void setInspector1ID(String inspector1id) {
		inspector1ID = inspector1id;
	}

	public String getInspector2ID() {
		return inspector2ID;
	}

	public void setInspector2ID(String inspector2id) {
		inspector2ID = inspector2id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Inspector getInspector1() {
		return inspector1;
	}

	public void setInspector1(Inspector inspector1) {
		this.inspector1 = inspector1;
	}

	public Inspector getInspector2() {
		return inspector2;
	}

	public void setInspector2(Inspector inspector2) {
		this.inspector2 = inspector2;
	}

	public Date getFirstInspection() {
		return firstInspection;
	}

	public void setFirstInspection(Date firstInspection) {
		this.firstInspection = firstInspection;
	}

	public Date getSecondInspection() {
		return secondInspection;
	}

	public void setSecondInspection(Date secondInspection) {
		this.secondInspection = secondInspection;
	}
	
	
	
}
