package member.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDao {
	public MemberDao() {
		super();
	}

	public Member selectLogin(Connection conn, String userid, String userpwd) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM MEMBER WHERE USERID = ? and USERPWD = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setUserId(userid);
				member.setUserPwd(userpwd);
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModified(rset.getDate("LASTMODIFIED"));
				member.setSignType(rset.getString("SIGNTYPE"));
				member.setAdmin(rset.getString("ADMIN"));
				member.setLoginOk(rset.getString("LOGIN_OK"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset, pstmt);
		}
		return member;
	}

	public int insertMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES("
				+ "?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Member selectMember(Connection conn, String userid) {
		Member m = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM MEMBER WHERE USERID = ?";
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member();
				
				m.setUserId(userid);
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setPhone(rset.getString("PHONE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setLastModified(rset.getDate("LASTMODIFIED"));
				m.setSignType(rset.getString("SIGNTYPE"));
				m.setAdmin(rset.getString("ADMIN"));
				m.setLoginOk(rset.getString("LOGIN_OK"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER "
				+ "SET AGE = ?, "
				+ "PHONE = ?, "
				+ "EMAIL = ?, "
				+ "LASTMODIFIED = DEFAULT "
				+ "WHERE USERID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, member.getAge());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteMember(Connection conn, String userid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM MEMBER "
				+ "WHERE USERID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectList(Connection conn) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE ADMIN = 'N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member member = new Member();
				
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModified(rset.getDate("LASTMODIFIED"));
				member.setSignType(rset.getString("SIGNTYPE"));
				member.setAdmin(rset.getString("ADMIN"));
				member.setLoginOk(rset.getString("LOGIN_OK"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateLoginOK(Connection conn, String userid, String loginok) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER "
				+ "SET LOGIN_OK = ? "
				+ "WHERE USERID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginok);
			pstmt.setString(2, userid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectSearchUserid(Connection conn, String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE USERID LIKE ?"; // '%'||?||'%' <= ERROR 물음표가 있냐는 SQL 구문이 됨
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member member = new Member();
				
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModified(rset.getDate("LASTMODIFIED"));
				member.setSignType(rset.getString("SIGNTYPE"));
				member.setAdmin(rset.getString("ADMIN"));
				member.setLoginOk(rset.getString("LOGIN_OK"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Member> selectSearchGender(Connection conn, String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE GENDER = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member member = new Member();
				
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModified(rset.getDate("LASTMODIFIED"));
				member.setSignType(rset.getString("SIGNTYPE"));
				member.setAdmin(rset.getString("ADMIN"));
				member.setLoginOk(rset.getString("LOGIN_OK"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Member> selectSearchAge(Connection conn, String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		boolean isOverSixty = false;
		if(Integer.parseInt(keyword) >= 60) {
			isOverSixty = true;
			query = "SELECT * FROM MEMBER WHERE AGE >= ?";
		}else {
			isOverSixty = false;
			query = "SELECT * FROM MEMBER WHERE AGE BETWEEN ? AND ?";
		}
		
		try {
			int beginAge = (int) Math.floor(Double.parseDouble(keyword));
			int endAge = beginAge + 9;
			pstmt = conn.prepareStatement(query);
			if(isOverSixty) {
				pstmt.setInt(1, beginAge);				
			} else {
				pstmt.setInt(1, beginAge);				
				pstmt.setInt(2, endAge);
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member member = new Member();
				
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModified(rset.getDate("LASTMODIFIED"));
				member.setSignType(rset.getString("SIGNTYPE"));
				member.setAdmin(rset.getString("ADMIN"));
				member.setLoginOk(rset.getString("LOGIN_OK"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Member> selectSearchEnrollDate(Connection conn, Date begin, Date end) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE ENROLL_DATE BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, begin);
			pstmt.setDate(2, end);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member member = new Member();
				
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModified(rset.getDate("LASTMODIFIED"));
				member.setSignType(rset.getString("SIGNTYPE"));
				member.setAdmin(rset.getString("ADMIN"));
				member.setLoginOk(rset.getString("LOGIN_OK"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Member> selectSearchLoginOK(Connection conn, String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE LOGIN_OK = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member member = new Member();
				
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModified(rset.getDate("LASTMODIFIED"));
				member.setSignType(rset.getString("SIGNTYPE"));
				member.setAdmin(rset.getString("ADMIN"));
				member.setLoginOk(rset.getString("LOGIN_OK"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
