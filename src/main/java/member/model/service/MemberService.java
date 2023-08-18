package member.model.service;

import static common.JDBCTemplate.*;


import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;
// 서비스는 커넥션의 트랜잭션(commit, rollback)관리하고 커넥션 제공한다
// Connection management service class
public class MemberService {
	//dependency injection
	private MemberDao mdao = new MemberDao();
	
	public MemberService() {
		super();
	}
	
	public Member selectLogin(String userid, String userpwd) {
		Connection conn = getConnection();
		Member loginMember = mdao.selectLogin(conn, userid, userpwd);
		close(conn);
		return loginMember;
	}
	
	public int insertMember(Member member) {
		Connection conn = getConnection();
		
		int result = mdao.insertMember(conn, member);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Member selectMember(String userid) {
		Connection conn = getConnection();
		Member loginMember = mdao.selectMember(conn, userid);
		close(conn);
		return loginMember;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		
		int result = mdao.updateMember(conn, member);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteMember(String userid) {
		Connection conn = getConnection();
		
		int result = mdao.deleteMember(conn, userid);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<Member> selectList() {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectList(conn);
		close(conn);
		return list;
	}

	public int updateLoginOK(String userid, String loginok) {
		Connection conn = getConnection();
		
		int result = mdao.updateLoginOK(conn, userid, loginok);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<Member> selectSearchUserid(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchUserid(conn, keyword);
		close(conn);
		return list;
	}

	public ArrayList<Member> selectSearchGender(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchGender(conn, keyword);
		close(conn);
		return list;
	}

	public ArrayList<Member> selectSearchAge(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchAge(conn, keyword);
		close(conn);
		return list;
	}

	public ArrayList<Member> selectSearchEnrollDate(Date begin, Date end) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchEnrollDate(conn, begin, end);
		close(conn);
		return list;
	}

	public ArrayList<Member> selectSearchLoginOK(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchLoginOK(conn, keyword);
		close(conn);
		return list;
	}
}
