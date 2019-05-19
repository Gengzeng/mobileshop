package domain;

public class User {

    private String logname;
    private String password;
    private String phone;
    private String address;
    private String realname;
    private String admin;
    private String adminStr;



    public String getAdminStr() {

        if (admin == null || admin.equals("0")){
            adminStr = "普通用户";
        }
        else if (admin.equals("1") ){
            adminStr = "管理员";
        }
        return adminStr;
    }

    public void setAdminStr(String adminStr) {
        this.adminStr = adminStr;
    }

    public String getAdmin() {

        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
