
/**
 * Tabs切换代码
 * 2014/11/18
 * @author jerry
 */

$(document).ready(function() {
	//Tabs...
	$(".tab_content_12").hide(); //Hide all content
	$("ul.tabs_12 li:first").addClass("active").show(); //Activate first tab
	$(".tab_content_12:first").show(); //Show first tab content

	//On Click Event
	$("ul.tabs_12 li").click(function() {

		$("ul.tabs_12 li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content_12").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});
});

