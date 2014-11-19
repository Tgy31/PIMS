package chrisTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;




//fake database
public class Database {

	private DatabaseConnection connection;
	public Map<String, Module> modules;	
	public Map<String, Coordinator> coordinators;		
	public Map<String, Student> students;
	public Map<String, Inspector> inspectors;
	public Map<String, Caseload> caseloads;
	public Map<String, Inspection1> firstInspections;
	public Map<String, Inspection2> secondInspections;
	//public Map<String, Timetable> timetables;
	public ArrayList<String> keywords;
	
	
	//constructor
	public Database(DatabaseConnection dbc) {
		
		this.connection = dbc;
		this.modules = new LinkedHashMap<String, Module>();
		this.coordinators = new LinkedHashMap<String, Coordinator>();
		this.students = new LinkedHashMap<String, Student>();
		this.inspectors = new LinkedHashMap<>();
		this.caseloads = new LinkedHashMap<String, Caseload>();
		this.firstInspections = new LinkedHashMap<>();
		this.secondInspections = new LinkedHashMap<>();
		//this.timetables = new LinkedHashMap<>();
		this.keywords = new ArrayList<String>();
		
	}
	
	public void printInspectors() {
		    Iterator it = this.inspectors.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + ": " + pairs.getValue());
		    }
		    
		    System.out.println("\n===============================================================\n");
		    
	}	
	
	public void printStudents() {
	    Iterator it = this.students.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + ": " + pairs.getValue());
	    }
	    System.out.println("\n===============================================================\n");

	}	
	
	
	
	public void printModules() {
	    Iterator it = this.modules.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + ": " + pairs.getValue());
	    }
	    System.out.println("\n===============================================================\n");

	}
	
	public void printCaseloads() {
	    Iterator it = this.caseloads.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        Caseload cl = ((Caseload) pairs.getValue());
	        
	       Inspector inspector = DatabaseRetrieveMethods.retrieveInspectorByIDSQL((String) pairs.getKey(), this);
	        
	        if (cl == null){
	        	
	        	System.out.println(inspector.getDisplayName() +": lecturer has no caseload at present.");
	        	
	        } else {
	        	System.out.println(cl.getLecturer().getDisplayName() +", "+pairs.getValue());
	        }
		    System.out.println("\n===============================================================\n");

	    }
	    System.out.println("\n===============================================================\n");

	}		
	
	public DatabaseConnection getConnection() {
		
		return connection;
		
	}
	

	

	
	public String printKeywords() {
		
		String kw = "";
		
		for (String key : this.keywords)
			kw+=key+", ";
		
		
		return kw;
	}

	
	
	
	
}
