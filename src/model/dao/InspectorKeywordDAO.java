package model.dao;
import static tools.Replace.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.InspectorKeyword;
import model.entity.Keyword;
import model.mapping.InspectorKeywordMapping;
import model.mapping.KeywordMapping;

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
	
	public List<Keyword> findByInspectorID(int ID){
		String sql = "SELECT * " + 
					"FROM inspector_keyword sk, keyword k " + 
					"WHERE sk.keyword_id = k.keyword_id AND sk.inspector_id = " + ID;
		List<Keyword> inspectorKeywords = null;
		try {
			inspectorKeywords = template.query(sql, new KeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return inspectorKeywords;
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
	
	public boolean addKeywordsforInspector(List<Integer> keywordIDs, int inspectorID){
		boolean success = false; 
		String sql = "INSERT INTO inspector_keyword"		+ENTER+
				"			 (keyword_id, " 	+
				"			 inspector_id)" 				+ENTER+
				"values"							 	+ENTER+
				"			(?,?)";
		for (Integer keywordID : keywordIDs) {
			try {
				template.update(sql, keywordID, inspectorID);
				success=true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
	
	public boolean setKeywordsforInspector(List<Integer> keywordIDs, int inspectorID) {
		boolean deleteStatus = this.deleteByInspectorID(inspectorID);
		boolean addStatus = this.addKeywordsforInspector(keywordIDs, inspectorID);	
		return deleteStatus && addStatus;
	}
	
}
