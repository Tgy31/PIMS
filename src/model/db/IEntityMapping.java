package model.db;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IEntityMapping {
	/***
	 * 
	 * @param rs 把rs当前行数据转换为一个实体对象
	 * @return 具体实体类
	 * @throws SQLException
	 */
	public Object mapping(ResultSet rs) throws SQLException;
	
}
