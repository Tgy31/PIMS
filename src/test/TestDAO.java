package test;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import model.dao.CoordinatorDAO;
import model.dao.InspectorDAO;
import model.dao.SlotDAO;
import model.dao.StudentDAO;
import model.entity.Coordinator;
import model.entity.Module;
import model.entity.Student;
import tools.DateConvert;


public class TestDAO {

	public static void main(String[] args) throws Exception {
		TestDAO testDAO = new TestDAO();
		
		
		
		
//*********Timetable***********		

		
//*********Slot***********
//		testDAO.testSlotImportCSV("F:/slot-c.csv");
//		testDAO.testSlotFind();
		
//*********Module***********
		
		
//*********Inspector**********		
//		testDAO.testInspectorFind();
//		testDAO.testInspectorImportCSV("G:/TestData.csv");
		testDAO.testInspectorExportCSV("G:/OutputData.csv");
		
//*********First_Inspection****
//		testDAO.testFirstInspectionSave();
//		testDAO.testFirstInspectionFind();
		
//**********Course**********
//		testDAO.testCourseSave();
//		testDAO.testCourseUpdate();
//		testDAO.testCourseFind();
//		testDAO.testCourseDeleteByID();
		
//**********Student**********		
//		testDAO.testStudentImportCSV("G:/TestData.csv");
//		testDAO.testInspectorImportCSV("G:/TestData.csv");
//		testDAO.testStudentExportCSV("G:/OutputData.csv");
//		testDAO.testStudentSave();
//		testDAO.testStudentLogin();
//		testDAO.testStudentFindByID();
//		testDAO.testStudentDeleteByID();
//		testDAO.testStudentDeleteByName();
//		testDAO.testStudentFindByModuleName();
		
//*********Project-Coordinator******		
//		testDAO.testCoordinatorSave();
		
	}

	//======Test Slot================
	public void testSlotImportCSV(String path) throws Exception{
		
		SlotDAO slotDAO = new SlotDAO();
		System.out.print(slotDAO.importCSV(new File(path)));
		
	}
	
	public void testSlotFind(){
		SlotDAO slotDAO = new SlotDAO();
		System.out.print(slotDAO.findAll());
	}
	
	
	//======Test Inspector================
	
	public void testInspectorExportCSV(String path) throws IOException{
		InspectorDAO inspectortDAO = new InspectorDAO();
		inspectortDAO.exportCSV(new File(path));
	}
	
	public void testInspectorFind(){
		InspectorDAO inspectorDAO = new InspectorDAO();
//		System.out.println(inspectorDAO.findAll());
//		System.out.println(inspectorDAO.findByInspectorID(2));
		System.out.println(inspectorDAO.findByInspectorName("Zhiwei", "Liu"));
	}
	
	public void testInspectorImportCSV(String path){
		InspectorDAO inspectorDAO = new InspectorDAO();
		System.out.println(inspectorDAO.importCSV(new File(path)));
	}
		

	
	
	//========Test Student=================
	
	public void testStudentExportCSV(String path) throws IOException{
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.exportCSV(new File(path));
	}
	
	public void testStudentImportCSV(String path) throws Exception{
		StudentDAO studentDAO = new StudentDAO();
		Module module = new Module(); 
		module.setModule_id(26581); 
		System.out.println(studentDAO.importCSV(new File(path), module));
	}
	
	
	
	
	public void testStudentSave(){
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student();
		student.setStudent_id(2014001);
		student.setUsername("jane2");
		student.setPassword("jane");
		student.setFirst_name("C");
		student.setLast_name("Jane");
		student.setEmail("jane@bham.ac.uk");
		System.out.println(studentDAO.save(student));
	}
	
	public void testStudentFindByID(){
		StudentDAO studentDAO = new StudentDAO();
		System.out.println(studentDAO.findByStudentID(10002).getFirst_name());
	}
	
	public void testStudentFindByModuleName(){
		StudentDAO studentDAO = new StudentDAO();
		System.out.println(studentDAO.findByModuleName("BSc project", "2014/15"));
	}
	
	
	public void testStudentDeleteByID(){
		StudentDAO studentDAO = new StudentDAO();
		System.out.println(studentDAO.deleteByID(10003));
	}
	
	public void testStudentDeleteByName(){
		StudentDAO studentDAO = new StudentDAO();
		System.out.println(studentDAO.deleteByName("test", "t"));
	}
	
	public void testStudentLogin(){
		StudentDAO studentDAO = new StudentDAO();
	}
	
	//=========Test Project-Coordinator===========
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
