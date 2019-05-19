package domain;

public class Order {

    private int id;
    private String logname;
    private String mess;
    private double sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
