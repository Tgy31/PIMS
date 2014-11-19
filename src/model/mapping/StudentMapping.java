package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.Student;

public class StudentMapping implements IEntityMapping{
	@Override
	public Student mapping(ResultSet rs) throws SQLException{
		Student student = new Student();
		student.setStudent_id(rs.getInt("student_id"));
		student.setProject_id(rs.getInt("project_id"));
		student.setUsername(rs.getString("username"));
		student.setPassword(rs.getString("password"));
		student.setFirst_name(rs.getString("first_name"));
		student.setLast_name(rs.getString("last_name"));
		student.setEmail(rs.getString("email"));
		return student;
	}
}
