package chrisTest;

import java.sql.Date;

public class Inspection2 {

	Date date;
	Student student;
	Inspector inspector1;
	Inspector inspector2;
	Module module;
	
	
	//constructor
	public Inspection2(Date d, Student s, Inspector i1, Inspector i2, Module m) {
		
		this.student = s;
		this.inspector1 = i1;
		this.inspector2 = i2;
		this.module = m;
		
	}
	
	public boolean uploadToDatabase() {
		
		
		
		return true;
	}

}