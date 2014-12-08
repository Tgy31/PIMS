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
import model.entity.Keyword;
import model.entity.Slot;
import model.mapping.InspectionMapping;
import model.mapping.KeywordMapping;
import model.mapping.SlotMapping;

public class InspectionDAO {
	private Template template = new Template();
	
	public boolean save(Inspection inspection){
		String sql = "INSERT INTO inspection"					+ENTER+
							"			(inspection_id, " +
							"			 inspectionweek_id, " 	+
							"			 student_id, " 	+
							"			 inspector_id, " 	+
							"			 start_date, " 	+
							"			 end_date, " 	    +
							"values"							+ENTER+
							"			(?,?,?,?,?,?)";
		try {
			return (template.update(sql, inspection.getInspection_id(),
					                     inspection.getInspectionweek_id(),
					                     inspection.getStudent_id(),
					                     inspection.getInspector_id(),
					                     inspection.getStart_date(),
					                     inspection.getEnd_date())== 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	
	public boolean update(Inspection inspection){
		String sql = "update inspection"								+ENTER+
							"set"										+ENTER+
						    "			 inspection_id= ?, "+
						    "			 inspectionweek_id= ?, "+
							"			 inspector_id= ?, "+
							"			 start_date= ?, "+
							"			 end_date= ?"					+ENTER+
							"where"										+ENTER+
							"			student_id = ?";
		try {
			return (template.update(sql, inspection.getInspection_id(),
                    inspection.getInspectionweek_id(),
                    inspection.getInspector_id(),
                    inspection.getStart_date(),
                    inspection.getEnd_date(),
                    inspection.getStudent_id())== 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public List<Inspectionweek> findByStudentID(int ID){
		String sql = "SELECT  * " + 
							"FROM inspection " + 
							"WHERE student_id= " + "'" + ID + "'";
		List<Inspectionweek> inspectionweeks= null;
		try {
			inspectionweeks = template.query(sql, new InspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectionweeks;
	}
	
	public boolean deleteByStudentID(int ID){
		String sql = "delete from inspection where student_id = '"+ID+"'";
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
		String sql = "delete from inspection where inspector_id = '"+ID+"'";
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
	
	

	

}
