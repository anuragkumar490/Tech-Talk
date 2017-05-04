<%@ page import="util.AppConstants, model.User" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	session = request.getSession();
	User user = (User) session.getAttribute(AppConstants.CURRENT_USER);
	String email = user.getEmail();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/table_style.css">

<script type="text/javascript">
	// function to fetch the employee records
	var x = function() {

		// asynchronously fetching the records
		var xmlRequest = getXMLHttp();

		xmlRequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("talk_lst").innerHTML = this.responseText;
				
				// For Button
				/* 
				var msg = '${param.msg}';
				if (msg == "Registered for TechTalk!!") {
					alert("AJAX:" + msg);
					changeBtn('${param.id}');
				}
				 */
			}
		};

		xmlRequest.open("GET", "fetch_tech_talks?type=normal", true);
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

	// function to register the TechTalk
	function callRegister(id) {
		var operation = document.getElementById(id).value;
		
		alert(id + " : " + operation);
		// window.location.href = "register_tech_talk?talkId=" + id + "&email=" + '<%=email%>';
		window.location.href = "register_tech_talk?talkId=" + id + "&email=" + '<%=email%>' + "&operation=" + operation;
		//alert('<%=email%>');
	}

	// function to change button
	function changeBtn(btnId) {
		//alert("Btn:" + '${param.id}');
		document.getElementById(btnId).value = "Unregister";
	}

	// request for a techtalk
	function onRequest() {
		modal.style.display = "block";
	}

	// check status
	function checkRequestStatus(email) {
		alert(email);
		y(email);
	}

	// function to fetch user's requests
	var y = function(email) {

		// asynchronously fetching the records
		var xmlRequest = getXMLHttp();

		xmlRequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				// Model Box
				document.getElementById("my_requests").innerHTML = this.responseText;

				var rows = countRows("tech_talk_req");
				document.getElementById("model_foot2").innerHTML = "Total: "
						+ (rows - 1);

				modal2.style.display = "block";
			}
		};

		xmlRequest.open("GET", "fetch_my_requests?email=" + email, true);
		xmlRequest.send();
	}

	// function to count the no of rows in the table
	function countRows(tbl_name) {
		var table = document.getElementById(tbl_name);
		var rows = table.getElementsByTagName("tr");
		return rows.length;
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
	
	
</script>

<style type="text/css">
/* TABLE STYLE 
#tech_talk_req {
	width: 80%;
}
*/


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

</style>

<style>
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
<body>

	<h2 id="title">User Portal:</h2>
	<p>
		Welcome <b><%=user.getName()%></b>
	</p>

	<table>
		<tr>
			<td><button onclick="onRequest()">Request TechTalk</button></td>
			<td><button
					onclick="checkRequestStatus('<%=user.getEmail()%>')">My
					Requests</button></td>
			<td><button onclick="location.href = 'logout.jsp';">Logout</button></td>
		</tr>
	</table>

	<!-- Filter talk by Names -->
	<input type="text" id="name_srch" placeholder="Search talks..."
		onkeyup="filter(this.value)">

	<p id="talk_lst"></p>

	<p>${param.msg}</p>

	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">&times;</span>
				<h2>Modal Header</h2>
			</div>
			<div class="modal-body">
				<form action="make_request">
					<span>What is your preferred topic?</span><br> <input
						type="text" name="topic"><br> <input type="submit">
				</form>
			</div>
			<div class="modal-footer">
				<h3>Modal Footer</h3>
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
				<h2 id="model_head2">My Requests</h2>
			</div>
			<div class="modal-body">
				<form action="update_talk">
					<p id="my_requests"></p>
				</form>
			</div>
			<div class="modal-footer">
				<h3 id="model_foot2">Modal Footer</h3>
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


</body>
</html>
