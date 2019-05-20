package myservlet.control;

import cn.itcast.servlet.BaseServlet;
import dao.OrderDao;
import domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class orderServlet extends BaseServlet {

    private OrderDao orderDao = new OrderDao();



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

        return "f:/lookOrderForm.jsp";

    }



}
