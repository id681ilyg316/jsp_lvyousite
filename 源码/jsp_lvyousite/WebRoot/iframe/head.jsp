<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="sys" scope="page" class="com.bean.SystemBean" />
<jsp:useBean id="fb" scope="page" class="com.bean.FriendLinkBean" />
<jsp:useBean id="abc" scope="page" class="com.bean.AfficheBean"/>
<jsp:useBean id="news" scope="page" class="com.bean.NewsBean"/>
<jsp:useBean id="tb" scope="page" class="com.bean.HzpBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List sysList=sys.getSiteInfo();
List flinkList=fb.getAllShowFriendLink();
List affList=abc.getAllAffiche();
List AllnewsList=news.getAllNews();
%>

<HTML lang="zh">
<HEAD>
<TITLE><%=sysList.get(0).toString() %></TITLE>
<META http-equiv=Content-Language content=zh-cn>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META name="keywords" content="<%=sysList.get(2).toString() %>" />
<META name="description" content="<%=sysList.get(3).toString() %>" />

<META content="MSHTML 6.00.2900.3243" name=GENERATOR>
<LINK href="<%=basePath %>images/css.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %>images/default.css" type=text/css rel=stylesheet>
</HEAD>
<SCRIPT language=JavaScript src="<%=basePath %>images/Common.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=basePath %>images/index.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=basePath %>images/calendar.js"></SCRIPT>

<SCRIPT language=JavaScript>
<!--//屏蔽出错代码
function killErr(){
	return true;
}
window.onerror=killErr;
//-->
</SCRIPT>
<SCRIPT language=JavaScript>
<!--//处理大分类一行两个小分类
function autoTable(div){
	fs=document.getElementById(div).getElementsByTagName("TABLE");
	for(var i=0;i<fs.length;i++){
		fs[i].style.width='49.5%';
		if(i%2==1){
			if (document.all) {
				fs[i].style.styleFloat="right";
			}else{
				fs[i].style.cssFloat="right;";
			}
		}else{
			if (document.all) {
				fs[i].style.styleFloat="left";
			}else{
				fs[i].style.cssFloat="left;";
			}
		}
	}
}
//-->
</SCRIPT>
<SCRIPT language=JavaScript src="images/inc.js"></SCRIPT>
<SCRIPT language=JavaScript src="images/default.js"></SCRIPT>
<SCRIPT language=JavaScript src="images/swfobject.js"></SCRIPT>
 
<BODY text=#000000  background=images/xh.gif  leftMargin=0 topMargin=0>
<SCRIPT language=JavaScript>
<!--//目的是为了做风格方便
document.write('<div class="wrap">');
//-->
</SCRIPT>
<TABLE id=toplogin cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TBODY>
  <TR>
    <TD vAlign=center align=left>
      <DIV class=jstime style="FLOAT: left; WIDTH: 45%">
      <%String member2=(String)session.getAttribute("member");if(member2==null){ %>
      【<a href="login.jsp">会员登录</a>】【<a href="reg.jsp">免费注册</a>】【<a href="lost.jsp">忘记密码</a>】
      <%}else{   %>
      欢迎您，尊敬的会员：<%=member2 %> <A href="member/index.jsp" target="">【会员中心】</A>
      <%} %>
      </DIV>
      <DIV class=jstime style="FLOAT: right; WIDTH: 45%; TEXT-ALIGN: right">
	  <!--****************时间日历开始****************-->
      <SCRIPT>setInterval("clock.innerHTML=new Date().toLocaleString()+'&nbsp;&nbsp;星期'+'日一二三四五六'.charAt(new Date().getDay());",1000)</SCRIPT>
      <SPAN id=clock></SPAN>
	  <!--****************时间日历结束****************-->&nbsp;&nbsp; 
      <A href="javascript:javascript:window.external.AddFavorite('<%=basePath %>','<%=sysList.get(0).toString() %>');"> </A> 
      <A onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('<%=basePath %>');" href="http://localhost/#"> </A> 
	  <A href="mailto:<%=sysList.get(4).toString() %>"> </A> 
	  </DIV>
	</TD>
   </TR>
   </TBODY>
</TABLE>
<TABLE id=header cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
<TBODY>
  <TR>
    <TD><DIV class=ad id=banner><img src="images/abc.gif" width="950" height="200" border=0></DIV></TD>
  </TR>
 </TBODY>
</TABLE>
<TABLE id=guide cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
<TBODY>
  <TR>
    <TD align=middle>
	<!--****************主菜单开始****************-->
	
	<A href="index.jsp" target="">首    页</A> |
	
	<A href="news.jsp" target="">景点介绍</A> |
	<A href="jd.jsp" target="">酒店信息</A> |
	<A href="lxs.jsp" target="">旅行社</A> |
	<A href="nhzp.jsp" target="">宾馆预定</A> |	
	<A href="search.jsp" target="">信息查询</A> |
	<A href="guestbook.jsp" target="">留言板</A> |
	<A href="member/index.jsp" target="">会员中心</A> 
	<A href="admin/login.jsp" target="">管理登陆</A> 
	<!--****************主菜单结束****************-->
	</TD>
  </TR>
</TBODY>
</TABLE>



