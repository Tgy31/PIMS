package model.dao;
import static tools.Enter.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.entity.Coordinator;
import model.entity.Student;
import model.db.Template;
import model.mapping.CoordinatorMapping;
import model.mapping.StudentMapping;

public class CoordinatorDAO {
	private Template template = new Template();
	
	public boolean save(Coordinator coordinator){
		String sql = "INSERT INTO project_coordinator"+ENTER+
							"			(pc_id, " +
							"			 title, " 	+
							"			 username, "+
							"			 password, "+
							"			 first_name, "+
							"			 last_name, "+
							"			 email)" 				 +ENTER+
							"values"							 +ENTER+
							"			(?,?,?,?,?,?,?)";
		try {
			return (template.update(sql,coordinator.getPc_id(), coordinator.getTitle(), coordinator.getUsername(), coordinator.getPassword(),coordinator.getFirst_name(), coordinator.getLast_name(), coordinator.getEmail()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public Coordinator findByUsernamePassword(String username, String password){
		String sql = "SELECT * " + 
							"FROM project_coordinator " + 
							"WHERE username= ? " + 
							"and password= ?";
		Coordinator coordinator = null;
		List<Coordinator> coordinators = null;
		try {
			coordinators = template.query(sql, new CoordinatorMapping(), username, password);
			if (coordinators.size() != 0) {
				coordinator = coordinators.get(0);
			}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found !");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Find by Username and Password is failed");
			}
		return coordinator;
	}
}
