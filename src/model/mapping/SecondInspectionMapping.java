package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.SecondInspection;

public class SecondInspectionMapping implements IEntityMapping{
	@Override
	public SecondInspection mapping(ResultSet rs) throws SQLException{
		SecondInspection secondInspection = new SecondInspection();
		secondInspection.setStudent_id(rs.getInt("student_id"));
		secondInspection.setInspector_id(rs.getInt("inspetor_id"));
		secondInspection.setModule_id(rs.getInt("module_id"));
		secondInspection.setDate(rs.getDate("date"));
		secondInspection.setSecond_inspectioncol(rs.getString("second_inspectioncol"));
		return secondInspection;
	}
}
