package model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.IEntityMapping;
import model.entity.StudentKeyword;

public class StudentKeywordMapping  implements IEntityMapping{
	@Override
	public StudentKeyword mapping(ResultSet rs) throws SQLException{
		StudentKeyword studentKeyword = new StudentKeyword();
		studentKeyword.setStudent_keyword_id(rs.getInt("student_keyword_id"));
		studentKeyword.setKeyword_id(rs.getInt("keyword_id"));
		studentKeyword.setStudent_id(rs.getInt("student_id"));
		return studentKeyword;
	}
}
