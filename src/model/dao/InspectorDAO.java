package model.dao;

import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.entity.Inspector;
import model.db.Template;
import model.mapping.InspectorMapping;

public class InspectorDAO {
	private Template template = new Template();
	
	public boolean save(Inspector inspector){
		String sql = "INSERT INTO inspector"+ENTER+
							"			(inspector_id, " +
							"			 timetable_id, " 	+
							"			 capacity, "+
							"			 username, "+
							"			 password, "+
							"			 title, "   +
							"			 first_name, "+
							"			 last_name, "+
							"			 keywords, "+
							"			 email)" 				 +ENTER+
							"values"							 +ENTER+
							"			(?,?,?,?,?,?,?,?,?,?)";
		try {
			return (template.update(sql, inspector.getInspector_id(), 
													  inspector.getTimetable_id(), 
													  inspector.getCapacity(), 
													  inspector.getUsername(), 
													  inspector.getPassword(), 
													  inspector.getTitle(), 
													  inspector.getFirst_name(), 
													  inspector.getLast_name(), 
													  inspector.getKeywords(), 
													  inspector.getEmail()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Inspector inspector){ //µ½µ×Ö÷¼üÊÇË­£¿£¿
		String sql = "update inspector"				+ENTER+
				"set"												+ENTER+
				"			 timetable_id= ?, "+
				"			 capacity= ?, "+
				"			 username= ?, "+
				"			 password= ?, "+
				"			 title= ?, "+
				"			 first_name= ?, "+
				"			 last_name= ?, "+
				"			 keywords= ?, "+
				"			 email= ?"							+ENTER+
				"where"											+ENTER+
				"			inspector_id = ?";
		try {
			return (template.update(sql, inspector.getTimetable_id(), 
													  inspector.getCapacity(), 
													  inspector.getUsername(), 
													  inspector.getPassword(), 
													  inspector.getTitle(), 
													  inspector.getFirst_name(), 
													  inspector.getLast_name(), 
													  inspector.getKeywords(), 
													  inspector.getEmail(),
													  inspector.getInspector_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByInspectorID(int ID){
		String sql = "delete from inspector where inspector_id = '"+ID+"'";
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
	
	public boolean deleteByInspectorName(String firstName, String lastName){
		String sql = "delete from inspector where first_name = '"+firstName+"'"+
																" last_name = '"+lastName+"'";
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
	
	public boolean deleteByInspectorUsername(String name){
		String sql = "delete from inspector where username = '"+name+"'";
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
	
	public Inspector findByInspectorID(int ID){
		String sql = "SELECT * " + 
							"FROM inspector " + 
							"WHERE inspector_id= ? ";
		Inspector inspector = null;
		List<Inspector> inspectors = null;
		try {
			inspectors = template.query(sql, new InspectorMapping(), ID);
			if (inspectors.size() != 0) {
				inspector = inspectors.get(0);
			}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Find by Username and Password is failed");
			}
		return inspector;
	}
	
	public Inspector findByUsername(String username){
		String sql = "SELECT * " + 
							"FROM inspector " + 
							"WHERE username= ? ";
		Inspector inspector = null;
		List<Inspector> inspectors = null;
		try {
			inspectors = template.query(sql, new InspectorMapping(), username);
			if (inspectors.size() != 0) {
				inspector = inspectors.get(0);
			}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Find by Username and Password is failed");
			}
		return inspector;
	}
	
	public Inspector findByInspectorName(String firstName, String lastName){
		String sql = "SELECT * " + 
							"FROM inspector " + 
							"WHERE first_name= ? "+
							"AND last_name= ?";
		Inspector inspector = null;
		List<Inspector> inspectors = null;
		try {
			inspectors = template.query(sql, new InspectorMapping(), firstName, lastName);
			if (inspectors.size() != 0) {
				inspector = inspectors.get(0);
			}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Find by Username and Password is failed");
			}
		return inspector;
	}
	
	public List<Inspector> findAll(){
		String sql = "SELECT * " + 
							"FROM inspector";
		List<Inspector> inspectors = null;
		try {
			inspectors = template.query(sql, new InspectorMapping());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return inspectors;
	}
	
}
