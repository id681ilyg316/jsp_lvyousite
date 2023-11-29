package com.bean;


/**
 * 留言本
 * @author Administrator
 *
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

public class GuestBookBean {

	private List list;
	private ResultSet rs;
	private String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	private String sql="select count(*) from guestbook where ifhide='1'";
	private String sql2="select * from guestbook where ifhide='1' order by addtime desc ";
    public void setEVERYPAGENUM(int EVERYPAGENUM){
    	this.EVERYPAGENUM=EVERYPAGENUM;
    }
    public int getMessageCount() { //得到信息总数
       DBO dbo=new DBO();
       dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            dbo.close();
        }
    }
    public int getPageCount() { //得到共多少页（根据每页要显示几条信息）
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }
    public List getMessage(int page) { //得到每页要显示的信息
        DBO dbo=new DBO();
        dbo.open();
        List list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql2);
            for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                rs.next();
            }
            for (int t = 0; t < EVERYPAGENUM; t++) {
                if (rs.next()) {
                    qq++;
                    List list2=new ArrayList();
                    list2.add(rs.getInt("id"));//0
    				list2.add(rs.getString("nickname"));//1
    				list2.add(rs.getString("pic"));//2
    				list2.add(rs.getString("email"));//3
    				list2.add(rs.getString("qq"));//4
    				list2.add(rs.getString("weburl"));//5
    				list2.add(rs.getString("blogurl"));//6
    				list2.add(rs.getString("expressions"));//7
    				list2.add(rs.getString("content"));//8
    				list2.add(rs.getString("addtime"));//9
    				list2.add(rs.getString("ip"));//10
    				list2.add(rs.getString("replay"));//11
    				list.add(list2);
                } else {
                    break; //减少空循环的时间
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }
    }
    
    //后台管理留言分页
    
	//private String sql4="select * from guestbook order by addtime desc ";
    public int getMessageCountM(String stime,String etime) { //得到信息总数
    	String sql3=null;
       if(stime.equals("0")){//屏蔽的
    	   sql3="select count(*) from guestbook where ifhide='0'";
       }   
       else if(stime.equals("1")){//显示的
    	   sql3="select count(*) from guestbook where ifhide='1'";
       }
       else if(stime.equals("2")){//所有的
    	   sql3="select count(*) from guestbook ";
       }
       else{
    	   sql3="select count(*) from guestbook where addtime between '"+stime+"' and '"+etime+" 23:59:59'";
       }
       DBO dbo=new DBO();
       dbo.open();
        try { 
            rs = dbo.executeQuery(sql3);
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            dbo.close();
        }
    }
    public List getMessageM(int page,String stime,String etime) { //得到每页要显示的信息
    	String sql4=null;
        if(stime.equals("0")){//屏蔽的
        	sql4="select * from guestbook where ifhide='0'";
        }   
        else if(stime.equals("1")){//显示的
        	sql4="select * from guestbook where ifhide='1'";
        }
        else if(stime.equals("2")){//所有的
        	sql4="select * from guestbook order by addtime desc ";
        }
        else{
        	sql4="select * from guestbook where addtime between '"+stime+"' and '"+etime+" 23:59:59'";
        }
        DBO dbo=new DBO();
        dbo.open();
        List list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql4);
            for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                rs.next();
            }
            for (int t = 0; t < EVERYPAGENUM; t++) {
                if (rs.next()) {
                    qq++;
                    List list2=new ArrayList();
                    list2.add(rs.getInt("id"));//0
    				list2.add(rs.getString("nickname"));//1
    				list2.add(rs.getString("pic"));//2
    				list2.add(rs.getString("email"));//3
    				list2.add(rs.getString("qq"));//4
    				list2.add(rs.getString("weburl"));//5
    				list2.add(rs.getString("blogurl"));//6
    				list2.add(rs.getString("expressions"));//7
    				list2.add(rs.getString("content"));//8
    				list2.add(rs.getString("addtime"));//9
    				list2.add(rs.getString("ip"));//10
    				list2.add(rs.getString("replay"));//11
    				list2.add(rs.getString("ifhide"));//12
    				list.add(list2);
                } else {
                    break; //减少空循环的时间
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }
    }
    //显示、隐藏留言
    public int hideGuestBook(int id){
    	String sql = "select ifhide from guestbook where id='"+id+"'  ";
    	String sql2 = "update guestbook set ifhide='0' where id ='"+id+"'";
    	String sql3 = "update guestbook set ifhide='1' where id ='"+id+"'";
    	DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			if(rs.getInt(1) == 1){
				int i = dbo.executeUpdate(sql2);
				if(i == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int i = dbo.executeUpdate(sql3);
				if(i == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
    }
	//发表留言
	public int addGuestBook(String nickname,String pic,String email,String qq,String weburl,String blogurl,String expressions,String content,String ip,int ifhide){
		String sql = "insert into guestbook ( nickname, pic, email, qq, weburl, blogurl, expressions, content,addtime,ip,replay,ifhide)" +
				" values('"+nickname+"','"+pic+"','"+email+"','"+qq+"','"+weburl+"','"+blogurl+"','"+expressions+"','"+content+"','"+date+"','"+ip+"','0','"+ifhide+"') ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	//查询留言内容
	public String getGuestBook(int id){
		String sql = "select content from guestbook where id='"+id+"' ";
		DBO dbo=new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			return rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
	}
	//	查询回复内容
	public String getReplay(int id){
		String sql = "select replay from replay where mid='"+id+"' ";
		DBO dbo=new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			return rs.getString(1);
		}catch(Exception e){
 			return null;
		}finally{
			dbo.close();
		}
	}
//	查询回复内容
	public List getReplayInfo(int id){ 
		String sql = "select * from replay where mid='"+id+"' ";
		DBO dbo=new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
	}
	//删除留言
	public int delGuestBook(int id[]){
		DBO dbo=new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				dbo.executeUpdate("delete from  guestbook  where  id = '"+id[i]+"'");	
				dbo.executeUpdate("delete from  replay  where  mid = '"+id[i]+"'");	
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//回复留言
	public int reGuestBook(int mid ,String replay,String replayer){
		String sql = "insert into replay (mid,replay,replayer,replaytime)" +
				" values ('"+mid+"','"+replay+"','"+replayer+"','"+date+"') ";
		String sql2 = "update guestbook set replay='1' where id='"+mid+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			int j = dbo.executeUpdate(sql2);
			if(i == j && i== 1)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	//	修改回复
	public int upReplay(int mid ,String replay){
		String sql = "update replay set replay='"+replay+"',replaytime='"+date+"' where mid='"+mid+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	//	查询会员性别
	public String getSex(String username){
		String sql = "select sex from member where username='"+username+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next())
				return rs.getString(1);
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
	}
}

