<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page session="false"%>

<div class="menu_seperator seperator"><!--  --></div>
<h2>Featured Deals</h2>
<c:if test="${not empty featured}">
	<div class="featured-deal">
		<c:forEach var="f" items="${featured}">
			<ul class="deal">
				<li><span class="desc"><a href="<c:url value="/show/deal/${f.deal.id}" />">${f.deal.description}</a></span></li>
				<li><span class="discount">${f.deal.discountPercentage}</span></li>
				<li><span class="price">${f.deal.price}</span></li>
				<li><span class="minSale">${f.deal.minSaleRequired}</span></li>
				<li><span class="buy"><a class="addToCart" href="<c:url value="/shoppingcart/add/${f.deal.id}" />">Buy</a></span></li>
			</ul>
		</c:forEach>
	</div>
</c:if>

<div class="content_seperator seperator"><!--  --></div>

<h2>Deals in progress</h2>
<c:if test="${not empty regular}">
	<div class="regular-deal">
		<c:forEach var="r" items="${regular}">
			<ul class="deal">
				<li><span class="desc"><a href="<c:url value="/show/deal/${r.deal.id}" />">${r.deal.description}</a></span></li>
				<li><span class="discount">${r.deal.discountPercentage}</span></li>
				<li><span class="price">${r.deal.price}</span></li>
				<li><span class="minSale">${r.deal.minSaleRequired}</span></li>
				<li><span class="buy"><a class="addToCart" href="<c:url value="/shoppingcart/add/${r.deal.id}" />">Buy</a></span></li>
			</ul>
		</c:forEach>
	</div>
</c:if>