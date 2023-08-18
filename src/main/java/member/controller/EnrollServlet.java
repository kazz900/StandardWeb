package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class EnrollServlet
 */
@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnrollServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원가입 처리용 컨트롤러
		// request에 form에 입력된 값들이 들어있음

		// 1. 전송온 값에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("UTF-8"); // 전송보낸 뷰페이지의 문자셋(charset)을 값으로 사용함

		// 2. 전송온 값 꺼내서 변수 또는 객체에 옮겨 저장하기
		// getParameter로 받을 값의 종류가 1개 이상이면 객체를 선언하는것이 원칙
		Member member = new Member();
		member.setUserId(request.getParameter("userid"));
//		member.setUserPwd(request.getParameter("userpwd"));
		String userpwd = request.getParameter("userpwd");
		member.setUserName(request.getParameter("username"));
		member.setGender(request.getParameter("gender"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		
		// Check box 전송형태 :
		// ...&hobby=game&hobby=sport&hobby=sex&.....
		// 같은 name으로 여러개의 값이 올때, getParameter가 오류난다
		// getParameterValues("name") : String[] 사용해야한다
		
		String [] hobbies = request.getParameterValues("hobby");
//		System.out.println("hobbies.length : " + hobbies.length);
		// 문자열 Array를 하나의 String으로 변환한다면 (구분자를 ,로 정한 경우)
		String hobby = String.join(",", hobbies);
//		System.out.println("hobby : " + hobby);
		
		String etc = request.getParameter("etc");
//		System.out.println("etc : " + etc);
		
		// ------ 암호화 ------
		// 웹에서는 암호화 알고리즘 사용시 단방향 알고리즘만 사용함 :
		// 단방향 알고리즘은 복호화 알고리즘이 없음
		// java.security.MessageDigest 클래스 이용함
		String cryptoUserpwd = null;
		
		try {
			// 1. 암호화 클래스 인스턴스 생성 + 알고리즘 세팅
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			// 2. 패스워드 문자열을 암호문으로 바꾸려면 byte[] 로 변환해야함
			byte [] pwdValues = userpwd.getBytes(Charset.forName("UTF-8"));
			// 3. 암호문으로 바꾸기
			md.update(pwdValues);
			// 4. 암호화된 byte[]를 String으로 바꿈 : 암호문 상태임
			cryptoUserpwd = Base64.getEncoder().encodeToString(pwdValues);
			
			// 확인
//			System.out.println("암호화된 패스워드 : " + cryptoUserpwd);
//			System.out.println("암호화된 패스워드 길이 : " + cryptoUserpwd.length());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// Database에 INSERT 하기전에 PWD 셋함
		member.setUserPwd(cryptoUserpwd);
		
		// 3. 모델의 서비스 메소드로 값 전달 실행하고 결과받기
		int result = new MemberService().insertMember(member);

		// 에러페이지 테스트
//		int result = 0; 
		
		// 4. 받은 결과로 성공 또는 실패 페이지 redirect
		if (result > 0) { // 회원가입 성공했을때
			// 로그인 페이지로 이동 처리
			// view에 전달값이 필요하면 RequestDispatcher (상대경로만 쓸수있다)
			// view에 전달값이 없으면 reponse.sendRedirect("html 절대경로")
			response.sendRedirect("/first/views/member/loginPage.html");
		} else {
			// 회원가입 실패
			// view에 전달값 필요하기에 RequestDispatcher 사용
			// 상대경로만 사용가능하다
			// view file 설정해준다
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			
			// 지정한 뷰로 내보낼 정보나 객체가 있으면 request에 기록 저장함
			// request.setAttribute(String name, Object value);
			request.setAttribute("message", "회원가입 실패 관리자에게 문의하세요.");
			
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
