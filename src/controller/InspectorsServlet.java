package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.InspectorDAO;
import model.dao.StudentDAO;
import model.entity.Module;
import model.entity.Inspector;
import model.entity.Student;
import model.entity.User;

/**
 * Servlet implementation class InspectorsServlet
 */
@WebServlet("/InspectorsServlet")
public class InspectorsServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InspectorsServlet() {
        super();
        this.addJavascriptFile("inspectors.js");
        this.layoutType = LayoutType.Grid;
    }
    
    public Inspector getInspectorBySlug(String inspectorSlug) {
		InspectorDAO inspectorDAO = new InspectorDAO();
		Inspector inspector = inspectorDAO.findByUsername(inspectorSlug);
		return inspector;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doView(request, response);
	}
	
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inspectorSlug = this.getObjectSlug(request);
		Inspector inspector = this.getInspectorBySlug(inspectorSlug);
		
		if (inspector != null) {
			this.proceedSingleInspector(inspector, request, response);
		} else if (inspectorSlug != null) {
    		this.setAlertView(AlertType.AlertTypeDanger, "Inspector not found", request);
			this.proceedSingleInspectorError(request, response);
		} else {
			this.proceedInspectorList(request, response);
		}
	}
	
	protected void proceedInspectorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspectors", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/", request);
		
        this.relatedMenuClass = "inspectors";
		this.proceedGet("/Inspectors.jsp", request, response);
	}
	
	protected void proceedSingleInspector(Inspector inspector, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.relatedMenuClass = "inspectors inspector-profile";
		request.setAttribute("inspector", inspector);
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspectors%"+ inspector.getUsername(), request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%/PIMS/inspectors/"+ module.getModule_id() +"/", request);
		
		this.proceedGet("/Inspector.jsp", request, response);
	}
	
	protected void proceedSingleInspectorError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspectors%Error", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%/PIMS/inspectors/"+ module.getModule_id() +"/", request);
		this.proceedGet("/Inspector.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("inputFirstName");
		String lastName = request.getParameter("inputLastName");
		String email = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");
		String sCapacity = request.getParameter("inputCapacity");
		
		int capacity = -1;
		try {
			capacity = Integer.parseInt(sCapacity);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String error = null;
		
		if (firstName == null || firstName.equals("")) {
			error = "Invalid first name";
		} else if (lastName == null || lastName.equals("")) {
			error = "Invalid last name";
		} else if (email == null || email.equals("")) {
			error = "Invalid email";
		} else if (password == null || password.length() < 5) {
			error = "Invalid password";
		} else if (capacity < 0) {
			error = "Invalid capacity";
		}
		
		if (error == null) {
			InspectorDAO inspectorDAO = new InspectorDAO();
			String inspectorSlug = this.getObjectSlug(request);
			Inspector inspector = inspectorDAO.findByUsername(inspectorSlug);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			boolean success = false;
			
			if (user.isCoordinator()) {
				
				// PC can change everything
				inspector.setFirst_name(firstName);
				inspector.setLast_name(lastName);
				inspector.setEmail(email);
				inspector.setPassword(password);
				inspector.setCapacity(capacity);
				
				success = inspectorDAO.update(inspector);
				if (success) {
					this.setAlertView(AlertType.AlertTypeSuccess, "Inspector saved successfuly", request);
				} else {
					this.setAlertView(AlertType.AlertTypeDanger, "Inspector not saved, unexpected error occurred", request);
				}
				
			} else if (user.isInspector() && user.getUsername().equals(inspector.getUsername())) {
				
				// Inspector can change everything except capacity
				inspector.setFirst_name(firstName);
				inspector.setLast_name(lastName);
				inspector.setEmail(email);
				inspector.setPassword(password);

				success = inspectorDAO.update(inspector);
				if (success) {
					this.setAlertView(AlertType.AlertTypeSuccess, "Inspector saved successfuly", request);
				} else {
					this.setAlertView(AlertType.AlertTypeDanger, "Inspector not saved, unexpected error occurred", request);
				}
				
			} else {
				success = false;
				this.setAlertView(AlertType.AlertTypeDanger, "Access denied", request);
			}
			
		} else {
			this.setAlertView(AlertType.AlertTypeDanger, error, request);
		}
		this.doView(request, response);
	}
	
	@Override
    public Boolean shouldDenyAcces(HttpServletRequest request) {
		
		if (super.shouldDenyAcces(request)) {
			return true;
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user.isCoordinator()) {
			return false; // coordinator can edit students
		} else {
			String userSlug = this.getObjectSlug(request);
			return !user.getUsername().equals(userSlug); // students can edit their own
		}
    }

}
