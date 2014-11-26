package model.dao;

import static tools.Replace.ENTER;

import java.sql.SQLException;
import java.util.List;

import model.db.Template;
import model.entity.Keyword;
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
	
	public boolean deleteByKeywordD(int ID){
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
	
	//importCSV ?
	
}
