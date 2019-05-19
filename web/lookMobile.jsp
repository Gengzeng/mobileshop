<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.sql.*" %>
<HTML><HEAD><%@ include file="head.txt" %></HEAD>
<BODY bgcolor=cyan><font size=2>
<div align="center">
<h2>选择某类手机<br>分页显示这类手机</h2>
<%   try {  Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){} 
      String uri="jdbc:mysql://127.0.0.1/mobileshop?"+
                             "user=root&password=123&characterEncoding=gb2312";
      Connection con; 
      Statement sql;
      ResultSet rs;
      try {
        con=DriverManager.getConnection(uri);
        sql=con.createStatement();
        //读取mobileClassify表，获得分类：  
        rs=sql.executeQuery("SELECT * FROM mobileClassify  ");
        out.print("<form action='queryServlet' method ='post'>") ;
        out.print("<select name='fenleiNumber'>") ;
        while(rs.next()){
           int id = rs.getInt(1);
           String mobileCategory = rs.getString(2);
           out.print("<option value ="+id+">"+mobileCategory+"</option>");
        }  
        out.print("</select>");
        out.print("<input type ='submit' value ='提交'>");  
        out.print("</form>");
        con.close();
     }
     catch(SQLException e){ 
        out.print(e);
     }
%>
</div></font>
</BODY></HTML>
