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
	<c:if test="${sessionScope.login == false}">
		<c:redirect url="/" />
	</c:if>
	<c:if test="${reservation == null}">
		<c:redirect url="/reservations" />
	</c:if>
	<c:choose>
		<c:when
			test="${sessionScope.role == 'fm' || sessionScope.role == 'repairer'}">
			Reservation Id: <input id="modifyResID" name="modifyResID" value="<c:out value='${reservation.reservationId}'/>" type="text" style="border: none; width: 800px">
			<br />
			Facility name: <c:out value="${reservation.name}"></c:out>
			<br />
			Username: <c:out value="${reservation.username}"></c:out>
			<br />
			Date: <c:out value="${reservation.date}"></c:out>
			<br />
			Start time: <input id="modifystartTime" name="modifystartTime" value="<c:out value='${reservation.startTime}'/>" type="text" style="border: none; width: 800px">
			<br />
			End time: <input id="modifyendTime" name="modifyendTime" value="<c:out value='${reservation.endTime}'/>" type="text" style="border: none; width: 800px">
			<br />
			Status: <c:out value="${reservation.status}"></c:out>
			<br />
			<br />
			<c:choose>
				<c:when test="${sessionScope.role == 'repairer'}">
					<c:if
						test="${reservation.status != 'cancelled' && reservation.status != 'completed'}">
						<c:url value="/decision" var="cancel">
							<c:param name="reservationId"
								value="${reservation.reservationId}" />
							<c:param name="marNumber" value="${marNumber}" />
							<c:param name="status" value="cancelled" />
						</c:url>
						<a href='<c:out value="${cancel}"></c:out>'><button>Cancel</button></a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<c:url value="/modifyReservation" var="modify">
							<c:param name="reservationId"
								value="${reservation.reservationId}" />
							<c:param name="name" value="${reservation.name}" />
							<c:param name="date" value="${reservation.date}" />
						</c:url>
						<a href='<c:out value="${modify}"></c:out>'><button>Modify</button></a>	
						&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${reservation.status == 'approved'}">
							<c:url value="/decision" var="complete">
								<c:param name="reservationId"
									value="${reservation.reservationId}" />
								<c:param name="status" value="completed" />
							</c:url>
							<a href='<c:out value="${complete}"></c:out>'><button>Complete</button></a>
						</c:if>
					</c:if>
				</c:when>
				<c:when test="${sessionScope.role == 'fm'}">
					<c:if test="${reservation.status == 'pending'}">
						<c:url value="/decision" var="approve">
							<c:param name="reservationId"
								value="${reservation.reservationId}" />
							<c:param name="status" value="approved" />
						</c:url>
						<a href='<c:out value="${approve}"></c:out>'><button>Approve</button></a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<c:url value="/decision" var="decline">
							<c:param name="reservationId"
								value="${reservation.reservationId}" />
							<c:param name="status" value="decline" />
						</c:url>
						<a href='<c:out value="${decline}"></c:out>'><button>Declined</button></a>
					</c:if>
				</c:when>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<br />
	<br />
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a id = "view_reservation_home" href="/mac/login">Home</a>
</body>
</html>