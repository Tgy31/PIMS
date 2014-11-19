package chrisTest;

import java.sql.Date;


//fake methods for test database. In reality will need proper SQL and appropriate error handling
public class DatabaseUpdateMethods {

	
	
	
	//methods for updating modules
	

	

	
	
	
	//method for updating caseload
	public static boolean updateCaseloadSQL(Inspector i, Database db) {
		
		db.caseloads.put(i.getStaffID(), i.getCaseload());
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	public static boolean setDateFirstInspectionSQL(Student student, Inspector inspector1, Module module, Date date) {
		
		
		//method will need to update inspection table with SQL and return true if successful and false if not
		
		
		return true;
	}
	
	
	
	public static boolean setDateSecondInspectionSQL(Student student, Inspector inspector1, Inspector inspector2, Module module, Date date) {
		
		
		//method will need to update inspection table with SQL and return true if successful and false if not
		
		
		return true;
	}

		//updates the students database record with details of new Inspector1. Also updates Inspector1 records with details of new student
	public static void updateStudentInspector1(Student studentPlay,	Inspector inspector1) {
		
		//need SQL to update student record in database with new inspector1
		//need SQL to update inspector1 record in database with new student
		
		
		return;
	}




	
	
}
