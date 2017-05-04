package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.AppConstants;
import model.User;
import DAO.DAO;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");

		User user = new DAO().authenticateUser(email, passwd);

		if (user != null) {
			// authentication successful
			HttpSession session = request.getSession();
			session.setAttribute(AppConstants.CURRENT_USER, user);

			// if the user is admin, then
			if (user.getEmail().equals(AppConstants.ADMIN))
				response.sendRedirect("admin_dashboard.jsp");
			else
				response.sendRedirect("user_dashboard.jsp");
		} else {
			// failure
			response.sendRedirect("index.jsp?msg=Invalid Login Credentials!!");
			// response.sendRedirect("dummy2.jsp?msg=Invalid Login Credentials!!");
		}
	}

}
