<%@ include file="/WEB-INF/views/include.jsp" %>

<h1><tiles:insertAttribute name="title" ignore="true" /></h1>
<table>

<tr><td>Deal Description:</td></tr>
<tr><td><c:out value="${deal.description}" /></td></tr>
<tr><td>Deal Price:</td></tr>
<tr><td><c:out value="${deal.price}" /></td></tr>
<tr><td>Minimum Required:</td></tr>
<tr><td><c:out value="${deal.minSaleRequired}" /></td></tr>
<tr><td>Deal Discount:</td></tr>
<tr><td><c:out value="${deal.discountPercentage}" />%</td></tr>

</table>

<a href="<c:url value="home.html"/>">Home</a>
