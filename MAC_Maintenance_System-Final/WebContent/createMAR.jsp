<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create MAR Form</title>
</head>
<body>
	<c:if test="${sessionScope.login == false}">
		<c:redirect url="/" />
	</c:if>
	<c:choose>
		<c:when test="${sessionScope.role == 'user'}">
			<form name="createMAR" action="createMAR" method="post">
				Facility Type :<select name="facilityType">
									<option value="Multipurpose rooms">Multipurpose rooms</option>
									<option value="Basketball courts (Indoor)">Basketball courts (Indoor)</option>
									<option value="Volleyball courts">Volleyball courts</option>
									<option value="Soccer gymnasium (Indoor)">Soccer gymnasium (Indoor)</option>
									<option value="Racquetball courts">Racquetball courts</option>
									<option value="Badminton courts">Badminton courts</option>
									<option value="Table Tennis">Table Tennis</option>
									<option value="Conference rooms">Conference rooms</option>
									<option value="Volleyball courts (Outdoor)">Volleyball courts (Outdoor)</option>
									<option value="Basketball courts (Outdoor)">Basketball courts (Outdoor)</option>
							</select> <br /><br />
				Name :<input name="name" value="" type="text"> 
				<input id="marnameError" name="marnameError" value = "<c:out value='${requestScope.error.nameError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br /><br />
				urgency: <select name="urgency">
					<option value="minor">Minor</option>
					<option value="medium">Medium</option>
					<option value="major">Major</option>
					<option value="unusable">Unusable</option>
				</select> <br /> <br />
				description: <textarea name="description" rows="4" cols="50"></textarea>
				<input id="descError" name="descError" value = "<c:out value='${requestScope.error.descriptionError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"><br /> <br /> 
				Reported by :<input name="reportedBy" value='<c:out value="${sessionScope.username}"></c:out>' type="text" readonly="readonly"> <br /><br />
				<button type="submit">submit</button> <br />
			</form>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<a href="/mac/logout" id = "logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="/mac/login">Home</a>
</body>
</html>