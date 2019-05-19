<%@ page contentType="text/html;charset=utf-8" %>
<jsp:useBean id="loginBean" class="mybean.data.Login" scope="session"/>
<HTML>
<HEAD>
    <%@ include file="head.txt" %>
</HEAD>
<BODY bgcolor=pink><font size=2>
    <div align="center">

        <tr>
            <th>登录</th>
        </tr>
        <FORM action="loginServlet" Method="post">
            <table border=2>
                <tr>
                    <td>登录名称:<Input type=text name="logname"></td>
                </tr>
                <tr>
                    <td>输入密码:<Input type=password name="password"></td>
                </tr>
            </table>
            <Input type=submit name="g" value="提交">
        </FORM>
    </div>
    <div align="center">
        登录反馈信息:<br>
        <jsp:getProperty name="loginBean" property="backNews"/>
        <br>登录名称:<br><jsp:getProperty name="loginBean" property="logname"/>
        <div>
</font>
</BODY>
</HTML>
