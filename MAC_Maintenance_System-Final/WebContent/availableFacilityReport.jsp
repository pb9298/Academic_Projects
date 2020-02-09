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
			test="${sessionScope.role == 'fm' || sessionScope.role == 'user' || sessionScope.role == 'repairer'}">
			<form action="availableFacilityReport" method="post">
				Date : <input name="date" type="date"> <c:out value="${dateError}"></c:out><br /> <br />
				Facility Type :<select name="facilityType">
					<option value="Multipurpose rooms">Multipurpose rooms</option>
					<option value="Basketball courts (Indoor)">Basketball
						courts (Indoor)</option>
					<option value="Volleyball courts">Volleyball courts</option>
					<option value="Soccer gymnasium (Indoor)">Soccer gymnasium
						(Indoor)</option>
					<option value="Racquetball courts">Racquetball courts</option>
					<option value="Badminton courts">Badminton courts</option>
					<option value="Table Tennis">Table Tennis</option>
					<option value="Conference rooms">Conference rooms</option>
					<option value="Volleyball courts (Outdoor)">Volleyball
						courts (Outdoor)</option>
					<option value="Basketball courts (Outdoor)">Basketball
						courts (Outdoor)</option>
				</select> <br /> <br />
				<button name="submit" type="submit">Submit</button>
				<br /> <br /> <br />
			</form>
			<c:if test="${report != null}">
				<table>
					<tr>
						<td>Facility Name</td>
						<td>06:00 - 06:30</td>
						<td>06:30 - 07:00</td>
						<td>07:00 - 07:30</td>
						<td>07:30 - 08:00</td>
						<td>08:00 - 08:30</td>
						<td>08:30 - 09:00</td>
						<td>09:00 - 09:30</td>
						<td>09:30 - 10:00</td>
						<td>10:00 - 10:30</td>
						<td>10:30 - 11:00</td>
						<td>11:00 - 11:30</td>
						<td>11:30 - 12:00</td>
						<td>12:00 - 12:30</td>
						<td>12:30 - 13:00</td>
						<td>13:00 - 13:30</td>
						<td>13:30 - 14:00</td>
						<td>14:00 - 14:30</td>
						<td>14:30 - 15:00</td>
						<td>15:00 - 15:30</td>
						<td>15:30 - 16:00</td>
						<td>16:00 - 16:30</td>
						<td>16:30 - 17:00</td>
						<td>17:00 - 17:30</td>
						<td>17:30 - 18:00</td>
						<td>18:00 - 18:30</td>
						<td>18:30 - 19:00</td>
						<td>19:00 - 19:30</td>
						<td>19:30 - 20:00</td>
						<td>20:00 - 20:30</td>
						<td>20:30 - 21:00</td>
						<td>21:00 - 21:30</td>
						<td>21:30 - 22:00</td>
						<td>22:00 - 22:30</td>
						<td>22:30 - 23:00</td>
						<td>23:00 - 23:30</td>
						<td>23:30 - 00:00</td>
					</tr>
					<c:forEach items="${report}" var="report">
						<tr>
							<td><c:out value="${report.key}"></c:out></td>
							<c:forEach items="${report.value}" var="timeline">
								<c:choose>
									<c:when test="${timeline.value}">
										<td style="background-color: red"></td>
									</c:when>
									<c:otherwise>
										<td style="background-color: green"></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:redirect url="/mac/login" />
		</c:otherwise>
	</c:choose>
	<br />
	<c:if
		test="${sessionScope.role == 'user'}">
		<a href="/mac/requestReservation">Request Reservation</a>
		<br />
		<br />
	</c:if>
	<br />
	<br />
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/mac/login">Home</a>
</body>
</html>