package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static Connection con = null;
	
	private DBManager() {
		
	}
	public synchronized static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (con == null){
			Class.forName(IConfig.DRIVER);
			con = DriverManager.getConnection
				(IConfig.URL + IConfig.DBNAME,IConfig.UNAME,IConfig.PWD);
		}
		return con;
		
	}
		
}
