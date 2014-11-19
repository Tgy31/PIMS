package chrisTest;

import java.util.ArrayList;

public class Caseload {

	private Inspector lecturer;
	private ArrayList<Student> supervisees;
	private ArrayList<Student> inspector1for;
	private ArrayList<Student> inspector2for;
	private Database database;
	private int caseloadSize;
	
	public Caseload(Inspector l, Database db) {
		
		this.lecturer = l;
		this.database = db;
		supervisees = new ArrayList<Student>();
		inspector1for = new ArrayList<Student>();		
		inspector2for = new ArrayList<Student>();		
		DatabaseInsertMethods.insertCaseloadSQL(lecturer, database);
		this.setCaseloadSize(0);
	}
	
	public boolean addSupervisee(Student s) {

		supervisees.add(s);
		DatabaseUpdateMethods.updateCaseloadSQL(lecturer, database);
		return true;
	}

	public int getNumSupervisees() {
		return supervisees.size();
	}

	public int getNumInspector1for() {
		return inspector1for.size();
	}
	
	public int getNumInspector2for() {
		return inspector2for.size();
	}

	public Inspector getLecturer() {
		return lecturer;
	}

	public void setLecturer(Inspector lecturer) {
		this.lecturer = lecturer;
	}

	public ArrayList<Student> getSupervisees() {
		return supervisees;
	}

	public void setSupervisees(ArrayList<Student> supervisees) {
		this.supervisees = supervisees;
	}

	public ArrayList<Student> getInspector1for() {
		return inspector1for;
	}

	public void setInspector1for(ArrayList<Student> inspector1for) {
		this.inspector1for = inspector1for;
	}

	public ArrayList<Student> getInspector2for() {
		return inspector2for;
	}

	public void setInspector2for(ArrayList<Student> inspector2for) {
		this.inspector2for = inspector2for;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public String printSupervisees() {

		String supervising = "";
		if (this.supervisees.size() == 0) 
			return "Not currently supervising any student projects";
		
		else {
			for (Student s : this.supervisees)
				supervising+=s.getDisplayName()+", ";
		}
		return supervising;
	}

	public String printInspector1For() {

		String ins1for = "";
		if (this.inspector1for.size() == 0) 
			return "Not currently acting as first inspector for any students.";
		
		else {
			for (Student s : this.inspector1for)
				ins1for+=s.getDisplayName()+", ";
		}
		return ins1for;
	}

	public String printInspector2For() {

		String ins2for = "";
		if (this.inspector2for.size() == 0) 
			return "Not currently acting as second inspector for any students.";
		
		else {
			for (Student s : this.inspector2for)
				ins2for+=s.getDisplayName()+", ";
		}
		return ins2for;
	}


	
	
	
	
	
	
	
	
	
	
	
	public String toString() {
		
		String cl = "Supervising:" + "\n" + printSupervisees() + "\nInspector 1 for: " + printInspector1For() + "\nInspector 2 for: " + printInspector2For() ;
		

		
		
		return cl;
	}

	public int getCaseloadSize() {
		this.caseloadSize = supervisees.size() + inspector1for.size() + inspector2for.size();
		return caseloadSize;
	}

	public void setCaseloadSize(int caseloadSize) {
		this.caseloadSize = caseloadSize;
	}
	
	
	
}
