package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ModuleDAO;
import model.dao.UserDAO;
import model.entity.Module;
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
		
		// Set module list
		ModuleDAO moduleDAO = new ModuleDAO();
		List<Module> modules = moduleDAO.findAll();
		request.setAttribute("modules", modules);
		//System.out.println(modules.toString());
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
			this.setAlertView(AlertType.AlertTypeSuccess, "You are now logged in as "+user.getFirst_name(), request);
			response.sendRedirect("/PIMS/students/26581/");
		} else {
			this.setAlertView(AlertType.AlertTypeDanger, "Log in failled", request);
			this.proceedPost("/Login.jsp", request, response);
		}

	}
	
	@Override
    public Boolean shouldDenyAcces(HttpServletRequest request) {
		return !super.shouldDenyAcces(request);
	}

}
