package model.mapping;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Inspector;

public class InspectorMapping implements IEntityMapping{
	@Override
	public Inspector mapping(ResultSet rs) throws SQLException{
		Inspector inspector = new Inspector();
		inspector.setInspector_id(rs.getInt("inspector_id"));
		inspector.setTimetable_id(rs.getInt("timetable_id"));
		inspector.setCapacity(rs.getInt("capacity"));
		inspector.setUsername(rs.getString("username"));
		inspector.setPassword(rs.getString("password"));
		inspector.setFirst_name(rs.getString("first_name"));
		inspector.setLast_name(rs.getString("last_name"));
		inspector.setEmail(rs.getString("email"));
		inspector.setTitle(rs.getString("title"));
		inspector.setKeywords(rs.getString("keywords"));
		return inspector;
	}
}


