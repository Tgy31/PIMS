package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.db.Template;
import model.entity.Course;
import model.entity.Module;
import model.mapping.CourseMapping;
import model.mapping.ModuleMapping;


public class ModuleDAO {
	private Template template = new Template();
	
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
	
}
