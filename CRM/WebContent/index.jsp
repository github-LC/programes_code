<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>CRM客户关系管理系统</title>
<style>
	.top-crm{
		width: 100%
	}
</style>
</head>
<body>
 <header class="Hui-header cl"> <a class="Hui-logo l" title="H-ui.admin v2.3" href="/">CRM客户关系管理系统</a> 
	<nav class="mainnav cl" id="Hui-nav">
		<ul>
			<li class="dropDown dropDown_click"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
					<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
					<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
					<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	<ul class="Hui-userbar">
		<li><s:property value="#session.loginUser.user_name"/>&nbsp;&nbsp;先生，欢迎登陆使用！</li>
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">账号管理 <i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="#">个人信息</a></li>
				<li><a href="#">切换账户</a></li>
				<li><a href="${pageContext.request.contextPath }/user_logout.action">退出</a></li>
			</ul>
		</li>
		<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
		<li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
				<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
				<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
				<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
				<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
				<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
			</ul>
		</li>
	</ul>
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> </header>
<iframe class="top-crm" scrolling="yes" frameborder="0" src="top.jsp"></iframe>
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
	<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 客户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				<!--./resource/customer/resource/list.html -->
					<li><a href="${pageContext.request.contextPath}/customer_pageFind.action">客户资源管理</a></li>
					<li><a href="${pageContext.request.contextPath}/customer_addUI.action" >添加客户</a></li>
					<li><a href="${pageContext.request.contextPath}/saleVisit_findAll.action?currentPage=1">客户拜访管理</a></li>
					<li><a href="${pageContext.request.contextPath}/customerSatisfaction_pageFind.action?currentPage=1">客户满意度管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 销售管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="./resource/market/plan/list.html" href="javascript:void(0)">销售计划</a></li>
					<li><a _href="./resource/market/forecast/list.html" href="javascript:void(0)">销售预测</a></li>
					<li><a _href="./resource/market/performance/list.html" href="javascript:void(0)">销售绩效</a></li>
					<li><a _href="./resource/market/opp/list.html" href="javascript:void(0)">机会管理</a></li>
				</ul>
			</dd>
		</dl>
		<!--<dl id="menu-page">
			<dt><i class="Hui-iconfont">&#xe626;</i> 页面管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="page-home.html" href="javascript:void(0)">首页管理</a></li>
					<li><a _href="page-flinks.html" href="javascript:void(0)">友情链接</a></li>
				</ul>
			</dd>
		</dl>-->
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 服务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="./resource/services/create/list.html" href="javascript:;">服务创建</a></li>
					<li><a _href="./resource/services/assign/list.html" href="javascript:void(0)">服务分配</a></li>
					<li><a _href="./resource/services/handle/list.html" href="javascript:void(0)">服务处理</a></li>
					<li><a _href="./resource/services/feedback/list.html" href="javascript:void(0)">服务反馈</a></li>
					<li><a _href="./resource/services/file/list.html" href="javascript:void(0)">服务归档</a></li>
					<li><a _href="./resource/services/question/list.html" href="javascript:void(0)">常见问题管理</a></li>
				</ul>
			</dd>
		</dl>
		<!--<dl id="menu-order">
			<dt><i class="Hui-iconfont">&#xe63a;</i> 财务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="order-list.html" href="javascript:void(0)">订单列表</a></li>
					<li><a _href="recharge-list.html" href="javascript:void(0)">充值管理</a></li>
					<li><a _href="invoice-list.html" href="javascript:void(0)">发票管理</a></li>
				</ul>
			</dd>
		</dl>-->
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 订单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="./resource/order/placeanorder/list.html" href="javascript:;">代下定单</a></li>
					<li><a _href="./resource/order/query/list.html" href="javascript:;">订单查询</a></li>
					<!--<li><a _href="./resource/order/query/shoppingList.html" href="javascript:;">商品管理</a></li>-->
				</ul>
			</dd>
		</dl>
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 合同管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="./resource/contract/create/list.html" href="javascript:void(0)">合同创建</a></li>
					<li><a _href="./resource/contract/review/list.html" href="javascript:void(0)">合同审核</a></li>
					<li><a _href="./resource/contract/management/list.html" href="javascript:void(0)">合同管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 数据统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="charts-1.html" href="javascript:void(0)">折线图</a></li>
					<li><a _href="charts-2.html" href="javascript:void(0)">时间轴折线图</a></li>
					<li><a _href="charts-3.html" href="javascript:void(0)">区域图</a></li>
					<li><a _href="charts-4.html" href="javascript:void(0)">柱状图</a></li>
					<li><a _href="charts-5.html" href="javascript:void(0)">饼状图</a></li>
					<li><a _href="charts-6.html" href="javascript:void(0)">3D柱状图</a></li>
					<li><a _href="charts-7.html" href="javascript:void(0)">3D饼状图</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 数据管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="system-category.html" href="javascript:void(0)">栏目管理</a></li>
					<li><a _href="getsystem-data.html" href="javascript:void(0)">数据字典</a></li>
					<li><a _href="system-shielding.html" href="javascript:void(0)">屏蔽词</a></li>
					<li><a _href="system-log.html" href="javascript:void(0)">系统日志</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 系统设置<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="./resource/sys/role/list.html" href="javascript:void(0)">角色管理</a></li>
					<li><a _href="./resource/sys/org/list.html" href="javascript:void(0)">组织机构</a></li>
					<li><a _href="./resource/sys/employee/list.html" href="javascript:void(0)">员工管理</a></li>
					<li><a _href="./resource/sys/menu/list.html" href="javascript:void(0)">菜单管理</a></li>
					<li><a _href="./resource/sys/announcement/list.html" href="javascript:void(0)">公告管理</a></li>
					<li><a _href="./resource/sys/myinfo/view.html" href="javascript:void(0)">个人信息</a></li>
					
				</ul>
			</dd>
		</dl>
	</div>
</aside>
<!-- <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div> -->
<section class="Hui-article-box">
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="main.jsp"></iframe>
		</div>
	</div>
</section>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
<script type="text/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s)})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>