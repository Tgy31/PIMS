package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Inspectionweek;

public class InspectionweekMapping implements IEntityMapping{

	@Override
	public Object mapping(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Inspectionweek inspectionweek = new Inspectionweek();
		inspectionweek.setInspectionweek_id(rs.getInt("inspectionweek_id"));
		inspectionweek.setModule_id(rs.getInt("module_id"));
		inspectionweek.setInspection_title(rs.getString("inspection_title"));
		inspectionweek.setStart_date(rs.getTimestamp("start_date"));
		return inspectionweek;
	}

}
