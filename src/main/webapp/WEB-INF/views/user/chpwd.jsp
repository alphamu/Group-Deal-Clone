<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Login</h1>

<div id="login-error">${error}</div>

<form:form action="chpwd" commandName="chpwd">
<table border="0" cellspacing="0" cellpadding="0">
<tr>
	<td><label for="currentpwd">Current Password:</label></td>
	<td><input id="currentpwd" name="currentpwd" type="password" /></td>
	<td><FONT color="red">${currentpwd}</FONT></td>
</tr>
<tr>
	<td><label for="newpwd">New Password:</label></td>
	<td><input id="newpwd" name="newpwd" type="password" /></td>
	<td><FONT color="red">${newpwd}</FONT></td>
	
</tr>
<tr>
	<td><label for="confirmpwd">Confirm New Password:</label></td>
	<td><input id="confirmpwd" name="confirmpwd" type="password" /></td>
	<td><FONT color="red">${confirmpwd}</FONT></td>
</tr>

<tr>
	<td>&nbsp;</td>
	<td><input  type="submit" value="Change Password"/></td>
</tr>								
</table>	
</form:form>

</body>
</html>