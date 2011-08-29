<%@ include file="/WEB-INF/views/include.jsp"%>

<h2>Categories</h2>
<c:if test="${not empty categories}">
	<ul class="category">
		<c:forEach var="cat" items="${categories}">
			<li><a href="<c:url value="/show/category/${cat.id}" />"><span class="name">${cat.name}</span></a></li>
		</c:forEach>
	</ul>
</c:if>

<div class="menu_seperator seperator"><!--  --></div>

<h2>Companies</h2>
<c:if test="${not empty companies}">
	<ul class="company">
		<c:forEach var="co" items="${companies}">
			<li><a href="<c:url value="/show/company/${co.id}" />"><span class="name">${co.name}</span></a></li>
		</c:forEach>
	</ul>
</c:if>

