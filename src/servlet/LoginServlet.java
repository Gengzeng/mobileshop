package servlet;

import cn.itcast.servlet.BaseServlet;
import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends BaseServlet {
    private UserDao userDao = new UserDao();
    public String login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          String logname = request.getParameter("logname");
          String password = request.getParameter("password");
          User user = userDao.findByLogname(logname);

          if (user == null || user.getPassword().equals(password)== false){
              request.setAttribute("msg","用户名或密码错误");
              return "f:/loginToAdmin.jsp";
          }

          else {
              request.getSession().setAttribute("user",user);

              return "r:/pages/main.jsp";
          }


    }
}
