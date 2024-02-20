import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentServlte implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter out=servletResponse.getWriter();
        Connection con=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String host="jdbc:mysql://localhost:3306/java_mysql";
            String name="root";
            String password="";
            con= DriverManager.getConnection(host,name,password);
            statement=con.createStatement();
            String sql="select * from emp";
            resultSet=statement.executeQuery(sql);

            while (resultSet.next()){
                String ENAME=resultSet.getString("ENAME");
                String JOB=resultSet.getString("JOB");
                String MGR=resultSet.getString("MGR");
                String HIREDATE=resultSet.getString("HIREDATE");
                String SAL=resultSet.getString("SAL");
                String COMM=resultSet.getString("COMM");
                String DEPTNO=resultSet.getString("DEPTNO");
                out.print(ENAME+" "+JOB+" "+MGR+" "+HIREDATE+" "+SAL+" "+COMM+" "+DEPTNO);
                out.print("<br>");
                System.out.println(ENAME+" "+JOB+" "+MGR+" "+HIREDATE+" "+SAL+" "+COMM+" "+DEPTNO);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
