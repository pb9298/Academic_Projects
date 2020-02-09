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
			<form action="assignMar" method="post">
				Mar number: <input name="marNumber" type="text" readonly="readonly"
					value=<c:out value="${requestScope.marNumber}"></c:out> /> <br />
				<br /> 
				Assign to: <select name="assignedTo">
								<c:forEach items="${repairers}" var="repairer">
									<option value='<c:out value="${repairer}"></c:out>'><c:out
											value="${repairer}"></c:out></option>
								</c:forEach>
							</select><br /><br/> 
				Assigned Date: <input name="assignedDate" type="date">
				<span id="assignedDateError"><c:out value="${requestScope.error.assignedDateError}"></c:out></span>
				<br />
				<br /> Estimate time of repair: <select name="etr">
					<option value="Multiple days">Multiple days</option>
					<option value="One day">One day</option>
					<option value="Multiple hours">Multiple hours</option>
					<option value="One hour">One hour</option>
					<option value="30 minutes">30 minutes</option>
				</select> <br /> <br />
				<button name="submit" type="submit">Submit</button>
				<br />
				<br />
				<br />
			</form>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/mac/login">Home</a>
</body>
</html>