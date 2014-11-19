package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.entity.Student;
import model.db.Template;
import model.mapping.StudentMapping;

public class StudentDAO {
	private Template template = new Template();
	
	public boolean save(Student student){
		String sql = "INSERT INTO student"+ENTER+
							"			(student_id, " +
							"			 project_id, " 	+
							"			 username, "+
							"			 password, "+
							"			 first_name, "+
							"			 last_name, "+
							"			 email)" 				 +ENTER+
							"values"							 +ENTER+
							"			(?,?,?,?,?,?,?)";
		try {
			return (template.update(sql, student.getStudent_id(), student.getProject_id(), student.getUsername(), student.getPassword(),student.getFirst_name(), student.getLast_name(), student.getEmail()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public Student findByUsernamePassword(String username){
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
}
