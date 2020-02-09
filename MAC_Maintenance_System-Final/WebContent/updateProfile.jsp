<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form name="profileUpdate" action="updateProfile" method="post">
		<table>
			<tr>
				<td>username:</td>
				<td><input name="username" type="text" value="${profile.username}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<c:if test="${sessionScope.username == profile.username}">
					<td>password:</td>
					<td><input name="password" type="password" value="${password}"/> 
					<input id="passwordError" name="passwordError" value = "<c:out
							value='${requestScope.credentialsError.passwordError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
					</td>
				</c:if>
				<c:if test="${sessionScope.username != profile.username}">
					<input name="password" type="hidden" value="${password}"/>
				</c:if>
			</tr>
			<tr>
				<td>firstname:</td>
				<td><input name="firstname" type="text" value="${profile.firstname}"/> 
				<input id="firstnameError" name="firstnameError" value = "<c:out
						value='${requestScope.userDetailsError.firstnameError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
			<tr>
				<td>middlename:</td>
				<td><input name="middlename" type="text" value="${profile.middlename}"/><br/></td>
			</tr>
			<tr>
				<td>lastname:</td>
				<td><input name="lastname" type="text" value="${profile.lastname}"/>
						<input id="lastnameError" name="lastnameError" value = "<c:out
						value='${requestScope.userDetailsError.lastnameError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
			<tr>
				<td>uta id:</td>
				<td><input name="utaId" type="text" value="${profile.utaId}" readonly="readonly"/>
						<input id="utaIdError" name="utaIdError" value = "<c:out
						value='${requestScope.userDetailsError.utaIdError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
			<tr>
				<td>phone:</td>
				<td><input name="phone" type="text" value="${profile.phone}"/>
						<input id="phoneError" name="phoneError" value = "<c:out
						value='${requestScope.userDetailsError.phoneError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input name="email" type="text" value="${profile.email}"/>
						<input id="emailError" name="emailError" value = "<c:out
						value='${requestScope.userDetailsError.emailError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
			<tr>
				<td>numeric address:</td>
				<td><input name="numericAddress" type="text" value="${profile.numericAddress}"/>
				</td>
			</tr>
			<tr>
				<td>street address:</td>
				<td><input name="streetAddress" type="text" value="${profile.streetAddress}"/>
						<input id="streetAddressError" name="streetAddressError" value = "<c:out
						value='${requestScope.userDetailsError.streetAddressError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
			<tr>
				<td>city:</td>
				<td><input name="city" type="text" value="${profile.city}"/>
						<input id="cityError" name="cityError" value = "<c:out
						value='${requestScope.userDetailsError.cityError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
			<tr>
				<td>state:</td>
				<td><select name="state">
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
						<option value="AR">Arkansas</option>
						<option value="CA">California</option>
						<option value="CO">Colorado</option>
						<option value="CT">Connecticut</option>
						<option value="DE">Delaware</option>
						<option value="DC">District Of Columbia</option>
						<option value="FL">Florida</option>
						<option value="GA">Georgia</option>
						<option value="HI">Hawaii</option>
						<option value="ID">Idaho</option>
						<option value="IL">Illinois</option>
						<option value="IN">Indiana</option>
						<option value="IA">Iowa</option>
						<option value="KS">Kansas</option>
						<option value="KY">Kentucky</option>
						<option value="LA">Louisiana</option>
						<option value="ME">Maine</option>
						<option value="MD">Maryland</option>
						<option value="MA">Massachusetts</option>
						<option value="MI">Michigan</option>
						<option value="MN">Minnesota</option>
						<option value="MS">Mississippi</option>
						<option value="MO">Missouri</option>
						<option value="MT">Montana</option>
						<option value="NE">Nebraska</option>
						<option value="NV">Nevada</option>
						<option value="NH">New Hampshire</option>
						<option value="NJ">New Jersey</option>
						<option value="NM">New Mexico</option>
						<option value="NY">New York</option>
						<option value="NC">North Carolina</option>
						<option value="ND">North Dakota</option>
						<option value="OH">Ohio</option>
						<option value="OK">Oklahoma</option>
						<option value="OR">Oregon</option>
						<option value="PA">Pennsylvania</option>
						<option value="RI">Rhode Island</option>
						<option value="SC">South Carolina</option>
						<option value="SD">South Dakota</option>
						<option value="TN">Tennessee</option>
						<option value="TX">Texas</option>
						<option value="UT">Utah</option>
						<option value="VT">Vermont</option>
						<option value="VA">Virginia</option>
						<option value="WA">Washington</option>
						<option value="WV">West Virginia</option>
						<option value="WI">Wisconsin</option>
						<option value="WY">Wyoming</option>
				</select></td>
			</tr>
			<tr>
				<td>zipcode:</td>
				<td><input name="zipcode" type="text" value="${profile.zipcode}"/>
						<input id="zipcodeError" name="zipcodeError" value = "<c:out
						value='${requestScope.userDetailsError.zipcodeError}'/>" type="text" disabled="disabled" style ="background-color: white; color: red; border: none; width: 800px"> <br/><br/>
				</td>
			</tr>
		</table>
		<br /><br />
		<button type="submit">submit</button>
	</form>
	<br />
	<br />
	<a href="/mac/logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a id="home" href="/mac/login">Home</a>
</body>
</html>