package model.dao;
import static tools.Replace.ENTER;
import static tools.Replace.PATTERN;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
							"			(inspectionweek_id, " 	+
							"			 student_id, " 	+
							"			 first_inspector_id, " 	+
							"			 second_inspector_id, " 	+
							"			 start_date, " 	+
							"			 end_date) " 	    +
							"values"							+ENTER+
							"			(?,?,?,?,?,?)";
		try {
			return (template.update(sql, inspection.getInspectionweek_id(),
					                     inspection.getStudent_id(),
					                     inspection.getFirst_inspector_id(),
					                     inspection.getSecond_inspector_id(),
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
							"			 first_inspector_id= ?, "+
							"			 second_inspector_id= ?, "+
							"			 start_date= ?, "+
							"			 end_date= ?"					+ENTER+
							"where"										+ENTER+
							"			student_id = ?";
		try {
			return (template.update(sql, inspection.getInspection_id(),
                    inspection.getInspectionweek_id(),
                    inspection.getFirst_inspector_id(),
                    inspection.getSecond_inspector_id(),
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
	
	public Inspection findByStudentAndInspectionWeek(int studentID, int inspectionWeekID){
		String sql = "SELECT  * " + 
							"FROM inspection " + 
							"WHERE student_id= " + "'" + studentID + "'" +
							"AND inspectionweek_id = '" + inspectionWeekID +"'";
		List<Inspection> inspections= null;
		try {
			inspections = template.query(sql, new InspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspections.size() > 0 ? inspections.get(0) : null;
	}
	
	public Inspection findByID(int ID){
		String sql = "SELECT  * " + 
							"FROM inspection " + 
							"WHERE inspection_id= " + "'" + ID + "'";
		List<Inspection> inspections= null;
		try {
			inspections = template.query(sql, new InspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspections.size() > 0 ? inspections.get(0) : null;
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
	
	public boolean deleteByFirstInspectorID(int ID){
		String sql = "delete from inspection where first_inspector_id = '"+ID+"'";
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
	
	
	public boolean deleteBySecondInspectorID(int ID){
		String sql = "delete from inspection where second_inspector_id = '"+ID+"'";
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
	
	public List<Inspection> inspectionsForFirstInspectorID(int inspectorID, int inspectionWeekID) {
		String sql = "SELECT  * " + 
				"FROM inspection " + 
				"WHERE first_inspector_id= " + "'" + inspectorID + "'" +
				"AND inspectionweek_id = '" + inspectionWeekID +"'";
		List<Inspection> inspections= null;
		try {
		inspections = template.query(sql, new InspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspections;
	}
	
	public List<Inspection> inspectionsForSecondInspectorID(int inspectorID, int inspectionWeekID) {
		String sql = "SELECT  * " + 
				"FROM inspection " + 
				"WHERE second_inspector_id= " + "'" + inspectorID + "'" +
				"AND inspectionweek_id = '" + inspectionWeekID +"'";
		List<Inspection> inspections= null;
		try {
		inspections = template.query(sql, new InspectionMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspections;
	}
	
	public List<Inspection> inspectionsForInspectorID(int inspectorID, int inspectionWeekID) {
		List<Inspection> inspectionsAsFirstInspector = this.inspectionsForFirstInspectorID(inspectorID, inspectionWeekID);
		//List<Inspection> inspectionsAsSecondInspector = this.inspectionsForSecondInspectorID(inspectorID, inspectionWeekID);
		
		List<Inspection> allInspections = new ArrayList<Inspection>();
		allInspections.addAll(inspectionsAsFirstInspector);
		//allInspections.addAll(inspectionsAsSecondInspector); // Second inspection does not count in load
		return allInspections;
	}
	
	

}
