package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Slot;

public class SlotMapping implements IEntityMapping{
	@Override
	public Slot mapping(ResultSet rs) throws SQLException{
		Slot slot = new Slot();
		slot.setSlot_id(rs.getInt("slot_id"));
		slot.setStart_date(rs.getDate("start_date"));
		slot.setEnd_date(rs.getDate("end_date"));
		slot.setStudent_id(rs.getInt("student_id"));
		slot.setInspector_id(rs.getInt("inspector_id"));
		return slot;
	}
}
