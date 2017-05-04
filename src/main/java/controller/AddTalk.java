package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import model.TechTalk;

/**
 * Servlet implementation class AddTalk
 */
public class AddTalk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTalk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String presenter = request.getParameter("presenter");
		
		int status = new DAO().addNewTalk(new TechTalk(date, title, desc, presenter));
		
		if (status == 100) {
			// success
			response.sendRedirect("add_techevent.jsp?msg=New TechTalk Added!!");
		} else if (status == -1) {
			// failed
			response.sendRedirect("add_techevent.jsp?msg=Error in Adding New TechTalk! Please Try Again...");
		} else if (status == 0) {
			// sql exception
			response.sendRedirect("add_techevent.jsp?msg=SQL Exception!!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
