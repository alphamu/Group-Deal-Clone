<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<form:form action="edit" commandName="campaign"
	enctype="multipart/form-data">
	<form:hidden path="id" />
	<form:hidden path="company.id" />
	<form:hidden path="deal.id" />
	<form:hidden path="imageStore.id" />
	<table>
		<tr>
			<td>Company Name: <FONT color="red"><form:errors
						path="company.name" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="company.name" />
			</td>
		</tr>
		<tr>
			<td>Campaign Name: <FONT color="red"><form:errors
						path="name" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="name" />
			</td>
		</tr>
		<tr>
			<td>Campaign Start Date: <FONT color="red"><form:errors
						path="startDate" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="startDate" />
			</td>
		</tr>
		<tr>
			<td>Campaign End Date: <FONT color="red"><form:errors
						path="endDate" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="endDate" />
			</td>
		</tr>
		<tr>
			<td>Featured: <FONT color="red"><form:errors
						path="featured" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:checkbox path="featured" />
			</td>
		</tr>
		<tr>
			<td>Deal Description: <FONT color="red"><form:errors
						path="deal.description" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="deal.description" />
			</td>
		</tr>
		<tr>
			<td>Deal Price: <FONT color="red"><form:errors
						path="deal.price" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="deal.price" />
			</td>
		</tr>
		<tr>
			<td>Minimum Required: <FONT color="red"><form:errors
						path="deal.minSaleRequired" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="deal.minSaleRequired" />
			</td>
		</tr>
		<tr>
			<td>Deal Discount: <FONT color="red"><form:errors
						path="deal.discountPercentage" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="deal.discountPercentage" />
			</td>
		</tr>
		<tr>
			<td>Categories: <FONT color="red"><form:errors
						path="campaignCategories" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="campaignCategories" />
			</td>
		</tr>
		<tr>
			<td>Cities: <FONT color="red"><form:errors
						path="campaignCities" /> </FONT>
			</td>
		</tr>
		<tr>
			<td><form:input path="campaignCities" />
			</td>
		</tr>
		<%--
			
								<c:forEach varStatus="n" var="city" items="${campaignCities.cities}">
									<tr id="city_${city.id}">
										<td><c:out value="${city.name}" />
										</td>
										<td><c:set var="contains" value="false" /> <c:forEach
												var="item" items="${campaign.campaignCities.cities}">
												<c:if test="${item.name eq city.name}">
													<c:set var="contains" value="true" />
													<input type="checkbox" id="campaignCities${city.id}"
														name="campaignCities" value="${city.id}" checked />
												</c:if>
											</c:forEach> <c:if test="${!contains}">
												<input type="checkbox" id="campaignCities${city.id}"
													name="campaignCities" value="${city.id}" />
											</c:if>
										</td>
									</tr>
								</c:forEach>
				 
		--%>

		<tr >
			<td style="vertical-align:middle">Thumbnail Small</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.thumbnailSmall" /> </FONT><br /> <input type="file"
				name="imageStore.thumbnailSmall" />
			</td>
			<c:if test="${ not empty campaign.imageStore.thumbnailSmall }">
				<td><form:hidden path="imageStore.thumbnailSmall.id" /> <img
					src="<c:url value="/images/stored/" />${campaign.imageStore.thumbnailSmall.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.thumbnailSmall.id}">remove</a>
				</td>
			</c:if>
		</tr>
		<tr >
			<td style="vertical-align:middle">Thumbnail Medium</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.thumbnailMedium" /> </FONT><br /> <input type="file"
				name="imageStore.thumbnailMedium" /></td>
			<c:if test="${ not empty campaign.imageStore.thumbnailMedium }">
				<td><form:hidden path="imageStore.thumbnailMedium.id" /> <img
					src="<c:url value="/images/stored/" />${campaign.imageStore.thumbnailMedium.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.thumbnailMedium.id}">remove</a>
				</td>
			</c:if>
		</tr>
		<tr >
			<td style="vertical-align:middle">Thumbnail Large</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.thumbnailLarge" /> </FONT><br /> <input type="file"
				name="imageStore.thumbnailLarge" /></td>
			<c:if test="${ not empty campaign.imageStore.thumbnailLarge }">
				<td><form:hidden path="imageStore.thumbnailLarge.id" /> <img
					src="<c:url value="/images/stored/" />${campaign.imageStore.thumbnailLarge.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.thumbnailLarge.id}">remove</a>
				</td>
			</c:if>

		</tr>
		<tr >
			<td style="vertical-align:middle">Thumbnail X-Large</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.thumbnailXLarge" /> </FONT><br /> <input type="file"
				name="imageStore.thumbnailXLarge" /></td>
			<c:if test="${ not empty campaign.imageStore.thumbnailXLarge }">
				<td><form:hidden path="imageStore.thumbnailXLarge.id" /> <img
					src="<c:url value="/images/stored/" />${campaign.imageStore.thumbnailXLarge.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.thumbnailXLarge.id}">remove</a>
				</td>
			</c:if>
		</tr>

		<tr >
			<td style="vertical-align:middle">Banner Small</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.bannerSmall" /> </FONT><br /> <input type="file"
				name="imageStore.bannerSmall" /></td>
			<c:if test="${ not empty campaign.imageStore.bannerSmall }">
				<td><form:hidden path="imageStore.bannerSmall.id"/><img
					src="<c:url value="/images/stored/" />${campaign.imageStore.bannerSmall.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.bannerSmall.id}">remove</a>
				</td>
			</c:if>
		</tr>
		<tr >
			<td style="vertical-align:middle">Banner Medium</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.bannerMedium" /> </FONT><br /> <input type="file"
				name="imageStore.bannerMedium" /></td>
			<c:if test="${ not empty campaign.imageStore.bannerMedium }">
				<td><form:hidden path="imageStore.bannerMedium.id"/><img
					src="<c:url value="/images/stored/" />${campaign.imageStore.bannerMedium.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.bannerMedium.id}">remove</a>
				</td>
			</c:if>
		</tr>
		<tr >
			<td style="vertical-align:middle">Banner Large</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.bannerLarge" /> </FONT><br /> <input type="file"
				name="imageStore.bannerLarge" /></td>
			<c:if test="${ not empty campaign.imageStore.bannerLarge }">
				<td><form:hidden path="imageStore.bannerLarge.id"/><img
					src="<c:url value="/images/stored/" />${campaign.imageStore.bannerLarge.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.bannerLarge.id}">remove</a>
				</td>
			</c:if>
		</tr>
		<tr >
			<td style="vertical-align:middle">Banner X-Large</td>
			<td style="vertical-align:middle"><FONT color="red"><form:errors
						path="imageStore.bannerXLarge" /> </FONT><br /> <input type="file"
				name="imageStore.bannerXLarge" /></td>
			<c:if test="${ not empty campaign.imageStore.bannerXLarge }">
				<td><form:hidden path="imageStore.bannerXLarge.id"/><img
					src="<c:url value="/images/stored/" />${campaign.imageStore.bannerXLarge.id}" /><br />
					<a class="removeImage"
					href="<c:url value="/campaign/${campaign.id}/i/r/" />${campaign.imageStore.bannerXLarge.id}">remove</a>
				</td>
			</c:if>
		</tr>

<%-- 		<c:forEach varStatus="n" var="img"
			items="${campaign.imageStore.image}">
			<tr>
				<td>Image ${n.count}</td>
				<td><img src="<c:url value="/images/stored/" />${img.id}" /><br />
					<a href="<c:url value="/campaign/${campaign.id}/i/r/" />${img.id}">remove</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td>Campaign Image</td>
			<td><FONT color="red"><form:errors path="imageStore" />
			</FONT><br /> <input type="file" name="imageStore" /><br /> <input
				type="file" name="imageStore" /></td>
		</tr> --%>
	</table>

	<input type="submit" value="Execute">
</form:form>
<a href="<c:url value="/home" />">Home</a>
