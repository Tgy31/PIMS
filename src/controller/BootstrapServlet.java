package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.InspectorDAO;
import model.dao.ModuleDAO;
import model.dao.StudentDAO;
import model.entity.Module;
import model.entity.User;

/**
 * Servlet implementation class BootstrapServlet
 */
@WebServlet("/BootstrapServlet")
public class BootstrapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String rootPath = "/PIMS/"; // Change for your installation, in production use "/"
	private ArrayList<String> javascriptFileNames = new ArrayList<String>();
	
	public String relatedMenuClass;
	
	public LayoutType layoutType;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BootstrapServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.relatedMenuClass = "BootstrapServlet";
        this.layoutType = LayoutType.Default;
    }
    
    public void proceedGet(String jspFile, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.proceedRequest(jspFile, request, response);
    }
    
    public void proceedPost(String jspFile, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.proceedRequest(jspFile, request, response);
    }

    public void proceedRequest(String jspFile, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.setEnvironmentAttributes(request, response);
		
		if (this.shouldDenyAcces(request)) {
	    	// Set layout
			request.setAttribute("layoutType", LayoutType.Default);
	        this.getServletContext().getRequestDispatcher("/AccessDenied.jsp").forward(request, response);
		} else {
	        this.getServletContext().getRequestDispatcher(jspFile).forward(request, response);
		}
    	
		this.clearAlertView(request);
    }
    
    public void setEnvironmentAttributes(HttpServletRequest request, HttpServletResponse response) {
    	// Set rootPath
		request.setAttribute("rootPath", BootstrapServlet.rootPath);
		
    	// Set related menu for the view
		request.setAttribute("activeMenu", this.relatedMenuClass);
		
    	// Set layout
		request.setAttribute("layoutType", this.layoutType);

		// Add conditional ressources
		request.setAttribute("javascriptFiles", this.javascriptFileNames);

		// Set Module
		Module selectedModule = this.getSelectedModule(request);
		request.setAttribute("selectedModule", selectedModule);

		// Add user profile path
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("userProfilePath", this.getProfilePathForUser(user, selectedModule));
		
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
    
    private String nameForAlertType(AlertType alertType) {
    	switch (alertType) {
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
    
    public String getSecondObjectSlug(HttpServletRequest request) {
		String[] pathValues = this.getValueFromRequestPath(request);
		
		if (pathValues.length >= 4) {
			if (!pathValues[3].equals("")) {
				return pathValues[3];
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
    
    public String getProfilePathForUser(User user, Module module) {
    	if (user == null) {
    		return null;
    	} else if (user.isStudent()) {
        	return BootstrapServlet.rootPath + "students/" + module.getModule_id() + "/" + user.getUsername();
    	} else if (user.isInspector()) {
        	return BootstrapServlet.rootPath + "inspectors/" + module.getModule_id() + "/" + user.getUsername();
    	} else {
        	return BootstrapServlet.rootPath + "modules/";
    	}
    }
	
	protected User getUserWithTypeAndID(String userType, String userID) {
		
		User user = null;
		
		try {
			int id = Integer.parseInt(userID);
			
			switch (userType) {
				case "student": {
					StudentDAO studentDAO = new StudentDAO();
					user = studentDAO.findByStudentID(id);
			        this.relatedMenuClass = "students keywords";
			        break;
				}
				case "inspector": {
					InspectorDAO inspectorDAO = new InspectorDAO();
					user = inspectorDAO.findByInspectorID(id);
			        this.relatedMenuClass = "inspectors keywords";
			        break;
				}
			}
			return user;
		} catch (NumberFormatException e) {
			return null;
		}
	}
    
    public Boolean shouldDenyAcces(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
    	return (user == null); // deny any access if no user
    }
    
    public void setAlertView(AlertType alertType, String alertMessage, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String alertTypeName = this.nameForAlertType(alertType);
		if (alertType != null && alertType != AlertType.AlertTypeNone && alertMessage != null) {
			session.setAttribute("alertType", alertTypeName);
			session.setAttribute("alertMessage", alertMessage);
		}
    }
    
    public void clearAlertView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("alertType");
		session.removeAttribute("alertMessage");
    }
    
    public void setBreadcrumbTitles(String titles, HttpServletRequest request) {
    	String[] aTitles = titles.split("%");
    	System.out.println(aTitles.length);
    	request.setAttribute("breadcrumbTitles", new ArrayList<String>(Arrays.asList(aTitles)));
    }
    
    public void setBreadcrumbLinks(String links, HttpServletRequest request) {
    	request.setAttribute("breadcrumbLinks", links.split("%"));
    }

}
