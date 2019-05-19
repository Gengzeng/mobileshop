package dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

   private  QueryRunner qr = new  TxQueryRunner();

    /**
     * 查找所有
     * @return
     */
   public List<User> findAll() {
       String sql = "select * from user";

       List<User> userList = null;
       try {
           userList = qr.query(sql, new BeanListHandler<User>(User.class));
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return userList;

   }

    /**
     * 删除用户
     * @param logname
     */
   public void delete(String logname){
       String sql = "delete from user where logname =?";

       try {
           qr.update(sql,logname);
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

    /**
     * 根据登录名来查询用户
     */
   public User findByLogname(String logname){

       String sql = "select * from user where logname = ?";
       User user = null;
       try {
           user = qr.query(sql,new BeanHandler<User>(User.class),logname);
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return user;
   }


    /**
     * 修改用户
     */
    public void edit(User user){

     String sql = "update user set phone = ?,address=?,realname=? ,`admin`=? where logname=?";
     Object[] params = {user.getPhone(),user.getAddress(),user.getRealname(),user.getAdmin(),user.getLogname()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
