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
 * Servlet implementation class ApproveRequest
 */
public class ApproveRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqId = request.getParameter("req_id");

		List<TalkRequest> list = new DAO().approveRequest(reqId);

		String responseText = prepareTable(list);
		response.getWriter().print(responseText);
	}

	private String prepareTable(List<TalkRequest> list) {
		if (list != null && list.size() > 0) {
			String responseText = "<table id='tech_talk_req'>";

			responseText += "<tr>";
			responseText += "<th>REQ. ID.</th>";
			responseText += "<th>DATE</th>";
			responseText += "<th>TOPIC</th>";
			responseText += "<th>EMAIL</th>";

			responseText += "<th>STATUS</th>";
			responseText += "</tr>";

			for (TalkRequest req : list) {

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

				responseText += "<td>";
				responseText += req.getEmail();
				responseText += "</td>";

				responseText += "<td>";

				if (req.getStatus().equals("pending")) {
					// prompt for approval
					responseText += "<input type='button' onclick='callApprove(this.id);' value='Approve' id="
							+ req.getReqId() + ">";
				} else {
					// already approved
					responseText += "<input type='button' value='Approved' disabled>";
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
