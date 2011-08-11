<%@ include file="/WEB-INF/views/include.jsp"%>

<table>
	<tr>
		<td>Company Name:</td>
	</tr>
	<tr>
		<td><c:out value="${company.name}" />
		</td>
	</tr>
</table>
<a href="<c:url value="/home"/>">Home</a>
