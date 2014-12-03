package test;
import java.io.File;
import java.text.ParseException;
import java.util.Date;

import model.dao.CoordinatorDAO;
import model.dao.CourseDAO;
import model.dao.FirstInspectionDAO;
import model.dao.InspectorDAO;
import model.dao.SlotDAO;
import model.dao.StudentDAO;
import model.entity.Coordinator;
import model.entity.Course;
import model.entity.FirstInspection;
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
		
//*********First_Inspection****
//		testDAO.testFirstInspectionSave();
//		testDAO.testFirstInspectionFind();
		
//**********Course**********
//		testDAO.testCourseSave();
//		testDAO.testCourseUpdate();
//		testDAO.testCourseFind();
//		testDAO.testCourseDeleteByID();
		
//**********Student**********		
		testDAO.testStudentImportCSV("G:/TestData.csv");
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
	public void testInspectorFind(){
		InspectorDAO inspectorDAO = new InspectorDAO();
//		System.out.println(inspectorDAO.findAll());
//		System.out.println(inspectorDAO.findByInspectorID(2));
		System.out.println(inspectorDAO.findByInspectorName("Zhiwei", "Liu"));
	}
	
	
	//======Test First Inspection=============
	public void testFirstInspectionSave() throws ParseException{
		Date date1=null;
		date1 = DateConvert.StringConvertToTimestamp("2014-11-21 14:32:2");
		
		FirstInspectionDAO firstInspectionDAO = new FirstInspectionDAO();
		FirstInspection firstInspection = new FirstInspection();
		firstInspection.setStudent_id(10002);
		firstInspection.setInspector_id(12);
		firstInspection.setModule_id(2);
		firstInspection.setDate(date1);
		System.out.println(firstInspectionDAO.save(firstInspection));
	}
	
	public void testFirstInspectionFind(){
		FirstInspectionDAO firstInspectionDAO = new FirstInspectionDAO();
		System.out.println(firstInspectionDAO.findAll());
		System.out.println(firstInspectionDAO.findByStudentID(10003));
		System.out.println(firstInspectionDAO.findByModuleID(2));
		
	}
	
	
	
	//=======Test Course==================
	public void testCourseSave(){
		CourseDAO courseDAO = new CourseDAO();
		Course course = new Course();
		course.setCourse_id(3);
		course.setCourse_name("Robotics");
		course.setCoures_description("Robotics");
		System.out.println(courseDAO.save(course));
	}
	
	public void testCourseUpdate(){
		CourseDAO courseDAO = new CourseDAO();
		Course course = new Course();
		course.setCourse_id(3);
		course.setCourse_name("Robotics");
		course.setCoures_description("no description at the moment");
		System.out.println(courseDAO.update(course));
	}
	
	public void testCourseFind(){
		CourseDAO courseDAO = new CourseDAO();
		System.out.println(courseDAO.findAll());
		System.out.println(courseDAO.findByCourseID(5));
		System.out.println(courseDAO.findByCourseName("Advanced Computer Science"));
	}
	
	public void testCourseDeleteByID(){
		CourseDAO courseDAO = new CourseDAO();
		System.out.println(courseDAO.deleteByCourseID(3));
	}
	
	
	//========Test Student=================
	public void testStudentImportCSV(String path) throws Exception{
		StudentDAO studentDAO = new StudentDAO();
		Module module = new Module(); 
		System.out.println(studentDAO.importCSV(new File(path), module));
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
