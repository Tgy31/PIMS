package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.InspectorKeyword;

public class InspectorKeywordMapping implements IEntityMapping{
	@Override
	public InspectorKeyword mapping(ResultSet rs) throws SQLException{
		InspectorKeyword inspectorKeyword = new InspectorKeyword();
		inspectorKeyword.setInspector_keyword_id(rs.getInt("inspector_keyword_id"));
		inspectorKeyword.setInspector_id(rs.getInt("inspector_id"));
		inspectorKeyword.setKeyword_id(rs.getInt("keyword_id"));
		return inspectorKeyword;
	}
}
