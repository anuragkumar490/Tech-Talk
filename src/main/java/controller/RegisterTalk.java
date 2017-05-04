package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;

/**
 * Servlet implementation class RegisterTalk
 */
public class RegisterTalk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterTalk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String talkId = request.getParameter("talkId");
		String email = request.getParameter("email");
		String operation = request.getParameter("operation");
		
		int status;
		boolean isRegister = operation.equals("Register");
		if (isRegister)
			status = new DAO().registerTechTalk(talkId, email);
		else
			status = new DAO().unregisterTechTalk(talkId, email);
		
		if (status == 100) {
			// success
			response.sendRedirect("user_dashboard.jsp?msg=" + (isRegister? "Registered for " : "Unregistered from ") + "TechTalk: " + talkId + "!!&id=" + talkId);
		} else if (status == -1) {
			// failed
			response.sendRedirect("user_dashboard.jsp?msg=Error in " + (isRegister? "registering for " : "unregistering from ") + "the TechTalk#" + talkId + "! Please Try Again...");
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
