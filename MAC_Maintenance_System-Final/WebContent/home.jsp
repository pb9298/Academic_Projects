<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.login == false}">
		<c:redirect url="/" />
	</c:if>
	<p>
		Hello,
		<c:out value="${sessionScope.username}"></c:out>
	</p>
	<c:choose>
		<c:when test="${sessionScope.role == 'fm'}">
			<a href="/mac/searchMar">Search Mar</a>
			<br />
			<br />
			<a href="/mac/availableFacilityReport">Available Facility Report</a>
			<br />
			<br />
			<a href="/mac/reservations">List All Reservations</a>
			<br />
			<br />
			<a href="/mac/addFacility">Add facility</a>
			<br />
			<br />
			<a href="/mac/searchFacility">Search Facility</a>
		</c:when>
		<c:when test="${sessionScope.role == 'admin'}">
			<a href="/mac/searchProfiles">Search Profile</a>
		</c:when>
		<c:when test="${sessionScope.role == 'repairer'}">
			<a href="/mac/searchAssgMar">List my assigned repairs</a>
			<br />
			<br />
			<a href="/mac/availableFacilityReport">View available facilities</a>
			<br />
			<br />
			<a href="/mac/reservations">List My Reservations</a>
		</c:when>
		<c:when test="${sessionScope.role == 'user'}">
			<a href="/mac/createMAR">Report Problem</a>
			<br />
			<br />
			<a href="/mac/availableFacilityReport">View available facilities</a>
		</c:when>
	</c:choose>
	<br />
	<br />
	<c:url value="/viewProfile" var="myURL">
		<c:param name="username" value="${sessionScope.username}" />
	</c:url>
	<a href="${myURL}">View My Profile</a>
	<br />
	<br />
	<a href="/mac/logout" id="logout">Logout</a>
</body>
</html>