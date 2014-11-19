package chrisTest;

import java.sql.Date;

public class DatabaseInsertMethods {
	
	
	
	//Method for inserting module to database
	public static boolean insertModuleSQL(Module m, Database db) {
		db.modules.put(m.getModule_id(), m);
		return true;
	}	
	
	
	
	//insert coordinator into database
	public static boolean insertCoordinatorSQL(Coordinator c, Database db) {
		
		db.coordinators.put(c.getStaffID(), c);
		return true;		
	}

	public static boolean insertInspectorSQL(Inspector i, Database db) {
		
		db.inspectors.put(i.getStaffID(), i);
		return true;
		
		
	}
	
	public static boolean insertStudentSQL(Student s, Database db) {
		db.students.put(s.getStudentID(), s);
		return true;
	
	}	
	
	
	public static boolean insertCaseloadSQL(Inspector i, Database db) {
		db.caseloads.put(i.getStaffID(), i.getCaseload());
		return true;
	
	}	
	
	
	
	public static boolean insertProjectSQL(Project p) {
		
		return true;
		
		
	}
	
	public static boolean insertStaffSQL(Inspector i) {		
		
		return true;
	
	}	
	
	

	
	
	public static boolean insertInspection1SQL(Inspection1 i) {
		return true;
	
	}
	
	public static boolean insertInspection2SQL(Inspection2 i) {
		return true;
	
	}	
	
}

