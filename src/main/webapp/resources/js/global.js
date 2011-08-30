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
			   $("#loginFrame").attr("src",$(this).attr("href"));
			   return false;
		   });
		   
		   
		   $('a#chpwd').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src",$(this).attr("href"));
			   return false;
		   });
		   
		   $('a#signup').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src",$(this).attr("href"));
			   return false;
		   });
		   
		   $('a#location').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src",$(this).attr("href"));
			   return false;
		   });
		   
		   $('a.addToCart').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src",$(this).attr("href"));
			   $(div).bind("dialogclose", function(){
				  window.location.reload(); 
			   });
			   return false;
		   });
		   
		   $('a#cart').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src",$(this).attr("href"));
			   return false;
		   });
		   
		   
/* 		   
 * removed because it seems pointless since the page reloads any ways. 
 $('a.removeImage').bind('click', function(){
			   $(div).dialog("open");
			   $("#loginFrame").attr("src",$(this).attr("href"));
			   $(this).siblings("img").hide();
			   $(div).dialog("close");
			   return false;
		   }); */
		   
		   /* Only there for new capaign right now */
			$("#startDate, #endDate").datepicker({
				dateFormat : "dd M yy"
			});

		});
	
	