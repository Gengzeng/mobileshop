package dao;

import cn.itcast.jdbc.TxQueryRunner;
import domain.Order;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查找所有
     *
     * @return
     */
    public List<Order> findAll() {
        String sql = "select * from orderform";

        List<Order> orderList = null;
        try {
            orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;

    }

    /**
     * 根据 id 删除订单
     *
     * @param id
     */
    public void deleteById(int id) {
        String sql = "delete from orderform where id =?";

        try {
            qr.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据 id 来查询订单
     */
    public Order findById(int id) {

        String sql = "select * from orderform where id = ?";
        Order order = null;
        try {
            order = qr.query(sql, new BeanHandler<Order>(Order.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }


}
