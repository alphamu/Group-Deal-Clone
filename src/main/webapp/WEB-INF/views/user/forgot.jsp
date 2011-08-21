<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head><title></title></head>
<body>
<FONT color="black">${message}</FONT>
<FONT color="red">${error}</FONT>
<form:form action="forgot" commandName="resetPassword" >
	<table>
		<tr>
			<td>Email Address:</td>
			<td><form:input path="username" /></td>
		</tr>
	</table>

	<input type="submit" value="Reset my password and email me">
</form:form>

<a href="<c:url value="/home"/>">Home</a> | 
<a href="<c:url value="/auth/login"/>">Login</a>

</body>
</html>
