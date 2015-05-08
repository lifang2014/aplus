<!DOCTYPE HTML>
<head>
	<meta charset="utf-8"/>
	<title></title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${base}/resources/back/css/layout.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/resources/back/css/jquery-ui-1.10.4.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/resources/back/css/custom/hr-all.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/resources/common/tab/css/tab_styles.css"/>

	<script type="text/javascript" src="${base}/resources/back/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${base}/resources/back/js/hideshow.js"></script>
	<script type="text/javascript" src="${base}/resources/back/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript" src="${base}/resources/back/js/jquery.equalHeight.js"></script>
    <script type="text/javascript" src="${base}/resources/back/js/jquery-ui-1.10.4.min.js"></script>
    <script type="text/javascript" src="${base}/resources/back/js/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="${base}/resources/common/layer/layer.js"></script>
	<script type="text/javascript" src="${base}/resources/common/tab/js/tab_scripts.js"></script>
    <script type="text/javascript" src="${base}/resources/back/js/custom/hr-all.js"></script>

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
				<h3 class="tabs_involved">${msg("hr.title.name")}</h3>
			</header>
			<div class="tab_container">

				<div id="tab1" class="tab_content">

					<div class="container_11">
						<div class="con_nav">
							${msg("hr.title.org.structure")}
						</div>
					</div>

                    <div class="container_12" style="float: right;">
                        <ul class="tabs_12">
                            <li><a href="#tab1_12">${msg("hr.title.msg")}</a></li>
                            <li><a href="#tab2_12">${msg("hr.title.archives")}</a></li>
                            <li><a href="#tab3_12">${msg("hr.title.salary")}</a></li>
                        </ul>
                        <div class="tab_container_12">
                            <div id="tab1_12" class="tab_content_12">
								<!-- 个人信息 START -->
                                <table class="tablesorter vote_list" id="tb_hr" cellspacing="0">
                                    <tbody id="vote_list">
                                    <tr>
                                        <td class="td_face" rowspan="5">
                                            <img src="${base}/resources/common/images/default_face_110_135.gif" alt="username"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_title">${msg("common.field.name")}</td>
                                        <td class="td_content">
                                            ${employee.name}
                                        </td>
                                        <td class="td_title">${msg("common.field.before.name")}</td>
                                        <td class="td_content">${employee.personal.alias}</td>
                                    </tr>
                                    <tr>
                                        <td class="td_title">${msg("common.field.no")}</td>
                                        <td class="td_content">${employee.employeeNo}</td>
                                        <td class="td_title">${msg("common.field.sex")}</td>
                                        <td class="td_content">${employee.sexText}</td>
                                    </tr>
                                    <tr>
                                        <td class="td_title">${msg("common.field.birthday")}</td>
                                        <td class="td_content">${employee.birthday}</td>
                                        <td class="td_title">${msg("common.field.age")}</td>
                                        <td class="td_content">20</td>
                                    </tr>
                                    <tr>
                                        <td class="td_title">${msg("common.field.tel")}</td>
                                        <td class="td_content">${employee.tel}</td>
                                        <td class="td_title">${msg("common.field.email")}</td>
                                        <td class="td_content">${employee.email}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.qq")}</td>
                                        <td class="td_content">${employee.personal.qq}</td>
                                        <td class="td_title">${msg("common.field.msn")}</td>
                                        <td class="td_content">${employee.personal.msn}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.phyle")}</td>
                                        <td class="td_content">${employee.personal.phyle}</td>
                                        <td class="td_title">${msg("common.field.politacal")}</td>
                                        <td class="td_content">${employee.personal.politacalText}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.native")}</td>
                                        <td class="td_content">${employee.personal.nativePlace}</td>
                                        <td class="td_title">${msg("common.field.marriage")}</td>
                                        <td class="td_content">${employee.personal.marriageText}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.address")}</td>
                                        <td class="td_content" colspan="3">${employee.personal.homeAddress}</td>
                                    </tr>

                                    <tr>
                                        <td colspan="4" class="td_row_title">
                                            ${msg("common.field.emp.status")}
                                        </td>
                                        <td style="padding-right: 20px;">
                                            <img class="td_icon" src="${base}/resources/common/images/icon/icon_cancel.png" id="hr_cancel_icon">
                                            <img class="td_icon" src="${base}/resources/common/images/icon/ico_edit.png" id="hr_edit_icon">
                                            <img class="td_icon" src="${base}/resources/common/images/icon/icon_save.png" id="hr_save_icon">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.emp.type")}</td>
                                        <td class="td_content">
                                            <input id="hr_input_emp_type" class="input_edit_emp_status" value="合同签约" readonly="true"/>
                                        </td>
                                        <td class="td_title">${msg("common.field.emp.status")}</td>
                                        <td class="td_content">
                                            <select class="td_select" id="hr_select_emp_type">
                                                <#list employeeTypes as type>
                                                    <#if type.name == employee.employeeType.name>
                                                        <option value="${type.id}" selected="selected">${type.name}</option>
                                                    <#else>
                                                        <option value="${type.id}">${type.name}</option>
                                                    </#if>
                                                </#list>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.start.date")}</td>
                                        <td class="td_content">${employee.startDate}</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="5" class="td_row_title">${msg("common.field.position")}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.belong.position")}</td>
                                        <td class="td_content">${employee.position.name}</td>
                                        <td class="td_title">${msg("common.field.belong.job")}</td>
                                        <td class="td_content">${employee.job.name}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="td_title">${msg("common.field.start.position")}</td>
                                        <td class="td_content">2014-06-28</td>
                                        <td class="td_title">${msg("common.field.position.level")}</td>
                                        <td class="td_content">2014-06-28</td>
                                    </tr>
                                    </tbody>
                                </table>
								<!-- 个人信息 END -->
                            </div>
                            <div id="tab2_12" class="tab_content_12">
                                <!-- 档案信息 START -->
                                <table class="tablesorter vote_list" id="tb_hr" cellspacing="0">
                                    <tbody id="vote_list">
                                    <tr>
                                        <td colspan="10">${msg("hr.title.edu")}</td>
                                    </tr>
                                    <tr>
										<td>&nbsp;</td>
                                        <td>${msg("common.hr.field.final.edu")}</td>
                                        <td>&nbsp;</td>
                                        <td>${msg("common.hr.field.major")}</td>
                                        <td colspan="2">&nbsp;</td>
                                        <td>${msg("common.hr.field.school")}</td>
                                        <td colspan="3">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>${msg("common.hr.field.school.name")}</td>
                                        <td>${msg("common.hr.field.study.type")}</td>
                                        <td>${msg("common.hr.field.major.bol")}</td>
                                        <td>${msg("common.hr.field.edu")}</td>
                                        <td>${msg("common.hr.field.degree.type")}</td>
                                        <td>${msg("common.hr.field.degree")}</td>
                                        <td>${msg("common.start.time")}</td>
                                        <td>${msg("common.end.time")}</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>财经大学</td>
                                        <td>全日制</td>
                                        <td>计算机</td>
                                        <td>本科</td>
                                        <td>科技类</td>
                                        <td>学士学位</td>
                                        <td>2010-09</td>
                                        <td>2014-06</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>财经大学</td>
                                        <td>全日制</td>
                                        <td>计算机</td>
                                        <td>本科</td>
                                        <td>科技类</td>
                                        <td>学士学位</td>
                                        <td>2010-09</td>
                                        <td>2014-06</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>财经大学</td>
                                        <td>全日制</td>
                                        <td>计算机</td>
                                        <td>本科</td>
                                        <td>科技类</td>
                                        <td>学士学位</td>
                                        <td>2010-09</td>
                                        <td>2014-06</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="10">${msg("hr.title.work.exp")}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>${msg("common.hr.field.work.start")}</td>
                                        <td colspan="2">&nbsp;</td>
                                        <td>${msg("common.hr.field.work.count")}</td>
                                        <td colspan="2">&nbsp;</td>
                                        <td colspan="3">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td colspan="2">${msg("common.hr.field.work.company")}</td>
                                        <td colspan="1">${msg("common.hr.field.work.dept")}</td>
                                        <td>${msg("common.hr.field.work.position")}</td>
                                        <td>${msg("common.start.time")}</td>
                                        <td>${msg("common.end.time")}</td>
                                        <td colspan="3">${msg("common.desc")}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td colspan="2">江西南昌圈圈移动互联</td>
                                        <td colspan="1">移动互联部</td>
                                        <td>Java开发工程师</td>
                                        <td>2013-07-01</td>
                                        <td>2014-06-01</td>
                                        <td colspan="3">--</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td colspan="2">江西南昌圈圈移动互联</td>
                                        <td colspan="1">移动互联部</td>
                                        <td>Java开发工程师</td>
                                        <td>2013-07-01</td>
                                        <td>2014-06-01</td>
                                        <td colspan="3">--</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td colspan="2">江西南昌圈圈移动互联</td>
                                        <td colspan="1">移动互联部</td>
                                        <td>Java开发工程师</td>
                                        <td>2013-07-01</td>
                                        <td>2014-06-01</td>
                                        <td colspan="3">--</td>
                                    </tr>
                                    <tr>
                                        <td colspan="10">${msg("hr.title.train")}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>${msg("common.start.time")}</td>
                                        <td>${msg("common.end.time")}</td>
                                        <td colspan="2">${msg("common.hr.field.train.company")}</td>
                                        <td colspan="2">${msg("common.hr.field.train.content")}</td>
                                        <td colspan="3">${msg("common.hr.field.train.certification")}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>2013-10-10</td>
                                        <td>2014-10-10</td>
                                        <td colspan="2">XXXXXXXX</td>
                                        <td colspan="2">XXXXXXXXX</td>
                                        <td colspan="3">$XXXXXXXXX</td>
                                    </tr>

                                    <tr>
                                        <td colspan="10">${msg("hr.title.certificate")}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>${msg("common.hr.field.cer.name")}</td>
                                        <td>${msg("common.hr.field.cer.type")}</td>
                                        <td>${msg("common.hr.field.cer.no")}</td>
                                        <td>${msg("common.hr.field.cer.authority")}</td>
                                        <td>${msg("common.hr.field.cer.auth.date")}</td>
                                        <td>${msg("common.hr.field.cer.valid")}</td>
                                        <td>${msg("common.hr.field.cer.annex")}</td>
                                        <td colspan="2"></td>
                                    </tr>

                                    <tr>
                                        <td colspan="10">${msg("hr.title.family")}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>${msg("common.hr.field.family.name")}</td>
                                        <td>${msg("common.hr.field.family.relation")}</td>
                                        <td>${msg("common.hr.field.family.birthday")}</td>
                                        <td>${msg("common.hr.field.family.edu")}</td>
                                        <td>${msg("common.hr.field.family.polities")}</td>
                                        <td>${msg("common.hr.field.family.job")}</td>
                                        <td>${msg("common.hr.field.family.jobtitle")}</td>
                                        <td>${msg("common.hr.field.family.tel")}</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    </tbody>
                                </table>
                                <!-- 档案信息 END -->
                            </div>

                            <!-- 薪酬福利 START -->
                            <div id="tab3_12" class="tab_content_12">
                                <table class="tablesorter vote_list" id="tb_hr" cellspacing="0">
                                    <tbody id="vote_list">
                                        <tr>
                                            <td colspan="10">${msg("hr.title.salary.contract")}</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>${msg("common.hr.field.contract.entry.date")}</td>
                                            <td>2010/10/10</td>
                                            <td>${msg("common.hr.field.contract.positive.date")}</td>
                                            <td>2010/12/01</td>
                                            <td>${msg("common.hr.field.contract.try.num")}</td>
                                            <td>6个月</td>
                                            <td>${msg("common.hr.field.contract.emp.status")}</td>
                                            <td>正式</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>${msg("common.hr.field.contract.no")}</td>
                                            <td>AJ103234</td>
                                            <td>${msg("common.hr.field.contract.type")}</td>
                                            <td>劳务合同</td>
                                            <td>${msg("common.hr.field.contract.sign")}</td>
                                            <td>2012-10-10</td>
                                            <td>${msg("common.hr.field.contract.status")}</td>
                                            <td>生效</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>${msg("common.hr.field.contract.effective")}</td>
                                            <td>2012-12-12</td>
                                            <td>${msg("common.hr.field.contract.end")}</td>
                                            <td>2014-12-12</td>
                                            <td colspan="5"></td>
                                        </tr>

                                        <tr>
                                            <td colspan="10">${msg("hr.title.salary.info")}</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>${msg("common.hr.field.salary.base")}</td>
                                            <td>3000</td>
                                            <td>${msg("common.hr.field.salary.position")}</td>
                                            <td>2000</td>
                                            <td>${msg("common.hr.field.salary.ach")}</td>
                                            <td>1000</td>
                                            <td>${msg("common.hr.field.salary.edu.allow")}</td>
                                            <td>1000</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>${msg("common.hr.field.salary.positon.allow")}</td>
                                            <td>0</td>
                                            <td>${msg("common.hr.field.salary.job.allow")}</td>
                                            <td>0</td>
                                            <td>${msg("common.hr.field.salary.age")}</td>
                                            <td>0</td>
                                            <td>${msg("common.hr.field.salary.position.num")}</td>
                                            <td>0</td>
                                        </tr>

                                        <tr>
                                            <td colspan="10">${msg("hr.title.social.info")}</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>${msg("common.hr.field.social.no")}</td>
                                            <td>AJ892843</td>
                                            <td>${msg("common.hr.field.social.type")}</td>
                                            <td>XXXX</td>
                                            <td>${msg("common.hr.field.social.address")}</td>
                                            <td>上海</td>
                                            <td>${msg("common.hr.field.social.num")}</td>
                                            <td>0</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>${msg("common.hr.field.social.fund.num")}</td>
                                            <td>0</td>
                                            <td colspan="7">&nbsp;</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- 薪酬福利 END -->

						</div>
					</div>
				</div>
		   		<!--活动设置选项卡结束-->
			</div><!-- end of .tab_container -->
		</article><!-- end of content manager article -->
	</section>
</body>

</html>