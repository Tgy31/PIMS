package model.dao;
import static tools.Replace.ENTER;
import static tools.Replace.PATTERN;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.db.Template;
import model.entity.Module;
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
							"			 module_id)"		 +ENTER+
							"values"							 +ENTER+
							"			(?,?,?,?,?,?,?,?,?,?)";
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
	
	public boolean deleteALLByModuleId(int ID){
		String sql = "delete from student where module_id = '"+ID+"'";
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
		return students.size() > 0 ? students.get(0) : null;
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
	
	public boolean importCSV(File file, Module module){
//		truncateTable();
		Map<String, Integer> titleName = new HashMap<String, Integer>();
		List<String[]> recordList = null;
		String[] record = null;
		int count=0;
		
		try {
			recordList = reader.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Student student = new Student();
		boolean hasHeaderRecords = true;
		
		for (String title : recordList.get(0)) {
			if (title.toLowerCase().contains("student") && title.toLowerCase().contains("id"))
				titleName.put("studentID", count);
			if (title.toLowerCase().contains("project") && title.toLowerCase().contains("title"))
				titleName.put("projectTitle", count);
			if (title.toLowerCase().contains("first") && title.toLowerCase().contains("name"))
				titleName.put("firstName", count);
			if (title.toLowerCase().contains("supervisor") && title.toLowerCase().contains("name"))
				titleName.put("supervisorName", count);
			if (title.toLowerCase().contains("last") && title.toLowerCase().contains("name")) {
				titleName.put("lastName", count);
			}
			
			count++;
		}
		
		for (int r = 0; r < recordList.size(); r++) {
			record = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try{
				student.setFirst_name(record[titleName.get("firstName")]);
				student.setLast_name(record[titleName.get("lastName")]);
				student.setProject_title(record[titleName.get("projectTitle")]);
				student.setUsername(record[titleName.get("lastName")]);
				student.setPassword(record[titleName.get("lastName")]);
				student.setEmail(record[titleName.get("firstName")].substring(0,1)+"."+record[titleName.get("lastName")]+"@bham.ac.uk");
				student.setSupervisor(record[titleName.get("supervisorName")]);
				student.setModule_id(Integer.valueOf(module.getModule_id()));
				
/*				if(records[titleName.get("studentID")].matches(PATTERN)){
					student.setStudent_id(Integer.valueOf(records[titleName.get("studentID")]));
				}*/
//				student.setProject_description(record[titleName.get("")]);
//				if(record[titleName.get("")].matches(PATTERN)){
//					student.setTimetable_id(Integer.valueOf(record[titleName.get("")]));
//				}
			} catch( NumberFormatException e){
				e.printStackTrace();
			}
			save(student);
		}
		return true;
	}
}
