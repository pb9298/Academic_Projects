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
		<c:when test="${sessionScope.role == 'admin'}">
			<form name="searchProfiles" action="searchProfiles" method="post"
				style="width: 300px;">
				Username: <input name="username" type="text">
				<c:out value="${requestScope.usernameError}"></c:out>
				<br /> <br /> UTA Id: <input name="utaId" type="text">
				<c:out value="${requestScope.utaIdError}"></c:out>
				<br /> <br /> Role:<select id= searchProfChangeRole name="role">
					<option value="user">user</option>
					<option value="fm">facility manager</option>
					<option value="repairer">repairer</option>
					<option value="admin">admin</option>
				</select><br /> <br />
				<button type="submit">submit</button>
			</form>
			<br />
			<br />
			<c:if test="${requestScope.profileMap != null}">
				<table>
					<tr>
						<td>Username</td>
						<td>Firstname</td>
						<td>Lastname</td>
						<td>Uta Id</td>
						<td>Role</td>
					</tr>
					<c:forEach items="${profileMap}" var="profile">
						<tr>
							<td><c:out value="${profile.key.username}"></c:out></td>
							<td><c:out value="${profile.key.firstname}"></c:out></td>
							<td><c:out value="${profile.key.lastname}"></c:out></td>
							<td><c:out value="${profile.key.utaId}"></c:out></td>
							<td><c:out value="${profile.value}"></c:out></td>
							<c:url value="/viewProfile" var="url">
								<c:param name="username" value="${profile.key.username}" />
							</c:url>
							<td><a  id="viewProfile" href='<c:out value="${url}"></c:out>'>view</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:redirect url="/login" />
		</c:otherwise>
	</c:choose>
	<br /><br />
	<a href="/mac/logout" id="Logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="/mac/login">Home</a>
</body>
</html>