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
            });
        </script>

    </head>

    <body>
        <section id="main" class="column" style="width:100%;min-width:980px;">
            <article class="module width_full">
                <header>
                    <h3 class="tabs_involved">${message("menu.title.list")}</h3>
                        <ul class="tabs">
                            <li><a href="#tab1">1、${message("menu.title.list")}</a></li>
                            <li><a href="#tab2">2、${message("menu.title.add")}</a></li>
                        </ul>
                </header>
                <div class="tab_container">
                    <!-- start of tab1 -->
                    <div id="tab1" class="tab_content">
                        <div style="overflow-y: scroll;height:400px;">
                            <table class="tablesorter" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>${message("menu.title.name")}</th>
                                        <th>${message("menu.small.title.path")}</th>
                                        <th>${message("menu.title.status")}</th>
                                        <th>${message("common.create.date")}</th>
                                        <th>${message("common.opeator")}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <!--
                                    <#list menus as menu>
                                        <tr>
                                            <td class="tdone"><input type="checkbox"></td>
                                            <td>${menu.name }</td>
                                            <td>&nbsp;</td>
                                            <td>
                                                <#if sm.isSystem==true>
                                                    <input type="image" src="${base}/resources/common/images/icon/ico_system.png" title="系统菜单">
                                                <#else>
                                                    &nbsp;&nbsp;
                                                </#if>
                                            </td>
                                            <td>${menu.createDate }</td>
                                            <td>
                                                <a href="${base}/admin/menu/edit.html?menuId=${menu.id}">
                                                    <input type="image" src="${base}/resources/common/images/icon/ico_edit.png" title="编辑">
                                                </a>
                                            </td>
                                        </tr>
                                        <#list menu.subMenus as sm>
                                            <tr style="background-color:#fff;">
                                                <td class="tdone">
                                                    <input type="checkbox">
                                                </td>
                                                <td style="padding-left:40px;">
                                                    ${sm.name }
                                                </td>
                                                <td>
                                                     ${base}${sm.path}
                                                </td>
                                                <td>
                                                    <#if sm.isSystem==true>
                                                        <input type="image" src="${base}/resources/common/images/icon/ico_system.png" title="系统菜单">
                                                    <#else>
                                                        &nbsp;&nbsp;
                                                    </#if>
                                                </td>
                                                <td>
                                                    ${sm.createDate }
                                                </td>
                                                <td>
                                                    &nbsp;
                                                </td>
                                            </tr>
                                        </#list>
                                    </#list>
                                    -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- end of #tab1 -->
                </div>
            </article>
        </section>
    </body>
</html>