package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CoordinatorDAO;
import model.dao.InspectorDAO;
import model.dao.StudentDAO;
import model.entity.Coordinator;
import model.entity.Inspector;
import model.entity.Student;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
		this.relatedMenuClass = "login";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Set user types
		request.setAttribute("userTypes", this.getStringForAllUserTypes());
		
		this.proceedGet("/Login.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Username and password
		String username = (String) request.getParameter("username").trim();
		String password = (String) request.getParameter("password").trim();
		
		// UserType
		String userTypeString = (String) request.getParameter("userType").trim();
		UserType userType = this.userTypeForString(userTypeString);
		
		switch (userType) {
	        case UserTypeStudent: {
				
				StudentDAO studentDAO = new StudentDAO();
				Student student = studentDAO.findByUsernamePassword(username);
				Boolean loginSucceeded = password.equals(student.getPassword());
				request.setAttribute("loginSucceeded", loginSucceeded);
				// if (ModelManager.loginSucceeded(user, pass)) {
				if (loginSucceeded) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("firstName", student.getFirst_name());
					this.alertType = AlertType.AlertTypeSuccess;
					this.alertMessage = "You are now logged in as "
							+ student.getFirst_name();
				} else {
					this.alertType = AlertType.AlertTypeDanger;
					this.alertMessage = "Log in failed"; 
				}
	        	
	        }
	        case UserTypeInspector: {
				
				InspectorDAO inspectorDAO = new InspectorDAO();
				Inspector inspector = inspectorDAO.findByUsernamePassword(username);
				Boolean loginSucceeded = password.equals(inspector.getPassword());
				request.setAttribute("loginSucceeded", loginSucceeded);
				// if (ModelManager.loginSucceeded(user, pass)) {
				if (loginSucceeded) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("firstName", inspector.getFirst_name());
					this.alertType = AlertType.AlertTypeSuccess;
					this.alertMessage = "You are now logged in as "
							+ inspector.getFirst_name();
				} else {
					this.alertType = AlertType.AlertTypeDanger;
					this.alertMessage = "Log in failed";
				}
	        	
	        }
	        case UserTypeProjectCoordinator: {
	        	
				CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
				Coordinator coordinator = coordinatorDAO.findByUsernamePassword(
						username, password);
				Boolean loginSucceeded = password.equals(coordinator.getPassword());
				request.setAttribute("loginSucceeded", loginSucceeded);
				if (loginSucceeded) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("firstName", coordinator.getFirst_name());
					this.alertType = AlertType.AlertTypeSuccess;
					this.alertMessage = "You are now logged in as "
							+ coordinator.getFirst_name();
				} else {
					this.alertType = AlertType.AlertTypeDanger;
					this.alertMessage = "Log in failed";
				}
	        	
	        }
	    }

		// Set user types
		request.setAttribute("userTypes", this.getStringForAllUserTypes());
		
		this.proceedGet("/Login.jsp", request, response);

	}

}
