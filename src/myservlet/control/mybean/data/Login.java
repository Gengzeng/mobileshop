package myservlet.control.mybean.data;
import java.util.*;
public class Login {
   String logname="",
          backNews="未登录";   
   ArrayList<String> list;
   public Login() {
      list = new ArrayList<String>();
   }
   public void setLogname(String logname){  
      this.logname=logname;
      if(!(list.contains(logname)))
        list.add(logname);
   }
   public String getLogname(){
        return logname;
   }
   public void setBackNews(String s) {
       backNews=s;
   } 
   public String getBackNews() {
      return backNews;
   }
   public ArrayList<String> getArrayList() {
      return list;
   }
}
