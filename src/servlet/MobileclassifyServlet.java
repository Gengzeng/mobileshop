package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import dao.MobileclassifyDao;
import domain.Mobileclassify;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MobileclassifyServlet extends BaseServlet {
    private MobileclassifyDao mobileclassifyDao = new MobileclassifyDao();

    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Mobileclassify> mobileclassifyList = mobileclassifyDao.findAll();

        request.setAttribute("mobileclassifyList",mobileclassifyList);

        return "f:/pages/mobileclassify-list.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mobileclassify mobileclassify = CommonUtils.toBean(request.getParameterMap(), Mobileclassify.class);
        mobileclassifyDao.add(mobileclassify);

        return "r:/MobileclassifyServlet?method=findAll";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mobileclassify mobileclassify = CommonUtils.toBean(request.getParameterMap(), Mobileclassify.class);
        mobileclassifyDao.edit(mobileclassify);

        return "r:/MobileclassifyServlet?method=findAll";
    }

    public String findById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Mobileclassify mobileclassify = mobileclassifyDao.findById(id);

        request.setAttribute("mobileclassify",mobileclassify);

        return "f:/pages/mobileclassify-show.jsp";
    }

    public String deleteById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        mobileclassifyDao.deleteById(id);
        return "r:/MobileclassifyServlet?method=findAll";
    }
}
