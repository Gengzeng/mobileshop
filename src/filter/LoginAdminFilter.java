package filter;


import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginAdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");

        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("user");

        if (user == null){
            response.getWriter().print("<A href=\"/loginToAdmin.jsp\"><font size=2>你还没有登录，请点击此处登录</font></A>");
            return;
        }
        else if (user.getAdmin() == null || user.getAdmin().equals("0")){

            response.getWriter().print("<A href=\"/loginToAdmin.jsp\"><font size=2>你不是管理员，请点击此处登录</font></A>");
            return;
        }

        else if ( user.getAdmin().equals("1")){

            filterChain.doFilter(request,response);
        }


    }

    @Override
    public void destroy() {

    }
}
