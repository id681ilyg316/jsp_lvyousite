package com.bean;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

/**
 * 网站公告、站内调查bean  会员中心公告
 * @author Administrator
 *
 */
public class AfficheBean {

	private ResultSet rs;
	private List list;
	private String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	
	//增加公告
	public int addAffiche(String title,String content,String adder,String ifhide){
		String sql = "insert into affiche (title,content,addtime,adder,ifhide) " +
				"values ('"+title+"','"+content+"','"+date+"','"+adder+"','"+ifhide+"')";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
//	update affiche
	public int updateAffiche(int id,String title,String content,String adder,String ifhide){
		String sql = "update affiche set title = '"+title+"',content='"+content+"',addtime='"+date+"'," +
				"adder='"+adder+"',ifhide='"+ifhide+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//delete affiche
	public int delAffiche(int id[]){
		DBO dbo = new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				dbo.executeUpdate("delete from  affiche  where  id = '"+id[i]+"'");			
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//open.close affiche
	public int hideAffiche(int id){
		String sql = "update affiche set ifhide='1' where id='"+id+"'";
		String sql2 = "update affiche set ifhide='0' where id='"+id+"'";
		String sql3 = "select ifhide from affiche where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql3);
			rs.next();
			int i = rs.getInt(1);
			if(i == 1){
				int flag = dbo.executeUpdate(sql2);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int flag = dbo.executeUpdate(sql);
				if(flag == 1)
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
	//	get one affiche to update
	public List getOneAffiche(int id){
		String sql = "select * from affiche where id = '"+id+"'";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getInt(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	//首页显示所有公告
	public List getAllAffiche(){
		String sql = "select id,content,addtime from affiche where ifhide='1' order by addtime desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getInt(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
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
//	后台显示所有公告
	public List getAllAfficheManage(){
		String sql = "select id,title,addtime,adder,ifhide from affiche order by addtime desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getInt("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
				list2.add(rs.getString("adder"));
				list2.add(rs.getInt("ifhide"));
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
//	增加投票
	public int addVote(String title,String ifhide ,String item1,String item2,String item3,String item4,String item5,String item6,
			String tick1,String tick2 ,String tick3 ,String tick4,String tick5,String tick6 ){
		String sql = "insert into vote ( title, ifhide , item1, item2, item3, item4,item5,item6,tick1, tick2 , tick3 , tick4,tick5,tick6,addtime ) " +
				"values( '"+title+"', '"+ifhide+"' , '"+item1+"', '"+item2+"', '"+item3+"', '"+item4+"','"+item5+"','"+item6+"','"+tick1+"', '"+tick2+"' ," +
						" '"+tick3+"' , '"+tick4+"' , '"+tick5+"' , '"+tick6+"' , '"+date+"' )";
		DBO dbo = new DBO();
		dbo.open();
		try{
			if(ifhide.equals("1")){
				dbo.executeUpdate("update  vote set ifhide='0' ");
			}
			int flag = dbo.executeUpdate(sql);
				if(flag == 1)
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
	//	修改投票
	public int updateVote(int id,String title,String ifhide ,String item1,String item2,String item3,String item4,String item5,String item6,
			String tick1,String tick2 ,String tick3 ,String tick4,String tick5,String tick6 ){
		String sql = "update  vote set title='"+title+"', ifhide='"+ifhide+"' , item1='"+item1+"', item2='"+item2+"'," +
				" item3='"+item3+"', item4='"+item4+"',item5='"+item5+"',item6='"+item6+"',tick1='"+tick1+"', tick2='"+tick2+"' ,tick3='"+tick3+"' , " +
						"tick4='"+tick4+"',tick5='"+tick5+"',tick6='"+tick6+"' where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			if(ifhide.equals("1")){
				dbo.executeUpdate("update  vote set ifhide='0' ");
			}
			int flag = dbo.executeUpdate(sql);
				if(flag == 1)
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
	//	删除投票
	public int delVote(int id){
		String sql = "delete from vote where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int flag = dbo.executeUpdate(sql);
				if(flag == 1)
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
	//查询某个投票修改
	public List getVote(int id){
		String sql = "select * from vote where id='"+id+"'  ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getInt(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
				list.add(rs.getString(7));
				list.add(rs.getString(8));
				list.add(rs.getString(9));
				list.add(rs.getString(10));
				list.add(rs.getString(11));
				list.add(rs.getString(12));
				list.add(rs.getString(13));
				list.add(rs.getString(14));
				list.add(rs.getString(15));
				list.add(rs.getString(16));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	查询所有投票
	public List getAllVote(){
		String sql = "select * from vote order by id desc  ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getInt(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list2.add(rs.getString(8));
				list2.add(rs.getString(9));
				list2.add(rs.getString(10));
				list2.add(rs.getString(11));
				list2.add(rs.getString(12));
				list2.add(rs.getString(13));
				list2.add(rs.getString(14));
				list2.add(rs.getString(15));
				list2.add(rs.getString(16));
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
//	查询前台显示投票
	public List getVote(){
		String sql = "select * from vote where ifhide='1'  ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getInt(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
				list.add(rs.getString(7));
				list.add(rs.getString(8));
				list.add(rs.getString(9));
				list.add(rs.getString(10));
				list.add(rs.getString(11));
				list.add(rs.getString(12));
				list.add(rs.getString(13));
				list.add(rs.getString(14));
				list.add(rs.getString(15));
				list.add(rs.getString(16));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	open.close vote
	public int hideSurvey(int id){
		String sql = "update vote set ifhide='1' where id='"+id+"'";
		String sql2 = "update vote set ifhide='0' where id='"+id+"'";
		String sql3 = "select ifhide from vote where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql3);
			rs.next();
			int i = rs.getInt(1);
			if(i == 1){
				int flag = dbo.executeUpdate(sql2);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int flag = dbo.executeUpdate(sql);
				if(flag == 1)
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
	//投票
	public int addVote(int id,String item){
		String sql = "update vote set "+item+"="+item+"+1  where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int flag = dbo.executeUpdate(sql);
			if(flag == 1)
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
	///会员中心公告（滚动无弹出内容，分个人会员公告和企业会员公告）
	public int addMemAff(String content,String type){
		String sql = "insert into memaff(content,type,addtime) values('"+content+"','"+type+"','"+date+"')";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int flag = dbo.executeUpdate(sql);
			if(flag == 1)
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
//	查询所有会员中心公告
	public List getAllMemAff(){
		String sql = "select * from memaff order by id desc  ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getInt(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
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
	///删除会员中心公告
	public int delMemAff(int id){
		String sql = "delete from memaff where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int flag = dbo.executeUpdate(sql);
			if(flag == 1)
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
//	查询所有会员中心公告 前台
	public List getAllMemAff(String type){
		String sql = "select content,addtime from memaff where type='"+type+"' order by id desc  ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
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
}
