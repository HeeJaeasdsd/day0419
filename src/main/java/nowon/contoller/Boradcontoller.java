package nowon.contoller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/board/*")
public class Boradcontoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn; // 보관?

	@Override // alt shift s -> init
	public void init(ServletConfig config) throws ServletException {
//      conn = 서버의 보관함에서 꺼내쓰기
		ServletContext sc = config.getServletContext();

//      Object obj = sc.getAttribute("byDB");
//      Connection conn = (Connection)obj;
//      or
//      Connection conn = (Connection)sc.getAttribute("myDB");
//      this.conn = conn;
//      or
		this.conn = (Connection) sc.getAttribute("myDB");
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/board/ 시작하는 모든 주소를 처리합니다.");

		System.out.println("서버 요청주소 : " + request.getRequestURI());

		String uri = request.getRequestURI();
//      페이지 연결 설정
		if (uri.equals("/day0419/board/list")) {
//         경로설정 주의   '/'는 webapp 폴더에서 찾아요. (파일패스경로)
			String path = "/WEB-INF/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

		} else if (uri.equals("/day0419/board/write")) {
			String path = "/WEB-INF/board/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

		} else if (uri.equals("/day0419/board/write.nowon")) {
			request.setCharacterEncoding("utf-8");
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			System.out.println(writer);
			System.out.println(subject);
			System.out.println(content);
//         DB에 board테이블 저장하세요
//         seq_board
			String sql = "insert into board(no, subject, content, writer, created_date) "
					+ "values(seq_board.nextval, ?, ?, ?, sysdate)";
			PreparedStatement pstmt = null;

			Context initContext;
			try {
				pstmt = conn.prepareStatement(sql);
//            ?로 되어있는 쿼리 세팅
				pstmt.setString(1, subject); // 첫번째꺼에 문자열로 대입
				pstmt.setString(2, content); // 두번째꺼에 문자열로 대입
				pstmt.setString(3, writer); // 세번째꺼에 문자열로 대입
//            쿼리실행
				int n = pstmt.executeUpdate();
				System.out.println(n + "개 저장완료");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
//               this.conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
