<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,java.util.*,com.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="yzm" scope="page" class="com.util.CheckCode"/>
<jsp:useBean id="sdir" scope="page" class="com.bean.SystemBean"/>
<%
List list = sdir.getSiteInfo();
String str = list.get(0).toString();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站后台管理登录---<%=str %></title>
<style type="text/css">
<!--
.font1 {  font-family: "宋体"; font-size: 12px; line-height: 130%}
a {  font-family: "宋体"; font-size: 12px}
a:link {  font-family: "宋体"; font-size: 12px; color: #CFD1E8; text-decoration: underline}
a:hover {  font-family: "宋体"; font-size: 12px; color: #FFCC00; text-decoration: none}
a:visited {  font-family: "宋体"; font-size: 12px; color: #CFD1E8; text-decoration: underline}
.input {  font-family: "宋体"; font-size: 12px; color: #FFFFFF; border: #4047A4; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; background-color: #000077}
-->
</style>
</head>
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
 	String code=yzm.getCheckCode();
 	String dir=sdir.getDir();
%>
<body bgcolor="green" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td   align="center"> 
       
       
      <table width="380" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td bgcolor="#4047A4" width="1"></td>
          <td> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="36"><br><br><h3><%=str %></h3></td>
              </tr>
            </table><form action="<%=basePath %>Admin.do" name=form1 method=post onSubmit="return checkform(form1)" autocomplete="off">
            <table width="260" border="0" cellspacing="0" cellpadding="0" align="center">
		      
              <tr> 
                <td colspan="3" class="news" height="5"><input type=hidden name=method value="one" /></td>
              </tr>
			 
              <tr> 
                <td width="5" class="nwes" height="36"></td>
                <td width="106" class="font1" height="36"><font color="#CFD1E8">用户名</font></td>
                <td><input type="text" name="username"  size="15" /></td>
              </tr>
              <tr> 
                <td class="nwes" height="36">&nbsp; </td>
                <td class="font1" height="36"><font color="#CFD1E8">口　令</font></td>
                <td><input type="password" name="password" size="15" ></td>
              </tr>
              
              <tr> 
                <td height="5" colspan="3"></td>
              </tr>
              <tr> 
                <td>&nbsp; </td>
                <td align="center">&nbsp; </td>
                <td> 
                  <input type="image" border="0" name="imageField" src="<%=basePath %><%=dir %>/images/bt_login.gif" width="70" height="16">
                </td>
              </tr>
			  
            </table></form>	
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="36">&nbsp;</td>
              </tr>
            </table>
          </td>
          <td bgcolor="#4047A4" width="1"></td>
        </tr>
      </table>
      <table width="280" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td bgcolor="#4047A4" height="1"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
<script language=Javascript>
	
	form1.username.focus()	

	function checkform(form)
	{
		var flag=true;
		if(form("username").value==""){alert("请输入用户名!");form("username").focus();return false};
		if(form("password").value==""){alert("请输入口令!");form("password").focus();return false};
		 
		return flag;
	}
</script>
