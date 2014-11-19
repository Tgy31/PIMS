package chrisTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class DatabaseRetrieveMethods {

	public static Module retrieveModuleByIDSQL(String id, Database db) {
		
		
		return db.modules.get(id);
	}	
	

	public static Coordinator retrieveCoordinatorByIDSQL(String id, Database db) {
		
		
		return db.coordinators.get(id);
	}	
	
	public static Inspector retrieveInspectorByIDSQL(String id, Database db) {
		
		return db.inspectors.get(id);
	}	
	
	
	public static Student retrieveStudentByIDSQL(String id, Database db) {
		
		
		return db.students.get(id);	}

	public static ArrayList<String> getSomeKeywords(Database db){
		
		ArrayList<String> myKW = new ArrayList<String>();
		String keyword1 = db.keywords.get(getRandom(db.keywords.size()));
		String keyword2 = db.keywords.get(getRandom(db.keywords.size()));
		String keyword3 = db.keywords.get(getRandom(db.keywords.size()));
		String keyword4 = db.keywords.get(getRandom(db.keywords.size()));
		String keyword5 = db.keywords.get(getRandom(db.keywords.size()));
		String keyword6 = db.keywords.get(getRandom(db.keywords.size()));
		
		if (!myKW.contains(keyword1))
			myKW.add(keyword1);
		if (!myKW.contains(keyword2))		
			myKW.add(keyword2);
		if (!myKW.contains(keyword3))		
			myKW.add(keyword3);
		if (!myKW.contains(keyword4))		
			myKW.add(keyword4);
		if (!myKW.contains(keyword5))			
			myKW.add(keyword5);
		if (!myKW.contains(keyword6))			
			myKW.add(keyword6);
		
		return myKW;
	} 

	public static int getRandom(int x) {
		
		double rand = Math.random();
		
		
		
		
		return (int) (x * rand);
		
	}


	public static ArrayList<Inspector> retrieveAllInspectors(Database database) {
	    Iterator it = database.inspectors.entrySet().iterator();
	    
	    ArrayList<Inspector> inspectors = new ArrayList<>();
	    
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        inspectors.add((Inspector) pairs.getValue());
	    }
		
		
		
		return inspectors;
	}
	
}
