package nowon.contoller;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class NowonListener implements ServletContextListener {

   
   @Override
   public void contextDestroyed(ServletContextEvent sce) {      //끝나기전에 한 번만 실행
//      서버가 종료될때
      Connection conn = (Connection)sce.getServletContext().getAttribute("myDB");
      
      try {
         if(conn != null) conn.close();
         System.out.println(">>>>>>>>>>>>>>>>>>>DBCP 연결해제");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void contextInitialized(ServletContextEvent sce) {   //초기설정
      Context initContext;
      try {
         initContext = new InitialContext();
         Context envContext  = (Context)initContext.lookup("java:/comp/env");
         DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
         Connection conn = ds.getConnection();   //보관할 접속 객체
         System.out.println("리스너에서 DBCP 연결완료");
//         서버영역에 보관할게요
         ServletContext sc = sce.getServletContext();
         sc.setAttribute("myDB", conn);
         
      } catch (NamingException | SQLException e) {
         
         e.printStackTrace();
      }
   }

   
}