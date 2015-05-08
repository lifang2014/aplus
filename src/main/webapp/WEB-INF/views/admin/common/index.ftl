<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="${base }/resources/menu/js/jquery-1.8.2.js"></script>
	<script src="${base }/resources/menu/js/jquery.bxslider.min.js"></script>
	<link href="${base }/resources/menu/css/jquery.bxslider.css" rel="stylesheet" />
</head>
<body>

<div id="main">
<ul class="bxslider">
  <li>
    <ul>
        <li>
          <a href="${base }/admin/menu/main.html">
	          <div class="case_w">
	              <img src="${base }/resources/menu/images/renren.png" />
	             <div class="fire"></div> 
	            <a href="#" class="x"></a>
	            <a href="#" class="y"></a>
          </div>
          </a>
        </li>
        <li>
        <div class="case_w">
            <img src="${base }/resources/menu/images/zhifubao.png" />
            <div class="fire"></div>
            <a href="#" class="x"></a>
            <a href="#" class="y"></a>
        </div>          
      </li>
   </ul>
</li>

<li>
    <ul>
        <li>
          <div class="case_w">
              <img src="${base }/resources/menu/images/sina.png" />
              <div class="fire"></div> 
            <a href="#" class="x"></a>
            <a href="#" class="y"></a>
          </div>
        </li>
        <li>
        <div class="case_w">
            <img src="${base }/resources/menu/images/appstore.com.png" />
            <div class="fire"></div>
            <a href="#" class="x"></a>
            <a href="#" class="y"></a>
        </div>          
      </li>
   </ul>
</li>

<li>
    <ul>
        <li>
          <div class="case_w">
              <img src="${base }/resources/menu/images/douban.png" />
             <div class="fire"></div> 
            <a href="#" class="x"></a>
            <a href="#" class="y"></a>
          </div>
        </li>
        <li>
        <div class="case_w">
            <img src="${base }/resources/menu/images/bdlogo.gif" />
            <div class="fire"></div>
            <a href="#" class="x"></a>
            <a href="#" class="y"></a>
        </div>          
      </li>
   </ul>
</li>

<li>
    <ul>
        <li>
          <div class="case_w">
              <img src="${base }/resources/menu/images/yahoo.com.png" />
             <div class="fire"></div> 
            <a href="#" class="x"></a>
            <a href="#" class="y"></a>
          </div>
        </li>
        <li>
        <div class="case_w">
            <img src="${base }/resources/menu/images/twitter.com.png" />
            <div class="fire"></div>
            <a href="#" class="x"></a>
            <a href="#" class="y"></a>
        </div>          
      </li>
   </ul>
</li>

</div>


<script type="text/javascript">
$(function(){
  $(".bxslider li ul li").hover(function(){ 
    $(".x",this).stop().css({'left':'35px'}).animate({'left':"40px"},400).show();
    $(".y",this).stop().css({'right':'35px'}).animate({'right':"40px"},400).show();
    $('.fire',this).fadeIn(500);
  },function(){
    $('.fire, .x, .y',this).fadeOut(500);
  }); 
});
</script>

<script type="text/javascript">
	$(document).ready(function(){
 	 	$('.bxslider').bxSlider({
 	 		  minSlides: 4,
			  maxSlides: 4,
			  nextSelector: '#slider-next',
  			  prevSelector: '#slider-prev',
  			  slideWidth: 240,
        	  slideMargin:0,
			  auto: false
 	 	});
	});
</script>

</body>
</html>