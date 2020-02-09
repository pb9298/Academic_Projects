 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.login == false}">
		<c:redirect url="/" />
	</c:if>
	<c:choose>
		<c:when
			test="${sessionScope.role == 'fm' || sessionScope.role == 'repairer'}">
			<table>
			<tr>
				<!--<input id="marNumber" name="marNumber" value="<c:out value='${mar.marNumber}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none; width: 1200px">-->
				<td>Mar Number:</td>
				<td>
				<c:out value="${mar.marNumber}"></c:out>
				</td>
			</tr>
			<tr>
				<td>Facility Type:</td>
				<td><c:out value="${mar.facilityType}"></c:out></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><c:out value="${mar.name}"></c:out></td>
			</tr>
			<tr>
				<td>Urgency:</td>
				<td><c:out value="${mar.urgency}"></c:out></td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><c:out value="${mar.date}"></c:out></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><c:out value="${mar.description}"></c:out></td>
			</tr>
			<tr>
				<td>Reported By:</td>
				<td><c:out value="${mar.reportedBy}"></c:out></td>
			</tr>
			<tr>
				<td>Assigned To:</td>
				<td><c:out value="${assignMar.assignedTo}"></c:out></td>
			</tr>
			<tr>
				<td>Assigned Date:</td>
				<td><c:out value="${assignMar.assignedDate}"></c:out></td>
			</tr>
			<tr>
				<td>Etr:</td>
				<td><c:out value="${assignMar.etr}"></c:out></td>
			</tr>
			</table>
			<c:if test="${assignMar.assignedTo == null}">
				<c:url value="/assignMar" var="myURL">
					<c:param name="marNumber" value="${mar.marNumber}" />
				</c:url>
				<a href="${myURL}"><button>assign</button></a>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<br />
	<br />
	<c:if test="${sessionScope.role == 'repairer'}">
		<a href="/mac/availableFacilityReport">Available Facility Report</a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<c:url value="/requestReservation" var="req">
		<c:param name="marNumber" value="${mar.marNumber}" />
		<c:param name="name" value="${mar.name}" />
		<c:param name="date" value="${mar.date}" />
	</c:url>
	<a href="${req}"><button>Request Reservation</button></a>
		<br />
		<br />
	</c:if>
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/mac/login">Home</a>
</body>
</html>