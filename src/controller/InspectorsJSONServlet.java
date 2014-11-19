package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.InspectorDAO;
import model.entity.Inspector;

/**
 * Servlet implementation class InspectorsJSONServlet
 */
@WebServlet("/InspectorsJSONServlet")
public class InspectorsJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InspectorsJSONServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InspectorDAO inspectorDAO = new InspectorDAO();
		List<Inspector> inspectors = inspectorDAO.findAllInspectors();
		
		request.setAttribute("inspectors", inspectors);
		
        this.getServletContext().getRequestDispatcher("/InspectorsJSON.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
