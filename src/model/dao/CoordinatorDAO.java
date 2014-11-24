package model.dao;
import static tools.Replace.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.skife.csv.CSVReader;
import org.skife.csv.SimpleReader;

import tools.DateConvert;
import model.db.Template;
import model.entity.Coordinator;
import model.entity.Slot;
import model.mapping.CoordinatorMapping;

public class CoordinatorDAO {
	private Template template = new Template();
	private CSVReader reader = new SimpleReader();
	
	public boolean save(Coordinator coordinator){
		String sql = "INSERT INTO project_coordinator"+ENTER+
							"			(username, " +
							"			 password, "+
							"			 pc_id, "+
							"			 title, " 	+
							"			 first_name, "+
							"			 last_name, "+
							"			 email)" 				 +ENTER+
							"values"							 +ENTER+
							"			(?,?,?,?,?,?,?)";
		try {
			return (template.update(sql, coordinator.getUsername(),
													  coordinator.getPassword(),
													  coordinator.getPc_id(),
													  coordinator.getTitle(),
													  coordinator.getFirst_name(),
													  coordinator.getLast_name(),
													  coordinator.getEmail()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Coordinator coordinator){
		String sql = "update project_coordinator"			+ENTER+
							"set"												+ENTER+
							"			 username= ?, "+
							"			 password= ?, "+
							"			 title= ?, "+
							"			 first_name= ?, "+
							"			 last_name= ?, "+
							"			 email= ?"		+ENTER+
							"where"											+ENTER+
							"			pc_id = ?";
		try {
			return (template.update(sql, coordinator.getUsername(),
													  coordinator.getPassword(),
													  coordinator.getTitle(),
													  coordinator.getFirst_name(),
													  coordinator.getLast_name(),
													  coordinator.getEmail(),
													  coordinator.getPc_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByPcID(int ID){
		String sql = "delete from project_coordinator where pc_id = '"+ID+"'";
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
	
	public boolean deleteByPcUsername(String name){
		String sql = "delete from project_coordinator where username = '"+name+"'";
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
	
	public boolean deleteByPcName(String firstName, String lastName){
		String sql = "delete from project_coordinator where first_name = " + "'"+firstName+ "'" + 
																			"and last_name = " + "'"+lastName+"'";
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
	
	
	public Coordinator findByUsername(String username){
		String sql = "SELECT * " + 
							"FROM project_coordinator " + 
							"WHERE username= ? ";
		Coordinator coordinator = null;
		List<Coordinator> coordinators = null;
		try {
			coordinators = template.query(sql, new CoordinatorMapping(), username);
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
	
	public Coordinator findByPcID(int ID){
		String sql = "SELECT * " + 
							"FROM project_coordinator " + 
							"WHERE pc_id= ? ";
		Coordinator coordinator = null;
		List<Coordinator> coordinators = null;
		try {
			coordinators = template.query(sql, new CoordinatorMapping(), ID);
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
	
	public Coordinator findByPcName(String firstName, String lastName){
		String sql = "SELECT * " + 
							"FROM project_coordinator " + 
							"WHERE first_name= ?, " +
							"AND last_name= ?";
		Coordinator coordinator = null;
		List<Coordinator> coordinators = null;
		try {
			coordinators = template.query(sql, new CoordinatorMapping(), firstName, lastName);
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
	
	public List<Coordinator> findAll(){
		String sql = "SELECT  * " + 
							"FROM project_coordinator ";
		List<Coordinator> coordinators = null;
		try {
			coordinators = template.query(sql, new CoordinatorMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return coordinators;
	}
	
	public boolean importCSV(File file) {
		List<String[]> recordList = null;
		try {
			recordList = reader.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Coordinator coordinator = new Coordinator();
		boolean hasHeaderRecords = true;
		for (int r = 0; r < recordList.size(); r++) {
			String[] records = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try {
				coordinator.setUsername(records[0]);
				coordinator.setPassword(records[1]);
				if(records[2].matches(PATTERN)){
					coordinator.setPc_id(Integer.valueOf(records[2]));
				}
				coordinator.setTitle(records[3]);
				coordinator.setFirst_name(records[4]);
				coordinator.setLast_name(records[5]);
				coordinator.setEmail(records[6]);
			}catch( NumberFormatException e){
				e.printStackTrace();
			}
		}
		return save(coordinator);
	}
	
}
