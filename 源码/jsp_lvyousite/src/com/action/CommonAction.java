package com.action;

/**
 * �ϴ�����servlet���޷�ʵ�ֵĹ����ɴ˲��� 
 * 
 * ͨ�ò�������
 * 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.bean.NewsBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;

public class CommonAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CommonAction() {
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
		try{
			String username2 = (String)session.getAttribute("user");
			if(username2 == null){
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			else{
				String method = request.getParameter("method").trim();
				
				/**********************************************
				 * ����
				 ************************************************/
				if(method.equals("DELNEWS")){//ɾ������ ������ͨ����ҳ��index.jsp
					String strDirPath = request.getSession().getServletContext().getRealPath("/");
					String check[] = request.getParameterValues("checkit");
        			if(check == null){
						request.setAttribute("message", "��ѡ��Ҫɾ���ļ�¼��");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						int id2[]= new int[check.length];
						for(int i = 0;i<check.length;i++){
							int s = Integer.parseInt(check[i]);				
							id2[i] = s;
						}
						int flag = new NewsBean().delNews(id2); 
						if(flag == Constant.SUCCESS){
							request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
						}
						else{
							request.setAttribute("message", "ϵͳά���У����Ժ����ԣ�");
							request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
						}
					}
				}
				else if(method.equals("HIDENEWS")){//���ء���ʾ���� ������ͨ����ҳ��index.jsp
					String id = Filter.escapeHTMLTags(request.getParameter("id").trim());
					int flag = new NewsBean().hideNews(Integer.parseInt(id));
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "ϵͳά���У����Ժ����ԣ�");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("UPNEWS")){//�ö� ���� ������ͨ����ҳ��index.jsp
					String id = Filter.escapeHTMLTags(request.getParameter("id").trim());
					int flag = new NewsBean().upNews(Integer.parseInt(id));
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "ϵͳά���У����Ժ����ԣ�");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
//				if(method.equals("DELUPNEWS")){//ɾ������ �����ö�����ҳ��up.jsp
//					String strDirPath = request.getSession().getServletContext().getRealPath("/");
//					String check[] = request.getParameterValues("checkit");
//        			if(check == null){
//						request.setAttribute("message", "��ѡ��Ҫɾ���ļ�¼��");
//						request.getRequestDispatcher(sysdir+"/news/up.jsp").forward(request, response);
//					}
//					else{
//						int id2[]= new int[check.length];
//						for(int i = 0;i<check.length;i++){
//							int s = Integer.parseInt(check[i]);				
//							id2[i] = s;
//						}
//						int flag = new NewsBean().delNews(id2,strDirPath); 
//						if(flag == Constant.SUCCESS){
//							request.getRequestDispatcher(sysdir+"/news/up.jsp").forward(request, response);
//						}
//						else{
//							request.setAttribute("message", "ϵͳά���У����Ժ����ԣ�");
//							request.getRequestDispatcher(sysdir+"/news/up.jsp").forward(request, response);
//						}
//					}
//				}
				
				
				else{
	            	request.getRequestDispatcher("error.jsp").forward(request, response);
	            }
			}
		}catch(Exception e){
			
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
