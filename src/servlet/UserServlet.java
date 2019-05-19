package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends BaseServlet {

    private UserDao userDao = new UserDao();

    /**
     * 查询所有用户
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<User> userList = userDao.findAll();

        request.setAttribute("userList",userList);

        return "f:/pages/user-list.jsp";
    }


    /**
     * 删除用户
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteByLogname(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String logname = request.getParameter("logname");
        userDao.delete(logname);

        return "r:/UserServlet?method=findAll";

    }

    public String findByLogname(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String logname = request.getParameter("logname");

        User user = userDao.findByLogname(logname);

        request.setAttribute("user",user);

        return "f:/pages/user-show.jsp";

    }

    public String edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        User user = CommonUtils.toBean(request.getParameterMap(),User.class);



        userDao.edit(user);

        return "r:/UserServlet?method=findAll";
    }





    }
