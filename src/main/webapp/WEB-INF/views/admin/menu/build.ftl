<!DOCTYPE HTML>
<head>
	<meta charset="utf-8"/>
	<title></title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${base}/resources/back/css/layout.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/resources/back/css/jquery-ui-1.10.4.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/resources/back/css/custom/menu-all.css"/>
    
	<script type="text/javascript" src="${base}/resources/back/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${base}/resources/back/js/hideshow.js"></script>
	<script type="text/javascript" src="${base}/resources/back/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript" src="${base}/resources/back/js/jquery.equalHeight.js"></script>
    <script type="text/javascript" src="${base}/resources/back/js/jquery-ui-1.10.4.min.js"></script>
    <script type="text/javascript" src="${base}/resources/back/js/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="${base}/resources/common/layer/layer.js"></script>
    <script type="text/javascript" src="${base}/resources/back/js/custom/lf.menu.js"></script>

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


<body style="overflow-y:hidden">	
	<section id="main" class="column" style="width:100%;min-width:980px;">
		<article class="module width_full">
			<header>
				<h3 class="tabs_involved">${msg("menu.title.list")}</h3>
				<ul class="tabs">
					<li><a href="#tab1">1、${msg("menu.title.list")}</a></li>
					<li><a href="#tab2">2、${msg("menu.title.add")}</a></li>
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
								<th>${msg("menu.title.name")}</th>
								<th>${msg("menu.small.title.path")}</th>
								<th>${msg("menu.title.status")}</th>
								<th>${msg("common.create.date")}</th>
								<th>${msg("common.opeator")}</th>
							</tr> 
						</thead> 
						<tbody> 
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
						</tbody> 
					</table>
					</div>
				</div>
				<!-- end of #tab1 -->
			
				<div id="tab2" class="tab_content">
					<!-- 菜单分类  -->
					<article class="module width_quarter">
				       <header>
				    		<h3 class="tabs_involved">${msg("menu.title.classification")}</h3>
				       </header>
				       <table class="tablesorter vote_list" cellspacing="0">
				           <tbody id="vote_list">
				            	<tr>
					            	<td>${msg("menu.title.name")}</td>
					            	<td>
					            		<input type="text" placeholder="${msg("menu.tip.input.name")}" name="menu_name" id="menu_name">
					            		<input type="hidden" value="" id="menu_id"/>
					            	</td>
				            	</tr>
				            	<tr>
				            		<td>${msg("menu.title.rank")}</td>
				            		<td><input type="text" placeholder="0" name="menu_rank" id="menu_rank"></td>
				            	</tr>
				            	<tr>
				            		<td>${msg("menu.title.status")}</td>
				            		<td>
				            			<input type="radio" name="menu_system" value="true" id="menu_system_yes">
				            			<label for="menu_system_yes">${msg("common.status.enable")}</label>
				            			&nbsp;&nbsp;
				            			<input type="radio" name="menu_system" value="false" id="menu_system_no" checked>
				            			<label for="menu_system_no">${msg("common.status.disable")}</label>
				            		</td>
				            	</tr>
				            	<tr>
				            		<td>&nbsp;</td>
				            		<td>&nbsp;</td>
				            	</tr>
				           </tbody>
				       </table>
				    </article>
				
					<!-- 子菜单 -->
					<article class="module width_3_quarter">
						<header>
							<h3 id="vote_title" class="tabs_involved">${msg("menu.small.title.add")}</h3>
						</header>
						<div style="overflow-y: scroll;max-height: 330px;">
							<table class="tablesorter vote_list" id="tb_small_menu" cellspacing="0">
					           <tbody id="vote_list">
					           		<tr>
					           			<td class="vote_list_td">
					           			
					           				<div style="min-width:450px;height:30px;line-height:30px;">
					           					<span class="td_span">${msg("menu.title.name")}</span>
					           					<input type="text" placeholder="" name="smallmenu_name" id="smallmenu_name"/>
					           					<input type="hidden" id="submenu_id"/>
					           				</div>
					           				
					           				<div style="min-width:450px;height:30px;line-height:30px;">
					           					<span class="td_span">${msg("menu.small.title.path")}</span>
					           					<input type="text" placeholder="" name="smallmenu_path" id="smallmenu_path"/>
					           					
					           					<span class="td_span" style="margin-left:25px;">${msg("menu.title.status")}</span>
				           						<input type="radio" name="smallmenu_system_1000" value="true" id="smallmenu_system_yes_1000">
				            					<label for="smallmenu_system_yes_1000">${msg("common.status.enable")}</label>
				            					&nbsp;&nbsp;
				            					<input type="radio" name="smallmenu_system_1000" value="false" id="smallmenu_system_no_1000" checked>
				            					<label for="smallmenu_system_no_1000">${msg("common.status.disable")}</label>
				            					
				            					<div class="vote_list_td_div">
							            			<input type="image" id="list_icon_add" title="${msg("common.title.add")}"  class="icon" src="${base}/resources/common/images/icon/ico_add.gif">
							            			<input type="image" id="list_icon_edit" title="${msg("common.title.edit")}" class="icon" src="${base}/resources/common/images/icon/ico_edit.png">
							            			<input type="image" id="list_icon_del" title="${msg("common.title.delete")}" class="icon" src="${base}/resources/common/images/icon/ico_delete.png">
							            			<input type="image" id="list_icon_save" title="${msg("common.title.save")}" class="icon" src="${base}/resources/common/images/icon/ico_save.png">
					           					<div>
					           				</div>
					           			</td>
					           		</tr>
					           </tbody>
					       </table>
				       <div>
				    </article>
			    
				</div>
		   		<!--活动设置选项卡结束-->
			</div><!-- end of .tab_container -->
		</article><!-- end of content manager article -->
	</section>
</body>

</html>