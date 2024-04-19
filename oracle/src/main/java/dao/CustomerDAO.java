package dao;
import java.sql.*;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

//DAO = >Data Access Object  데이터만 가져오게하는 것이다.

// 이렇게 main static 함수를 이렇게 class 안에서 디버깅 하는 방법이 TDD 방법이다.
public class CustomerDAO {
	// 디버깅용 메인 메서드
	public static void main(String[] args) throws Exception {
		// 메일 체크 메서드 디버깅
		//System.out.println(CustomerDAO.checkMail("a@goodee.com")); // false
		 
		//System.out.println(CustomerDAO.insertCustomer(
		//"z@goodee.com","1234","zzz","1999/09/09","여")); // 1
		
		// System.out.println(CustomerDAO.login("a@goodee.com", "1234")); // 성공...
		//System.out.println(CustomerDAO.deleteCustomer("a@goodee.com", "1234")); 
		
		System.out.println(CustomerDAO.selectCustomerListByPage(1, 10));
		
	}
	
	// 비밀번호 수정
	// 호출 : editPwAction.jsp
	// param : String(mail), String(수정전 pw), String(수정할 pw)
	// return : int(1성공, 0실패)
	public static int updatePw(String mail, String oldPw, String newPw) throws Exception {
		int row = 0;
		
		Connection conn = DBHelper.getConnection();
		String sql = "update customer"
				+ " set pw = ?"
				+ " where mail = ? and pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, newPw);
		stmt.setString(2, mail);
		stmt.setString(3, oldPw);
		row = stmt.executeUpdate();
		
		return row;
	}
	
	
	//관리자 페이지 전체 회원정보(pw제외)
	// 호출 : /admin/customerList.jsp
	// param : void
	// return : Cunstomer배열  -> ArrayList<HashMap<String, Object>>
	
	public static ArrayList<HashMap<String, Object>> selectCustomerListByPage(int startRow, int rowPerPage) throws Exception{
		// 액션 currentPage + rowPerPage -> startRow를 구하는 알고리즘 코드구현 액션에서.....
		// startRow를 구하는 알고리즘 코드구현을 DAO에 하지 않도록.....
		
		ArrayList<HashMap<String, Object>> list =
				new ArrayList<HashMap<String, Object>>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "select "
				+ "mail,name,birth,gender,update_date updateDate,create_date createDate "
				+ " from customer"
				+ " order by mail"
				+ " offset ? rows fetch next ? rows only";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, startRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		
		// JDBC Resulst(자바에서 일반적이지 않은 자료구조) 
		//  -> (자바에서 평이한 자료구조) Collections API 타입 -> List, Set, Map 
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("mail", rs.getString("mail"));
			m.put("name", rs.getString("name"));
			list.add(m);
		}
		
		conn.close();
		
		return list;

	}
	
	
	//로그인 사용자 회원정보보기
	// 호출 : customerOne.jsp
	// param : String(세션의 mail)
	// return : Customer (비밀번호 제외)
	
	public static HashMap<String, Object> selectCumstomerOne(String mail ) throws Exception{
		HashMap<String, Object> map = null;
		
		return map;
	}
	
	
	// 회원탈퇴
	// 호출 : dropCustomerAction.jsp
	// param : String(세션안의 mail), String(pw)
	// return : int(1이면 탈퇴, 0이면 탈퇴실패)
	public static int deleteCustomer(String mail, String pw) throws Exception {
		int row = 0;
		
		Connection conn = DBHelper.getConnection();
		String sql = "delete from customer"
				+ " where mail = ? and pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, mail);
		stmt.setString(2, pw);
		row = stmt.executeUpdate();
		
		return row;
	}
	
	
	// 로그인 메서드
	// 호출 : loginAction.jsp
	// param : String(mail), String(pw)
	// return : HashMap(메일, 이름)
	public static HashMap<String, String> login(String mail, String pw) throws Exception {
		HashMap<String, String> map = null;
		
		Connection conn = DBHelper.getConnection();
		String sql = "select mail, name"
				+ " from customer"
				+ " where mail = ? and pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, mail);
		stmt.setString(2, pw);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			map = new HashMap<String, String>();
			map.put("mail", rs.getString("mail"));
			map.put("name", rs.getString("name"));
		}
		
		conn.close();
		
		return map;
	}
	
	
	
	// 회원가입 액션
	// 호출 : addCustomerAction.jsp
	// param : customer ...
	// return : int(입력실패 0, 성공이면 1)
	public static int insertCustomer(String mail, String pw, String name,
							String birth, String gender) throws Exception {
		int row = 0;
		
		Connection conn = DBHelper.getConnection();
		String sql = "insert into customer("
				+ "mail, pw, name, birth, gender,"
				+ " update_date, create_date) values ("
				+ "?,?,?,?,?, sysdate, sysdate)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, mail);
		stmt.setString(2, pw);
		stmt.setString(3, name);
		stmt.setString(4, birth);
		stmt.setString(5, gender);
		row = stmt.executeUpdate();
		
		conn.close();
		
		return row;
	}
	
	// 회원가입시 mail 중복확인
		// 호출 : checkMailAction.jsp
		// param : String(메일문자열)
		// return : boolean(사용가능하면 true, 불가면 false)
		public static boolean checkMail(String mail) throws Exception {
			boolean result = false;
			
			Connection conn = DBHelper.getConnection();
			String sql = "select mail"
					+ " from customer"
					+ " where mail = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mail);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()) { // 사용불가
				result = true;
			}		
			conn.close();
			
			return result;
		}
}




