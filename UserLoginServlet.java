package cn.glwsq.servlet;
import javax.servlet.Servlet;

// �䵱��ɫ webapp������ ������XXXServlet��Ҫʵ��Servlet�ӿ�
public class UserLoginServlet implements Servlet {
  public void service() {
    System.out.println("UserLoginServlet's service...");
  }
}
