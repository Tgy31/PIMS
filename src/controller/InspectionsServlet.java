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

import model.dao.InspectorDAO;
import model.dao.KeywordDAO;
import model.dao.StudentDAO;
import model.entity.Inspector;
import model.entity.Keyword;
import model.entity.Module;
import model.entity.Student;
import model.entity.User;

/**
 * Servlet implementation class InspectionsServlet
 */
@WebServlet("/InspectionsServlet")
public class InspectionsServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InspectionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contentType = request.getParameter("content");
		if (contentType == null) {
			contentType = "";
		}
		
		switch (contentType) {
			case "json": {
				this.doJSON(request, response);
				break;
			}
			case "view": {
				this.doView(request, response);
				break;
			}
			
			default: {
				this.doView(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
    
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inspectionSlug = 0;
		try {
			inspectionSlug = Integer.parseInt(this.getObjectSlug(request));
			
			StudentDAO inspectionDAO = new StudentDAO();
			Student inspection = inspectionDAO.findByStudentID(inspectionSlug);
			
			if (inspection != null) {
				this.proceedSingleInspection(inspection, request, response);
			} else {
	    		this.setAlertView(AlertType.AlertTypeDanger, "Inspection not found", request);
				this.proceedSingleInspection(inspection, request, response);
			}
		} catch (NumberFormatException e) {
			this.proceedInspectionList(request, response);
		}
	}
	
	private void doJSON(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		
		// existingKeywords && userKeywords
		KeywordDAO inspectionDAO = new KeywordDAO();
		List<Keyword> inspections = inspectionDAO.findByModule(this.getSelectedModule(request));
		request.setAttribute("inspections", inspections);		
	
        this.layoutType = LayoutType.JSON;
        response.setContentType("application/json"); 
		this.proceedGet("/InspectionsJSON.jsp", request, response);
	}
	
	protected void proceedInspectionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspections", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/", request);
		
        this.relatedMenuClass = "inspections";
        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Inspections.jsp", request, response);
	}
	
	protected void proceedSingleInspection(Student inspection, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.relatedMenuClass = "inspections inspection";
		request.setAttribute("inspection", inspection);
		
		InspectorDAO inspectorDAO = new InspectorDAO();
		List<Inspector> allInspectors = inspectorDAO.findAll();

		List<Inspector> suggestedInspectors = allInspectors.subList(0, 2);
		List<Inspector> otherInspectors = new ArrayList<Inspector>(allInspectors);
		otherInspectors.removeAll(suggestedInspectors);

		request.setAttribute("otherInspectors", otherInspectors);
		request.setAttribute("suggestedInspectors", suggestedInspectors);
		
		Module module = this.getSelectedModule(request);
		//this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspections%"+ inspection.getUsername(), request);
		//this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%/PIMS/inspections/"+ module.getModule_id() +"/", request);

        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Inspection.jsp", request, response);
	}
	
	protected void proceedSingleInspectionError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspections%Error", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%/PIMS/inspections/"+ module.getModule_id() +"/", request);
        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Inspection.jsp", request, response);
	}

	
	@Override
    public Boolean shouldDenyAcces(HttpServletRequest request) {
		
		if (super.shouldDenyAcces(request)) {
			return true;
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return !user.isCoordinator();
    }

}
