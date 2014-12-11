package model.dao;
import static tools.Replace.ENTER;
import static tools.Replace.PATTERN;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.Inspection;
import model.entity.Inspectionweek;
import model.mapping.InspectionweekMapping;

public class InspectionweekDAO {
	private Template template = new Template();
	
	public boolean save(Inspectionweek inspectionweek){
		String sql = "INSERT INTO inspectionweek"					+ENTER+
							"			(inspectionweek_id, " 	+
							"			 module_id, " 	+
							"			 inspection_title, " 	+
							"			 start_date, " 	+
							"values"							+ENTER+
							"			(?,?,?,?)";
		try {
			return (template.update(sql, inspectionweek.getInspectionweek_id(),
					                     inspectionweek.getModule_id(),
					                     inspectionweek.getInspection_title(),
					                     inspectionweek.getStart_date())== 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Inspectionweek inspectionweek){
		String sql = "update inspectionweek"								+ENTER+
							"set"										+ENTER+
							"			 inspection_title= ?, "+
							"			 start_date= ?"					+ENTER+
							"where"										+ENTER+
							"			inspectionweek_id = ?";
		try {
			return (template.update(sql,
                    inspectionweek.getInspection_title(),
                    inspectionweek.getStart_date(),
                    inspectionweek.getInspectionweek_id())== 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByModuleID(int ID){
		String sql = "delete from inspectionweek where module_id = '"+ID+"'";
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
	
	public Inspectionweek findByID(int ID){
		String sql = "SELECT  * " + 
							"FROM inspectionweek " + 
							"WHERE inspectionweek_id= " + "'" + ID + "'";
		List<Inspectionweek> inspectionweeks = null;
		try {
			inspectionweeks = template.query(sql, new InspectionweekMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return (inspectionweeks.size() > 0) ? inspectionweeks.get(0) : null;
	}
	
	public List<Inspectionweek> findByModuleID(int ID){
		String sql = "SELECT  * " + 
							"FROM inspectionweek " + 
							"WHERE module_id= " + "'" + ID + "'";
		List<Inspectionweek> inspectionweeks = null;
		try {
			inspectionweeks = template.query(sql, new InspectionweekMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectionweeks;
	}
	
	public List<Inspectionweek> findAll(){
		String sql = "SELECT  * " + 
							"FROM inspectionweek ";
		List<Inspectionweek> inspectionweeks = null;
		try {
			inspectionweeks = template.query(sql, new InspectionweekMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectionweeks;
	}
	
}
