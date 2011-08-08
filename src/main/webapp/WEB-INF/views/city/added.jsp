<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
  <title><fmt:message key="title"/></title>
</head>
<body>
<h1>City Updated</h1>
<table>
<tr><td>City Name:</td></tr>
<tr><td><c:out value="${city.name}" /></td></tr>
</table>
<a href="<c:url value="/home"/>">Home</a>
</body>
</html>