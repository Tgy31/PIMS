package model.dao;

import static tools.Replace.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.Keyword;
import model.entity.Module;
import model.mapping.KeywordMapping;

public class KeywordDAO {	
	private Template template = new Template();
	
	public boolean save(Keyword keyword){
		String sql = "INSERT INTO keyword"			+ENTER+
							"			(keyword_id, " +
							"			 keyword_name, " 	+
							"			 module_id)" 				+ENTER+
							"values"							 		+ENTER+
							"			(?,?,?)";
		try {
			return (template.update(sql, keyword.getKeyword_id(),
													 keyword.getKeyword_name(),
													 keyword.getModule_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return false;
	}
	
	public boolean update(Keyword keyword){
		String sql = "update keyword"								+ENTER+
							"set"												+ENTER+
							"			 keyword_name= ?, "+
							"			 module_id= ?"					+ENTER+
							"where"											+ENTER+
							"			keyword_id = ?";
		try {
			return (template.update(sql, keyword.getKeyword_name(),
				 									 keyword.getModule_id(),
				 									 keyword.getKeyword_id()) == 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update opertaion failed !");
		}
		return false;
	}
	
	public boolean deleteByKeywordID(int ID){
		String sql = "delete from keyword where keyword_id = '"+ID+"'";
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
	
	public boolean deleteByKeywordName(String name){
		String sql = "delete from keyword where keyword_name = '"+name+"'";
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
	
	public Keyword findByKeywordID(int ID){
		String sql = "SELECT  * " + 
							"FROM keyword " + 
							"WHERE keyword_id= " + "'" + ID + "'";
		List<Keyword> keywords = null;
		try {
			keywords = template.query(sql, new KeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return (keywords.size() > 0) ? keywords.get(0) : null;
	}
	
	public Keyword findByKeywordName(String name){
		String sql = "SELECT  * " + 
							"FROM keyword " + 
							"WHERE keyword_id= " + "'" + name + "'";
		List<Keyword> keywords = null;
		try {
			keywords = template.query(sql, new KeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return keywords.get(0);
	}
	
	public List<Keyword> findAll(){
		String sql = "SELECT  * " + 
							"FROM keyword ";
		List<Keyword> keywords = null;
		try {
			keywords = template.query(sql, new KeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return keywords;
	}

	
	public List<Keyword> findByModule(Module module){
		String sql = "SELECT  * " + 
							"FROM keyword " + 
							"WHERE module_id= " + "'" + module.getModule_id() + "'";
		List<Keyword> keywords = null;
		try {
			keywords = template.query(sql, new KeywordMapping());
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Class not found !");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Find by No operation is failed ");
		}
		return keywords;
	}
	
	public boolean  addKeywordsForModule(List<Keyword> keywords, Module module){
		boolean success = true;
		for (Keyword keyword : keywords) {
			boolean tempSuccess = this.addKeywordForModule(keyword, module);
			success = success && tempSuccess;
		}
		return success;
	}
	
	public boolean  addKeywordForModule(Keyword keyword, Module module){
		boolean success = false;
		String sql = null;
		
		try {
			
			if (keyword.getKeyword_id() > 0) {

				sql = "INSERT INTO keyword"	+ENTER+
						"			(keyword_id, "+
						"            keyword_name, "+
						"			 module_id)" 					+ENTER+
						"values"							 			+ENTER+
						"			(?,?,?)";
				success = template.update(sql, keyword.getKeyword_id(), keyword.getKeyword_name(), module.getModule_id()) == 1;
			} else {

				sql = "INSERT INTO keyword"	+ENTER+
						"			(keyword_name, " 	+
						"			 module_id)" 					+ENTER+
						"values"							 			+ENTER+
						"			(?,?)";
				success = template.update(sql, keyword.getKeyword_name(), module.getModule_id()) == 1;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Save opertaion failed !");
		}
		return success;
	}
	
	public boolean deleteKeywordsForModule(Module module){
		String sql = "delete from keyword where module_id = '"+module.getModule_id()+"'";
		try {
			return (template.update(sql) > 0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class not found !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete opertaion failed !");
		}
		return false;
	}

	
	public boolean setKeywordsForModule(List<Keyword> keywords, Module module) {
		boolean deleteStatus = this.deleteKeywordsForModule(module);
		boolean addStatus = this.addKeywordsForModule(keywords, module);
		return deleteStatus && addStatus;
	}
	
	//importCSV ?
	
}
