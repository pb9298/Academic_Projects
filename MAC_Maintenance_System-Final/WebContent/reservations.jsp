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
			<table id="ListReservationTable">
				<tr>
					<td>reservation_id</td>
					<td>date</td>
					<td>status</td>
				</tr>
				<c:forEach items="${reservations}" var="reservation">
					<tr>
						<td><c:out value="${reservation.reservationId}" /></td>
						<td><c:out value="${reservation.date}" /></td>
						<td><c:out value="${reservation.status}" /></td>
						<td><c:url value="/viewReservation" var="url">
								<c:param name="reservationId" value="${reservation.reservationId}" />
							</c:url> <a href="${url}">view</a></td>
						<td>
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