<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8"/>
        <title></title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${base}/resources/back/css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="${base}/resources/back/css/jquery-ui-1.10.4.min.css"/>


        <script type="text/javascript" src="${base}/resources/back/js/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="${base}/resources/back/js/hideshow.js"></script>
        <script type="text/javascript" src="${base}/resources/back/js/jquery.tablesorter.min.js"></script>
        <script type="text/javascript" src="${base}/resources/back/js/jquery.equalHeight.js"></script>
        <script type="text/javascript" src="${base}/resources/back/js/jquery-ui-1.10.4.min.js"></script>
        <script type="text/javascript" src="${base}/resources/back/js/jquery.ui.datepicker-zh-CN.js"></script>
        <script type="text/javascript" src="${base}/resources/common/layer/layer.js"></script>

        <!--TreeViw图标样式-->
        <link rel="stylesheet" type="text/css" href="${base}/resources/common/treeview/css/bootstrap.min.css" />
        <!--TreeView主要样式-->
        <link rel="stylesheet" type="text/css" href="${base}/resources/common/treeview/css/style.css" />

        <script type="text/javascript">
            $(document).ready(function(e) {
                $(".tablesorter").tablesorter();
                $(".tab_content").hide(); //Hide all content
                $("ul.tabs li:first").addClass("active").show(); //Activate first tab
                $(".tab_content:first").show(); //Show first tab content
                $("ul.tabs li").click(function() {
                    $("ul.tabs li").removeClass("active"); //Remove any "active" class
                    $(this).addClass("active"); //Add "active" class to selected tab
                    $(".tab_content").hide(); //Hide all tab content
                    var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
                    $(activeTab).fadeIn(); //Fade in the active ID content
                    resizeMain();
                    return false;
                });
                function resizeMain(){
                    var main = $(window.parent.document).find("#main");
                    var thisheight = $(document).height()+30;
                    main.height(thisheight);
                }

                //加载组织架构
                loadOrganization();
                function loadOrganization(){
                    $.ajax({
                        type:'POST',
                        url:'${base}/admin/company/get.html',
                        data:{},
                        dataType:'json',
                        success:function(data){
                            var html = "<ul>";
                            $.each(data.object,function(index,item){
                                html += "<li><span><i class='icon-minus-sign'></i>" + item.name + "</span>";
                                html += "<ul>";
                                $.each(item.positions,function(index,item){
                                    html += "<li>";
                                    html += "<span><i class='icon-leaf'></i>" + item.name + "</span>";
                                    html += "</li>";
                                });
                                html += "</ul>";
                            });
                            html += "</ul>";
                            $("#orgs_node").html(html);
                            treeView();
                        }
                    });
                }

                treeView();
                function treeView(){
                    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
                    $('.tree li.parent_li > span').on('click', function (e) {
                        var children = $(this).parent('li.parent_li').find(' > ul > li');
                        if (children.is(":visible")) {
                            children.hide('fast');
                            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
                        } else {
                            children.show('fast');
                            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
                        }
                        e.stopPropagation();
                    });
                }

            });

        </script>

    </head>

    <body style="overflow-y:hidden">
        <section id="main" class="column" style="width:100%;min-width:980px;">
            <article class="module width_full">
                <header>
                    <h3 class="tabs_involved" style="line-height: normal;">${message("hr.title.name")}</h3>
                    <!--
                        <ul class="tabs">
                            <li><a href="#tab1">1、${message("menu.title.list")}</a></li>
                            <li><a href="#tab2">2、${message("menu.title.add")}</a></li>
                    -->
                </header>
                <div class="tab_container">
                    <!-- start of tab1 -->
                    <div id="tab1" class="tab_content">
                        <div style="height:420px;">
                            <article class="module width_quarter" style="min-height: 390px;width:300px;float: left;">
                                <header>
                                    <h3 class="tabs_involved" style="line-height: normal;">${message("hr.title.org.structure")}</h3>
                                </header>
                                <div class="tree" style="overflow-y: auto;max-height: 345px;">
                                    <ul style="margin-left: 0px;">
                                        <li>
                                            <span><i class="icon-folder-open"></i>上海起航软件有限公司</span>
                                            <ul id="orgs_node"></ul>
                                        </li>
                                    </ul>
                                </div>
                            </article>

                            <article class="module width_3_quarter" style="min-height: 390px;width: 67%;float: right;margin-right: 10px;">
                                <header>
                                    <h3 id="vote_title" class="tabs_involved">${message("menu.small.title.add")}</h3>
                                    <ul class="tabs">
                                        <li><a href="">AAA</a> </li>
                                    </ul>
                                </header>
                                <div style="overflow-y: scroll;max-height: 330px;">
                                    <table class="tablesorter vote_list" id="tb_small_menu" cellspacing="0">
                                        <tbody id="vote_list">

                                        </tbody>
                                    </table>
                                    <div>
                            </article>

                        </div>
                    </div>
                </div>
            </article>
        </section>
    </body>
</html>