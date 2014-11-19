package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BootstrapServlet
 */
@WebServlet("/BootstrapServlet")
public class BootstrapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String relatedMenuClass;

	public AlertType alertType;
	public String alertMessage;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BootstrapServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.relatedMenuClass = "BootstrapServlet";
        this.alertType = AlertType.AlertTypeNone;
    }
    
    public void proceedGet(String jspFile, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("activeMenu", this.relatedMenuClass);
		
		String alertTypeName = this.alertTypeName();
		if (this.alertType != AlertType.AlertTypeNone) {
			request.setAttribute("alertType", alertTypeName);
			request.setAttribute("alertMessage", this.alertMessage);
		}
		this.alertType = AlertType.AlertTypeNone;
		this.alertMessage = null;
        this.getServletContext().getRequestDispatcher(jspFile).forward(request, response);
    }
    
    public void proceedPost(String jspFile, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("activeMenu", this.relatedMenuClass);
        this.getServletContext().getRequestDispatcher(jspFile).forward(request, response);
    }
    
    private String alertTypeName() {
    	switch (this.alertType) {
	        case AlertTypeDefault:
	            return "alert-default";
	        case AlertTypeInfo:
	            return "alert-info";
	        case AlertTypePrimary:
	            return "alert-primary";
	        case AlertTypeWarning:
	            return "alert-warning";
	        case AlertTypeDanger:
	            return "alert-danger";
	        case AlertTypeSuccess:
	            return "alert-success";
	                    
	        default:
	            return null;
	    }
    }
    
    public String stringForUserType(UserType type) {
    	switch (type) {
	        case UserTypeStudent:
	            return "UserTypeStudent";
	        case UserTypeInspector:
	            return "UserTypeInspector";
	        case UserTypeProjectCoordinator:
	            return "UserTypeProjectCoordinator";
	                    
	        default:
	            return null;
	    }
    }
    
    public UserType userTypeForString(String string) {
    	switch (string) {
	        case "UserTypeStudent":
	            return UserType.UserTypeStudent;
	        case "UserTypeInspector":
	            return UserType.UserTypeInspector;
	        case "UserTypeProjectCoordinator":
	            return UserType.UserTypeProjectCoordinator;
	                    
	        default:
	            return null;
	    }
    }
    
    public ArrayList<String> getStringForAllUserTypes() {
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("UserTypeStudent");
    	list.add("UserTypeInspector");
    	list.add("UserTypeProjectCoordinator");
    	return list;
    }

}
