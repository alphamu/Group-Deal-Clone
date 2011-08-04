<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="newdeal.heading"/></h1>
 <table>

<tr><td>Campaign Name:</td></tr>
<tr><td><c:out value="${campaign.name}" /></td></tr>
<tr><td>Deal Price:</td></tr>
<tr><td><c:out value="${campaign.deal.price}" /></td></tr>
<tr><td>Minimum Required:</td></tr>
<tr><td><c:out value="${campaign.deal.minSaleRequired}" /></td></tr>
<tr><td>Deal Discount:</td></tr>
<tr><td><c:out value="${campaign.deal.discountPercentage}" />%</td></tr>

</table>

<a href="<c:url value="home"/>">Home</a>
</body>
</html>