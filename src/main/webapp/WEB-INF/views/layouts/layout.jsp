<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
</style>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/smoothness/jquery-ui-1.8.16.custom.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/gdc.css"/>" />
<c:set var="titleKey">
	<tiles:insertAttribute name="title" ignore="true" />
</c:set>
<c:set var="titleValue">
	<fmt:message key="${titleKey}" />
</c:set>
<title><fmt:message key="${titleKey}" /></title>
</head>
<body>
	<img alt="full screen background image"
		src="<c:url value="/resources/img/karachi-nobo23-2.jpg" />"
		id="full-screen-background-image" />
	<div id="layout">
		<div id="top" class="clearfix">

			<div class="topMenu">
				<sec:authorize access="!isAuthenticated()">
					<a id="login" href="<c:url value="/auth/login"/>" class="topMenuLink">Login</a> | 
					<a id="signup" href="<c:url value="/user/new"/>" class="topMenuLink">Sign up</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<a id="logout" href="<c:url value="/auth/logout"/>" class="topMenuLink">Log Out</a> | 
					<a id="chpwd" href="<c:url value="/user/chpwd"/>" class="topMenuLink">Change Password</a> | 
					<a id="location" href="<c:url value="/location"/>" class="topMenuLink">Change Location</a> | 
					<a id="cart" href="<c:url value="/shoppingcart/show"/>" class="topMenuLink">Cart</a>
				</sec:authorize>
			</div>
			
		</div>
		
		<div id="header" class="roundCorners">
			<tiles:insertAttribute name="header">
				<tiles:putAttribute name="title" value="${titleValue}" />
			</tiles:insertAttribute>
		</div>
		<div class="clearer">
			<!--  -->
		</div>
		<div id="menu" class="roundCorners">
			<tiles:insertAttribute name="menu" />
		</div>
		<div id="content" class="roundCorners">
			<div id="contentTitle">
				<h2>
					<fmt:message key="${titleKey}" /> - ${location}
				</h2>
			</div>
			<tiles:insertAttribute name="body">
				<tiles:putAttribute name="title" value="${titleValue}"></tiles:putAttribute>
			</tiles:insertAttribute>
		</div>
		<div class="clearer">
			<!--  -->
		</div>
		<div id="footer" class="roundCorners">
			<tiles:insertAttribute name="footer"/>
		</div>
	</div>

	<script src="<c:url value="/resources/js/jquery-1.6.2.js"/>"></script>
	<script
		src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js"/>"></script>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		var div = $("<div id='loginDiv'><iframe id='loginFrame' frameBorder='0'></iframe></div>");
		   $(div).dialog({
		           autoOpen: false,
		           modal: true,
		           height: 400,
		           width: 500
		       });
		   
		   $('a#login').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src","<c:url value="/auth/login"/>");
			   return false;
		   });
		   
		   
		   $('a#chpwd').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src","<c:url value="/user/chpwd"/>");
			   return false;
		   });
		   
		   $('a#signup').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src","<c:url value="/user/new"/>");
			   return false;
		   });
		   
		   $('a#location').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src","<c:url value="/location"/>");
			   return false;
		   });
		});
	
		$(function() {

			$("#company\\.name").autocomplete({
				source : "<c:url value="/company/list"/>",
				minLength : 2,
				select : function(event, ui) {
					if (ui.item) {
						$("#company\\.id").val(ui.item.id);
					}
				}
			});

			$("#campaignCategories").autocomplete({
				source : "<c:url value="/category/list"/>",
				minLength : 2,
				select : function(event, ui) {
					if (ui.item) {
						var val = $(this).val();
						if (val.lastIndexOf(',') == -1) {
							$(this).val(ui.item.label);
						} else {
							val = val.substring(0, val.lastIndexOf(',') + 1);
							val += ui.item.label;
							$(this).val(val);
						}
						return false;
					}
				}
			});

			$("#campaignCities").autocomplete({
				source : "<c:url value="/city/list"/>",
				minLength : 2,
				select : function(event, ui) {
					if (ui.item) {
						var val = $(this).val();
						if (val.lastIndexOf(',') == -1) {
							$(this).val(ui.item.label);
						} else {
							val = val.substring(0, val.lastIndexOf(',') + 1);
							val += ui.item.label;
							$(this).val(val);
						}
						return false;
					}
				}
			});
		});

		$("#startDate, #endDate").datepicker({
			dateFormat : "dd M yy"
		});
	</script>
</body>
</html>