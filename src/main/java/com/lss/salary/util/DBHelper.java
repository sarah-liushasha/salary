package com.lss.salary.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;


//数据库操作帮助类
public class DBHelper {
	private static DataSource dataSource=null;
	
	//因为调用一次就执行一次，所以用静态代码块只执行一次
	static{
			Properties prop=new Properties();
			try {
			//官方文档全部的配置参数：url等，所以配置文件有要求
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			//通过DataSource工厂类  返回数据源
			dataSource=DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//直接用静态代码块
	private static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static void close (PreparedStatement ps,Connection conn){
		close(null,ps,conn);
	}
	
	public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				//连接池中的连接关闭时，实际上不是真的关了，而是还给了池
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//封装一个方法 执行所有的DML语句
	public static int executeDML(String sql,Object...params){
		PreparedStatement ps=null;
		Connection conn=null;
		try{
			conn=getConnection();
			ps=conn.prepareStatement(sql);
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			
			return ps.executeUpdate();//int类型    新增,修改,删除都是update  DML
			
		}catch(Exception e){
			
		}finally {
			close(ps,conn);
		}
		return 0;
	}

	//执行这个方法会返回一个数字，比如获取最大的ID，或者统计总条数
	public static int executeInt(String sql,Object... params){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			if(params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);//把第i+1个 换成params[i]
				}
			}
			
			rs=ps.executeQuery();
			rs.next();//把光标移动到第一行
			return rs.getInt(1);//获取第一列的值，拿出来是个数字直接返回
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rs,ps,conn);
		}
		return 0;
		
	}
	
	//查询
	//泛型方法要加在方法的返回自前面
	public static<T> List<T> executeQuery(String sql,Class<T> c,Object... params) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<T> result=new ArrayList<>();
		try{
			conn=getConnection();
			ps=conn.prepareStatement(sql);//ps是准备好数据库
		
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			
			rs=ps.executeQuery();//rs是查询返回结果集
			ResultSetMetaData rsmd= rs.getMetaData();//获取结果集的元数据
			while(rs.next()){//遍历结果集
				T m=c.newInstance();//每遍历一次创建一个对象
				for(int i=0;i<rsmd.getColumnCount();i++){//遍历每行数据里的所有列       rsmd.getColumnCount();查询获取的元数据有几列
					
					String headName=rsmd.getColumnLabel(i+1);//获取列的表头名字
					Object value=rs.getObject(headName);//根据表头名字获取结果集中的值
					
					Field field=c.getDeclaredField(headName);//根据表头名字获取列的成员变量
					if(field!=null){
						field.setAccessible(true);
						field.set(m, value);//向m中field属性赋值
					}
				}
				result.add(m);//将对象添加到返回的集合中
			}

		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			close(rs,ps,conn);
		}
		
		return result;
	}
	

	
	
	
	

}
