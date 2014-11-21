package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Timetable;

public class TimetableMapping implements IEntityMapping{
	@Override
	public Timetable mapping(ResultSet rs) throws SQLException{
		Timetable timetable = new Timetable();
		timetable.setTimetable_id(rs.getInt("timetable_id"));
		timetable.setSlot_id(rs.getInt("slot_id"));
		return timetable;
	}
}
