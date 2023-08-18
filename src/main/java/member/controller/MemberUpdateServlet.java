package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/mupdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberUpdateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원 정보 수정 처리용 컨트롤러

		// 1. 전송온 값에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("UTF-8");

		// 2. 전송온 값 꺼내서 변수 또는 객체에 저장하기
		Member member = new Member();

		member.setUserId(request.getParameter("userid"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));

		// 3. 모델 서비스 메소드로 값 또는 객체 전달 실행하고 결과 받기
		int result = new MemberService().updateMember(member);

		// 4. 받은 결과로 내보낼 뷰 선택 처리
		if (result > 0) {
			RequestDispatcher view = request.getRequestDispatcher("views/member/myinfoPage.jsp");
			//수정 성공했을때 내보낼 뷰
			//수정 성공시 마이페이지뷰가 출력되게 한다면, myinfo 서블릿을 구동시킴
			//서블릿에서 다른 서블릿을 실행 시킬 수 있음
			response.sendRedirect("/first/myinfo?userid=" + member.getUserId());
			//수정 성공시 메인페이지가 출력되게 한다면
//			response.sendRedirect("index.jsp");
			
		} else {
			//수정 실패시 내보낼 뷰
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
