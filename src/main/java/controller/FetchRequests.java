package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TalkRequest;
import DAO.DAO;

/**
 * Servlet implementation class FetchRequests
 */
public class FetchRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchRequests() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		boolean fetchAll = false;
		List<TalkRequest> reqLst = null;
		
		if (email == null) {
			// fetch all
			fetchAll = true;
			reqLst = new DAO().fetchAllRequests();
		}
		else
			reqLst = new DAO().fetchMyRequests(email);

		String responseText = prepareTable(reqLst, fetchAll);

		response.getWriter().print(responseText);
	}

	private String prepareTable(List<TalkRequest> reqLst, boolean fetchAll) {
		if (reqLst != null && reqLst.size() > 0) {
			String responseText = "<table id='tech_talk_req'>";

			responseText += "<tr>";
			responseText += "<th>REQ. ID.</th>";
			responseText += "<th>DATE</th>";
			responseText += "<th>TOPIC</th>";
			
			if (fetchAll)
				responseText += "<th>EMAIL</th>";
			
			responseText += "<th>STATUS</th>";
			responseText += "</tr>";

			for (TalkRequest req : reqLst) {

				responseText += "<tr>";
				responseText += "<td>";
				responseText += req.getReqId();
				responseText += "</td>";

				responseText += "<td>";
				responseText += req.getDate();
				responseText += "</td>";

				responseText += "<td>";
				responseText += req.getTopic();
				responseText += "</td>";
				
				if (fetchAll) {
					responseText += "<td>";
					responseText += req.getEmail();
					responseText += "</td>";
				}

				responseText += "<td>";
				
				if (fetchAll) {
					if (req.getStatus().equals("pending")) {
						// prompt for approval
						responseText += "<input type='button' onclick='callApprove(this.id);' value='Approve' id=" + req.getReqId() + ">";
					} else {
						// already approved
						responseText += "<input type='button' value='Approved' disabled>";
					}
				}
				else {
					if (req.getStatus().equals("approved")) {
						// <span style="color:blue">blue</span>
						responseText += "<span style='color:green'> <B>" + req.getStatus().toUpperCase() + "</B> </span>";
					} else
					responseText += req.getStatus().toUpperCase();
				}
					
				
				responseText += "</td>";
				responseText += "</tr>";
			}

			responseText += "</table>";
			return responseText;
		} else
			return "No requests!";
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
