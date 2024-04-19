package dao;

import java.util.ArrayList;
import java.util.HashMap;


public class OrdersDAO {
	//고객이 자신의 주문을 확인(페이징)
	public static ArrayList<HashMap<String, Object>> selectOrdersListByCustomer(
			String mail, int startRow, int rowPerPage) throws Exception {
		ArrayList<HashMap<String, Object>> list
				= new ArrayList<HashMap<String, Object>>();
		
		
		String sql = "select o.oreders_no ordersNo, "
				+ "			 o.goods_no goodsNo g.goods_title goodsTitle"
				+ " from orders o inner join goods g"
				+ " on o.goods_no = g.goods_no"
				+ " where o.mail = ?"
				+ " order by o.oders_no desc"
				+ " offset ? rows fetch next ? rows only";
		
		
		return list;
		
	}
	
	//관리자 전체 주문을 확인(페이징)
	
	public static ArrayList<HashMap<String, Object>> selectOrdersListAll(
			 int startRow, int rowPerPage) throws Exception {
		ArrayList<HashMap<String, Object>> list
				= new ArrayList<HashMap<String, Object>>();
		
		String sql = "select o.oreders_no ordersNo, "
				+ "			 o.goods_no goodsNo g.goods_title goodsTitle"
				+ " from orders o inner join goods g"
				+ " on o.goods_no = g.goods_no"
				+ " order by o.oders_no desc"
				+ " offset ? rows fetch next ? rows only";
		
		return list;
		
	}

}
