package test;
import java.text.ParseException;
import java.util.Date;

import model.dao.CoordinatorDAO;
import model.dao.StudentDAO;
import model.entity.Coordinator;
import model.entity.Student;


public class TestDAO {

	public static void main(String[] args) {
		TestDAO testStudentDAO = new TestDAO();
//		testStudentDAO.testStudentSave();
//		testStudentDAO.testStudentLogin();
		
		TestDAO testCoordinatorDAO = new TestDAO();
		testCoordinatorDAO.testCoordinatorSave();
	}
	
	public void testStudentSave(){
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student();
		student.setStudent_id(2014001);
		student.setProject_id(2000001);
		student.setUsername("jane2");
		student.setPassword("jane");
		student.setFirst_name("C");
		student.setLast_name("Jane");
		student.setEmail("jane@bham.ac.uk");
		System.out.println(studentDAO.save(student));
	}
	
	public void testStudentLogin(){
		StudentDAO studentDAO = new StudentDAO();
		System.out.println(studentDAO.findByUsernamePassword("cxz413"));
	}
	
	public void testCoordinatorSave(){
		CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
		Coordinator coordinator = new Coordinator();
		coordinator.setPc_id(3);
		coordinator.setTitle("Mr.");
		coordinator.setUsername("Jane2");
		coordinator.setPassword("jane");
		coordinator.setFirst_name("C");
		coordinator.setFirst_name("Jane");
		coordinator.setEmail("jane@bham.ac.uk");
		System.out.println(coordinatorDAO.save(coordinator));
	}
	
}
