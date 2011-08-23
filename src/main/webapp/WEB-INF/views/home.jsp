<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false"%>

<h3><tiles:insertAttribute name="title" ignore="true" /></h3>

<h2>Featured Deals</h2>
<c:forEach var="f" items="${featured}">
	<div class="featured-deal deal">
		<div class="desc">${f.deal.description}</div>
		<div class="discount">${f.deal.discountPercentage}</div>
		<div class="price">${f.deal.price}</div>
		<div class="minSale">${f.deal.minSaleRequired}</div>
	</div>
</c:forEach>

<h2>Deals in progress</h2>
<c:forEach var="r" items="${regular}">
	<div class="regular-deal deal">
		<div class="desc">${r.deal.description}</div>
		<div class="discount">${r.deal.discountPercentage}</div>
		<div class="price">${r.deal.price}</div>
		<div class="minSale">${r.deal.minSaleRequired}</div>
	</div>
</c:forEach>

<h2>Companies</h2>
<c:forEach var="co" items="${company}">
	<div class="companies">
		<div class="name">${co.name}</div>
	</div>
</c:forEach>