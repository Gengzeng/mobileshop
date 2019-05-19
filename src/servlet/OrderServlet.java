package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import dao.OrderDao;
import dao.UserDao;
import domain.Order;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderDao orderDao = new OrderDao();

    /**
     * 查询所有订单
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Order> orderList = orderDao.findAll();

        request.setAttribute("orderList", orderList);

        return "f:/pages/order-list.jsp";
    }


    /**
     * 删除订单
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("id"));
        orderDao.deleteById(orderId);

        return "r:/OrderServlet?method=findAll";

    }

}
