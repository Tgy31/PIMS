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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.dao.InspectorDAO;
import model.dao.KeywordDAO;
import model.dao.SlotDAO;
import model.dao.StudentDAO;
import model.dao.StudentKeywordDAO;
import model.entity.Keyword;
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
        // TODO Auto-generated constructor stub
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
			
			SlotDAO slotDAO = new SlotDAO();
			slotDAO.setSlotsForStudent(slots, userID);
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

        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Availability.jsp", request, response);
	}
	
	private void doJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userType = request.getParameter("type");
		String userID = request.getParameter("id");
		
		User user = this.getUserWithTypeAndID(userType, userID);
		
		SlotDAO slotDAO = new SlotDAO();
		List<Slot> timeslots = null;
		
		switch (userType) {
			case "student": {
				Student student = (Student)user;
				timeslots = slotDAO.findByStudentID(student.getStudent_id());
				break;
			}
		}
		request.setAttribute("timeslots", timeslots);
		
		request.setAttribute("maxUnavailableHours", 5);
		request.setAttribute("inspectionDate", "2014-11-12");
	
        this.layoutType = LayoutType.JSON;
        response.setContentType("application/json"); 
		this.proceedGet("/AvailabilityJSON.jsp", request, response);
		
	}
	
	
	private User getUserWithTypeAndID(String userType, String userID) {
		
		User user = null;
		int id = Integer.parseInt(userID);
		
		switch (userType) {
			case "student": {
				StudentDAO studentDAO = new StudentDAO();
				user = studentDAO.findByStudentID(id);
		        this.relatedMenuClass = "students";
		        break;
			}
			case "inspector": {
				InspectorDAO inspectorDAO = new InspectorDAO();
				user = inspectorDAO.findByInspectorID(id);
		        this.relatedMenuClass = "inspectors";
		        break;
			}
		}
		return user;
	}

}
