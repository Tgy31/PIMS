package model.entity;

public class User {
	String username;
	String password;
	String first_name;
	String last_name;
	String email;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean isStudent() {
		return (this instanceof Student);
	}
	
	public Boolean isInspector() {
		return (this instanceof Inspector);
	}
	
	public Boolean isCoordinator() {
		return (this instanceof Coordinator);
	}

}