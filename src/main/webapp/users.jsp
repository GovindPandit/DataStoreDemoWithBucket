<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<center>
	<a href="adduser.jsp">Add User</a>
	<table class="table table-bordered">
		<tr>
			<td>UserKey</td>
			<td>Username</td>
			<td>Email</td>
			<td>Password</td>
			<td>Age</td>
			<td>Image</td>
			<td></td>
		</tr>
		
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.userKey}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.age}</td>
				<td>${user.password}</td>
				<td><img width="200px" height="200px" src="https://storage.cloud.google.com/niitgaebucket/${user.userKey}.jpg"/></td>
				<td><a href="EditServlet?userKey=${user.userKey}">Edit</a> | <a  href="DeleteServlet?userKey=${user.userKey}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</center>
</body>
</html>