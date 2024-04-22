package dao;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBHelper {
	public static Connection getConnection() throws Exception{
		// 오라클 DB 접근 클래스 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//System.out.println("드라이버 로딩 성공");
		
		Connection conn = null;
		// TNS_ADMIN 매개값은 전자지갑의 위치
		String dbUrl="jdbc:oracle:thin:@kunhee80_high?TNS_ADMIN=c:/dev/Wallet_kunhee80";
		String dbUser="admin";
		// 보안이슈로 로컬에서 설정파일 로드(소스코드에서 비밀번호 노출 X )
		FileReader fr = new FileReader("c:/dev/auth/oracle.properties");
		Properties prop = new Properties();
		prop.load(fr);
		String dbPw = prop.getProperty("dbPw");
		// System.out.println(dbPw + " <-- dbPw");
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		return conn;
	}
	
	// getConnection() 메서드 디버깅용 테스트 코드
	public static void main(String[] args) throws Exception {
		Connection conn = DBHelper.getConnection();
		System.out.println(conn);
	}
}
