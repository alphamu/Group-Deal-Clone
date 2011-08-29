<%@ include file="/WEB-INF/views/include.jsp"%>

<h2>Categories</h2>
<c:if test="${not empty categories}">
	<ul class="category">
		<c:forEach var="cat" items="${categories}">
			<li><span class="name">${cat.name}</span></li>
		</c:forEach>
	</ul>
</c:if>

<div class="menu_seperator seperator"><!--  --></div>

<h2>Companies</h2>
<c:if test="${not empty companies}">
	<ul class="company">
		<c:forEach var="co" items="${companies}">
			<li><span class="name">${co.name}</span></li>
		</c:forEach>
	</ul>
</c:if>

