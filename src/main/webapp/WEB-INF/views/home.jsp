<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false"%>
<html>
<head>
<title><fmt:message key="heading"/></title>
</head>
<body>
	<h1><fmt:message key="title"/></h1>

	<P>The time on the server is ${model.serverTime}.</P>

	<h3>Products</h3>
	<c:forEach items="${model.deals}" var="deal">
		<c:out value="${deal.description}" />
		<i>$<c:out value="${deal.price}" /></i>
		<br>
		<br>
	</c:forEach>
</body>
</html>
