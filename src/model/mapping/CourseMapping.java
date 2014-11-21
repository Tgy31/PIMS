package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Course;

public class CourseMapping implements IEntityMapping{
	@Override
	public Course mapping(ResultSet rs) throws SQLException{
		Course course = new Course();
		course.setCourse_id(rs.getInt("course_id"));
		course.setCourse_name(rs.getString("course_name"));
		course.setCoures_description(rs.getString("course_description"));
		return course;
	}
}
