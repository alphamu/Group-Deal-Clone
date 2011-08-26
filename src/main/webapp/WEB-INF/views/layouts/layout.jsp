<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
table#layout {
	width: 95%;
}
td.menu {
	width: 15%;
}
</style>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/smoothness/jquery-ui-1.8.16.custom.css"/>" />
<c:set var="titleKey">
	<tiles:insertAttribute name="title" ignore="true" />
</c:set>
<c:set var="titleValue" >
	<fmt:message key="${titleKey}" />
</c:set>
<title><fmt:message key="${titleKey}" /></title>
</head>
<body>
<table id="layout" border="1" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td colspan="2" class="header"><tiles:insertAttribute name="header" >
        	<tiles:putAttribute name="title" value="${titleValue}" />
        	</tiles:insertAttribute></td>
    </tr>
    <tr>
        <td class="menu">
        <tiles:insertAttribute name="menu" /></td>
        <td class="body"><tiles:insertAttribute name="body" >
        	<tiles:putAttribute name="title" value="${titleValue}"></tiles:putAttribute>
        </tiles:insertAttribute></td>
    </tr>
    <tr>
        <td colspan="2"><tiles:insertAttribute name="footer" /></td>
    </tr>
</table>
<script src="<c:url value="/resources/js/jquery-1.6.2.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js"/>"></script>
<script type="text/javascript">
$(function() {

	$( "#company\\.name" ).autocomplete({
		source: "<c:url value="/company/list"/>",
		minLength: 2,
		select: function( event, ui ) {
			if(ui.item) {
				$( "#company\\.id" ).val(ui.item.id);
			}
		}
	});
	
	$( "#campaignCategories" ).autocomplete({
		source: "<c:url value="/category/list"/>",
		minLength: 2,
		select: function( event, ui ) {
			if(ui.item) {
				var val = $( this ).val();
				if(val.lastIndexOf(',') == -1) {
					$(this).val(ui.item.label);
				} else {
					val = val.substring(0, val.lastIndexOf(',')+1);
					val += ui.item.label;
					$( this ).val(val);
				}
				return false;
			}
		}
	});
	
	$( "#campaignCities" ).autocomplete({
		source: "<c:url value="/city/list"/>",
		minLength: 2,
		select: function( event, ui ) {
			if(ui.item) {
				var val = $( this ).val();
				if(val.lastIndexOf(',') == -1) {
					$(this).val(ui.item.label);
				} else {
					val = val.substring(0, val.lastIndexOf(',')+1);
					val += ui.item.label;
					$( this ).val(val);
				}
				return false;
			}
		}
	});
});

</script>
</body>
</html>