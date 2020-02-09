<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add facility form</title>
</head>
<body>
	<c:if test="${sessionScope.login == false}">
		<c:redirect url="/addfacility" />
	</c:if>
	<c:choose>
		<c:when test="${sessionScope.role == 'fm'}">
			<br />
			<br />
			<form name="addfacility" action="addFacility" method="post">
			<table>
			<tr>
				<td>Facility Type :</td>
				<td><select name="facilityType">
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
						</select></td>
			</tr>
			<tr>
						<td>Name :</td>
						<td><input name="name" type="text">
						<input id="nameError1" name="nameError1" value="<c:out value="${requestScope.error.nameError}"></c:out>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 1200px">
						<input id="nameError2" name="nameError2" value="<c:out value="${message}"></c:out>" type="text" disabled="disabled" style ="background-color: white; color: black; border: none; width: 1200px">
						</td>
			</tr>
			<tr>
				 <td>Interval :</td>
				 <td><select name="interval">
					<option value="1 hour">1 hour</option>
					<option value="2 hours">2 hours</option>
					<option value="30 min">30 min</option>
				</select></td>
			</tr> 
			<tr>
			 	 <td>Duration: </td>
			 	<td><select name="duration">
					<option value="Same day">Same day</option>
					<option value="2 hours">7-day</option>
				</select></td>
			</tr>
			<tr>	 
				  <td>Venue: </td>
				  <td><select name="venue">
					<option value="Indoor">Indoor</option>
					<option value="Outdoor">Outdoor</option>
				</select></td>
			</tr>
				</table>
				 <br /> <br />
				
				<button type="submit">submit</button>
				<br />
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