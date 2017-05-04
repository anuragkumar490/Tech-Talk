package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TechTalk;
import DAO.DAO;

/**
 * Servlet implementation class UpdateTalk
 */
public class UpdateTalk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTalk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id").trim());
		String date = request.getParameter("date");
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String presenter = request.getParameter("presenter");
		
		int status = new DAO().updateTalk(new TechTalk(id, date, title, desc, presenter));
		
		if (status == 100) {
			// success
			response.sendRedirect("admin_dashboard.jsp?msg=TechTalk Info Updated!!");
		} else if (status == -1) {
			// failed
			response.sendRedirect("edit_talk.jsp?msg=Error in Updating the TechTalk! Please Try Again...");
		} else if (status == 0) {
			// sql exception
			response.sendRedirect("edit_talk.jsp?msg=SQL Exception!!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
