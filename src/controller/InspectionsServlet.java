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

import org.json.JSONException;
import org.json.JSONObject;

import model.dao.InspectionDAO;
import model.dao.InspectionweekDAO;
import model.dao.InspectorDAO;
import model.dao.InspectorKeywordDAO;
import model.dao.ModuleDAO;
import model.dao.StudentDAO;
import model.dao.StudentKeywordDAO;
import model.entity.Inspection;
import model.entity.Inspectionweek;
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
        this.addJavascriptFile("moment.min.js");
        this.addJavascriptFile("fullcalendar.min.js");
        this.addJavascriptFile("knockout-3.2.0.js");
        this.addJavascriptFile("inspection-calendar.js");
        this.addJavascriptFile("inspection.js");
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
		String firstInspectorSlug = request.getParameter("firstInspector");
		String secondInspectorSlug = request.getParameter("secondInspector");
		
		int firstInspectorID = -1;
		int secondInspectorID = -1;
		
		try {
			firstInspectorID = Integer.parseInt(firstInspectorSlug);
			secondInspectorID = Integer.parseInt(secondInspectorSlug);
		} catch (NumberFormatException e) {
			response.setStatus(500); 
		    PrintWriter out = response.getWriter();
		    out.println("Invalid users");
		    out.close();
			e.printStackTrace();
		}
		
		Date start = null;
		Date end = null;
		
		try {
			String sSlot = request.getParameter("slot");
			System.out.println(sSlot);
			JSONObject slot = new JSONObject(sSlot);
			String sStart = slot.getString("start");
			String sEnd = slot.getString("end");
			start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sStart);
			end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sEnd);
		} catch (JSONException e) {
			response.setStatus(500); 
		    PrintWriter out = response.getWriter();
		    out.println("Invalid data");
		    out.close();
			e.printStackTrace();
		} catch (ParseException e) {
			response.setStatus(500); 
		    PrintWriter out = response.getWriter();
		    out.println("Invalid time");
		    out.close();
			e.printStackTrace();
		}

		int inspectionWeekID = -1;
		int studentID = -1;
		try {
			inspectionWeekID = Integer.parseInt(this.getObjectSlug(request));
			studentID = Integer.parseInt(this.getSecondObjectSlug(request));
		} catch (NumberFormatException e) {
			response.setStatus(500); 
		    PrintWriter out = response.getWriter();
		    out.println("Invalid inspection");
		    out.close();
			e.printStackTrace();
		}
		
		InspectionDAO inspectionDAO = new InspectionDAO();
		Inspection inspection = inspectionDAO.findByStudentAndInspectionWeek(studentID, inspectionWeekID);
		
		if (inspection == null) {
			inspection = new Inspection();
			inspection.setStudent_id(studentID);
			inspection.setInspectionweek_id(inspectionWeekID);
		}
		
		inspection.setFirst_inspector_id(firstInspectorID);
		inspection.setSecond_inspector_id(secondInspectorID);
		inspection.setStart_date(start);
		inspection.setEnd_date(end);
		
		if (inspection.getInspection_id() > 0) {
			System.out.println("Update "+inspection.getInspection_id());
			inspectionDAO.update(inspection);
		} else {
			System.out.println("Save "+inspection.getInspection_id());
			inspectionDAO.save(inspection);
		}
	}
    
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int inspectionWeekID = Integer.parseInt(this.getObjectSlug(request));
			int studentID = Integer.parseInt(this.getSecondObjectSlug(request));
			
			InspectionDAO inspectionDAO = new InspectionDAO();
			Inspection inspection = inspectionDAO.findByStudentAndInspectionWeek(studentID, inspectionWeekID);
			
			if (inspection != null) {
				this.proceedSingleInspection(inspection, request, response);
			} else {
				StudentDAO studentDAO = new StudentDAO();
				Student student = studentDAO.findByStudentID(studentID);
				
				if (student != null) { // create inspection if student ok
					inspection = new Inspection();
					inspection.setInspectionweek_id(inspectionWeekID);
					inspection.setStudent_id(studentID);
					this.proceedSingleInspection(inspection, request, response);
				} else { // Inspection not found either
		    		this.setAlertView(AlertType.AlertTypeDanger, "Inspection not found", request);
					this.proceedSingleInspection(inspection, request, response);
				}
			}
		} catch (NumberFormatException e) {
			this.proceedInspectionList(request, response);
		}
	}
	
	private void doJSON(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		
		String inspectionWeekSlug = this.getModuleSlug(request);
		int inspectionWeekID = Integer.parseInt(inspectionWeekSlug);
		InspectionweekDAO inspectionWeekDAO = new InspectionweekDAO();
		Inspectionweek inspectionWeek = inspectionWeekDAO.findByID(inspectionWeekID);
		
		request.setAttribute("inspectionWeek", inspectionWeek);
		
		ModuleDAO moduleDAO = new ModuleDAO();
		Module module = moduleDAO.findByModuleID(inspectionWeek.getModule_id());
		
		StudentDAO studentDAO = new StudentDAO();
		List<Student> students = studentDAO.findByModuleID(module.getModule_id());
		request.setAttribute("students", students);
		
		request.setAttribute("servlet", this);
	
        this.layoutType = LayoutType.JSON;
        response.setContentType("application/json"); 
		this.proceedGet("/InspectionsJSON.jsp", request, response);
	}
	
	protected void proceedInspectionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%Inspections", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/", request);
		
        this.relatedMenuClass = "inspections inspection-weeks";
        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Inspections.jsp", request, response);
	}
	
	protected void proceedSingleInspection(Inspection inspection, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.relatedMenuClass = "inspections inspection inspection-weeks";
        
        // DAOs
		StudentDAO studentDAO = new StudentDAO();
		StudentKeywordDAO studentKeywordDAO = new StudentKeywordDAO();
		InspectorDAO inspectorDAO = new InspectorDAO();
		InspectionweekDAO inspectionWeekDAO = new InspectionweekDAO();
        
        // Inspection
		request.setAttribute("inspection", inspection);
		
		// Inspection week
		Inspectionweek inspectionWeek = inspectionWeekDAO.findByID(inspection.getInspectionweek_id());
		request.setAttribute("inspectionWeek", inspectionWeek);
		
		// Student
		Student student = studentDAO.findByStudentID(inspection.getStudent_id());
		request.setAttribute("student", student);
		
		// Keywords
		List<Keyword> studentKeywords = studentKeywordDAO.findByStudentID(student.getStudent_id());
		String lKeywords = null;
		if (studentKeywords.size() > 0) {
			for (Keyword keyword : studentKeywords) {
				if (lKeywords == null) {
					lKeywords = new String(keyword.getKeyword_name());
				} else {
					lKeywords += ", " + keyword.getKeyword_name();
				}
			}
		}
		request.setAttribute("keywords", lKeywords);
		
		// Supervisor
		Inspector supervisor = inspectorDAO.findByUsername(student.getSupervisor());
		request.setAttribute("supervisor", supervisor);
		
		// Inspectors
		List<Inspector> allInspectors = inspectorDAO.findAll();
		List<Inspector> suggestedInspectors = allInspectors.subList(0, 2);
		List<Inspector> otherInspectors = new ArrayList<Inspector>(allInspectors);
		otherInspectors.removeAll(suggestedInspectors);

		request.setAttribute("otherInspectors", otherInspectors);
		request.setAttribute("suggestedInspectors", suggestedInspectors);

		// First inspector
		Inspector firstInspector = inspectorDAO.findByInspectorID(inspection.getFirst_inspector_id());
		if (firstInspector != null) {
			request.setAttribute("firstInspector", firstInspector.getInspector_id());
		} else {
			request.setAttribute("firstInspector", -1);
		}
		
		// Second inspector
		Inspector secondInspector = inspectorDAO.findByInspectorID(inspection.getSecond_inspector_id());
		if (secondInspector != null) {
			request.setAttribute("secondInspector", secondInspector.getInspector_id());
		} else {
			request.setAttribute("secondInspector", -1);
		}
		
		
		Module module = this.getSelectedModule(request);
		this.setBreadcrumbTitles("Modules%"+
									module.getModule_name()+"%"+
									"Inspection weeks%"+ 
									inspectionWeek.getInspection_title() +"%"+
									student.getUsername(), request);
		this.setBreadcrumbLinks("/PIMS/modules/%"+
									"/PIMS/modules/"+ module.getModule_id() +"/%"+
									"/PIMS/inspectionweeks/"+ module.getModule_id() +"/%"+
									"/PIMS/inspectionweeks/"+ module.getModule_id() +"/"+ inspection.getInspectionweek_id()+"/", request);

		
		request.setAttribute("servlet", this);
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
	
	public Inspection inspectionForStudent(Student student, Inspectionweek inspectionWeek) {
		InspectionDAO inspectionDAO = new InspectionDAO();
		Inspection inspection = inspectionDAO.findByStudentAndInspectionWeek(student.getStudent_id(), inspectionWeek.getInspectionweek_id());
		return inspection;
	}
	
	public String studentHasInspection(Student student, Inspectionweek inspectionWeek) {
		if(this.inspectionForStudent(student, inspectionWeek) != null) {
			return "true";
		} else {
			return "false";
		}
	}

	public int loadForInspector(Inspector inspector, Inspection inspection) {
        InspectionDAO inspectionDAO = new InspectionDAO();
		List<Inspection> firstInspectorInspections = inspectionDAO.inspectionsForInspectorID(inspector.getInspector_id(), inspection.getInspectionweek_id());
		return firstInspectorInspections.size();
	}
	
	public String matchedKeywords(Student student, Inspector inspector) {
		StudentKeywordDAO studentKeywordDAO = new StudentKeywordDAO();
		List<Keyword> studentKeywords = studentKeywordDAO.findByStudentID(student.getStudent_id());
		InspectorKeywordDAO inspectorKeywordDAO = new InspectorKeywordDAO();
		List<Keyword> inspectorKeywords = inspectorKeywordDAO.findByInspectorID(inspector.getInspector_id());

		System.out.println(inspectorKeywords);
		String result = null;
		for (Keyword keyword : studentKeywords) {
			if (inspectorKeywords.contains(keyword)) {
				if (result == null) {
					result = new String(keyword.getKeyword_name());
				} else {
					result += ", " + keyword.getKeyword_name();
				}
			}
		}
		return result != null ? result : "None";
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
