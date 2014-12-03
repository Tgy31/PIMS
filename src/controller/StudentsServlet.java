package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.StudentDAO;
import model.entity.Student;
import model.entity.User;

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
        this.layoutType = LayoutType.Grid;
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
		this.doView(request, response);
	}
	
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentSlug = this.getObjectSlug(request);
		Student student = this.getStudentBySlug(studentSlug);
		
		if (student != null) {
			this.proceedSingleStudent(student, request, response);
		} else if (studentSlug != null) {
    		this.setAlertView(AlertType.AlertTypeDanger, "Student not found", request);
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
		
		String firstName = request.getParameter("inputFirstName");
		String lastName = request.getParameter("inputLastName");
		String email = request.getParameter("inputEmail");
		String projectTitle = request.getParameter("inputTitle");
		String projectDescription = request.getParameter("inputDescription");
		String supervisorID = request.getParameter("inputSupervisor");

		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(projectTitle);
		System.out.println(projectDescription);
		System.out.println(supervisorID);
		
		String error = null;
		
		if (firstName != null && !firstName.equals("")) {
			error = "Invalid first name";
		} else if (lastName != null && !lastName.equals("")) {
			error = "Invalid last name";
		} else if (email != null && !email.equals("")) {
			error = "Invalid email";
		} else if (projectTitle != null && !projectTitle.equals("")) {
			error = "Invalid project title";
		} else if (projectDescription != null && !projectDescription.equals("")) {
			error = "Invalid project descriptino";
		} else if (supervisorID != null && !supervisorID.equals("")) {
			error = "Invalid supervisor";
		}
		
		if (error != null) {
			StudentDAO studentDAO = new StudentDAO();
			String studentSlug = this.getObjectSlug(request);
			Student student = this.getStudentBySlug(studentSlug);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			boolean success = false;
			
			if (user.isCoordinator()) {
				
				// PC can change everything
				student.setFirst_name(firstName);
				student.setLast_name(lastName);
				student.setEmail(email);
				student.setProject_title(projectTitle);
				student.setProject_description(projectDescription);
				student.setSupervisor(supervisorID);
				
				success = studentDAO.save(student);
				if (success) {
					this.setAlertView(AlertType.AlertTypeSuccess, "Student saved successfuly", request);
				} else {
					this.setAlertView(AlertType.AlertTypeDanger, "Student not saved, unexpected error occurred", request);
				}
				
			} else if (user.isStudent() && user.getUsername().equals(student.getUsername())) {
				
				// Student can only change his own project and email
				student.setEmail(email);
				student.setProject_title(projectTitle);
				student.setProject_description(projectDescription);
				
				success = studentDAO.save(student);
				if (success) {
					this.setAlertView(AlertType.AlertTypeSuccess, "Student saved successfuly", request);
				} else {
					this.setAlertView(AlertType.AlertTypeDanger, "Student not saved, unexpected error occurred", request);
				}
				
			} else {
				success = false;
				this.setAlertView(AlertType.AlertTypeDanger, "Access denied", request);
			}
			
		} else {
			this.setAlertView(AlertType.AlertTypeDanger, error, request);
		}
		this.doView(request, response);
	}

}
