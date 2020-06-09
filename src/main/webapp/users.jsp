<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
	<a href="adduser.jsp">Add User</a>
	<table border="">
		<tr>
			<td>UserKey</td>
			<td>Username</td>
			<td>Email</td>
			<td>Password</td>
			<td>Image</td>
			<td></td>
		</tr>
		
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.userKey}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td><img width="200px" height="200px" src="https://storage.cloud.google.com/niitgaebucket/${user.userKey}.jpg"/></td>
				<td><a href="EditServlet?userKey=${user.userKey}">Edit</a> | <a  href="DeleteServlet?userKey=${user.userKey}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</center>
</body>
</html>