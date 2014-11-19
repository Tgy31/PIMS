package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Coordinator;

public class CoordinatorMapping implements IEntityMapping{
	@Override
	public Coordinator mapping(ResultSet rs) throws SQLException{
		Coordinator coordinator = new Coordinator();
		coordinator.setTitle(rs.getString("title"));
		coordinator.setUsername(rs.getString("username"));
		coordinator.setPassword(rs.getString("password"));
		coordinator.setFirst_name(rs.getString("first_name"));
		coordinator.setLast_name(rs.getString("last_name"));
		coordinator.setEmail(rs.getString("email"));
		coordinator.setPc_id(rs.getInt("pc_id"));
		return coordinator;
	}
}
