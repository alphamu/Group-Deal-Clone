<%@ include file="/WEB-INF/views/include.jsp" %>

<h3><tiles:insertAttribute name="title" ignore="true" /></h3>
<c:forEach var="deal" items="${deals}">
	<a href="<c:url value="/shoppingcart/remove/${deal.id}"/>">Remove</a> ${deal.id} - ${deal.description} - ${deal.price}<br/>
</c:forEach>