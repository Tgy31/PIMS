package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.dao.ModuleDAO;
import model.entity.Module;

/**
 * Servlet implementation class ModulesServlet
 */
@WebServlet("/ModulesServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
	maxFileSize=1024*1024*10,
	maxRequestSize=1024*1024*50)   // 50MB
public class ModulesServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;
 
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModulesServlet() {
        super();
        this.relatedMenuClass = "modules";
        this.layoutType = LayoutType.Grid;
        this.addJavascriptFile("modules.js");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String moduleSlug = this.getModuleSlug(request);
		Module module = this.getModuleFromRequestPath(request);
		
		if (module != null) {
			this.proceedSingleModule(module, request, response);
		} else if (moduleSlug != null) {
			this.setAlertView(AlertType.AlertTypeDanger, "Student not found", request);
			this.proceedSingleModuleError(request, response);
		} else {
			this.proceedModuleList(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		File inspectorFile = this.getInspectorFile(request);
		File studentFile = this.getStudentFile(request);
		
		if (studentFile != null) {
			System.out.println("Student file uploaded");
			System.out.println(studentFile);
			this.setAlertView(AlertType.AlertTypeSuccess, "Student file uploaded", request);
		}
		
		if (inspectorFile != null) {
			System.out.println("Inspector file uploaded");
			System.out.println(inspectorFile);
			this.setAlertView(AlertType.AlertTypeSuccess, "Inspector file uploaded", request);
		}
		
		this.doGet(request, response);
	}
	
	protected void proceedModuleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.relatedMenuClass = "modules"; // Menu for module list
		this.proceedGet("/Modules.jsp", request, response);
	}
	
	protected void proceedSingleModule(Module module, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.relatedMenuClass = "module"; // Menu for selected module
		request.setAttribute("module", module);
		this.proceedGet("/Module.jsp", request, response);
	}
	
	protected void proceedSingleModuleError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.proceedGet("/Module.jsp", request, response);
	}
	
	private File getFile(String inputName, HttpServletRequest request) {
		// gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        File file = null;
        try {
			Part part = request.getPart(inputName);
			if (part != null) {
			    String fileName = extractFileName(part);
			    String newPath = savePath + File.separator + fileName;
			    part.write(newPath);
			    file = new File(newPath);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return file;
	}
	
	private File getStudentFile(HttpServletRequest request) {
		return this.getFile("inputStudentFile", request);
	}
	
	private File getInspectorFile(HttpServletRequest request) {
		return this.getFile("inputInspectorFile", request);
	}
 
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
