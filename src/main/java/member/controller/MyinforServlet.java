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
 * Servlet implementation class MyinforServlet
 */
@WebServlet("/myinfo")
public class MyinforServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyinforServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 내 정보보기(My page) 요청 처리용 컨트롤러

		// 1. 전송온 값에 한글이 있으면 인코딩 처리함
		request.setCharacterEncoding("UTF-8");
		// 2. 전송온 값 꺼내서 변수 또는 객체에 저장하기
		String userid = request.getParameter("userid");

		// 3. 모델 서비스의 메소드 값으로 값 전달 실행하고 결과 받기
		Member member = new MemberService().selectMember(userid);
		RequestDispatcher view = null;
		// 4. 받은 결과로 성공/실패 페이지 내보내기
		if (member != null) { //요청 성공시
			// RequestDispatcher view 받아올때는 상대경로만 쓸 수 있다
			view = request.getRequestDispatcher("views/member/myinfoPage.jsp");
			request.setAttribute("member", member);
		} else { // 요청 실패시
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", userid + " 회원정보 조회 실패");
		}
		
		view.forward(request, response);
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
