package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class GoodsDAO {
	
	// 상품 주문 or 취소시 수정할 수량
	// /customer//addOrdersAction.jsp or dropOrdersAction.jsp....
	// param : int(상품번호), int(변경할 수량 + or - )
	public static int updateGoodsAmount(int goods_no, int amount) 
										throws Exception{
		int row = 0;
		
		String sql = "update goods"
				+ " set goods amount = ?, update_date = stsdate"
				+ " where goods_no = ?";
		
		return row;
	}
	
	// 상품 상세 보기 
	// /customer/goodsOne.jsp
	// param : int(goods_no)
	// return : Goods -> HashMap
	public static HashMap<String, Object> selectGoodsOne(int goodsNo)
												throws Exception {
		HashMap<String, Object> map = null;
		
		/*
		 * SELECT *
		 * 
		 */
		
		String sql = "select"
				+ " from goods"
				+ " where goods_no = ?";
		
		return map;
	}
	
	// 고객 로그인 후 상품목록 페이지
	// /customer/goodsList.jsp
	// param : void
	// return : Goods(일부 속성)의 배열 -> ArrayList<HashMap<String, Object>>
	public static ArrayList<HashMap<String, Object>> selectGoodsList(
			String category, int startRow, int rowPerPage) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		// Connection
		Connection conn = DBHelper.getConnection();
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		if(category != null || category.equals("")) {
			sql = "select"
					+ " goods_no goodsNo,"
					+ " category,"
					+ " goods_title goodsTitle"
					+ " goods_price goodsPrice"
					+ " from goods"
					+ " where category = ?"
					+ " order by goods_no desc"
					+ " offset ? rows fetch next ? rows only";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category);
			stmt.setInt(2, startRow);
			stmt.setInt(3, rowPerPage);
		} else {
			sql = "select"
					+ " goods_no goodsNo,"
					+ " category,"
					+ " goods_title goodsTitle"
					+ " goods_price goodsPrice"
					+ " from goods"
					+ " order by goods_no desc"
					+ " offset ? rows fetch next ? rows only";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, startRow);
			stmt.setInt(2, rowPerPage);
		}
		rs = stmt.executeQuery();
		
        // 자료구조 변환
        
		// 자원반납
		return list;
	}
}
