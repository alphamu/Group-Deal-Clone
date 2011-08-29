<%@ include file="/WEB-INF/views/include.jsp"%>

<h2>Cities</h2>

<form action="<c:url value="/location"/>" method="post" >
<c:if test="${not empty cities}">
	<select id="location" name="city">
		<c:forEach var="city" items="${cities}">
			<option class="name" value="${city.name}">${city.name}</option>
		</c:forEach>
	</select>
	<input  type="submit" value="Change City"/>
</c:if>

</form>


