package com.bean;

/**
 * 
 * ����Ա��½ �޸����� ��½��¼��ѯ ͨ�����ļ�
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

public class AdminBean {

	private List list;
	private ResultSet rs = null;
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	private String sql="select count(*) from adminlog";
	private String sql2="select * from adminlog order by id desc ";
	//����ʱ�����
	String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	
	//��ҳ��ѯ��½��־
	public void setEVERYPAGENUM(int EVERYPAGENUM){
    	this.EVERYPAGENUM=EVERYPAGENUM;
    }
    public int getMessageCount() { //�õ���Ϣ����
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
    public int getPageCount() { //�õ�������ҳ������ÿҳҪ��ʾ������Ϣ��
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }
    public List getMessage(int page) { //�õ�ÿҳҪ��ʾ����Ϣ
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
                    list2.add(rs.getInt("id"));
    				list2.add(rs.getString("username"));
    				list2.add(rs.getString("password"));
    				list2.add(rs.getString("logintime"));
    				list2.add(rs.getString("loginip"));
    				list2.add(rs.getString("useros"));
    				list2.add(rs.getString("ok"));
    				list.add(list2);
                } else {
                    break; //���ٿ�ѭ����ʱ��
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
	//����Ա��¼ ���µ�¼���� д��¼��־
	public int adminLogin(String username,String md5password,String password,String loginip,String useros){
		String sql = "select * from admin where username = '"+username+"' and isuse='1'";
		String sql2 = "insert into adminlog(username,password,logintime,loginip,useros,ok) values('"+username+"','"+md5password+"','"+date+"','"+loginip+"','"+useros+"','true')";
		String sql3 = "insert into adminlog(username,password,logintime,loginip,useros,ok) values('"+username+"','"+password+"','"+date+"','"+loginip+"','"+useros+"','false')";
		String sql4 = "update admin set logintimes = logintimes+1 where username = '"+username+"' ";
		DBO dbo = new DBO();
		String pwd;
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				pwd = rs.getString("password");
				if(pwd.trim().equals(md5password)){
					dbo.executeUpdate(sql2);
					dbo.executeUpdate(sql4);
					return Constant.SUCCESS;
				}
				else{
					dbo.executeUpdate(sql3);
					return Constant.PASSWORD_ERROR;
				}
			}
			else{
				dbo.executeUpdate(sql3);
				return Constant.NAME_ERROR;
			}			
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}	
	}
	
	//��ѯ����Ա��Ϣ
	public List getAdminInfo(String username){
		String sql = "select * from admin where username='"+username+"' ";
		DBO dbo = new DBO();
		dbo.open();
		list = new ArrayList();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list.add(rs.getString("username"));
			list.add(rs.getInt("flag"));
			list.add(rs.getInt("logintimes"));
			list.add(date);
			list.add(rs.getString("quanxian"));
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//admin edit password
	public int editPassword(String username,String oldpwd,String newpwd){
		String sql="select * from admin where username = '"+username+"' and password = '"+oldpwd+"'";
		String sql2="update admin set password = '"+newpwd+"' where username = '"+username+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				int i = dbo.executeUpdate(sql2);
				if(i == 1){
					return Constant.SUCCESS;
				}
				else{
					return Constant.SYSTEM_ERROR;
				}
			}
			else{
				return Constant.PASSWORD_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//add manager
	public int addManager(String username,String password,String type,String isuse){
		String sql = "insert into admin(username,password,creattime,flag,isuse,logintimes,quanxian) values('"+username+"','"+password+"','"+date+"','"+type+"','"+isuse+"','0','111')";
		String sql2 = "select * from admin where username = '"+username+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql2);
			if(rs.next()){
				return Constant.SAME_NAME;
			}
			else{
				int i = dbo.executeUpdate(sql);
				if(i == 1)return Constant.SUCCESS;
				else return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//update manager
	public int updateManager(String username,String password,String type,String isuse){
		String sql;
		if(password.equals("")){
			sql = "update admin set flag = '"+type+"' ,isuse = '"+isuse+"' where username = '"+username+"'";
		}
		else{
			sql = "update admin set password = '"+password+"' ,flag = '"+type+"' ,isuse = '"+isuse+"' where username = '"+username+"'";
		}
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)return Constant.SUCCESS;
			else return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//get all manager
	public List getAllManager(){
		String sql = "select * from admin where flag !='1' order by id asc";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getInt(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(4));
				list2.add(rs.getInt(5));
				list2.add(rs.getInt(6));
				list2.add(rs.getInt(7));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//delete manager
	public int delManager(int id){
		String sql = "delete from admin where id = '"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)return Constant.SUCCESS;
			else return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//delete login note
	public int delLog(int id[]){
		DBO dbo=new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				dbo.executeUpdate("delete from  adminlog  where  id = '"+id[i]+"'");			
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
} 

