package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.AppConstants;
import model.TechTalk;
import model.User;
import DAO.DAO;

/**
 * Servlet implementation class GetTechTalks
 */
public class GetTechTalks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTechTalks() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("type");
		String responseText = null;
		List<TechTalk> lstTechTalks = new DAO().fetchAllTechTalks();

		if (user.equals("normal")) {
			// fetch all the registered tech_ids
			HttpSession session = request.getSession();
			User curUser = (User) session
					.getAttribute(AppConstants.CURRENT_USER);
			String curEmail = curUser.getEmail();

			List<Integer> myTechIds = new DAO().fetchMyRegistartion(curEmail);
			Collections.sort(myTechIds);

			responseText = prepareTextResponse(lstTechTalks, user, myTechIds);
		} else
			responseText = prepareTextResponse(lstTechTalks, user, null);

		System.out.println(responseText);
		response.getWriter().print(responseText);
	}

	// method to prepare response text...
	private String prepareTextResponse(List<TechTalk> lstTechTalks,
			String user, List<Integer> myTechIds) {
		if (lstTechTalks != null && !lstTechTalks.isEmpty()) {
			String responseText = "<table id='tech_talk_table'>";

			responseText += "<tr>";
			responseText += "<th>ID</th>";
			responseText += "<th>Date</th>";
			responseText += "<th>Title</th>";
			responseText += "<th>Description</th>";
			responseText += "<th>Presenter</th>";
			
			if (user.equals("admin")) {
				responseText += "<th></th>";

				responseText += "<th></th>";
				
				responseText += "<th></th>";

			} else {
				responseText += "<th></th>";
			}
			
			responseText += "</tr>";

			for (TechTalk talk : lstTechTalks) {

				responseText += "<tr>";
				responseText += "<td>";
				responseText += talk.getId();
				responseText += "</td>";

				responseText += "<td>";
				responseText += talk.getDate();
				responseText += "</td>";

				responseText += "<td>";
				responseText += talk.getTitle();
				responseText += "</td>";

				responseText += "<td>";
				responseText += talk.getDescription();
				responseText += "</td>";

				responseText += "<td>";
				responseText += talk.getPresenter();
				responseText += "</td>";

				if (user.equals("admin")) {
					responseText += "<td>";
					responseText += "<button onclick='callEdit(this.value);' value='"
							+ talk.getId() + "'>Edit</button>";
					responseText += "</td>";

					responseText += "<td>";
					responseText += "<button class='popup' onclick='callFetch(this.value);' value='"
							+ talk.getId() + "'>Registered Users</button>";
					responseText += "</td>";
					
					responseText += "<td>";
					responseText += "<button onclick='callDelete(this.value);' value='"
							+ talk.getId() + "'>Delete</button>";
					responseText += "</td>";

				} else {
					responseText += "<td>";
					
					// searching whether is user for the given techId or Not
					int index = myTechIds.indexOf(talk.getId());
					if (index == -1) {
						// still have to register
						responseText += "<input type='button' onclick='callRegister(this.id);' value='Register' id="
								+ talk.getId() + ">";
					} else {
						// already registered
						responseText += "<input type='button' onclick='callRegister(this.id);' value='Unregister' id="
								+ talk.getId() + ">";
					}
					
					responseText += "</td>";
					
				}

				responseText += "</tr>";
				// <td><button onclick='callEdit(this.id);'
				// id='1'>Edit</button></td>
			}

			responseText += "</table>";

			return responseText;
		} else if (lstTechTalks == null) {
			return "No Response Received!!";
		} else {
			return "Table is Empty!!";
		}
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
