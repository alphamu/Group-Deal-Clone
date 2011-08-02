<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="newdeal.heading"/></h1>
<form:form action="new-deal-input.html" commandName="deal">
 <table>

<tr><td>Deal Description:<FONT color="red"><form:errors path="description" /></FONT></td></tr>
<tr><td><form:input path="description" /></td></tr>
<tr><td>Deal Price:<FONT color="red"><form:errors path="price" /></FONT></td></tr>
<tr><td><form:input path="price" /></td></tr>
<tr><td>Minimum Required:<FONT color="red"><form:errors path="minSaleRequired" /></FONT></td></tr>
<tr><td><form:input path="minSaleRequired" /></td></tr>
<tr><td>Deal Discount:<FONT color="red"><form:errors path="discountPercentage" /></FONT></td></tr>
<tr><td><form:input path="discountPercentage" /></td></tr>

</table>

<input type="submit" value="Execute">
</form:form>
<a href="<c:url value="home.html"/>">Home</a>
</body>
</html>