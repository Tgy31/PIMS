package test;
import java.sql.SQLException;
import model.db.DBManager;

public class TestDBManager {
	
		public static void main(String[] args) {
			try {
				System.out.println(DBManager.getConnection());
				System.out.println("Connection success");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Connection failed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Connection failed");
			}
	}
}
