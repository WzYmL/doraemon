<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
<style type="text/css"> 
<!-- 
body  {
	background: #666666;
	margin: 0; /* 最好将 baody 元素的边距和填充设置为 0 以覆盖不同的浏览器默认值 */
	padding: 0;
	text-align: center; /* 在 IE 5* 浏览器中，这会将容器居中。文本随后将在 #container 选择器中设置为默认左对齐 */
	color: #000000;
}
.twoColLiqLtHdr #container { 
	width: 80%;  /* 这将创建一个占据 80% 浏览器宽度的容器 */
	background: #FFFFFF;
	margin: 0 auto; /* 自动边距（与宽度一起）会将页面居中 */
	border: 1px solid #000000;
	text-align: left; /* 这将覆盖 body 元素上的“text-align: center”。 */
} 
.twoColLiqLtHdr #header { 
	background: #DDDDDD; 
	padding: 0 10px;  /* 此填充会将出现在它后面的 div 中的元素左对齐。如果 #header 中使用的是图像（而不是文本），您最好删除填充。 */
} 
.twoColLiqLtHdr #header h1 {
	margin: 0; /* 将 #header div 中最后一个元素的边距设置为零将避免边距重叠（即 div 之间出现的无法解释的空白）。如果 div 周围有边框，则不必将边距设置为零，因为边框也会避免边距重叠 */
	padding: 10px 0; /* 使用填充而不使用边距将可以使元素远离 div 的边缘 */
}

/* sidebar1 提示：
1. 由于我们使用的是百分比，因此最好不要在侧栏中使用填充。它将会新增至宽度，而让符合标准的浏览器建立未知的实际宽度。 
2. 如果为 div 中的元素设置左边距和右边距，则会在 div 边缘和这些元素之间产生空白，如“.twoColLiqLtHdr #sidebar1 p”规则中所示。
3. 由于 Explorer 会在父元素显示之后计算宽度，因此基于百分比的栏有时会出现无法解释的错误。如果您需要更可预见的结果，可选择改为以像素为单位设置栏的大小。
*/
.twoColLiqLtHdr #sidebar1 {
	float: left; 
	width: 24%; /* 由于此元素是浮动的，因此必须指定宽度 */
	background: #EBEBEB; /* 将显示背景色，其宽度等于栏中内容的长度，*/
	padding: 15px 0; /* 顶部和底部的填充将在该 div 中产生视觉空间 */
}
.twoColLiqLtHdr #sidebar1 h3, .twoColLiqLtHdr #sidebar1 p {
	margin-left: 10px; /* 对于将要放在侧栏中的每个元素，都应当设置左边距和右边距 */
	margin-right: 10px;
}

/* mainContent 提示：
1. mainContent 和 sidebar1 之间的空白是由 mainContent div 的左边距创建的。无论 sidebar1 div 中包含多少内容，都将保留栏空白。如果您希望在 #sidebar1 中的内容结束时，用 #mainContent div 的文本填充 #sidebar1 空白，则可以删除此左边距。
2. 为了避免在所支持的最小分辨率 800 x 600 下出现“浮动下降”，mainContent div 中的元素（包括图像）不应大于 430 像素。
3. 在下面的 Internet Explorer 条件注释中，zoom 属性用来赋予 mainContent“hasLayout”。这会避免几个特定于 IE 的错误。
*/
.twoColLiqLtHdr #mainContent { 
	margin: 0 20px 0 26%; /* 右边距可以用百分比或像素来指定，它会在页面的右下方产生空白。 */
} 
.twoColLiqLtHdr #footer { 
	padding: 0 10px; /* 此填充会将它上面 div 中的所有元素左对齐。 */
	background:#DDDDDD;
} 
.twoColLiqLtHdr #footer p {
	margin: 0; /* 将脚注中第一个元素的边距设置为零将避免出现可能的边距重叠（即 div 之间出现的空白）*/
	padding: 10px 0; /* 就像边距会产生空白一样，此元素上的填充也将产生空白，但不会出现边距重叠问题 */
}

/* 要重用的各种类 */
.fltrt { /* 此类可用来使页面中的元素向右浮动。浮动元素必须位于页面上要与之相邻的元素之前。 */
	float: right;
	margin-left: 8px;
}
.fltlft { /* 此类可用来使页面上的元素向左浮动 */
	float: left;
	margin-right: 8px;
}
.clearfloat { /* 此类应当放在 div 或 break 元素上，而且该元素应当是完全包含浮动的容器关闭之前的最后一个元素 */
	clear:both;
    height:0;
    font-size: 1px;
    line-height: 0px;
}
.headerLeft {
}
.headerRight {
	text-align: right;
	font-size: 12px;
}
--> 
</style><!--[if IE]>
<style type="text/css"> 
/* 请将所有版本的 IE 的 css 修复放在这个条件注释中 */
.twoColLiqLtHdr #sidebar1 { padding-top: 30px; }
.twoColLiqLtHdr #mainContent { zoom: 1; padding-top: 15px; }
/* 上面的专用 zoom 属性为 IE 提供避免错误所需的 hasLayout */
</style>
<![endif]--></head>

<body class="twoColLiqLtHdr">

<div id="container"> 
  <div id="header">
  <div id="headerLeft">
  <h1>Collective Buy</h1>
  </div>
  <div id="headerRight" class="headerRight">
 <a href="member/login.gsp">Login</a>
</div>
  
  <!-- end #header --></div>
  <div id="sidebar1">
<h3>Categories</h3>
<table>
<tr>
	<td>
		 <ul> 
               <input type="checkbox">Sport</inut>
                    
         </ul>
	</td>
</tr>
<tr>
	<td>
		<input type="checkbox">Sport</inut>
	</td>
</tr>
<tr>
	<td>
		<input type="checkbox">Sport</inut>
	</td>
</tr>
<tr>
	<td>
		<input type="checkbox">Sport</inut>
	</td>
</tr>
<tr>
	<td>
		<input type="checkbox">Sport</inut>
	</td>
</tr><tr>
	<td>
		<input type="checkbox">Sport</inut>
	</td>
</tr><tr>
	<td>
		<input type="checkbox">Sport</inut>
	</td>
</tr>
</table>
  <!-- end #sidebar1 --></div>
  <div id="mainContent">
<h1> Deals </h1>
            <div id="controllerList" class="dialog">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                    </g:each>
                </ul>
            </div>
	<!-- end #mainContent --></div>
	<!-- 这个用于清除浮动的元素应当紧跟 #mainContent div 之后，以便强制 #container div 包含所有的子浮动 --><br class="clearfloat" />
  <div id="footer">
<p>Welcome </p>
  <!-- end #footer --></div>
<!-- end #container --></div>
</body>
</html>
