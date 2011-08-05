<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false"%>
<html>
<head>
<title><fmt:message key="heading"/></title>
</head>
<body>
	<h1><fmt:message key="title"/></h1>

	<h3>Products</h3>
<c:forEach var="deal" items="${deals.deals}">
	${deal.id} - ${deal.description}<br/>
</c:forEach>
</body>
</html>
