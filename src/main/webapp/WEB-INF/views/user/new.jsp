<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head><title></title></head>
<body>
<form:form action="new" commandName="account" >
	<table>
		<tr>
			<td>Email Address:</td>
			<td><form:input path="username" /></td>
			<td><FONT color="red"><form:errors path="username" /></FONT></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:input path="password" /></td>
			<td><FONT color="red"><form:errors path="password" /></FONT></td>
		</tr>
		<tr>
			<td>Confirm Password:</td>
			<td><form:input path="confirmPassword" /></td>
			<td><FONT color="red"><form:errors path="confirmPassword" /></FONT></td>
		</tr>
		<c:if test="${isadmin}">
		<tr>
			<td>Make Administrator:</td>
			<td><form:checkbox path="admin" /></td>
			<td><FONT color="red"><form:errors path="admin" /></FONT></td>
		</tr>			
		</c:if>

	</table>

	<input type="submit" value="Create Account">
</form:form>
</body>
</html>
