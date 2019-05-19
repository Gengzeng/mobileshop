package dao;

import cn.itcast.jdbc.TxQueryRunner;
import domain.Mobileclassify;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MobileclassifyDao {
    private QueryRunner qr = new TxQueryRunner();
        public List<Mobileclassify> findAll() {
            String sql = "select * from mobileclassify";

            try {
                return qr.query(sql,new BeanListHandler<>(Mobileclassify.class));
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }

    public void add(Mobileclassify mobileclassify) {
        String sql = "insert into mobileclassify values (?,?)";
        Object[] params = {mobileclassify.getId(),mobileclassify.getName()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void edit(Mobileclassify mobileclassify) {
        String sql = "update mobileclassify set name=? where id=?";
        Object[] params = {mobileclassify.getName(),mobileclassify.getId()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Mobileclassify findById(String id) {
        String sql = "select * from mobileclassify where id = ?";
        try {
            return qr.query(sql,new BeanHandler<>(Mobileclassify.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(String id) {
        String sql = "delete from mobileclassify where id = ?";
        try {
            qr.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
