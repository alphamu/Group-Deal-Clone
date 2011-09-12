<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
<head>
<script type="text/javascript">

function gup( name )
{
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( window.location.href );
  if( results == null )
    return "";
  else
    return results[1];
}

<sec:authorize access="isAuthenticated()">
	window.top.window.closeDialog();
</sec:authorize>
	
</script>


</head>
<body>
</body>
</html>