package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import dao.MobileDao;
import domain.Mobile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Phone 产品Servlet
 */
public class MobileServlet extends BaseServlet {
    private MobileDao mobileDao = new MobileDao();

    /**
     * 查找所有商品
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Mobile> mobileList = mobileDao.findAll();

        request.setAttribute("mobileList",mobileList);

        return "f:/pages/mobile-list.jsp";
    }

    /**
     * 根据version 查找手机
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByVersion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String version = request.getParameter("version");
        Mobile mobile = mobileDao.findByVersion(version);
        request.setAttribute("mobile",mobile);
        return "f:/pages/mobile-show.jsp";
    }

    /**
     * 通过version删除手机
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteByVersion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String version = request.getParameter("version");
        mobileDao.deleteByVersion(version);


        return "r:/MobileServlet?method=findAll";
    }

    /**
     * 编辑手机
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mobile mobile = CommonUtils.toBean(request.getParameterMap(), Mobile.class);
        mobileDao.edit(mobile);

        return "r:/MobileServlet?method=findAll";
    }

    /**
     * 表单提交添加手机
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException {

        request.setCharacterEncoding("utf-8");
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/image");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        List<FileItem> fileItemList = sfu.parseRequest(request);

        String version = fileItemList.get(0).getString();
        String name = encoding(fileItemList.get(1).getString());
        String made = encoding(fileItemList.get(2).getString()) ;
        String price = fileItemList.get(3).getString();
        FileItem fi1 = fileItemList.get(4);
        String filename = fi1.getName();
        String id = fileItemList.get(5).getString();

        Mobile mobile = new Mobile();
        mobile.setMobile_version(version);
        mobile.setMobile_name(name);
        mobile.setMobile_made(made);
        mobile.setMobile_price(price);
        mobile.setMobile_pic(filename);
        mobile.setId(Integer.parseInt(id));
        System.out.println(mobile);

        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        filename = filename.substring(filename.lastIndexOf("\\")+1);
        //获取item中的上传文件的输入流
        InputStream in = fi1.getInputStream();
        //创建一个文件输出流
        FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
        //创建一个缓冲区
        byte buffer[] = new byte[1024];
        //判断输入流中的数据是否已经读完的标识
        int len = 0;
        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
        while((len=in.read(buffer))>0){
            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
            out.write(buffer, 0, len);
        }
        //关闭输入流
        in.close();
        //关闭输出流
        out.close();
        //删除处理文件上传时生成的临时文件
//        item.delete();


        mobileDao.add(mobile);

        return "r:/MobileServlet?method=findAll";
    }
    /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */
//    private String makeFileName(String filename) {
//        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
//        return CommonUtils.uuid()+ "_" + filename;
//    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储目录
     */
//    private String makePath(String filename,String savePath){
//        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
//        int hashcode = filename.hashCode();
//        int dir1 = hashcode&0xf;  //0--15
//        int dir2 = (hashcode&0xf0)>>4;  //0-15
//        //构造新的保存目录
//        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
//        //File既可以代表文件也可以代表目录
//        File file = new File(dir);
//        //如果目录不存在
//        if(!file.exists()){
//            //创建目录
//            file.mkdirs();
//        }
//        return dir;
//    }


    public String encoding(String s){

        byte bb[]= new byte[0];
        try {
            bb = s.getBytes("iso-8859-1");
            s=new String(bb,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;

    }
}
