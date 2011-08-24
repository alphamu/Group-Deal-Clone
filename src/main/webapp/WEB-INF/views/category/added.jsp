<%@ include file="/WEB-INF/views/include.jsp"%>


<table>
	<tr>
		<td>Category Name:</td>
	</tr>
	<tr>
		<td><c:out value="${category.name}" />
		</td>
	</tr>
</table>
<a href="<c:url value="/home"/>">Home</a>
