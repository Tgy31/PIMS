package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.Course;
import model.mapping.CourseMapping;

public class CourseDAO {
	private Template template = new Template();
	
	public boolean save(Course course){
		String sql = "INSERT INTO course"							+ENTER+
							"			(course_id, " +
							"			 course_name, " 	+
							"			 course_description)" 				 +ENTER+
							"values"							 					+ENTER+
							"			(?,?,?)";
		try {
			return (template.update(sql,course.getCourse_id(),
													course.getCourse_name(),
													course.getCoures_description()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Course course){
		String sql = "update course"								+ENTER+
							"set"													+ENTER+
							"			 course_name= ?, "+
							"			 course_description= ?"		+ENTER+
							"where"											+ENTER+
							"			course_id = ?";
		try {
			return (template.update(sql, course.getCourse_name(),
														  course.getCoures_description(),
														  course.getCourse_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByCourseID(int ID){
		String sql = "delete from course where course_id = '"+ID+"'";
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
	
	public boolean deleteByCourseName(String name){
		String sql = "delete from course where course_name = " + "'"+name+ "'";			
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
	
	public Course findByCourseID(int ID){
		String sql = "SELECT  * " + 
							"FROM course " + 
							"WHERE course_id= " + "'" + ID + "'";
		List<Course> courses = null;
		try {
			courses = template.query(sql, new CourseMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return courses.get(0);
	}
	
	public Course findByCourseName(String name){
		String sql = "SELECT  * " + 
							"FROM course " + 
							"WHERE course_name= " + "'" + name + "'";
		List<Course> courses = null;
		try {
			courses = template.query(sql, new CourseMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return courses.get(0);
	}
	
	public List<Course> findAll(){
		String sql = "SELECT  * " + 
							"FROM course ";
		List<Course> courses = null;
		try {
			courses = template.query(sql, new CourseMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return courses;
	}
}
