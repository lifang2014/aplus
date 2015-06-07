window.onload = function() {
    var oDivRight = document.getElementById("tab_select_right");
    var oLiRight = oDivRight.getElementsByTagName("div")[0].getElementsByTagName("li");
    var aConRight = oDivRight.getElementsByTagName("div")[1].getElementsByTagName("div");
    var timerRight = null;
    for (var i = 0; i < oLiRight.length; i++) {
        oLiRight[i].index = i;
        oLiRight[i].onclick = function() {
            show(this.index);
        }
    }
    function show(a) {
        var indexRight = a;
        var alpha = 0;
        for (var j = 0; j < oLiRight.length; j++) {
            oLiRight[j].className = "";
            aConRight[j].className = "";
            aConRight[j].style.opacity = 0;
            aConRight[j].style.filter = "alpha(opacity=0)";
        }
        oLiRight[indexRight].className = "cur";
        clearInterval(timerRight);
        timerRight = setInterval(function() {
                alpha += 2;
                alpha > 100 && (alpha = 100);
                aConRight[indexRight].style.opacity = alpha / 100;
                aConRight[indexRight].style.filter = "alpha(opacity=" + alpha + ")";
                alpha == 100 && clearInterval(timerRight);
            },
            5)
    }
}