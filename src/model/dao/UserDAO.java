package model.dao;

import model.entity.*;

public class UserDAO {
	
	public User findUserByUsername(String username) {
		
		User user = null;
		
		StudentDAO studentDAO = new StudentDAO();
		user = studentDAO.findByUsername(username);
		
		if (user == null) {
			// If no student check for inspector
			InspectorDAO inspectorDAO = new InspectorDAO();
			user = inspectorDAO.findByUsername(username);
		}
		if (user == null) {
			// If no inspector check for PC
			CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
			user = coordinatorDAO.findByUsername(username);	
		}
		
		return user;
	}
	
	public User getUserForUsernameAndPassword(String username, String password) {
		User user = this.findUserByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

}
