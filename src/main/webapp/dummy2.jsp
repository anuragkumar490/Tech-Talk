<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>

<style>
/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

/* Set a style for all buttons */
button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
	position: relative;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
	width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
	position: absolute;
	right: 25px;
	top: 0;
	color: #000;
	font-size: 35px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: red;
	cursor: pointer;
}

/* Add Zoom Animation */
.animate {
	-webkit-animation: animatezoom 0.6s;
	animation: animatezoom 0.6s
}

@
-webkit-keyframes animatezoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes animatezoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>

<style>
html, body {
	height: 100%;
}

#tableContainer-1 {
	height: 100%;
	width: 100%;
	display: table;
}

#tableContainer-2 {
	vertical-align: middle;
	display: table-cell;
	height: 100%;
}

#myTable {
	margin: 0 auto;
}
</style>

<style>
body {
	background-image: url("images/img_mountains_wide.jpg");
	background-color: #cccccc;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>

</head>



<body>


	<div id="tableContainer-1">
		<div id="tableContainer-2">
			<table id="myTable">
				<tr>
					<td align="center">
						<h1 style="color: white;">Welcome to TechTonics</h2>
					</td>
				</tr>

				<tr>
					<td align="center">
						<button
							onclick="document.getElementById('id01').style.display='block'"
							style="width: auto;">Login</button>
					</td>
				</tr>

				<tr>
					<td align="center">
						<p style="color: white;">
							Not Registered Yet? <a href="signup.jsp"><B style="size: 14px;">SignUp</B></a>
						</p>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div id="id01" class="modal">

		<form class="modal-content animate" action="login" method="post">
			<div class="imgcontainer">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="close" title="Close Modal">&times;</span> <img
					src="images/img_avatar.png" alt="Avatar" class="avatar">
			</div>

			<div class="container">
				<label><b>Username</b></label> <input type="text"
					placeholder="Enter Username" name="email"> <label><b>Password</b></label>
				<input type="password" placeholder="Enter Password" name="passwd">

				<button type="submit">Login</button>
				
				<!-- <input type="checkbox" checked="checked"> Remember me -->
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button"
					onclick="document.getElementById('id01').style.display='none'"
					class="cancelbtn">Cancel</button>
				<!-- <span class="psw">Forgot <a href="#">password?</a></span> -->
				<span class="psw">${param.msg}</span>
				
			</div>
		</form>
	</div>

	<script>
		// Get the modal
		var modal = document.getElementById('id01');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	
	<script type="text/javascript">
		function validate() {
			
			// var passwd =  document.forms["loginForm"]["passwd"].value;
			// console.log(passwd);
			if (validation()) {
				document.getElementById("loginForm").submit();	
			}
		}

		function validation() {
			// var email = document.getElementById("email");
			// var passwd = document.getElementById("passwd");
			
			var email = document.forms["loginForm"]["email"].value;
			var passwd = document.forms["loginForm"]["passwd"].value;
			
			var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			if (email === '' || passwd === '') {
				alert("Please fill all fields...!!!!!!");
				return false;
			} else if (!(email).match(emailReg)) {
				alert("Invalid Email...!!!!!!");
				return false;
			} else {
				return true;
			}
		}
	</script>

</body>
</html>
