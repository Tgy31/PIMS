package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.Course;
import model.entity.Timetable;
import model.mapping.CourseMapping;
import model.mapping.TimetableMapping;

public class TimetableDAO {
	private Template template = new Template();
	
	public boolean save(Timetable timetable){
		String sql = "INSERT INTO timetable"							+ENTER+
							"			(timetable_id, " +
							"			 slot_id)" 				 +ENTER+
							"values"							 					+ENTER+
							"			(?,?)";
		try {
			return (template.update(sql,timetable.getTimetable_id(), timetable.getSlot_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Timetable timetable){
		String sql = "update timetable"								+ENTER+
							"set"													+ENTER+
							"			 slot_id= ?"		+ENTER+
							"where"											+ENTER+
							"			timetable_id = ?";
		try {
			return (template.update(sql, timetable.getSlot_id(), timetable.getTimetable_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByTimetableID(int ID){
		String sql = "delete from timetable where timetable_id = '"+ID+"'";
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
	
	public boolean deleteBySlotID(int ID){
		String sql = "delete from timetable where slot_id = '"+ID+"'";
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
	
	public Timetable findByCourseID(int ID){
		String sql = "SELECT  * " + 
							"FROM timetable " + 
							"WHERE timetable_id= " + "'" + ID + "'";
		List<Timetable> timetables = null;
		try {
			timetables = template.query(sql, new TimetableMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return timetables.get(0);
	}
	
	public Timetable findBySlotID(int ID){
		String sql = "SELECT  * " + 
							"FROM timetable " + 
							"WHERE slot_id= " + "'" + ID + "'";
		List<Timetable> timetables = null;
		try {
			timetables = template.query(sql, new TimetableMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return timetables.get(0);
	}
	
	public Timetable findAll(){
		String sql = "SELECT  * " + 
							"FROM timetable ";
		List<Timetable> timetables = null;
		try {
			timetables = template.query(sql, new TimetableMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return timetables.get(0);
	}
	
}
