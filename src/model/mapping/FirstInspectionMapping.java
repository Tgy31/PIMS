package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.FirstInspection;

public class FirstInspectionMapping implements IEntityMapping{
	@Override
	public FirstInspection mapping(ResultSet rs) throws SQLException{
		FirstInspection firstInspection = new FirstInspection();
		firstInspection.setStudent_id(rs.getInt("student_id"));
		firstInspection.setInspector_id(rs.getInt("inspector_id"));
		firstInspection.setModule_id(rs.getInt("module_id"));
		firstInspection.setDate(rs.getDate("date"));
		firstInspection.setFirst_inspectioncol(rs.getString("first_inspectioncol"));
		return firstInspection;
	}
}
