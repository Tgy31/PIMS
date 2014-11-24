package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ModuleDAO;
import model.entity.Module;
import model.entity.User;

/**
 * Servlet implementation class BootstrapServlet
 */
@WebServlet("/BootstrapServlet")
public class BootstrapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String rootPath = "/PIMS/"; // Change for your installation, in production use "/"
	public String relatedMenuClass;
	private ArrayList<String> javascriptFileNames = new ArrayList<String>();

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
		this.setEnvironmentAttributes(request, response);
		
		String alertTypeName = this.alertTypeName();
		if (this.alertType != AlertType.AlertTypeNone) {
			request.setAttribute("alertType", alertTypeName);
			request.setAttribute("alertMessage", this.alertMessage);
		}
		this.alertType = AlertType.AlertTypeNone;
		this.alertMessage = null;

		this.proceedRequest(jspFile, request, response);
    }
    
    public void proceedPost(String jspFile, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.setEnvironmentAttributes(request, response);
		this.proceedRequest(jspFile, request, response);
    }

    public void proceedRequest(String jspFile, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (this.shouldDenyAcces(request)) {
	        this.getServletContext().getRequestDispatcher("/AccessDenied.jsp").forward(request, response);
		} else {
	        this.getServletContext().getRequestDispatcher(jspFile).forward(request, response);
		}
    	
    }
    
    public void setEnvironmentAttributes(HttpServletRequest request, HttpServletResponse response) {
    	// Set rootPath
		request.setAttribute("rootPath", BootstrapServlet.rootPath);
		
    	// Set related menu for the view
		request.setAttribute("activeMenu", this.relatedMenuClass);

		// Add conditional ressources
		request.setAttribute("javascriptFiles", this.javascriptFileNames);

		// Add user profile path
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("userProfilePath", this.getProfilePathForUser(user));

		// Set Module
		Module selectedModule = this.getSelectedModule(request);
		request.setAttribute("selectedModule", selectedModule);
		
		// Set for coordinator
		if (user != null && user.isCoordinator()) {
			this.setEnvironmentAttributesForCoordinator(request, response);
		}
    }
    
    public void setEnvironmentAttributesForCoordinator(HttpServletRequest request, HttpServletResponse response) {
    	
    	// Set module list
    	ModuleDAO moduleDAO = new ModuleDAO();
    	List<Module> modules = moduleDAO.findAll();
    	request.setAttribute("modules", modules);
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
	            return "Student";
	        case UserTypeInspector:
	            return "Inspector";
	        case UserTypeProjectCoordinator:
	            return "Project Coordinator";
	                    
	        default:
	            return null;
	    }
    }
    
    public UserType userTypeForString(String string) {
    	switch (string) {
	        case "Student":
	            return UserType.UserTypeStudent;
	        case "Inspector":
	            return UserType.UserTypeInspector;
	        case "Project Coordinator":
	            return UserType.UserTypeProjectCoordinator;
	                    
	        default:
	            return null;
	    }
    }
    
    public ArrayList<String> getStringForAllUserTypes() {
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("Student");
    	list.add("Inspector");
    	list.add("Project Coordinator");
    	return list;
    }
    
    public void addJavascriptFile(String fileName) {
    	this.javascriptFileNames.add(fileName);
    }
    
    public String[] getValueFromRequestPath(HttpServletRequest request) {
		String path = request.getPathInfo();
		if (path == null) {
			return new String[0];
		}
		String[] pathValues = path.split("/");
		//return new ArrayList<String>(Arrays.asList(pathValues));
		return pathValues;
    }
    
    public String getModuleSlug(HttpServletRequest request) {
		String[] pathValues = this.getValueFromRequestPath(request);
		
		if (pathValues.length >= 2) {
			if (!pathValues[1].equals("")) {
				return pathValues[1];
			}
		}
		return null;
    }
    
    public String getObjectSlug(HttpServletRequest request) {
		String[] pathValues = this.getValueFromRequestPath(request);
		
		if (pathValues.length >= 3) {
			if (!pathValues[2].equals("")) {
				return pathValues[2];
			}
		}
		return null;
    }
    
    public Module getSelectedModule(HttpServletRequest request) {

		String moduleSlug = this.getModuleSlug(request);

		HttpSession session = request.getSession();
		ModuleDAO moduleDAO = new ModuleDAO();
		Module module = null;
		
		// Get from path
		module = this.getModuleFromRequestPath(request);
		
		// If not selectedMenu from path try from session
		if (module == null) {
			module = (Module) session.getAttribute("lastSelectedModule");
		}
		
		// If still no selectedMenu, get default
		if (module == null) {
			List<Module> modules = moduleDAO.findAll();
			module = modules.get(0);
		}

		session.setAttribute("lastSelectedModule", module);
		return module;
    }
    
    public Module getModuleFromRequestPath(HttpServletRequest request) {
		String moduleSlug = this.getModuleSlug(request);
		ModuleDAO moduleDAO = new ModuleDAO();
		Module module = null;
		
		// Get from path
		if (moduleSlug != null && !moduleSlug.equals("")) {
			int moduleID = Integer.parseInt(moduleSlug);
			module = moduleDAO.findByModuleID(moduleID);
		}
    	return module;
    }
    
    public String getProfilePathForUser(User user) {
    	if (user == null) {
    		return null;
    	} else {
        	return BootstrapServlet.rootPath + "students/default-module" + "/" + user.getUsername();
    	}
    }
    
    public Boolean shouldDenyAcces(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
    	return (user == null); // deny any access if no user
    }

}
