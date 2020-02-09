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
	<p>
		<c:out value="${message}"></c:out>
	</p>
	<c:if
		test="${(type == 'create' || type == 'modify') && dateError == null && facilityNames == null}">
		<c:url value="requestReservation" var="url">
			<c:param name="message" value="${message}" />
		</c:url>
		<c:redirect url="${url}" />
	</c:if>
	<c:choose>
		<c:when
			test="${sessionScope.role == 'user' || sessionScope.role == 'repairer'}">
			<c:if test="${type == 'create'}">
				<c:set var="action" scope="request" value="requestReservation" />
			</c:if>
			<c:if test="${type == 'modify'}">
				<c:set var="action" scope="request" value="modifyReservation" />
			</c:if>
			<form name="${action}" action="${action}" method="post">
				<c:if test="${type == 'create'}">
					<c:if test="${sessionScope.role == 'repairer'}">
						<input type="hidden" name="marNumber" value="${marNumber}"
							readonly="readonly">
						<input type="hidden" name="facilityName" value="${name}"
							readonly="readonly">
						<input type="hidden" name="date" value="${date}"
							readonly="readonly">
					</c:if>
					<c:if test="${sessionScope.role == 'user'}">
						Facility Name :<select name="facilityName">
							<c:forEach items="${facilityNames}" var="name">
								<option value='<c:out value="${name}"></c:out>'><c:out
										value="${name}"></c:out></option>
							</c:forEach>
						</select>
						<br />
						<br /> Date : <input name="date" type="date" />
						<c:out value="${error.dateError}"></c:out>
					</c:if>
					<br />
					<br /> Username :<input name="username"
						value='<c:out value="${sessionScope.username}"></c:out>'
						type="text" readonly="readonly"></input>
					<br />
				</c:if>
				<c:if test="${type == 'modify'}">
					<input type="hidden" name="reservationId" value="${reservationId}" />
					<input type="hidden" name="status" value="pending" />
					<input type="hidden" name="facilityName" value="${name}"
						readonly="readonly">
					<input type="hidden" name="date" value="${date}"
						readonly="readonly">
				</c:if>
				<br /> <br /> Start Time :<select name="startTime">
					<option value="6">06:00</option>
					<option value="6.5">06:30</option>
					<option value="7">07:00</option>
					<option value="7.5">07:30</option>
					<option value="8">08:00</option>
					<option value="8.5">08:30</option>
					<option value="9">09:00</option>
					<option value="9.5">09:30</option>
					<option value="10">10:00</option>
					<option value="10.5">10:30</option>
					<option value="11">11:00</option>
					<option value="11.5">11:30</option>
					<option value="12">12:00</option>
					<option value="12.5">12:30</option>
					<option value="13">13:00</option>
					<option value="13.5">13:30</option>
					<option value="14">14:00</option>
					<option value="14.5">14:30</option>
					<option value="15">15:00</option>
					<option value="15.5">15:30</option>
					<option value="16">16:00</option>
					<option value="16.5">16:30</option>
					<option value="17">17:00</option>
					<option value="17.5">17:30</option>
					<option value="18">18:00</option>
					<option value="18.5">18:30</option>
					<option value="19">19:00</option>
					<option value="19.5">19:30</option>
					<option value="20">20:00</option>
					<option value="20.5">20:30</option>
					<option value="21">21:00</option>
					<option value="21.5">21:30</option>
					<option value="22">22:00</option>
					<option value="22.5">22:30</option>
					<option value="23">23:00</option>
					<option value="23.5">23:30</option>
				</select>
				<input id="startTimeError" name="startTimeError" value="<c:out value='${error.startTimeError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px">
				<br /> <br /> End Time :<select name="endTime">
					<option value="6.5">06:30</option>
					<option value="7">07:00</option>
					<option value="7.5">07:30</option>
					<option value="8">08:00</option>
					<option value="8.5">08:30</option>
					<option value="9">09:00</option>
					<option value="9.5">09:30</option>
					<option value="10">10:00</option>
					<option value="10.5">10:30</option>
					<option value="11">11:00</option>
					<option value="11.5">11:30</option>
					<option value="12">12:00</option>
					<option value="12.5">12:30</option>
					<option value="13">13:00</option>
					<option value="13.5">13:30</option>
					<option value="14">14:00</option>
					<option value="14.5">14:30</option>
					<option value="15">15:00</option>
					<option value="15.5">15:30</option>
					<option value="16">16:00</option>
					<option value="16.5">16:30</option>
					<option value="17">17:00</option>
					<option value="17.5">17:30</option>
					<option value="18">18:00</option>
					<option value="18.5">18:30</option>
					<option value="19">19:00</option>
					<option value="19.5">19:30</option>
					<option value="20">20:00</option>
					<option value="20.5">20:30</option>
					<option value="21">21:00</option>
					<option value="21.5">21:30</option>
					<option value="22">22:00</option>
					<option value="22.5">22:30</option>
					<option value="23">23:00</option>
					<option value="23.5">23:30</option>
					<option value="24">24:00</option>
				</select>
				<input id="endTimeError" name="endTimeError" value="<c:out value='${error.endTimeError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px">
				<br /> <br />
				<button type="submit">submit</button>
			</form>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<br />
	<br />
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/mac/login">Home</a>
</body>
</html>