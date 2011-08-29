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
if(gup("reload") == "false")
	window.parent.location.reload();
	window.location.href = window.location.href+"?reload=false";
</script>
</head>
<body>

</body>
</html>