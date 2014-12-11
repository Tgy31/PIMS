package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.InspectionweekDAO;
import model.dao.ModuleDAO;
import model.dao.StudentDAO;
import model.dao.StudentKeywordDAO;
import model.entity.Inspectionweek;
import model.entity.Keyword;
import model.entity.Module;
import model.entity.Student;
import model.entity.User;

/**
 * Servlet implementation class InspectionWeeksServlet
 */
@WebServlet("/InspectionWeeksServlet")
public class InspectionWeeksServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InspectionWeeksServlet() {
        super();
        this.addJavascriptFile("inspectionweeks.js");
        this.addJavascriptFile("inspectionweek.js");
        this.addJavascriptFile("jquery-ui.min.js");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int inspectionWeekID = Integer.parseInt(this.getObjectSlug(request));
			InspectionweekDAO inspectionWeekDAO = new InspectionweekDAO();
			Inspectionweek inspectionWeek = inspectionWeekDAO.findByID(inspectionWeekID);
			
			if (inspectionWeek != null) {

				String title = request.getParameter("inputTitle");
				String sStartDate = request.getParameter("inputStartDate");
				Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sStartDate);
				
				String error = null;
				if (title == null || title.length() == 0) {
					error = "Invalid title";
				} else if (startDate == null) {
					error = "Invalid start date";
				}
				
				if (error == null) {
					inspectionWeek.setInspection_title(title);
					inspectionWeek.setStart_date(startDate);
					if (inspectionWeekDAO.update(inspectionWeek)) {
						this.setAlertView(AlertType.AlertTypeSuccess, "Inspection week successfully saved", request);
						this.proceedSingleInspectionWeek(inspectionWeek, request, response);
					} else {
						this.setAlertView(AlertType.AlertTypeDanger, "Failed to save inspection week", request);
						this.proceedSingleInspectionWeek(inspectionWeek, request, response);
					}
				} else {
					this.setAlertView(AlertType.AlertTypeDanger, error, request);
					this.proceedSingleInspectionWeek(inspectionWeek, request, response);
				}
			}
		} catch (NumberFormatException e) {
			this.setAlertView(AlertType.AlertTypeDanger, "Inspection week not found", request);
			this.proceedInspectionWeekList(request, response);
			e.printStackTrace();
		} catch (ParseException e) {
			this.setAlertView(AlertType.AlertTypeDanger, "Invalid start date", request);
			this.doView(request, response);
			e.printStackTrace();
		}
	}
	
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inspectionWeekSlug = 0;
		try {
			inspectionWeekSlug = Integer.parseInt(this.getObjectSlug(request));
			
			InspectionweekDAO inspectionWeekDAO = new InspectionweekDAO();
			Inspectionweek inspectionWeek = inspectionWeekDAO.findByID(inspectionWeekSlug);
			
			if (inspectionWeek != null) {
				this.proceedSingleInspectionWeek(inspectionWeek, request, response);
			} else {
	    		this.setAlertView(AlertType.AlertTypeDanger, "Inspection week not found", request);
				this.proceedSingleInspectionWeekError(request, response);
			}
		} catch (NumberFormatException e) {
			this.proceedInspectionWeekList(request, response);
		}
	}
	
	private void doJSON(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		
		InspectionweekDAO inspectionWeekDAO = new InspectionweekDAO();
		Module module = this.getSelectedModule(request);
		List<Inspectionweek> inspectionWeeks = inspectionWeekDAO.findByModuleID(module.getModule_id());
		request.setAttribute("inspectionWeeks", inspectionWeeks);		
	
        this.layoutType = LayoutType.JSON;
        response.setContentType("application/json"); 
		this.proceedGet("/InspectionWeeksJSON.jsp", request, response);
	}
	
	protected void proceedInspectionWeekList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspection Weeks", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/", request);
		
        this.relatedMenuClass = "inspection-weeks";
        this.layoutType = LayoutType.Grid;
		this.proceedGet("/InspectionWeeks.jsp", request, response);
	}
	
	protected void proceedSingleInspectionWeek(Inspectionweek inspectionWeek, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.relatedMenuClass = "inspection-weeks inspection-week-details";
		request.setAttribute("inspectionWeek", inspectionWeek);
		
		ModuleDAO moduleDAO = new ModuleDAO();
		Module inspectionModule = moduleDAO.findByModuleID(inspectionWeek.getModule_id());
		request.setAttribute("inspectionModule", inspectionModule);
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspection Weeks%"+ inspectionWeek.getInspection_title(), request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%/PIMS/inspectionweeks/"+ module.getModule_id() +"/", request);

        this.layoutType = LayoutType.Grid;
		this.proceedGet("/InspectionWeek.jsp", request, response);
	}
	
	protected void proceedSingleInspectionWeekError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspection Weeks%Error", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%/PIMS/inspectionweeks/"+ module.getModule_id() +"/", request);
        this.layoutType = LayoutType.Grid;
		this.proceedGet("/InspectionWeek.jsp", request, response);
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
