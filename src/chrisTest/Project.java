package chrisTest;

import java.util.ArrayList;

public class Project {

	private Student student;
	private Inspector supervisor;
	private String projectTitle;
	private String projectDescription;
	private ArrayList<String> keywords;
	private Database database;
	
	public Project(Student stud, Inspector spvsr, String title, Database db) {
		
		this.student = stud;
		this.supervisor = spvsr;
		this.projectDescription = title;
		this.database = db;
		this.keywords = setRandomKeywords();
		
		
	}
	
	
	public ArrayList<String> setRandomKeywords() {
		
		return DatabaseRetrieveMethods.getSomeKeywords(database);
		
	}


	public ArrayList<String> getKeywords() {

		return keywords;
	}
	
	
}
