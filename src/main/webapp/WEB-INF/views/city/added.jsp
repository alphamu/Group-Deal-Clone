<%@ include file="/WEB-INF/views/include.jsp"%>


<table>
	<tr>
		<td>City Name:</td>
	</tr>
	<tr>
		<td><c:out value="${city.name}" />
		</td>
	</tr>
</table>
<a href="<c:url value="/home"/>">Home</a>
