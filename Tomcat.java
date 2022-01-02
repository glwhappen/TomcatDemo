package org.apache;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;
import javax.servlet.Servlet;
/**
 * 我们现在充当的角色是Tomcat公司
 */
public class Tomcat {
  public static void main(String[] args) throws Exception {
    System.out.println("Tomcat服务器启动成功，开始接受用户的访问。");
    
    // 简单的使用Scanner来模拟一下用户请求
    // 用户访问服务器是通过浏览器上的“请求路径”
    // 也就是说用户请求路径不同，后台执行Servlet不同。
    /*
      /userList UserListServlet
      /login    UserLoginServlet
      /bank     BankServlet
      ......
    */
    System.out.println("请输入您的访问路径：");
    Scanner sc = new Scanner(System.in);
    // 用户请求地址
    String path = sc.nextLine();

    // Tomcat服务器应该通过用户的请求路径找对应的XXXServlet
    // 请求路径和XXXServlet之间的关系应该由谁指定呢？
    // 对于Tomcat服务器来说需要解析配置文件
    FileReader reader = new FileReader("web.properties");
    Properties pro = new Properties();
    pro.load(reader);
    reader.close();

    // 通过path获取value
    String className = pro.getProperty(path);
    // 通过反射机制创建对象
    Class clazz = Class.forName(className);
    Object obj = clazz.newInstance(); // 对于Tomcat的开发人员，不知道你的类怎么写的，但是知道你一定实现了Servlet接口
    Servlet servlet = (Servlet)obj;
    servlet.service();
  }
}

/*
1. 编译所有java文件,-d是带包编译
javac -d . *.java

2. 启动Tomcat服务器
java org.apache.Tomcat

3. 输入网址，例如
/userList

*/