window.onload = function() {
    var oDivLeft = document.getElementById("tab_select_left");
    var oLiLeft = oDivLeft.getElementsByTagName("div")[0].getElementsByTagName("li");
    var aConLeft = oDivLeft.getElementsByTagName("div")[1].getElementsByTagName("div");
    var timer = null;
    for (var i = 0; i < oLiLeft.length; i++) {
        oLiLeft[i].index = i;
        oLiLeft[i].onclick = function() {
            show(this.index);
        }
    }
    function show(a) {
        index = a;
        var alpha = 0;
        for (var j = 0; j < oLiLeft.length; j++) {
            oLiLeft[j].className = "";
            aConLeft[j].className = "";
            aConLeft[j].style.opacity = 0;
            aConLeft[j].style.filter = "alpha(opacity=0)";
        }
        oLiLeft[index].className = "cur";
        clearInterval(timer);
        timer = setInterval(function() {
                alpha += 2;
                alpha > 100 && (alpha = 100);
                aConLeft[index].style.opacity = alpha / 100;
                aConLeft[index].style.filter = "alpha(opacity=" + alpha + ")";
                alpha == 100 && clearInterval(timer);
            },
            5)
    }
}

