<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>

</head>

<body>
	<h1>TechTonics Login</h1>

	<form action="login" id="loginForm" method="post">
		<table>
			<tr>
				<td>Email Id:</td>
				<td><input type="text" name="email"></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input type="password" name="passwd"></td>
			</tr>

			<tr>
				<td colspan="2"><input type="button" value="Login"
					onclick="validate()">
					
					 <!-- <input type="submit" value="Login"> -->
				</td>
			</tr>
		</table>
	</form>

	<hr color="#0000ff" width="100%">

	<p>
		Not Registered Yet? <a href="signup.jsp">SignUp</a>
	</p>

	<p>${param.msg}</p>


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
