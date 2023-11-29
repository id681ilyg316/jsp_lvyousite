package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.NewsBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;

public class NewsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewsServlet() {
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

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		String sysdir = new SystemBean().getDir();
		HttpSession session = request.getSession();
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		try{
			String username2 = (String)session.getAttribute("user");
			 
				String method = request.getParameter("method").trim();
				if(method.equals("addNews")){
					String title = request.getParameter("title");
					String fenlei = request.getParameter("fenlei");
					String infoContent = request.getParameter("infoContent");
					int flag = new NewsBean().exeUp("insert into news(title,fenlei,content,addtime,adder,visit) " +
							"values('"+title+"','"+fenlei+"','"+infoContent+"','"+date+"','"+username2+"','0')");
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("upNews")){
					String id = request.getParameter("id");
					String title = request.getParameter("title");
					String fenlei = request.getParameter("fenlei");
					String infoContent = request.getParameter("infoContent");
					int flag = new NewsBean().exeUp("update news set title='"+title+"',fenlei='"+fenlei+"',content='"+infoContent+"' where id='"+id+"'");
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("delNews")){
					String id = request.getParameter("id");
					int flag = new NewsBean().exeUp("delete from news  where id='"+id+"'");
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("addPrep")){
					String member=(String)session.getAttribute("member");
					String sid = request.getParameter("sid");
					String title = request.getParameter("title");
					String rs = request.getParameter("rs");
					String sj = request.getParameter("sj");
					String ts = request.getParameter("ts");
					String lxr = request.getParameter("lxr");
					String lxfs = request.getParameter("lxfs");
					int flag = new NewsBean().exeUp("insert into prep(title,rs,sj,ts,lxr,lxfs,addtime,member) " +
							"values('"+title+"','"+rs+"','"+sj+"','"+ts+"','"+lxr+"','"+lxfs+"','"+date+"','"+member+"')");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "预订成功，稍后本站客服人员会与您取得联系。您可以在会员中心查看您的预订记录！");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}
				else if(method.equals("delPrep")){
					String id = request.getParameter("id");
					int flag = new NewsBean().exeUp("delete from  prep where id='"+id+"'");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
					}
				}
		}catch(Exception e){
			e.printStackTrace();
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
