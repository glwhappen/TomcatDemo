package org.apache;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;
import javax.servlet.Servlet;
/**
 * �������ڳ䵱�Ľ�ɫ��Tomcat��˾
 */
public class Tomcat {
  public static void main(String[] args) throws Exception {
    System.out.println("Tomcat�����������ɹ�����ʼ�����û��ķ��ʡ�");
    
    // �򵥵�ʹ��Scanner��ģ��һ���û�����
    // �û����ʷ�������ͨ��������ϵġ�����·����
    // Ҳ����˵�û�����·����ͬ����ִ̨��Servlet��ͬ��
    /*
      /userList UserListServlet
      /login    UserLoginServlet
      /bank     BankServlet
      ......
    */
    System.out.println("���������ķ���·����");
    Scanner sc = new Scanner(System.in);
    // �û������ַ
    String path = sc.nextLine();

    // Tomcat������Ӧ��ͨ���û�������·���Ҷ�Ӧ��XXXServlet
    // ����·����XXXServlet֮��Ĺ�ϵӦ����˭ָ���أ�
    // ����Tomcat��������˵��Ҫ���������ļ�
    FileReader reader = new FileReader("web.properties");
    Properties pro = new Properties();
    pro.load(reader);
    reader.close();

    // ͨ��path��ȡvalue
    String className = pro.getProperty(path);
    // ͨ��������ƴ�������
    Class clazz = Class.forName(className);
    Object obj = clazz.newInstance(); // ����Tomcat�Ŀ�����Ա����֪���������ôд�ģ�����֪����һ��ʵ����Servlet�ӿ�
    Servlet servlet = (Servlet)obj;
    servlet.service();
  }
}

/*
1. ��������java�ļ�,-d�Ǵ�������
javac -d . *.java

2. ����Tomcat������
java org.apache.Tomcat

3. ������ַ������
/userList

*/