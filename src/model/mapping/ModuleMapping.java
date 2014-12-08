package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Module;

public class ModuleMapping implements IEntityMapping{
	@Override
	public Module mapping(ResultSet rs) throws SQLException{
		Module module = new Module();
		module.setModule_id(rs.getInt("module_id"));
		module.setModule_name(rs.getString("module_name"));
		module.setYear(rs.getString("year"));
		module.setStart_date(rs.getDate("start_date"));
		module.setEnd_date(rs.getDate("end_date"));
		module.setDefault_inspector_capacity(rs.getInt("default_inspector_capacity"));
		return module;
	}
}
