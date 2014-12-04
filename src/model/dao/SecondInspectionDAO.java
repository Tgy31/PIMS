package model.dao;
import static tools.Replace.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.skife.csv.CSVReader;
import org.skife.csv.SimpleReader;

import tools.DateConvert;
import model.db.Template;
import model.entity.FirstInspection;
import model.entity.SecondInspection;
import model.mapping.SecondInspectionMapping;

public class SecondInspectionDAO {
	
	private Template template = new Template();
	private CSVReader reader = new SimpleReader();
	
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
	
	public boolean importCSV(File file) {
		List<String[]> recordList = null;
		try {
			recordList = reader.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SecondInspection secondInspection = new SecondInspection();
		boolean hasHeaderRecords = true;
		for (int r = 0; r < recordList.size(); r++) {
			String[] records = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try {
				if(records[0].matches(PATTERN)){
					secondInspection.setStudent_id(Integer.valueOf(records[0]));
				}
				if(records[1].matches(PATTERN)){
					secondInspection.setInspector_id(Integer.valueOf(records[1]));
				}
				if(records[2].matches(PATTERN)){
					secondInspection.setModule_id(Integer.valueOf(records[2]));
				}
				secondInspection.setDate(DateConvert.ConverFromCSVToDate(records[3]));
				secondInspection.setSecond_inspectioncol(records[4]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return save(secondInspection);
	}
}
