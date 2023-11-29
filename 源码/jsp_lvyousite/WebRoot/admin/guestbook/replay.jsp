<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="sys" scope="page" class="com.bean.SystemBean"></jsp:useBean>
<jsp:useBean id="guestbean" scope="page" class="com.bean.GuestBookBean"></jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sys.getDir();
%>
<HTML><HEAD>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="<%=basePath %><%=dir %>/images/Common.js"></SCRIPT>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<script type="text/javascript">
function check()
{
	if(document.form1.replay.value.replace(/\s+$|^\s+/g,"").length<=0)
	{
		alert("请输入回复内容！");
		document.form1.replay.focus();
		return false;
	}
	if(document.form1.replay.value.replace(/\s+$|^\s+/g,"").length>200)
	{
		alert("回复内容不能超过200个字！");
		document.form1.replay.focus();
		return false;
	}
}
</script>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(basePath+"/error.jsp");
	}
	else{
		String id=request.getParameter("id").trim();
		String content=guestbean.getGuestBook(Integer.parseInt(id));
		String replay=guestbean.getReplay(Integer.parseInt(id));
		String method="upreplay";
		if(replay==null){
			replay="";
			method="replay";
		}
		
%>
<BODY oncontextmenu="return false;" onselectstart="return false;" leftMargin=0 
background=<%=basePath %><%=dir %>/images/MainBg.gif topMargin=0 scroll=no 
marginheight="0" marginwidth="0">
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
	<form action="<%=basePath %>GuestBook.do" name="form1" onSubmit="return check()">
       <TABLE width="100%" border=0 align="center" cellPadding=3 cellSpacing=1 class=tablewidth>
		  <TBODY>
		  <TR align="center" class=head>
			<TD colspan="2" height=23>回复留言</TD>
		  </TR>
		 <TR align="center" bgColor=#ffffff><input type="hidden" name="id" value="<%=id %>" ><input type="hidden" name="method" value="<%=method %>" >
			<TD width="30%" align="right" >留言内容：</TD><TD width="70%" align="left" id=map><textarea name="reasons" cols="50" rows="5" readonly><%=content %></textarea></TD>
		  </TR>
		  <TR align="center" bgColor=#ffffff>
			<TD width="30%" align="right" >回&nbsp;&nbsp;&nbsp;&nbsp;复：</TD><TD width="70%" align="left" id=map><textarea name="replay" cols="50" rows="5" ><%=replay %></textarea></TD>
		  </TR>
		  <TR align="center" bgColor=#ffffff>
			<TD colspan="2" align="center" ><input type="submit" value="提交">&nbsp;&nbsp;<input type="reset" value="重填"></TD>
		  </TR>
		  </TBODY>
	   </TABLE>
	  </form>
    </TD>
  </TR>
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
