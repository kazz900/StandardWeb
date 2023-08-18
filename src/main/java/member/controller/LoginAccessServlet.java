package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginAccessServlet
 */
@WebServlet("/loginok")
public class LoginAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAccessServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//관리자용 로그인 접속 제한/해제 처리용 컨트롤러
		
		String userid = request.getParameter("userid");
		String ok = request.getParameter("ok");
		
		String loginok = null;
		
		if(ok.equals("true")) {
			loginok = "Y";
		}else {
			loginok = "N";
		}
		RequestDispatcher view = null;
		MemberService mservice = new MemberService();
		ArrayList<Member> list = null;
		// 회원전체 조회를 다시 실행해서, memberListView.jsp에 list를 다시 보냄 (브라우저 새로고침됨)
		int result = mservice.updateLoginOK(userid, loginok);
		if(result > 0) {
			list = mservice.selectList();
		}
		if(result > 0 && list.size() > 0) {
			view = request.getRequestDispatcher("views/member/memberListView.jsp");
			request.setAttribute("list", list);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "회원 전체 조회 실패");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
