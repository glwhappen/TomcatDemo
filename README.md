# 简单模拟Tomcat
> 本篇文章模拟了一下Tomcat的运行流程，通过这个小例子，可以深刻的理解一下Tomcat服务器的运行过程
> 参考视频教程:https://www.bilibili.com/video/BV1Z3411C7NZ?p=6

代码分为三个部分，分别是

- Tomcat
- Servlet
- webapp

实现了用户运行tomcat服务器，输入对应的网址，通过tomcat的反射机制来调用实现了Servlet接口的Webapp的java程序

通过web.properties这个配置文件来进行地址和java的映射
```
/userList=cn.glwsq.servlet.UserListServlet
/bank=cn.glwsq.servlet.BankServlet
/userLogin=cn.glwsq.servlet.UserLoginServlet
```
## 运行

> 下载所有代码以后，进入到代码所在目录

1. 编译所有java文件,-d是带包编译
```batch
javac -d . *.java
```
2. 启动Tomcat服务器
```batch
java org.apache.Tomcat
```
3. 输入网址，例如
```batch
/userList
```

## 代码

完整代码GitHub：https://github.com/glwhappen/TomcatDemo.git

### Tomcat代码
```java
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
```
### Servlet接口代码
```java
package javax.servlet;
/**
 * 我们现在充当的角色是SUN公司
 * SUN公司
 */
public interface Servlet {
  // 一个专门提供服务的方法
  void service();
}
```
### WebApp代码-UserListServlet

```java
package cn.glwsq.servlet;
import javax.servlet.Servlet;

// 充当角色 webapp开发者 开发的XXXServlet都要实现Servlet接口
public class UserListServlet implements Servlet {
  public void service() {
    System.out.println("UserListServlet's service...");
  }
}
```
