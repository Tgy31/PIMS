package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.StudentDAO;
import model.entity.Student;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/StudentsServlet")
public class StudentsServlet extends BootstrapServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentsServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.relatedMenuClass = "students";
        this.addJavascriptFile("students.js");
    }
    
    public Student getStudentBySlug(String studentSlug) {
		StudentDAO studentDAO = new StudentDAO();
		Student student = studentDAO.findByUsername(studentSlug);
		return student;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String studentSlug = this.getObjectSlug(request);
		Student student = this.getStudentBySlug(studentSlug);
		
		if (student != null) {
			this.proceedSingleStudent(student, request, response);
		} else if (studentSlug != null) {
			this.alertType = AlertType.AlertTypeDanger;
			this.alertMessage = "Studen not found";
			this.proceedSingleStudentError(request, response);
		} else {
			this.proceedStudentList(request, response);
		}
		
	}
	
	protected void proceedStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.proceedGet("/Students.jsp", request, response);
	}
	
	protected void proceedSingleStudent(Student student, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("student", student);
		this.proceedGet("/Student.jsp", request, response);
	}
	
	protected void proceedSingleStudentError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.proceedGet("/Student.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
