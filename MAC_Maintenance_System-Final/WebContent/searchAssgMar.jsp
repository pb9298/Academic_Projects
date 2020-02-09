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
			test="${sessionScope.role == 'repairer'}">
			<table id="ListSearchAssgnMARTable">
				<tr>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Mar Number</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Type</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Name</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Urgency</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Description</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Date</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Reported By</th>
					<!--  <td>Mar Number</td>
					<td>Facility Type</td>
					<td>Name</td>
					<td>Urgency</td>
					<td>Description</td>
					<td>Date</td>
					<td>Reported By</td>-->
				</tr>
				<c:forEach items="${mars}" var="mar">
					<tr class="myTableRow" >
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${mar.marNumber}" /></td>
					<td class="myTableCell" style="padding-right: 25px; "><c:out value="${mar.facilityType}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${mar.name}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${mar.urgency}" /></td>
					<td class="myTableCell" style="padding-right: 35px; "><c:out value="${mar.description}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${mar.date}" /></td>
					<td class="myTableCell" style="padding-right: 20px; "><c:out value="${mar.reportedBy}" /></td>
						<!-- <td><input id="marNumber" name="marNumber" value="<c:out value='${mar.marNumber}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none "></td>
						<td><input id="facilityType" name="facilityType" value="<c:out value='${mar.facilityType}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none "></td>
						<td><input id="name" name="name" value="<c:out value='${mar.name}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none "></td>
						<td><input id="urgency" name="urgency" value="<c:out value='${mar.urgency}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none "></td>
						<td><input id="description" name="description" value="<c:out value='${mar.description}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none" ></td>
						<td><input id="date" name="date" value="<c:out value='${mar.date}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none "></td>
						<td><input id="reportedBy" name="reportedBy" value="<c:out value='${mar.reportedBy}'/>" type="text" disabled="disabled" style ="background-color: white; font-size:13.5px; color: black; border: none "></td>-->
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
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="/mac/login">Home</a>
</body>
</html>