package model.db;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IEntityMapping {
	/***
	 * 
	 * @param rs ��rs��ǰ������ת��Ϊһ��ʵ�����
	 * @return ����ʵ����
	 * @throws SQLException
	 */
	public Object mapping(ResultSet rs) throws SQLException;
	
}
