package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.User;

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
		

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserForUsernameAndPassword(username, password);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.alertType = AlertType.AlertTypeSuccess;
			this.alertMessage = "You are now logged in as "
					+ user.getFirst_name();
			response.sendRedirect("/PIMS/students/26581/");
		} else {
			this.alertType = AlertType.AlertTypeDanger;
			this.alertMessage = "Log in failed"; 
			this.proceedPost("/Login.jsp", request, response);
		}

	}
	
	@Override
    public Boolean shouldDenyAcces(HttpServletRequest request) {
		return !super.shouldDenyAcces(request);
	}

}
