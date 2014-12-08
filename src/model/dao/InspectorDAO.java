package model.dao;

import static tools.Replace.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.skife.csv.CSVReader;
import org.skife.csv.SimpleReader;

import model.entity.Inspector;
import model.entity.Student;
import model.db.Template;
import model.mapping.InspectorMapping;

public class InspectorDAO {
	private Template template = new Template();
	private CSVReader reader = new SimpleReader();
	
	public boolean save(Inspector inspector){
		String sql = "INSERT INTO inspector"+ENTER+
							"			(inspector_id, " +
							"			 capacity, "+
							"			 username, "+
							"			 password, "+
							"			 first_name, "+
							"			 last_name, "+
							"			 email)" 				 +ENTER+
							"values"							 +ENTER+
							"			(?,?,?,?,?,?,?)";
		try {
			return (template.update(sql, inspector.getInspector_id(), 
													  inspector.getCapacity(), 
													  inspector.getUsername(), 
													  inspector.getPassword(), 
													  inspector.getFirst_name(), 
													  inspector.getLast_name(), 
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
	
	public boolean update(Inspector inspector){
		String sql = "update inspector"				+ENTER+
				"set"												+ENTER+
				"			 capacity= ?, "+
				"			 username= ?, "+
				"			 password= ?, "+
				"			 first_name= ?, "+
				"			 last_name= ?, "+
				"			 email= ?"							+ENTER+
				"where"											+ENTER+
				"			inspector_id = ?";
		try {
			return (template.update(sql, inspector.getCapacity(), 
													  inspector.getUsername(), 
													  inspector.getPassword(), 
													  inspector.getFirst_name(), 
													  inspector.getLast_name(), 
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
	
	public void truncateTable(){
		String sql = "TRUNCATE TABLE inspector";
		try {
			template.update(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean importCSV(File file) {
		truncateTable();
		Map<String, Integer> titleName = new HashMap<String, Integer>();
		List<String[]> recordList = null;
		String[] record = null;
		int count=0;
		
		try {
			recordList = reader.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Inspector inspector= new Inspector();
		boolean hasHeaderRecords = true;
		
		for (String title : recordList.get(0)) {
			if (title.toLowerCase().contains("username"))
				titleName.put("username", count);
			count++;
		}
		
		for (int r = 0; r < recordList.size(); r++) {
			record = recordList.get(r);
			if (r == 0 && hasHeaderRecords) {
				continue;
			}
			try{
				inspector.setFirst_name(record[titleName.get("username")]);
				inspector.setLast_name(record[titleName.get("username")]);
				inspector.setUsername(record[titleName.get("username")]);
				inspector.setPassword(record[titleName.get("username")]);
				inspector.setEmail(record[titleName.get("username")].substring(0,1)+"."+record[titleName.get("username")]+"@bham.ac.uk");
			} catch( NumberFormatException e){
				e.printStackTrace();
			}
			save(inspector);
		}
		return true;
	}
	
}
