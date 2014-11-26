package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Keyword;


public class KeywordMapping  implements IEntityMapping{
	@Override
	public Keyword mapping(ResultSet rs) throws SQLException{
		Keyword keyword = new Keyword();
		keyword.setKeyword_id(rs.getInt("keyword_id"));
		keyword.setKeyword_name(rs.getString("keyword_name"));
		keyword.setModule_id(rs.getInt("module_id"));
		return keyword;
	}

}
