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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.dao.InspectorDAO;
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
		String userID = request.getParameter("userID");
		String data = request.getParameter("keywords");

		System.out.println(userType + " - " + userID);
		System.out.println(data);
		
		JSONArray json;
		List<Integer> keywordIDs = new ArrayList<Integer>();
		try {
			json = new JSONArray(data);
			for (int i = 0; i < json.length(); i++) {
				JSONObject jsonKeyword = json.getJSONObject(i+4);
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
	}
	
	private void doView(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		
		String userType = request.getParameter("type");
		String userID = request.getParameter("id");
		
		User user = this.getUserWithTypeAndID(userType, userID);
		request.setAttribute("user", user);

        this.layoutType = LayoutType.Grid;
		this.proceedGet("/Keywords.jsp", request, response);
		
	}
	
	private void doJSON(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		String userType = request.getParameter("type");
		String userID = request.getParameter("id");
		
		User user = this.getUserWithTypeAndID(userType, userID);
		
		// existingKeywords && userKeywords
		KeywordDAO keywordDAO = new KeywordDAO();
		List<Keyword> moduleKeywords = keywordDAO.findAll();
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
