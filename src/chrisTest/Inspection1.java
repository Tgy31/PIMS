package chrisTest;

import java.sql.Date;

public class Inspection1 {

	Date date;
	Student student;
	Inspector inspector1;
	Inspector inspector2;
	Module module;
	
	//constructor for first inspection
	public Inspection1(Date d, Student s, Inspector i1, Module m) {
		
		this.student = s;
		this.inspector1 = i1;
		this.module = m;
		
	}
	
	public boolean uploadToDatabase() {
		
		
		
		return true;
	}
	
	
}
