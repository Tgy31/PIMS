package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Inspection;

public class InspectionMapping implements IEntityMapping{

	@Override
	public Object mapping(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Inspection inspection = new Inspection();
		inspection.setInspection_id(rs.getInt("inspection_id"));
		inspection.setInspectionweek_id(rs.getInt("inspectionweek_id"));
		inspection.setInspector_id(rs.getInt("inspector_id"));
		inspection.setStudent_id(rs.getInt("student_id"));
		inspection.setStart_date(rs.getTimestamp("start_date"));
		inspection.setEnd_date(rs.getTimestamp("end_date"));	
		return inspection;
	}

}


