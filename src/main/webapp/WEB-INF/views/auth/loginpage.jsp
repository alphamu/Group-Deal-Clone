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

<form action="../j_spring_security_check" method="post" >
<table border="0" cellspacing="0" cellpadding="0">
<tr>
	<td><label for="j_username">Username:</label></td>
	<td><input id="j_username" name="j_username" type="text" /></td>
</tr>
<tr>
	<td><label for="j_password">Password:</label></td>
	<td><input id="j_password" name="j_password" type="password" /></td>
</tr>
<tr>
	<td><label for="_spring_security_remember_me">Remember Me:</label></td>
	<td><input type='checkbox' name='_spring_security_remember_me'/></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td><input  type="submit" value="Login"/></td>
</tr>								
</table>	
</form>

<span class="newMembersSignup"><a href="<c:url value="/user/new"/>">New members sign-up</a></span> 
<span class="forgotPassword"><a href="<c:url value="/user/forgot"/>">Forgot password?</a></span>
</body>
</html>