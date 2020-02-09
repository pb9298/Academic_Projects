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
		<c:when test="${sessionScope.role == 'fm'}">
			<form action="searchMar" method="post">
				Assigned: <input type="radio" name="assigned" value="yes">
				yes &nbsp; <input type="radio" name="assigned" value="no">
				No 
				<br /> 
				<br /> 
				Assigned Date: <input type="date" name="assignedDate" /> <c:out value="${assignedDateError}"></c:out>
				<br /> 
				<br /> 
				<input type="submit" value="submit">
			</form>
			<br />
			<br />
			<table>
				<tr>
					<td>Mar Number</td>
					<td>Facility Type</td>
					<td>Name</td>
					<td>Urgency</td>
					<td>Description</td>
					<td>Date</td>
					<td>Reported By</td>
				</tr>
				<c:forEach items="${mars}" var="mar">
					<tr>
						<td><c:out value="${mar.marNumber}" /></td>
						<td><c:out value="${mar.facilityType}" /></td>
						<td><c:out value="${mar.name}" /></td>
						<td><c:out value="${mar.urgency}" /></td>
						<td><c:out value="${mar.description}" /></td>
						<td><c:out value="${mar.date}" /></td>
						<td><c:out value="${mar.reportedBy}" /></td>
						<td><c:url value="/viewMar" var="url">
								<c:param name="marNumber" value="${mar.marNumber}" />
							</c:url> <a href="${url}">view</a></td>
						<td><c:if test="${mar.assigned == false}">
								<c:if test="${sessionScope.role == 'fm'}">
									<c:url value="/assignMar" var="myURL">
										<c:param name="marNumber" value="${mar.marNumber}" />
									</c:url>
									<a href="${myURL}">assign</a>
								</c:if>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/mac/login">Home</a>
</body>
</html>