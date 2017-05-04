<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Enter your details to SignUp...</h3>

	<form action="register_user" id="regForm">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type="text" name="email"></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input type="password" name="passwd"></td>
			</tr>

			<tr>
				<td>Confirm Password:</td>
				<td><input type="password" name="cnf_passwd"></td>
			</tr>

			<tr>
				<td><input type="button" value="Register" onclick="validate()"></td>
				<td><input type="reset"></td>
			</tr>

		</table>
	</form>
	
	<p>${param.msg}</p>
	
	
	<script type="text/javascript">
	
	function validate() {
		if (validator()) {
			// submit
			document.getElementById("regForm").submit();
		}
	}
	
	
	function validator() {
		var name = document.forms["regForm"]["name"].value;
		var email = document.forms["regForm"]["email"].value;
		var passwd = document.forms["regForm"]["passwd"].value;
		var cnf_passwd = document.forms["regForm"]["cnf_passwd"].value;
		
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		
		if (name === '' || email === '' || passwd === '' || cnf_passwd === '') {
			alert("Please fill all fields...!!!!!!");
			return false;
		}	
		else if (!(email).match(emailReg)) {
			alert("Invalid Email...!!!!!!");
			return false;
		}
		else if (passwd.length < 2) {
			alert("Passwords must be of atleast 2 characters!!!!!");
			return false;
		}
		else if (!(passwd === cnf_passwd)) {
			alert("Entered passwords do not match!!!!!");
			return false;
		}
		else {
			return true;
		}
	}
	</script>
</body>
</html>