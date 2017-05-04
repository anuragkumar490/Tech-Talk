package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;

/**
 * Servlet implementation class DeleteTalk
 */
public class DeleteTalk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTalk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String techTalkId = request.getParameter("talkId");
		
		int status = new DAO().deleteTechTalk(techTalkId);
		
		if (status == 100) {
			// success
			response.sendRedirect("admin_dashboard.jsp?msg=Deleted TechTalk: " + techTalkId);
		} else if (status == -1) {
			// failed
			response.sendRedirect("admin_dashboard.jsp?msg=Error in deleting the TechTalk: " + techTalkId + "! Please Try Again...");
		} else if (status == 0) {
			// sql exception
			response.sendRedirect("admin_dashboard.jsp?msg=SQL Exception!!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
