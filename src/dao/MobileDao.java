package dao;

import cn.itcast.jdbc.TxQueryRunner;
import domain.Mobile;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MobileDao {
    private QueryRunner qr = new TxQueryRunner();
    public List<Mobile> findAll() {
        String sql = "select * from mobileform";

        try {
            return qr.query(sql,new BeanListHandler<>(Mobile.class));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Mobile findByVersion(String version) {
        String sql = "select * from mobileform where mobile_version = ?";
        try {
            return qr.query(sql,new BeanHandler<>(Mobile.class),version);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteByVersion(String version) {
        String sql = "delete from mobileform where mobile_version = ?";
        try {
            qr.update(sql,version);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void edit(Mobile mobile) {
        String sql = "update mobileform set mobile_name=?,mobile_made=? ,mobile_price=?," +
                "mobile_mess=?,id=? where mobile_version=?";
        Object[] params = {mobile.getMobile_name(),mobile.getMobile_made(),mobile.getMobile_price(),
                mobile.getMobile_mess(),mobile.getId(),mobile.getMobile_version()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void add(Mobile mobile) {
        String sql = "insert into mobileform values (?,?,?,?,?,?,?)";
        Object[] params = {mobile.getMobile_version(),mobile.getMobile_name(),mobile.getMobile_made(),
                mobile.getMobile_price(), null,mobile.getMobile_pic(),mobile.getId()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
