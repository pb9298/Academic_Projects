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
	<c:choose>
		<c:when test="${profile != null}">
			<c:out value="${Message}"></c:out>
			<br />
			<br />
		Username: <input id="username" name="username" value="<c:out value='${profile.username}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Firstname:<input id="firstname" name="firstname" value="<c:out value='${profile.firstname}'/>" readonly="readonly" style="border: hidden; width: 800px"/> 
			<br />
		Middlename:<input id="middlename" name="middlename" value="<c:out value='${profile.middlename}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Lastname:<input id="lastname" name="lastname" value="<c:out value='${profile.lastname}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		UTA Id: <input id="utaId" name="utaId" value="<c:out value='${profile.utaId}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Phone: <input id="phone" name="phone" value="<c:out value='${profile.phone}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Role: <input id="role" name="role" value="<c:out value='${role}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Email:<input id="email" name="email" value="<c:out value='${profile.email}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Numeric Address: <input id="numericAddress" name="numericAddress" value="<c:out value='${profile.numericAddress}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Street Address: <input id="streetAddress" name="streetAddress" value="<c:out value='${profile.streetAddress}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		City: <input id="city" name="city" value="<c:out value='${profile.city}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		State: <input id="state" name="state" value="<c:out value='${profile.state}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
		Zipcode: <input id="zipcode" name="zipcode" value="<c:out value='${profile.zipcode}'/>" readonly="readonly" style="border: hidden; width: 800px"/>
			<br />
			<c:if test="${sessionScope.role == 'admin'}">
				Change Role:<form name="changeRole" action="changeRole"
					method="post">
					<input type="hidden" name="username" value="${profile.username}" />
					<select id= viewProfChangeRole name="role">
						<option value="user">user</option>
						<option value="fm">facility manager</option>
						<option value="repairer">repairer</option>
						<option value="admin">admin</option>
					</select> <input type="submit" value="change"></input>
				</form>
			</c:if>
			<br />
			<br />
			<c:url value="/updateProfile" var="myURL">
				<c:param name="username" value="${profile.username}" />
			</c:url>
			<a href="${myURL}">Update profile</a>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<br />
	<br />
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a id="home" href="/mac/login">Home</a>
</body>
</html>