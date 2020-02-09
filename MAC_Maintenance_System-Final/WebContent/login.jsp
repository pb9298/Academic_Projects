<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Greeting Form</title>
</head>
<body>
	<!--<c:out value="${genericError}"></c:out>--> <br /><br />
	<form name="greetingForm" action="login" method="post"
		style="width: 300px;">
		Username: <input name="username" type="text">
		<input id="usernameError" name="usernameError" value="<c:out value='${requestScope.error.usernameError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
		Password: <input name="password" type="password">
		<input id="passwordError" name="passwordError" value="<c:out value='${requestScope.error.passwordError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
		<button type="submit">submit</button>
	</form>
	<a href="/mac">home</a>
</body>
</html>