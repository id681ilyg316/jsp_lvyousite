package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.HzpBean;
import com.bean.NewsBean;
import com.util.Constant;

public class HzpAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HzpAction() {
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
		HttpSession session=request.getSession();
		HzpBean tb=new HzpBean();
		String method=request.getParameter("method").trim();
		///////////////////////////////////////////////////////////////////// 
		if(method.equals("deltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
			}
		}
		else if(method.equals("hotdeltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/hot.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/hot.jsp").forward(request, response);
			}
		}
		else if(method.equals("tejiadeltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/tejia.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/tejia.jsp").forward(request, response);
			}
		}
		else if(method.equals("tuijiandeltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/tuijian.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/tuijian.jsp").forward(request, response);
			}
		}
		 
		else if(method.equals("addType")){
			String type=request.getParameter("type").trim();
			int flag=tb.addType(type);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
			else if(flag==Constant.DEFAULT_ERROR){
				request.setAttribute("message", "该分类已存在！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
		}
		else if(method.equals("delType")){
			String id=request.getParameter("id").trim();
			int flag=tb.delType(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
			
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
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
