<%@ page contentType="text/html;charset=utf-8" %>
<jsp:useBean id="loginBean" class="mybean.data.Login" scope="session"/>
<%@ page import="java.sql.*" %>
<HTML><HEAD><%@ include file="head.txt" %></HEAD>
<body>
<div align="center">
<%  if(loginBean==null){
        response.sendRedirect("login.jsp");//重定向到登录页面
    }
    else {
       boolean b =loginBean.getLogname()==null||
                  loginBean.getLogname().length()==0;
       if(b)
         response.sendRedirect("login.jsp");//重定向到登录页面
    }
    Connection con;
    Statement sql; 
    ResultSet rs;
    try{  Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e){}
    try { String uri= "jdbc:mysql://127.0.0.1/mobileshop";
          String user="root";
          String password="123";
          con=DriverManager.getConnection(uri,user,password);
          sql=con.createStatement();
          String cdn=
         "SELECT id,mess,sum,status FROM orderform where logname= '"+loginBean.getLogname()+"'";
          rs=sql.executeQuery(cdn);
          out.print("<table border=2>");
          out.print("<tr>");
            out.print("<th width=100>"+"订单号");
            out.print("<th width=100>"+"信息");
            out.print("<th width=100>"+"价格");
            out.print("<th width=100>"+"操作");
          out.print("</TR>");
          while(rs.next()){
            out.print("<tr>");
              out.print("<td >"+rs.getString(1)+"</td>"); 
              out.print("<td >"+rs.getString(2)+"</td>");
              out.print("<td >"+rs.getString(3)+"</td>");
              if (rs.getString(4) .equals("0")  ) {
                  out.print("<td ><A href=\"http://localhost:8080/orderServlet?method=deleteById&id=" + rs.getString(1) + "\"><font size=2>删除</font></A></td>");
              }
              else {out.print("<td >已审核</td>");}
              out.print("</tr>") ; 
          }
          out.print("</table>");
          con.close();
    }
    catch(SQLException e){ 
          out.print(e);
    }
 %>
</div">
</BODY></HTML>
