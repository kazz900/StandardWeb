package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {

	// 1. 직접 값을 지정하는 경우
//	public static Connection getConnection() {
//		Connection conn = null;
//		
//		try {
//			//오라클 드라이버 연결
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			//JDBC로 연결 (오라클주소, 사용자명, 비번)
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##testweb","testweb")
//			//코밋은 무조건 수동으로 하기 위해 false로 해줌
//			conn.setAutoCommit(false);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		return conn;
//	}

	// 2. driver.properties 이용 파일에서 설정값들을 읽어와 DB로 연결
	// static method에서는 this 사용 못하므로 별도의 파일 읽기용 내부 클래스 작성
	// static 메소드 내에서 사용해야 하므로 static 클래스로 작성한다
//	private static class ReadProperties {
//		private Properties prop;
//
//		public ReadProperties() {
//			prop = new Properties();
//
//			try {
//				prop.load(new InputStreamReader(this.getClass().getResourceAsStream("driver.properties")));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		public Properties getProperties() {
//			return prop;
//		}
//	}
//	
//	public static Connection getConnection() {
//		Connection conn = null;
//		Properties prop = new ReadProperties().getProperties();
//		
//		try {
//			
//			// 오라클 드라이버 연결
//			Class.forName(prop.getProperty("driver"));
//			// JDBC로 연결 (오라클주소, 사용자명, 비번)
//			conn = DriverManager.getConnection(
//					prop.getProperty("url"), 
//					prop.getProperty("user"),
//					prop.getProperty("password"));
//			// 코밋은 무조건 수동으로 하기 위해 false로 해줌
//			conn.setAutoCommit(false);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return conn;
//	}
	
	//3. 톰캣이 제공하는 DBCP(Database Connection Pool)을 이용
	//	 XML파일로 설정값을 주면 된다.
	//	 Wep app의 경우 contents directory가 정해져 있다. : project/src/main/webapp 
	//	 webapp/META-INF/context.xml 파일에서 설정변경
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			// context.xml에 설정된 <resource> element의 값들을 이용해서
			// DBCP (Database Connection Pool)을 통해서 Connection을 가져옴
			// 현재는 톰캣 구동 시 20개가 생성되도록 설정 해뒀음
			// context.xml과 연결한다.
			Context initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:comp/env/jdbc/oraDB");
			
			conn = ds.getConnection();
			conn.setAutoCommit(false); //자동 commit 해제를 잊지 말자
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return conn;
	}


	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stat) {
		try {
			if (stat != null && !stat.isClosed())
				stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed())
				rset.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset, Statement stat) {
		try {
			if ((rset != null && !rset.isClosed() && (stat != null && !stat.isClosed()))) {
				rset.close();
				stat.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stat, ResultSet rset) {
		try {
			if ((rset != null && !rset.isClosed() && (stat != null && !stat.isClosed()))) {
				rset.close();
				stat.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
