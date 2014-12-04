package model.dao;
import static tools.Replace.ENTER;
import static tools.Replace.PATTERN;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import model.db.Template;
import model.entity.FirstInspection;
import model.mapping.FirstInspectionMapping;

import org.skife.csv.CSVReader;
import org.skife.csv.SimpleReader;

import tools.DateConvert;

public class FirstInspectionDAO {
	
	private Template template = new Template();
	private CSVReader reader = new SimpleReader();
	
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
	
	public boolean importCSV(File file) {
		List<String[]> recordList = null;
		try {
			recordList = reader.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FirstInspection firstInspection = new FirstInspection();
		boolean hasHeaderRecords = true;
		for (int r = 0; r < recordList.size(); r++) {
			String[] records = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try {
				if(records[0].matches(PATTERN)){
					firstInspection.setStudent_id(Integer.valueOf(records[0]));
				}
				if(records[1].matches(PATTERN)){
					firstInspection.setInspector_id(Integer.valueOf(records[1]));
				}
				if(records[2].matches(PATTERN)){
					firstInspection.setModule_id(Integer.valueOf(records[2]));
				}
				firstInspection.setDate(DateConvert.ConverFromCSVToDate(records[3]));
				firstInspection.setFirst_inspectioncol(records[4]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return save(firstInspection);
	}
	
}
