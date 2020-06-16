<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<Center>
	<a href="DisplayUsers">Display Users</a>
	<form>
		<table border="1">
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" value="${user.username}"/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" value="${user.email}"/></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="age" value="${user.age}"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="${user.password}"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update"/></td>
				<td><input type="reset" value="Reset"/></td>
			</tr>
		</table>
	</form>
	</Center>
</body>
</html>