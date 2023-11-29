package com.action;

/**
 * 
 * 留言本
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.GuestBookBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;

public class GuestBookAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GuestBookAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		response.setContentType(Constant.CONTENTTYPE);
		//nikename, pic, email, qq, weburl, blogurl, expressions, content
		String sysdir = new SystemBean().getDir();
		HttpSession session = request.getSession();
		String method = request.getParameter("method").trim();
			GuestBookBean guestBookBean = new GuestBookBean();
			if(method.equals("add")){//发表留言
				String member=(String)session.getAttribute("member");
				String nikename = Filter.escapeHTMLTags(request.getParameter("nikename").trim());
				String face ="images/nobody.gif";
				String email = Filter.escapeHTMLTags(request.getParameter("email").trim());
				String qq = Filter.escapeHTMLTags(request.getParameter("qq").trim());
				String weburl = Filter.escapeHTMLTags(request.getParameter("weburl").trim());
				String blogurl = Filter.escapeHTMLTags(request.getParameter("blogurl").trim());
				String expressions = "images/face/"+Filter.escapeHTMLTags(request.getParameter("face").trim())+".gif";
				String content = Filter.escapeHTMLTags(request.getParameter("content").trim());
				String ip = request.getRemoteAddr();
				 
				int guestbook=1;
				int flag = guestBookBean.addGuestBook(nikename, face, email, qq, weburl, blogurl, expressions, content, ip,guestbook);
				if(flag == 1){
					
						request.setAttribute("message", "谢谢您的留言，请等候管理员回复！");
						request.getRequestDispatcher("guestbook.jsp").forward(request, response);
					
				}
				else{
					request.setAttribute("message", "系统维护中，请稍后再试！");
					request.getRequestDispatcher("guestbook.jsp").forward(request, response);
				}
			}
			else if(method.equals("delguestbook")){//删除留言
				try{
					String username2 = (String)session.getAttribute("user");
					if(username2 == null){
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
					else{
						String check[] = request.getParameterValues("checkit");
						if(check == null){
							request.setAttribute("message", "请选择要删除的记录！");
							request.getRequestDispatcher(sysdir+"/guestbook/index.jsp").forward(request, response);
						}
						else{
							int id[]= new int[check.length];
							for(int i = 0;i<check.length;i++){
								int s = Integer.parseInt(check[i]);				
								id[i] = s;
							}
							int flag = guestBookBean.delGuestBook(id);
							if(flag == Constant.SUCCESS){
								request.getRequestDispatcher(sysdir+"/guestbook/index.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/guestbook/index.jsp").forward(request, response);
							}
						}
					}
				}catch(Exception e){
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
			
			else if(method.equals("replay")){//回复留言
				try{
					String username2 = (String)session.getAttribute("user");
					if(username2 == null){
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
					else{
						String messageid = Filter.escapeHTMLTags(request.getParameter("id").trim());
						String replay = Filter.escapeHTMLTags(request.getParameter("replay").trim());
						int flag = guestBookBean.reGuestBook(Integer.parseInt(messageid), replay, username2);
						if(flag == Constant.SUCCESS){
							request.setAttribute("message", "回复成功！");
							request.getRequestDispatcher(sysdir+"/guestbook/index.jsp").forward(request, response);
						}
						else{
							request.setAttribute("message", "系统维护中，请稍后再试！");
							request.getRequestDispatcher(sysdir+"/guestbook/index.jsp").forward(request, response);
						}
					}
				}catch(Exception e){
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
			else if(method.equals("upreplay")){
				try{
					String username2 = (String)session.getAttribute("user");
					if(username2 == null){
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
					else{
						String messageid = Filter.escapeHTMLTags(request.getParameter("id").trim());
						String replay = Filter.escapeHTMLTags(request.getParameter("replay").trim());
						int flag = guestBookBean.upReplay(Integer.parseInt(messageid), replay);
						if(flag == Constant.SUCCESS){
							request.setAttribute("message", "修改成功！");
							request.getRequestDispatcher(sysdir+"/guestbook/index.jsp").forward(request, response);
						}
						else{
							request.setAttribute("message", "系统维护中，请稍后再试！");
							request.getRequestDispatcher(sysdir+"/guestbook/index.jsp").forward(request, response);
						}
					}
				}catch(Exception e){
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
