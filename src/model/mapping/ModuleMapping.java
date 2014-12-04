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
		module.setStudents_enrolled(rs.getInt("students_enrolled"));
		module.setInspector_available(rs.getInt("inspector_available"));
		module.setDefault_inspector_capacity(rs.getInt("default_inspector_capacity"));
		module.setFirst_inspection_start_date(rs.getDate("first_inspection_start_date"));
		module.setFirst_inspection_end_date(rs.getDate("first_inspection_end_date"));
		module.setSecond_inspection_start_date(rs.getDate("second_inspection_start_date"));
		module.setSecond_inspection_end_date(rs.getDate("second_inspection_end_date"));
		module.setDisseration_deadline(rs.getDate("dissertation_deadline"));
		module.setPc_id(rs.getInt("pc_id"));
		return module;
	}
}
