<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/include.jsp" %>

<h1>Edit City</h1>
<form:form action="edit" commandName="city">
<form:hidden path="id" />
<table>
<tr><td>City Name:<FONT color="red"><form:errors path="name" /></FONT></td></tr>
<tr><td><form:input path="name" /></td></tr>
</table>
<input type="submit" value="Execute">
</form:form>
<a href="<c:url value="/home"/>">Home</a>
</body>
</html>