package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import model.User;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		int status = new DAO().addUser(new User(name, email, passwd));
		
		if (status == 100) {
			// new user added
			response.sendRedirect("register_success.jsp");
		} else if (status == -1) {
			// user already doesn't exit but failed to add new user
			response.sendRedirect("signup.jsp?msg=Failed to create new Account!! Try again...");
		} else if (status == 1) {
			// user already exists
			response.sendRedirect("signup.jsp?msg=User already Exists!!!");
		} else if (status == 0) {
			// sql exception
			response.sendRedirect("signup.jsp?msg=SQL Exception!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
