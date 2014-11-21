package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.db.Template;
import model.entity.SecondInspection;
import model.mapping.SecondInspectionMapping;

public class SecondInspectionDAO {
	
	private Template template = new Template();
	
	public boolean save(SecondInspection secondInspection){
		String sql = "INSERT INTO second_inspection"			+ENTER+
							"			(student_id, " +
							"			 inspector_id, " 	+
							"			 module_id, " 	+
							"			 date, " 	+
							"			 second_inspectioncol)" 			+ENTER+
							"values"							 					+ENTER+
							"			(?,?,?,?,?)";
		try {
			return (template.update(sql,secondInspection.getStudent_id(),
													secondInspection.getInspector_id(),
													secondInspection.getModule_id(),
													secondInspection.getDate(),
													secondInspection.getSecond_inspectioncol()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(SecondInspection secondInspection){
		String sql = "update second_inspection"								+ENTER+
							"set"													+ENTER+
							"			 inspector_id= ?, "+
							"			 module_id= ?, "+
							"			 date= ?, "+
							"			 second_inspectioncol= ?"		+ENTER+
							"where"											+ENTER+
							"			student_id = ?";
		try {
			return (template.update(sql, secondInspection.getInspector_id(),
														secondInspection.getModule_id(),
														secondInspection.getDate(),
														secondInspection.getSecond_inspectioncol(),
														secondInspection.getStudent_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByStudentID(int ID){
		String sql = "delete from second_inspection where student_id = '"+ID+"'";
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
	
	public boolean deleteByInspectorID(int ID){
		String sql = "delete from second_inspection where inspector_id = '"+ID+"'";
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
	
	public boolean deleteByModuleID(int ID){
		String sql = "delete from second_inspection where module_id = '"+ID+"'";
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
	
	public SecondInspection findByStudentID(int ID){
		String sql = "SELECT  * " + 
							"FROM second_inspection " + 
							"WHERE student_id= " + "'" + ID + "'";
		List<SecondInspection> secondInspection = null;
		try {
			secondInspection = template.query(sql, new SecondInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return secondInspection.get(0);
	}
	
	public SecondInspection findByInspectorID(int ID){
		String sql = "SELECT  * " + 
							"FROM second_inspection " + 
							"WHERE inspector_id= " + "'" + ID + "'";
		List<SecondInspection> secondInspection = null;
		try {
			secondInspection = template.query(sql, new SecondInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return secondInspection.get(0);
	}
	
	public SecondInspection findByModuleID(int ID){
		String sql = "SELECT  * " + 
							"FROM second_inspection " + 
							"WHERE module_id= " + "'" + ID + "'";
		List<SecondInspection> secondInspection = null;
		try {
			secondInspection = template.query(sql, new SecondInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return secondInspection.get(0);
	}
	
	public SecondInspection findByDate(Date date){
		String sql = "SELECT  * " + 
							"FROM second_inspection " + 
							"WHERE date= " + "'" + date + "'";
		List<SecondInspection> secondInspection = null;
		try {
			secondInspection = template.query(sql, new SecondInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return secondInspection.get(0);
	}
	
	public List<SecondInspection> findAll(){
		String sql = "SELECT  * " + 
							"FROM second_inspection ";
		List<SecondInspection> secondInspection = null;
		try {
			secondInspection = template.query(sql, new SecondInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return secondInspection;
	}
	
}
