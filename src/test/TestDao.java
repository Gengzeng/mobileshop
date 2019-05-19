package test;

import dao.UserDao;
import domain.User;
import org.junit.Test;

import java.util.List;

public class TestDao {
    @Test
    public void test1(){

        UserDao userDao = new UserDao();
        List<User> userList = userDao.findAll();
        System.out.println(userList);
    }


    @Test
    public void test2(){

        UserDao userDao = new UserDao();
        User user = userDao.findByLogname("zzz");

        System.out.println(user.getAdmin());
    }
}
