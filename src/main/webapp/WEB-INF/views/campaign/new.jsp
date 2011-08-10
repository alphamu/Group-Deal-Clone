<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" />
</title>
</head>
<body>
	<h1>
		<fmt:message key="newdeal.title" />
	</h1>
	<form:form action="new" commandName="campaign">
		<form:hidden path="id" />
		<form:hidden path="company.id" />
		<table>
			<tr>
				<td>Company Name:<FONT color="red"><form:errors
							path="company.name" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="company.name" />
				</td>
			</tr>
			<tr>
				<td>Campaign Name:<FONT color="red"><form:errors
							path="name" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="name" />
				</td>
			</tr>
			<tr>
				<td>Campaign Start Date:<FONT color="red"><form:errors
							path="startDate" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="startDate" />
				</td>
			</tr>
			<tr>
				<td>Campaign End Date:<FONT color="red"><form:errors
							path="endDate" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="endDate" />
				</td>
			</tr>
			<tr>
				<td>Featured:<FONT color="red"><form:errors
							path="featured" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:checkbox path="featured" />
				</td>
			</tr>
			<tr>
				<td>Deal Description:<FONT color="red"><form:errors
							path="deal.description" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="deal.description" />
				</td>
			</tr>
			<tr>
				<td>Deal Price:<FONT color="red"><form:errors
							path="deal.price" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="deal.price" />
				</td>
			</tr>
			<tr>
				<td>Minimum Required:<FONT color="red"><form:errors
							path="deal.minSaleRequired" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="deal.minSaleRequired" />
				</td>
			</tr>
			<tr>
				<td>Deal Discount:<FONT color="red"><form:errors
							path="deal.discountPercentage" />
				</FONT>
				</td>
			</tr>
			<tr>
				<td><form:input path="deal.discountPercentage" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><form:checkboxes items="${campaignCities.cities}" path="campaignCities" itemLabel="name" itemValue="id" delimiter="<br/>" /></td>
			</tr>


		</table>

		<input type="submit" value="Execute">
	</form:form>
	<a href="<c:url value="/home"/>">Home</a>
</body>
</html>