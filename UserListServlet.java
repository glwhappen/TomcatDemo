package cn.glwsq.servlet;
import javax.servlet.Servlet;

// 充当角色 webapp开发者 开发的XXXServlet都要实现Servlet接口
public class UserListServlet implements Servlet {
  public void service() {
    System.out.println("UserListServlet's service...");
  }
}
