<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Facility</title>
</head>
<body>
	<c:if test="${sessionScope.login == false}">
		<c:redirect url="/" />
	</c:if>
	<table>
	<tr>
		<td>Facility Type:</td>
		<td><c:out value="${facility.facilityType}"></c:out></td>
	</tr>
	<tr>
		<td>Name:</td>
		<td><c:out value="${facility.name}"></c:out></td>
	</tr>
	<tr>
		<td>Interval:</td>
		<td><c:out value="${facility.interval}"></c:out></td>
	</tr>
	<tr>
		<td>Duration:</td>
		<td><c:out value="${facility.duration}"></c:out></td>
	</tr>
	<tr>
		<td>Venue:</td>
		<td><c:out value="${facility.venue}"></c:out></td>
	</tr>
	</table>
	<br /> <br />
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/mac/login">Home</a>
</body>
</html>