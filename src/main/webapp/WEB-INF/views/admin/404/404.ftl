<!doctype html>
<html>
    <head>
        <meta http-equiv="CONTENT-TYPE" content="text/html;" charset="UTF-8" />
        <title>${msg("error.msg.404")}</title>
        <style type="text/css">
	        body{background-color:#9fa8b1; background-size:cover;}
	        .error_box{background-image:url(${path}/resources/images/404/error_bg.png); background-repeat:no-repeat; margin:0 auto; width:942px; height:387px; margin-top:3%; position:relative; overflow:hidden;}
	        .left{float:left; width:483px; height:387px;}
	        .right{float:left; width:459px; height:387px;}
	        .right ul{margin-top:40px; list-style:none; color:#626A76;}
	        .right ul li{margin-bottom:5px;}
        </style>
    </head>
	<body>
		<div class="error_box">
	    	<div class="left"></div>
	        <div class="right">
	       		<ul>
	           		<li>
	           			<h3>${msg("error.msg.info")}</h3>
	           		</li>
	            	<li>${msg("error.msg.url.error")}</li>
	              	<li>${msg("error.msg.content.error")}</li>
	              	<li>
	              		<h2>${msg("error.msg.tip")}</h2>
	              	</li>
	              	<li>&radic; <a href="javascript:;" onclick="window.history.back(); return false;">${msg("error.msg.back.page")}</a></li>
	              	<li>&radic; <a href="/">${msg("error.msg.back.home")}</a></li>
	           </ul>
	       </div>
	    </div>
	</body>
</html>
