package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.dao.InspectionDAO;
import model.dao.InspectionweekDAO;
import model.dao.ModuleDAO;
import model.dao.SlotDAO;
import model.entity.Inspection;
import model.entity.Inspectionweek;
import model.entity.Inspector;
import model.entity.Module;
import model.entity.Slot;
import model.entity.Student;
import model.entity.User;

/**
 * Servlet implementation class AvailabilityServlet
 */
@WebServlet("/AvaibilityServlet")
public class AvailabilityServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvailabilityServlet() {
        super();
        this.addJavascriptFile("moment.min.js");
        this.addJavascriptFile("fullcalendar.min.js");
        this.addJavascriptFile("knockout-3.2.0.js");
        this.addJavascriptFile("availability-calendar.js");
        this.addJavascriptFile("availability.js");
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
		
		String userType = request.getParameter("userType");
		int userID = Integer.parseInt(request.getParameter("userID"));
		String data = request.getParameter("slots");
		
		JSONArray json;
		List<Slot> slots = new ArrayList<Slot>();
		try {
			json = new JSONArray(data);
			for (int i = 0; i < json.length(); i++) {
				JSONObject jsonSlot = json.getJSONObject(i);
				String sStart = jsonSlot.getString("start");
				String sEnd = jsonSlot.getString("end");
				Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sStart);
				Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sEnd);
				
				slots.add(new Slot(start, end));
			}
			
			switch (userType) {
				case "student": {
					SlotDAO slotDAO = new SlotDAO();
					slotDAO.setSlotsForStudent(slots, userID);
					break;
				}
				case "inspector": {
					SlotDAO slotDAO = new SlotDAO();
					slotDAO.setSlotsForInspector(slots, userID);
					break;
				}
			}
		} catch (JSONException e) {
			response.setStatus(500); 
		    PrintWriter out = response.getWriter();
		    out.println("error");
		    out.close();
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userType = request.getParameter("type");
		String userID = request.getParameter("id");
		
		User user = this.getUserWithTypeAndID(userType, userID);
		request.setAttribute("user", user);
		
		Module module = this.getSelectedModule(request);
		String entityName = Character.toUpperCase(userType.charAt(0)) + userType.substring(1) + "s"; // capitalizes userType
		String entityLink = "/PIMS/"+ userType +"s/"+ module.getModule_id() +"/";
		String userLink = entityLink + user.getUsername() + "/";
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%" + entityName +"%"+ user.getUsername() +"%Availability", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%"+ entityLink +"%"+ userLink, request);

		this.relatedMenuClass = userType + "s availability";
        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Availability.jsp", request, response);
	}
	
	private void doJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userType = request.getParameter("type");
		String userID = request.getParameter("id");
		int inspectionWeekID = Integer.parseInt(request.getParameter("week"));
		
		User user = this.getUserWithTypeAndID(userType, userID);
		
		SlotDAO slotDAO = new SlotDAO();
		List<Slot> timeslots = null;

		switch (userType) {
			case "student": {
				Student student = (Student)user;
				timeslots = slotDAO.findByStudentID(student.getStudent_id());
				break;
			}
			case "inspector": {
				Inspector inspector = (Inspector)user;
				timeslots = slotDAO.findByInspectorID(inspector.getInspector_id());
				break;
			}
		}
		request.setAttribute("timeslots", timeslots);

		// Inspection week
		InspectionweekDAO inspectionWeekDAO = new InspectionweekDAO();
		Inspectionweek inspectionWeek = inspectionWeekDAO.findByID(inspectionWeekID);
		request.setAttribute("inspectionWeek", inspectionWeek);
		
		// Module
		ModuleDAO moduleDAO = new ModuleDAO();
		Module module = moduleDAO.findByModuleID(inspectionWeek.getModule_id());
		request.setAttribute("maxUnavailableHours", module.getUnavailability_hour_limit());
		request.setAttribute("inspectionDate", inspectionWeek.getFormattedStartDate());
		
		// Inspections
		InspectionDAO inspectionDAO = new InspectionDAO();
		List<Inspection> inspections = new ArrayList<Inspection>();
		switch (userType) {
			case "student": {
				Student student = (Student)user;
				Inspection inspection = inspectionDAO.findByStudentAndInspectionWeek(student.getStudent_id(), inspectionWeekID);
				if (inspection != null) {
					inspections.add(inspection);
				}
				break;
			}
			case "inspector": {
				Inspector inspector = (Inspector)user;
				inspections = inspectionDAO.findByInspector(inspector);
				break;
			}
		}
		request.setAttribute("inspections", inspections);
	
        this.layoutType = LayoutType.JSON;
        response.setContentType("application/json"); 
		this.proceedGet("/AvailabilityJSON.jsp", request, response);
		
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
			int userID = Integer.parseInt(request.getParameter("id"));
			return user.getUserID() != userID; // students can edit their own
		}
    }

}
