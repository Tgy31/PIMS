package model.dao;
import static tools.Replace.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.InspectorKeyword;
import model.mapping.InspectorKeywordMapping;

public class InspectorKeywordDAO {
private Template template = new Template();
	
	public boolean save(InspectorKeyword inspectorKeyword){
		String sql = "INSERT INTO inspector_keyword"			+ENTER+
							"			(inspector_keyword_id, " +
							"			 inspector_id, " 	+
							"			 keyword_id)" 				+ENTER+
							"values"							 		+ENTER+
							"			(?,?,?)";
		try {
			return (template.update(sql, inspectorKeyword.getInspector_keyword_id(),
													inspectorKeyword.getInspector_id(),
													inspectorKeyword.getKeyword_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(InspectorKeyword inspectorKeyword){
		String sql = "update inspector_keyword"				+ENTER+
							"set"												+ENTER+
							"			 inspector_id= ?, "+
							"			 keyword_id= ?"					+ENTER+
							"where"											+ENTER+
							"			inspector_keyword_id = ?";
		try {
			return (template.update(sql, inspectorKeyword.getInspector_id(),
													inspectorKeyword.getKeyword_id(),
													inspectorKeyword.getInspector_keyword_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	
	public boolean deleteByInspectorID(int ID){
		String sql = "delete from inspector_keyword where inspector_id = '"+ID+"'";
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
	
	public boolean deleteByInspectorKeywordID(int ID){
		String sql = "delete from inspector_keyword where inspector_keyword_id = '"+ID+"'";
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
	
	public boolean deleteByKeywordID(int ID){
		String sql = "delete from inspector_keyword where keyword_id = '"+ID+"'";
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
	
	public InspectorKeyword findByInspectorID(int ID){
		String sql = "SELECT  * " + 
							"FROM inspector_keyword " + 
							"WHERE inspector_id= " + "'" + ID + "'";
		List<InspectorKeyword> inspectorKeywords = null;
		try {
			inspectorKeywords = template.query(sql, new InspectorKeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectorKeywords.get(0);
	}
	
	public InspectorKeyword findByInspectorKeywordID(int ID){
		String sql = "SELECT  * " + 
							"FROM inspector_keyword " + 
							"WHERE inspector_keyword_id= " + "'" + ID + "'";
		List<InspectorKeyword> inspectorKeywords = null;
		try {
			inspectorKeywords = template.query(sql, new InspectorKeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectorKeywords.get(0);
	}
	
	public InspectorKeyword findByKeywordID(int ID){
		String sql = "SELECT  * " + 
							"FROM inspector_keyword " + 
							"WHERE keyword_id= " + "'" + ID + "'";
		List<InspectorKeyword> inspectorKeywords = null;
		try {
			inspectorKeywords = template.query(sql, new InspectorKeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectorKeywords.get(0);
	}
	
	public List<InspectorKeyword> findAll(){
		String sql = "SELECT  * " + 
							"FROM inspector_keyword ";
		List<InspectorKeyword> inspectorKeywords = null;
		try {
			inspectorKeywords = template.query(sql, new InspectorKeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectorKeywords;
	}
	
}
