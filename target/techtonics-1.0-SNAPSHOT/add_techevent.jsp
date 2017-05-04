<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="css/datetimepicker_css.js"></script>
</head>
<body>
	<h3>Add details about the TechTalk:</h3>

	<form action="add_talk">
		<table>

			<tr>
				<td>Select Date:</td>
				<td><input type="Text" id="demo1" maxlength="25" size="25"
					placeholder="DD-MM-YYYY hh:mm:ss" name="date" /></td>
				<td><img src="images/cal.gif"
					onclick="javascript:NewCssCal ('demo1','yyyyMMdd','dropdown',true,'24',true)" /></td>

				<!--
			onclick="javascript:NewCssCal ('demo1','yyyyMMdd','dropdown',true,'24',true)"
			onclick="javascript:NewCssCal ('demo1','MMddyyyy','dropdown',true,'24',true)"
			onclick="javascript:NewCssCal('demo1')" style="cursor:pointer"
			 -->
			</tr>

			<tr>
				<td>Title:</td>
				<td><input type="text" name="title"></td>
			</tr>

			<tr>
				<td>Description:</td>
				<td><textarea rows="3" cols="20" name="desc"></textarea></td>
			</tr>

			<tr>
				<td>Presenter:</td>
				<td><input type="text" name="presenter"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Add"></td>
				<td><input type="reset"></td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<td>
			 <button id="back" onclick="location.href = 'admin_dashboard.jsp';">Back</button>
			 
			 <!-- 
			 <a href="admin_dashboard.jsp">Back</a>
			  -->
			</td>
		</tr>
	</table>
	
	<p>${param.msg}</p>
</body>
</html>