package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import DAO.DAO;

/**
 * Servlet implementation class FetchRegisteration
 */
public class FetchRegisteration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchRegisteration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String talkId = request.getParameter("talkId");

		List<User> emailLst = new DAO().fethRegistration(talkId);

		String table = prepareTable(emailLst);
		// response.sendRedirect("show_registration.jsp");
		response.getWriter().print(table);
	}

	private String prepareTable(List<User> emailLst) {
		if (emailLst != null && emailLst.size() > 0) {
			String responseText = "<table id='tech_talk_reg'>";

			responseText += "<tr>";
			responseText += "<th>Name</th>";
			responseText += "<th>Email</th>";
			responseText += "</tr>";

			for (User user : emailLst) {

				responseText += "<tr>";
				responseText += "<td>";
				responseText += user.getName();
				responseText += "</td>";

				responseText += "<td>";
				responseText += user.getEmail();
				responseText += "</td>";
				responseText += "</tr>";
			}

			responseText += "</table>";
			return responseText;
		} else
			return "No user registered yet!";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
