<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
<c:set var="titleKey">
	<tiles:insertAttribute name="title" ignore="true" />
</c:set>
<title><fmt:message key="${titleKey}" /></title>

</head>
<body>
<table id="layout" border="1" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td colspan="2" class="header"><tiles:insertAttribute name="header" /></td>
    </tr>
    <tr>
        <td class="menu"><tiles:insertAttribute name="menu" /></td>
        <td class="body"><tiles:insertAttribute name="body" /></td>
    </tr>
    <tr>
        <td colspan="2"><tiles:insertAttribute name="footer" /></td>
    </tr>
</table>
</body>
</html>