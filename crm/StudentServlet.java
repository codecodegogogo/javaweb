package com.my.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class StudentServlet implements Servlet{

	public void init(ServletConfig config) throws ServletException{
	
	}

	public void service(ServletRequest request,ServletResponse response)
		throws ServletException , IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		Statement statement=null;
		ResultSet res=null;
		int res2;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String host="jdbc:mysql://localhost:3306/java_mysql";
			String name="root";
			String password="";
			con=DriverManager.getConnection(host,name,password);
			statement=con.createStatement();
			String sql1="select * from emp";
			res=statement.executeQuery(sql1);
			while (res.next()){
				String ENAME=res.getString("ENAME");
				String JOB=res.getString("JOB");
				String MGR=res.getString("MGR");
				String HIREDATE=res.getString("HIREDATE");
				String SAL=res.getString("SAL");
				String COMM=res.getString("COMM");
				String DEPTNO=res.getString("DEPTNO");
				out.print(ENAME+" "+JOB+" "+MGR+" "+HIREDATE+" "+SAL+" "+COMM+" "+DEPTNO);
				out.print("<br>");
				System.out.println(ENAME+" "+JOB+" "+MGR+" "+HIREDATE+" "+SAL+" "+COMM+" "+DEPTNO);
			}
			System.out.println("查询完毕");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			if(res!=null){
				try{
					res.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}

			if(statement!=null){
				try{
					statement.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public void destroy(){
	
	}

	public String getServletInfo(){
		return "";
	}

	public ServletConfig getServletConfig(){
		return null;
	}
}