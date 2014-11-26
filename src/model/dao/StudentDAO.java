package model.dao;
import static tools.Replace.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.Student;
import model.mapping.StudentMapping;

import org.skife.csv.CSVReader;
import org.skife.csv.SimpleReader;

public class StudentDAO {
	private Template template = new Template();
	private CSVReader reader = new SimpleReader();
	
	public boolean save(Student student){
		String sql = "INSERT INTO student"		+ENTER+
							"			(student_id, " +
							"			 first_name, "+
							"			 last_name, "+
							"			 email, " 		+
							"			 project_title, " +
							"			 project_description, " +
							"			 supervisor, "+
							"			 username, "+
							"			 password, "+
							"			 timetable_id, "+
							"			 course_id, "+	
							"			 module_id)"		 +ENTER+
							"values"							 +ENTER+
							"			(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			return (template.update(sql, student.getStudent_id(), 
													student.getFirst_name(),
													student.getLast_name(),
													student.getEmail(),
													student.getProject_title(),
													student.getProject_description(),
													student.getSupervisor(),
													student.getUsername(),
													student.getPassword(),
													student.getTimetable_id(),
													student.getCourse_id(),
													student.getModule_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	
	public boolean update(Student student){
		String sql = "update student"									+ENTER+
							"set"														+ENTER+
							"			 first_name= ?, "+
							"			 last_name= ?, "+
							"			 email= ?, " 		+
							"			 project_title= ?, " +
							"			 project_description= ?, " +
							"			 supervisor= ?, "+
							"			 username= ?, "+
							"			 password= ?, "+
							"			 timetable_id= ?, "+
							"			 course_id= ?, "+
							"			 module_id= ?"					+ENTER+
							"where"											+ENTER+
							"			student_id = ?";
		try {
			return (template.update(sql, student.getFirst_name(),
															student.getLast_name(),
															student.getEmail(),
															student.getProject_title(),
															student.getProject_description(),
															student.getSupervisor(),
															student.getUsername(),
															student.getPassword(),
															student.getTimetable_id(),
															student.getCourse_id(),
															student.getModule_id(),
															student.getStudent_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByID(int ID){
		String sql = "delete from student where student_id = '"+ID+"'";
		try {
			return (template.update(sql) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByName(String firstName, String lastName){
		String sql = "delete from student where first_name = " + "'"+firstName+ "'" + 
																"and last_name = " + "'"+lastName+"'";			
		try {
			return (template.update(sql) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}
	
	public Student findByStudentID(int ID){
		String sql = "SELECT  * " + 
							"FROM student " + 
							"WHERE student_id= " + "'" + ID + "'";
		List<Student> students = null;
		try {
			students = template.query(sql, new StudentMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return students.get(0);
	}
	
	public Student findByUsername(String username){
		String sql = "SELECT * " + 
							"FROM student " + 
							"WHERE username= ? ";
		Student student = null;
		List<Student> students = null;
		try {
			students = template.query(sql, new StudentMapping(), username);
			if (students.size() != 0) {
				student = students.get(0);
			}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Find by Username and Password is failed");
			}
		return student;
	}
	
	
	public Student findBySupervisor(String ID){
		String sql = "SELECT  * " + 
							"FROM student " + 
							"WHERE supervisor= " + "'" + ID + "'";
		List<Student> students = null;
		try {
			students = template.query(sql, new StudentMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return students.get(0);
	}
	
	public Student findByCourseID(int ID){
		String sql = "SELECT  * " + 
							"FROM student " + 
							"WHERE course_id= " + "'" + ID + "'";
		List<Student> students = null;
		try {
			students = template.query(sql, new StudentMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return students.get(0);
	}
	
	public List<Student> findByModuleID(int ID){
		String sql = "SELECT  * " + 
							"FROM student " + 
							"WHERE module_id= " + "'" + ID + "'";
		List<Student> students = null;
		try {
			students = template.query(sql, new StudentMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return students;
	}
	
	/**
	 * 
	 * @param module name 
	 * @param please give a module year
	 * @return
	 */
	public List<Student> findByModuleName(String name, String year){ 
		String sql = "SELECT  * " + 
							"FROM student, module " + 
							"WHERE module.module_name= " + "'" + name + "' " +
							"AND " +
							"module.year= " + "'" + year + "'";
		List<Student> students = null;
		try {
			students = template.query(sql, new StudentMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return students;
	}
	
	
	public List<Student> findAll(){
		String sql = "SELECT * " + 
							"FROM student ";
		List<Student> students = null;
		try {
			students = template.query(sql, new StudentMapping());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return students;
	}
	
	public boolean importCSV(File file){
		List<String[]> recordList = null;
		try {
			recordList = reader.parse(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Student student = new Student();
		boolean hasHeaderRecords = true;
		for (int r = 0; r < recordList.size(); r++) {
			String[] records = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try{
				
				if(records[0].matches(PATTERN)){
					student.setStudent_id(Integer.valueOf(records[0]));
				}
				student.setFirst_name(records[1]);
				student.setLast_name(records[2]);
				student.setEmail(records[3]);
				student.setProject_title(records[5]);
				student.setProject_description(records[6]);
				student.setSupervisor(records[7]);
				student.setUsername(records[8]);
				student.setPassword(records[9]);
				if(records[10].matches(PATTERN)){
					student.setTimetable_id(Integer.valueOf(records[10]));
				}
				if(records[11].matches(PATTERN)){
					student.setCourse_id(Integer.valueOf(records[11]));
				}
				if(records[12].matches(PATTERN)){
					student.setModule_id(Integer.valueOf(records[12]));
				}
			} catch( NumberFormatException e){
				e.printStackTrace();
			}
		}
		return save(student);
	}
}
