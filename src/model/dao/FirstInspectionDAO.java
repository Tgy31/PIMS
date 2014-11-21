package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.db.Template;
import model.entity.FirstInspection;
import model.mapping.FirstInspectionMapping;

public class FirstInspectionDAO {
	
	private Template template = new Template();
	
	public boolean save(FirstInspection firstInspection){
		String sql = "INSERT INTO first_inspection"							+ENTER+
							"			(student_id, " +
							"			 inspector_id, " 	+
							"			 module_id, " 	+
							"			 date, " 	+
							"			 first_inspectioncol)" 				 +ENTER+
							"values"							 					+ENTER+
							"			(?,?,?,?,?)";
		try {
			return (template.update(sql,firstInspection.getStudent_id(),
													firstInspection.getInspector_id(),
													firstInspection.getModule_id(),
													firstInspection.getDate(),
													firstInspection.getFirst_inspectioncol()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(FirstInspection firstInspection){
		String sql = "update first_inspection"								+ENTER+
							"set"													+ENTER+
							"			 inspector_id= ?, "+
							"			 module_id= ?, "+
							"			 date= ?, "+
							"			 first_inspectioncol= ?"		+ENTER+
							"where"											+ENTER+
							"			student_id = ?";
		try {
			return (template.update(sql, firstInspection.getInspector_id(),
													  firstInspection.getModule_id(),
													  firstInspection.getDate(),
													  firstInspection.getFirst_inspectioncol(),
													  firstInspection.getStudent_id()) == 1);
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
		String sql = "delete from first_inspection where student_id = '"+ID+"'";
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
		String sql = "delete from first_inspection where inspector_id = '"+ID+"'";
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
		String sql = "delete from first_inspection where module_id = '"+ID+"'";
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
	
	public FirstInspection findByStudentID(int ID){
		String sql = "SELECT  * " + 
							"FROM first_inspection " + 
							"WHERE student_id= " + "'" + ID + "'";
		List<FirstInspection> firstInspection = null;
		try {
			firstInspection = template.query(sql, new FirstInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return firstInspection.get(0);
	}
	
	public FirstInspection findByInspectorID(int ID){
		String sql = "SELECT  * " + 
							"FROM first_inspection " + 
							"WHERE inspector_id= " + "'" + ID + "'";
		List<FirstInspection> firstInspection = null;
		try {
			firstInspection = template.query(sql, new FirstInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return firstInspection.get(0);
	}
	
	public FirstInspection findByModuleID(int ID){
		String sql = "SELECT  * " + 
							"FROM first_inspection " + 
							"WHERE module_id= " + "'" + ID + "'";
		List<FirstInspection> firstInspection = null;
		try {
			firstInspection = template.query(sql, new FirstInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return firstInspection.get(0);
	}
	
	public FirstInspection findByDate(Date date){
		String sql = "SELECT  * " + 
							"FROM first_inspection " + 
							"WHERE date= " + "'" + date + "'";
		List<FirstInspection> firstInspection = null;
		try {
			firstInspection = template.query(sql, new FirstInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return firstInspection.get(0);
	}
	
	public List<FirstInspection> findAll(){
		String sql = "SELECT  * " + 
							"FROM first_inspection ";
		List<FirstInspection> firstInspection = null;
		try {
			firstInspection = template.query(sql, new FirstInspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return firstInspection;
	}
	
}
