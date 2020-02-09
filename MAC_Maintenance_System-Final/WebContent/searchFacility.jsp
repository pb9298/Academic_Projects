<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Facility</title>
</head>
<body>
	<c:if test="${sessionScope.login == false}">
		<c:redirect url="/" />
	</c:if>
	<form name="searchFacility" action="searchFacility" method="post"
		style="width: 300px;">
		<table>
		<tr>
		<td>Facility Type :</td>
		<td><select name="facilityType">
			<option value="">All</option>
			<option value="Multipurpose rooms">Multipurpose rooms</option>
			<option value="Basketball courts (Indoor)">Basketball courts
				(Indoor)</option>
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
		<td>Duration: </td>
		<td><select name="duration">
			<option value="">All</option>
			<option value="Same day">Same day</option>
			<option value="2 hours">7-day</option>
		</select></td>
		</tr>
		<tr>
		<td>Venue: </td>
		<td><select name="venue">
			<option value="">All</option>
			<option value="Indoor">Indoor</option>
			<option value="Outdoor">Outdoor</option>
		</select></td>
		</tr>
		<tr>
		<td>Date : </td>
		<td><input name="date" type="date" />
		<!--<span id="dateError"> <c:out value="${error.dateError}"></c:out>-->
		<input id="dateError" name="dateError" value="<c:out value="${error.dateError}"></c:out>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 1200px">
		</td>
		</tr>
		<tr>
		<td>Start Time :</td>
		<td><select name="startTime">
			<option value="6">06:00</option>
			<option value="7">07:00</option>
			<option value="8">08:00</option>
			<option value="9">09:00</option>
			<option value="10">10:00</option>
			<option value="11">11:00</option>
			<option value="12">12:00</option>
			<option value="13">13:00</option>
			<option value="14">14:00</option>
			<option value="15">15:00</option>
			<option value="16">16:00</option>
			<option value="17">17:00</option>
			<option value="18">18:00</option>
			<option value="19">19:00</option>
			<option value="20">20:00</option>
			<option value="21">21:00</option>
			<option value="22">22:00</option>
			<option value="23">23:00</option>
		</select></td>
		</tr>
<%-- 		<c:out value="${error.startTimeError}"></c:out> --%>
		<tr>
		<td>End Time :</td>
		<td><select name="endTime">
			<option value="7">07:00</option>
			<option value="8">08:00</option>
			<option value="9">09:00</option>
			<option value="10">10:00</option>
			<option value="11">11:00</option>
			<option value="12">12:00</option>
			<option value="13">13:00</option>
			<option value="14">14:00</option>
			<option value="15">15:00</option>
			<option value="16">16:00</option>
			<option value="17">17:00</option>
			<option value="18">18:00</option>
			<option value="19">19:00</option>
			<option value="20">20:00</option>
			<option value="21">21:00</option>
			<option value="22">22:00</option>
			<option value="23">23:00</option>
			<option value="24">00:00</option>
		</select>
		<input id="endTimeError" name="endTimeError" value="<c:out value="${error.endTimeError}"></c:out>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 1200px">
		</td></tr>
		</table>
		 <br /> <br />
		<button type="submit">submit</button>
	</form>
	<br />
	<br />
	<c:if test="${facDet != null}">
		<table>
			<tr>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Type</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Name</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Interval</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Duration</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Venue</th>
			</tr>
				<!--<td>Facility Type</td>
				<td>Name</td>
				<td>Interval</td>
				<td>Duration</td>
				<td>Venue</td>-->
			<c:forEach items="${facDet}" var="FacilityDetails">
			<tr class="myTableRow" >
					<td class="myTableCell" style="padding-right: 30px; "><c:out value="${FacilityDetails.facilityType}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${FacilityDetails.name}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${FacilityDetails.interval}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${FacilityDetails.duration}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${FacilityDetails.venue}" /></td>
				<!--<tr>
					<td><c:out value="${FacilityDetails.facilityType}" /></td>
					<td><c:out value="${FacilityDetails.name}" /></td>
					<td><c:out value="${FacilityDetails.interval}" /></td>
					<td><c:out value="${FacilityDetails.duration}" /></td>
					<td><c:out value="${FacilityDetails.venue}" /></td>-->
					<td><c:url value="/viewFacility" var="viewUrl">
							<c:param name="name" value="${FacilityDetails.name}" />
						</c:url> <a href="${viewUrl}">view</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a id = home href="/mac/login">Home</a>
</body>
</html>