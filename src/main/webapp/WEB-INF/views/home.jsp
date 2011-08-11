<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false"%>

<h3><tiles:insertAttribute name="title" ignore="true" /></h3>
<c:forEach var="deal" items="${deals.deals}">
	${deal.id} - ${deal.description}<br/>
</c:forEach>

