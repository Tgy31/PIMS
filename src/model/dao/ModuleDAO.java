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
import model.entity.Module;
import model.mapping.ModuleMapping;

import org.skife.csv.CSVReader;
import org.skife.csv.SimpleReader;

import tools.DateConvert;


public class ModuleDAO {
	private Template template = new Template();
	private CSVReader reader = new SimpleReader();
	
	public boolean save(Module module){
		String sql = "INSERT INTO module"							+ENTER+
							"			(module_id, " +
							"			 module_name, " 	+
							"			 year, " 	+
							"			 start_date, " 	+
							"			 end_date, " 	+
							"			 students_enrolled, " 	+
							"			 inspector_available, " 	+
							"			 default_inspector_capacity, " 	+
							"			 first_inspection_start_date, " 	+
							"			 first_inspection_end_date, " 	+
							"			 second_inspection_start_date, " 	+
							"			 second_inspection_end_date, " 	+
							"			 dissertation_deadline, " 	+
							"			 pc_id)" 				 +ENTER+
							"values"							 					+ENTER+
							"			(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			return (template.update(sql, module.getModule_id(),
													  module.getModule_name(),
													  module.getYear(),
													  module.getStart_date(),
													  module.getEnd_date(),
													  module.getStudents_enrolled(),
													  module.getInspector_available(),
													  module.getDefault_inspector_capacity(),
													  module.getFirst_inspection_start_date(),
													  module.getFirst_inspection_end_date(),
													  module.getSecond_inspection_start_date(),
													  module.getSecond_inspection_end_date(),
													  module.getDisseration_deadline(),
													  module.getPc_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Module module){
		String sql = "update module"								+ENTER+
							"set"													+ENTER+
							"			 module_name= ?, "+
							"			 year= ?, "+
							"			 start_date= ?, "+
							"			 end_date= ?, "+
							"			 students_enrolled= ?, "+
							"			 inspector_available= ?, "+
							"			 default_inspector_capacity= ?, "+
							"			 first_inspection_start_date= ?, "+
							"			 first_inspection_end_date= ?, "+
							"			 second_inspection_start_date= ?, "+
							"			 second_inspection_end_date= ?, "+
							"			 dissertation_deadline= ?, "+
							"			 pc_id= ?"		+ENTER+
							"where"											+ENTER+
							"			module_id = ?";
		try {
			return (template.update(sql, module.getModule_name(),
													  module.getYear(),
													  module.getStart_date(),
													  module.getEnd_date(),
													  module.getStudents_enrolled(),
													  module.getInspector_available(),
													  module.getDefault_inspector_capacity(),
													  module.getFirst_inspection_start_date(),
													  module.getFirst_inspection_end_date(),
													  module.getSecond_inspection_start_date(),
													  module.getSecond_inspection_end_date(),
													  module.getDisseration_deadline(),
													  module.getPc_id(),
													  module.getModule_id()) == 1);
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
		String sql = "delete from module where module_id = '"+ID+"'";
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
	
	public boolean deleteByModuleName(String name){
		String sql = "delete from module where module_name = '"+name+"'";
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
	
	public Module findByModuleID(int ID){
		String sql = "SELECT  * " + 
							"FROM module " + 
							"WHERE module_id= " + "'" + ID + "'";
		List<Module> modules = null;
		try {
			modules = template.query(sql, new ModuleMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return modules.get(0);
	}
	
	public Module findByModuleName(String name){
		String sql = "SELECT  * " + 
							"FROM module " + 
							"WHERE module_name= " + "'" + name + "'";
		List<Module> modules = null;
		try {
			modules = template.query(sql, new ModuleMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return modules.get(0);
	}
	
	public Module findByYear(String year){
		String sql = "SELECT  * " + 
							"FROM module " + 
							"WHERE year= " + "'" + year + "'";
		List<Module> modules = null;
		try {
			modules = template.query(sql, new ModuleMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return modules.get(0);
	}
	
	public Module findByDateScope(Date startDate, Date endDate){
		String sql = "SELECT  * " + 
							"FROM module " + 
							"WHERE start_date>= " + "'" + startDate + "' " +
							"AND " +
							"end_date<= " + "'" + endDate +"'";
		List<Module> modules = null;
		try {
			modules = template.query(sql, new ModuleMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return modules.get(0);
	}
	
	public List<Module> findAll(){
		String sql = "SELECT  * " + 
							"FROM module ";
		List<Module> modules = null;
		try {
			modules = template.query(sql, new ModuleMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return modules;
	}
	
	public boolean importCSV(File file) {
		List<String[]> recordList = null;
		try {
			recordList = reader.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Module module = new Module();
		boolean hasHeaderRecords = true;
		for (int r = 0; r < recordList.size(); r++) {
			String[] records = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try {
				if(records[0].matches(PATTERN)){
					module.setModule_id(Integer.valueOf(records[0]));
				}
				module.setModule_name(records[1]);
				module.setYear(records[2]);
				module.setStart_date(DateConvert.ConverFromCSVToDate(records[3]));
				module.setEnd_date(DateConvert.ConverFromCSVToDate(records[4]));
				module.setStudents_enrolled(Integer.valueOf(records[5]));
				module.setInspector_available(Integer.valueOf(records[6]));
				if(records[7].matches(PATTERN)){
					module.setDefault_inspector_capacity(Integer.valueOf(records[7]));
				}
				module.setFirst_inspection_start_date(DateConvert.ConverFromCSVToDate(records[8]));
				module.setFirst_inspection_end_date(DateConvert.ConverFromCSVToDate(records[9]));
				module.setSecond_inspection_start_date(DateConvert.ConverFromCSVToDate(records[10]));
				module.setSecond_inspection_end_date(DateConvert.ConverFromCSVToDate(records[11]));
				module.setDisseration_deadline(DateConvert.ConverFromCSVToDate(records[12]));
				if(records[13].matches(PATTERN)){
					module.setPc_id(Integer.valueOf(records[13]));
				}
			}catch( NumberFormatException e){
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return save(module);
	}
	
}
