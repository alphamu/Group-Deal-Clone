<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page session="false"%>

<h3>
	<tiles:insertAttribute name="title" ignore="true" />
</h3>

<h2>Featured Deals</h2>
<c:if test="${not empty regular}">
	<div class="featured-deal">
		<c:forEach var="f" items="${featured}">
			<ul class="deal">
				<li><span class="desc">${r.deal.description}</span></li>
				<li><span class="discount">${r.deal.discountPercentage}</span>
				</li>
				<li><span class="price">${r.deal.price}</span></li>
				<li><span class="minSale">${r.deal.minSaleRequired}</span></li>
			</ul>
		</c:forEach>
	</div>
</c:if>

<h2>Deals in progress</h2>
<c:if test="${not empty regular}">
	<div class="regular-deal">
		<c:forEach var="r" items="${regular}">
			<ul class="deal">
				<li><span class="desc">${r.deal.description}</span></li>
				<li><span class="discount">${r.deal.discountPercentage}</span>
				</li>
				<li><span class="price">${r.deal.price}</span></li>
				<li><span class="minSale">${r.deal.minSaleRequired}</span></li>
			</ul>
		</c:forEach>
	</div>
</c:if>

<h2>Companies</h2>
<c:if test="${not empty company}">
	<ul class="company">
		<c:forEach var="co" items="${company}">
			<li><span class="name">${co.name}</span></li>
		</c:forEach>
	</ul>
</c:if>

<h2>Categories</h2>
<c:if test="${not empty categories}">
	<ul class="category">
		<c:forEach var="cat" items="${categories}">
			<li><span class="name">${cat.name}</span></li>
		</c:forEach>
	</ul>
</c:if>