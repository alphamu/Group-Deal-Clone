<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head><title></title></head>
<body>
<form:form action="edit" commandName="account" >
<form:hidden path="id" />
	<table>
		<tr>
			<td>Full Name:</td>
			<td><form:input path="fullname" /></td>
			<td><FONT color="red"><form:errors path="fullname" /></FONT></td>
		</tr>
		<tr>
			<td>Email Address:</td>
			<td><form:input path="username" /></td>
			<td><FONT color="red"><form:errors path="username" /></FONT></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:password path="password" /></td>
			<td><FONT color="red"><form:errors path="password" /></FONT></td>
		</tr>
		<tr>
			<td>Confirm Password:</td>
			<td><form:password path="confirmPassword" /></td>
			<td><FONT color="red"><form:errors path="confirmPassword" /></FONT></td>
		</tr>
		<tr>
			<td>Make Administrator:</td>
			<td><form:checkbox path="admin" /></td>
			<td><FONT color="red"><form:errors path="admin" /></FONT></td>
		</tr>
		<tr>
			<td>Account Not Expired:</td>
			<td><form:checkbox path="accountNonExpired" value="true"/></td>
			<td><FONT color="red"><form:errors path="accountNonExpired" /></FONT></td>
		</tr>
		<tr>
			<td>Account Not Locked:</td>
			<td><form:checkbox path="accountNonLocked" value="true"/></td>
			<td><FONT color="red"><form:errors path="accountNonLocked" /></FONT></td>
		</tr>
		<tr>
			<td>Credentials Not Expired:</td>
			<td><form:checkbox path="credentialsNonExpired" value="true"/></td>
			<td><FONT color="red"><form:errors path="credentialsNonExpired" /></FONT></td>
		</tr>
		<tr>
			<td>Account Enabled:</td>
			<td><form:checkbox path="enabled" /></td>
			<td><FONT color="red"><form:errors path="enabled" /></FONT></td>
		</tr>

	</table>

	<input type="submit" value="Modify Account">
</form:form>
</body>
</html>
