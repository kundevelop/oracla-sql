<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>

<!-- 컨트롤러 -->
<%
	// 어떤 컬럼으로 정렬
	String col = request.getParameter("col");
	System.out.println(col + " <-- q005OrderBy.jsp param col");

	// asc / desc
	String sort = request.getParameter("sort");
	System.out.println(sort + " <-- q005OrderBy.jsp param sort");
	
	
	// DAO(모델) 호출 -> 모델 반환
	ArrayList<Emp> list = EmpDAO.selectEmpListSort(col, sort);
	System.out.println(list.size() + " <-- q005OrderBy.jsp list.size()");
%>


<!-- 뷰 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>
	<table border="1">
		<tr>
			<th>
				empno
				<a href="./q005OrderBy.jsp?col=empno&sort=asc">오름</a>
				<a href="./q005OrderBy.jsp?col=empno&sort=desc">내림</a>
			</th>
			<th>
				ename
				<a href="./q005OrderBy.jsp?col=ename&sort=asc">오름</a>
				<a href="./q005OrderBy.jsp?col=ename&sort=desc">내림</a>
			</th>
		</tr>
		
		<%
			for(Emp e : list) {
		%>
				<tr>
					<td><%=e.getEmpNo()%></td>
					<td><%=e.getEname()%></td>
				</tr>
		<%		
			}
		%>
	</table>
</body>
</html>
