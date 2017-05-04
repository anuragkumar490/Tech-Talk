<%@ page import="util.AppConstants, model.User" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/table_style.css">
<script src="css/datetimepicker_css.js"></script>
<script type="text/javascript">
	var var_date;
	var var_title;
	var var_desc;
	var var_presenter;

	// function to fetch the employee records
	var x = function() {

		// asynchronously fetching the records
		var xmlRequest = getXMLHttp();

		xmlRequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("talk_lst").innerHTML = this.responseText;
			}
		};

		xmlRequest.open("GET", "fetch_tech_talks?type=admin", true);
		xmlRequest.send();
	}

	// when the page is loaded
	window.addEventListener("load", function() {
		x(); // calling anonymous function
	});

	// function to return XMLHttpRequest object
	function getXMLHttp() {
		if (window.XMLHttpRequest)
			return new XMLHttpRequest();
		else
			return new ActiveXObject("Microsoft.XMLHTTP"); // for IE 6 or 7
	}

	// function to edit the TechTalk
	function callEdit(id) {
		// alert(id);
		// window.location.href = "edit_tech_talk?talkId=" + id;
		fetchRecord(id);

		fillValues(id);
		modal2.style.display = "block";

	}

	function callDelete(id) {
		// alert(id);

		if (confirm("Are you sure you wanna delete TechTalk ID: " + id + "!") == true) {
			window.location.href = "delete_tech_talk?talkId=" + id;
		} else {
			// abort
		}
	}

	// function to detch the details from the selected record
	function fetchRecord(id) {
		var table = document.getElementById("tech_talk_table");
		var rows = table.getElementsByTagName("tr");
		for (var i = 0; i < rows.length; i++) {
			var column = rows[i].getElementsByTagName("td")[0];
			if (column) {
				if (column.innerHTML == id) {
					// retain the visibility of the row
					var_date = rows[i].getElementsByTagName("td")[1].innerHTML;
					var_title = rows[i].getElementsByTagName("td")[2].innerHTML;
					var_desc = rows[i].getElementsByTagName("td")[3].innerHTML;
					var_presenter = rows[i].getElementsByTagName("td")[4].innerHTML;

					break;
				}
			}
		}
	}

	function fillValues(talkId) {
		document.getElementById("talk_id").value = talkId;
		document.getElementById("demo1").value = var_date;
		document.getElementById("title").value = var_title;
		document.getElementById("desc").innerHTML = var_desc;
		document.getElementById("presenter").value = var_presenter;

		document.getElementById("model_head2").innerHTML = "Edit info of TechTalk#"
				+ talkId;

	}

	// function to fetch the email list
	var y = function(id) {

		// asynchronously fetching the records
		var xmlRequest = getXMLHttp();

		xmlRequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				// Model Box
				document.getElementById("model_txt").innerHTML = this.responseText;
				document.getElementById("model_head").innerHTML = "Registrations for TechTalk#"
						+ id;

				var rows = countRows("tech_talk_reg");
				document.getElementById("model_foot").innerHTML = "Total: "
						+ (rows - 1);
				modal.style.display = "block";
			}
		};

		xmlRequest.open("GET", "fetch_registeration?talkId=" + id, true);
		xmlRequest.send();
	}

	// function to fetch the registered emails
	function callFetch(id) {
		// alert(id);
		// window.location.href = "fetch_registeration?talkId=" + id;
		y(id);
	}

	// function to count the no of rows in the table
	function countRows(tbl_name) {
		var table = document.getElementById(tbl_name);
		var rows = table.getElementsByTagName("tr");
		return rows.length;
	}

	// function to open user's requests
	function openRequests() {
		z();
	}

	// function to fetch the email list
	var z = function() {

		// asynchronously fetching the records
		var xmlRequest = getXMLHttp();

		xmlRequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				// Model Box
				document.getElementById("model_txt").innerHTML = this.responseText;
				document.getElementById("model_head").innerHTML = "Requests for TechTalks";

				var rows = countRows("tech_talk_req");
				document.getElementById("model_foot").innerHTML = "Total: "
						+ (rows - 1);
				modal.style.display = "block";
			}
		};

		xmlRequest.open("GET", "fetch_my_requests", true);
		xmlRequest.send();
	}

	// function to approve the user's request
	function callApprove(reqId) {
		alert("ReqId: " + reqId);
		m(reqId);
	}

	// function to fetch the email list
	var m = function(reqId) {

		// asynchronously fetching the records
		var xmlRequest = getXMLHttp();

		xmlRequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				// Model Box
				document.getElementById("model_txt").innerHTML = this.responseText;
				document.getElementById("model_head").innerHTML = "Requests for TechTalks";

				var rows = countRows("tech_talk_req");
				document.getElementById("model_foot").innerHTML = "Total: "
						+ (rows - 1);
				modal.style.display = "block";
			}
		};

		xmlRequest.open("GET", "approve_my_requests?req_id=" + reqId, true);
		xmlRequest.send();
	}

	// function to filter out the records by name
	function filter(search_token) {
		search_token = search_token.toUpperCase();
		var table = document.getElementById("tech_talk_table");
		var rows = table.getElementsByTagName("tr");
		for (var i = 0; i < rows.length; i++) {
			var column = rows[i].getElementsByTagName("td")[2];
			if (column) {
				if (column.innerHTML.toUpperCase().indexOf(search_token) > -1) {
					// retain the visibility of the row
					rows[i].style.display = "";
				} else {
					// if the token is Not found, hide the visibility
					rows[i].style.display = "none";
				}
			}
		}
	}

	function displayMsg(msg) {
		alert("Success!");
	}
</script>

<style type="text/css">

/* INPUT STYLE */
#name_srch {
	background-image: url('images/searchicon.png');
	background-position: 10px 10px;
	background-repeat: no-repeat;
	width: 95.5%;
	font-family: sans-serif;
	font-size: 16px;
	padding: 12px 20px 12px 40px;
	border: 1px solid #ddd;
	margin-bottom: 4px;
}

/*HEADING STYLE */
#title {
	background-color: buttonface;
}
/*--------------------------------------------------*/

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding: 0;
	border: 1px solid #888;
	width: 80%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

/* Add Animation */
@
-webkit-keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
@
keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}

/* The Close Button */
.close {
	color: white;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.modal-header {
	padding: 2px 16px;
	background-color: #5cb85c;
	color: white;
}

.modal-body {
	padding: 2px 16px;
}

.modal-footer {
	padding: 2px 16px;
	background-color: #5cb85c;
	color: white;
}
</style>

</head>

<%
	session = request.getSession();
	User user = (User) session.getAttribute(AppConstants.CURRENT_USER);
%>

<body>
	<h2 id="title">Admin Portal:</h2>
	<p>
		Logged in as: <b><%=user.getName()%></b>
	</p>

	<table>
		<tr>
			<td><button onclick="location.href = 'add_techevent.jsp';">Add
					TechTalk</button></td>

			<td><button onclick="openRequests()">TechTalk Requests</button></td>

			<td><button onclick="location.href = 'logout.jsp';">Logout</button></td>
		</tr>
	</table>

	<!-- Filter talk by Names -->
	<input type="text" id="name_srch" placeholder="Search talks..."
		onkeyup="filter(this.value)">

	<p id="talk_lst"></p>


	<!-- EXPERIMENT -->
	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">&times;</span>
				<h2 id="model_head">Modal Header</h2>
			</div>
			<div class="modal-body">
				<p id="model_txt">Some text in the Modal Body</p>
			</div>
			<div class="modal-footer">
				<h3 id="model_foot">Modal Footer</h3>
			</div>
		</div>

	</div>


	<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>


	<!-- ANOTHER EXPERIMENT -->
	<!-- The Modal -->
	<div id="myModal2" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">&times;</span>
				<h2 id="model_head2">Modal Header</h2>
			</div>
			<div class="modal-body">

				<!-- EDIT FORM -->

				<form action="update_talk">
					<input type="hidden" id="talk_id" value="${param.id}" name="id">

					<table>
						<tr>
							<td>Select Date:</td>
							<td><input type="Text" id="demo1" maxlength="25" size="25"
								placeholder="DD-MM-YYYY hh:mm:ss" name="date" value='' /></td>
							<td><img src="images/cal.gif"
								onclick="javascript:NewCssCal ('demo1','yyyyMMdd','dropdown',true,'24',true)" /></td>
						</tr>

						<tr>
							<td>Title:</td>
							<td><input type="text" id="title" name="title"
								value="${param.title}"></td>
						</tr>

						<tr>
							<td>Description:</td>
							<td><textarea rows="3" cols="20" id="desc" name="desc">${param.desc}</textarea></td>
						</tr>

						<tr>
							<td>Presenter:</td>
							<td><input type="text" id="presenter" name="presenter"
								value="${param.presenter}"></td>
						</tr>

						<tr>
							<td><input type="submit" value="Update"></td>
							<td><input type="reset"></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<h3 id="model_foot2"></h3>
			</div>
		</div>

	</div>


	<script>
		// Get the modal
		var modal2 = document.getElementById('myModal2');

		// Get the <span> element that closes the modal
		var span2 = document.getElementsByClassName("close")[1];

		// When the user clicks on <span> (x), close the modal
		span2.onclick = function() {
			modal2.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal2) {
				modal2.style.display = "none";
			}
		}
	</script>


	<!-- Hidden Fields 
	<input type="hidden" id="date">
	<input type="hidden" id="title">
	<input type="hidden" id="desc">
	<input type="hidden" id="presenter">
	-->

</body>
</html>