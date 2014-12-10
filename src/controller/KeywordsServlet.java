package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.dao.InspectorDAO;
import model.dao.InspectorKeywordDAO;
import model.dao.KeywordDAO;
import model.dao.StudentDAO;
import model.dao.StudentKeywordDAO;
import model.entity.*;

/**
 * Servlet implementation class KeywordsServlet
 */
@WebServlet("/KeywordsServlet")
public class KeywordsServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KeywordsServlet() {
        super();
        
        this.addJavascriptFile("knockout-3.2.0.js");
        this.addJavascriptFile("keywords.js");
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
		String data = request.getParameter("keywords");

		System.out.println(userType + " - " + userID);
		System.out.println(data);
		
		JSONArray json;
		List<Integer> keywordIDs = new ArrayList<Integer>();
		try {
			json = new JSONArray(data);
			for (int i = 0; i < json.length(); i++) {
				JSONObject jsonKeyword = json.getJSONObject(i);
				Integer keywordID = jsonKeyword.getInt("id");
			    keywordIDs.add(keywordID);
			}
		} catch (JSONException e) {
			response.setStatus(500); 
		    PrintWriter out = response.getWriter();
		    out.println("error");
		    out.close();
			e.printStackTrace();
		}
		
		System.out.println(keywordIDs);
		
		switch (userType) {
			case "student": {
				StudentKeywordDAO studentKeywordDAO = new StudentKeywordDAO();
				studentKeywordDAO.setKeywordsforStudent(keywordIDs, userID);
				break;
			}
			case "inspector": {
				InspectorKeywordDAO inspectorKeywordDAO = new InspectorKeywordDAO();
				inspectorKeywordDAO.setKeywordsforInspector(keywordIDs, userID);
				break;
			}
		}
	}
	
	private void doView(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		
		String userType = request.getParameter("type");
		String userID = request.getParameter("id");
		
		User user = this.getUserWithTypeAndID(userType, userID);
		request.setAttribute("user", user);
		
		Module module = this.getSelectedModule(request);
		String entityName = Character.toUpperCase(userType.charAt(0)) + userType.substring(1) + "s"; // capitalizes userType
		String entityLink = "/PIMS/"+ userType +"s/"+ module.getModule_id() +"/";
		String userLink = entityLink + user.getUsername() + "/";
		this.setBreadcrumbTitles("Modules%"+ module.getModule_name() +"%" + entityName +"%"+ user.getUsername() +"%Keywords", request);
		this.setBreadcrumbLinks("/PIMS/modules/%/PIMS/modules/"+ module.getModule_id() +"/%"+ entityLink +"%"+ userLink, request);

        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Keywords.jsp", request, response);
		
	}
	
	private void doJSON(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		String userType = request.getParameter("type");
		String userID = request.getParameter("id");
		
		User user = this.getUserWithTypeAndID(userType, userID);
		
		// existingKeywords && userKeywords
		KeywordDAO keywordDAO = new KeywordDAO();
		List<Keyword> moduleKeywords = keywordDAO.findByModule(this.getSelectedModule(request));
		request.setAttribute("moduleKeywords", moduleKeywords);
		
		System.out.println(userType);
		switch (userType) {
			case "student": {
				Student student = (Student)user;
				StudentKeywordDAO studentKeywordDAO = new StudentKeywordDAO();
				List<Keyword> userKeywords = studentKeywordDAO.findByStudentID(student.getStudent_id());
				request.setAttribute("userKeywords", userKeywords);
				break;
			}
		}
		
	
        this.layoutType = LayoutType.JSON;
        response.setContentType("application/json"); 
		this.proceedGet("/KeywordsJSON.jsp", request, response);
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
