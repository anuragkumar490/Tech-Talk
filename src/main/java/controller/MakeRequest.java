package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;
import model.User;
import util.AppConstants;

/**
 * Servlet implementation class MakeRequest
 */
public class MakeRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("topic");
		HttpSession session = request.getSession();
		User curUser = (User) session.getAttribute(AppConstants.CURRENT_USER);
		String email = curUser.getEmail();
		
		int status = new DAO().makeRequest(topic, email);
		
		if (status == 100) {
			// success
			response.sendRedirect("user_dashboard.jsp?msg=Request Submitted Successfully!!");
		} else if (status == -1) {
			// failed
			response.sendRedirect("user_dashboard.jsp?msg=Error in making Request!! Try Again...");
		} else if (status == 0) {
			// sql exception
			response.sendRedirect("user_dashboard.jsp?msg=SQL Exception!!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
