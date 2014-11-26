package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.InspectorDAO;
import model.dao.KeywordDAO;
import model.dao.StudentDAO;
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
		// TODO Auto-generated method stub
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
		
		//User user = this.getUserWithTypeAndID(userType, userID);
		
		// existingKeywords && userKeywords
		KeywordDAO keywordDAO = new KeywordDAO();
		List<Keyword> moduleKeywords = keywordDAO.findAll();
		request.setAttribute("moduleKeywords", moduleKeywords);
		
        this.layoutType = LayoutType.JSON;
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
