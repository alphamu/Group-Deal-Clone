<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account Activation</title>
</head>
<body>
<p>${message}</p>
<p>
Account activation 
<c:if test="${success}">
was successful - <a href="<c:url value="/auth/login"/>">Login</a>
</c:if>
<c:if test="!${success}">failed</c:if>
</p>
</body>
</html>