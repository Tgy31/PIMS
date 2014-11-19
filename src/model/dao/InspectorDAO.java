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
			return (template.update(sql, inspector.getInspector_id(), inspector.getTimetable_id(), inspector.getCapacity(), inspector.getUsername(), inspector.getPassword(), inspector.getTitle(), inspector.getFirst_name(), inspector.getLast_name(), inspector.getKeywords(), inspector.getEmail()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public Inspector findByUsernamePassword(String username){
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
}
