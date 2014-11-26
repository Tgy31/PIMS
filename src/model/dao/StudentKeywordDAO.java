package model.dao;

import static tools.Replace.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.StudentKeyword;
import model.mapping.StudentKeywordMapping;

public class StudentKeywordDAO {
	private Template template = new Template();
	
	public boolean save(StudentKeyword studentKeyword){
		String sql = "INSERT INTO student_keyword"			+ENTER+
							"			(student_keyword_id, " +
							"			 keyword_id, " 	+
							"			 student_id)" 				+ENTER+
							"values"							 		+ENTER+
							"			(?,?,?)";
		try {
			return (template.update(sql, studentKeyword.getStudent_keyword_id(),
													studentKeyword.getKeyword_id(),
													studentKeyword.getStudent_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(StudentKeyword studentKeyword){
		String sql = "update student_keyword"					+ENTER+
							"set"												+ENTER+
							"			 keyword_id= ?, "+
							"			 student_id= ?"					+ENTER+
							"where"											+ENTER+
							"			student_keyword_id = ?";
		try {
			return (template.update(sql, studentKeyword.getKeyword_id(),
													studentKeyword.getStudent_id(),
													studentKeyword.getStudent_keyword_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByStudentKeywordD(int ID){
		String sql = "delete from student_keyword where student_keyword_id = '"+ID+"'";
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
	
	public StudentKeyword findByStudentKeywordID(int ID){
		String sql = "SELECT  * " + 
							"FROM student_keyword " + 
							"WHERE student_keyword_id= " + "'" + ID + "'";
		List<StudentKeyword> StudentKeywords = null;
		try {
			StudentKeywords = template.query(sql, new StudentKeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return StudentKeywords.get(0);
	}
	
	public List<StudentKeyword> findAll(){
		String sql = "SELECT  * " + 
							"FROM student_keyword ";
		List<StudentKeyword> StudentKeywords = null;
		try {
			StudentKeywords = template.query(sql, new StudentKeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return StudentKeywords;
	}
	
	
}
