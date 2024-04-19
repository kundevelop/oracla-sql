<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>

<!-- Controller(컨트롤러) -->

<%
	// DAO호출로 모델 반환	
	ArrayList<Dept> deptList = DeptDAO.selectDeptList();
	ArrayList<Emp> empList = EmpDAO.selectEmpList();
	ArrayList<HashMap<String, Object>> deptOnOffList 
								= DeptDAO.selectDeptOnOffList();
	ArrayList<HashMap<String, Object>> empAndDeptList 
								= EmpDAO.selectEmpAndDeptList();
%>

<!-- View(뷰) -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>q001VoOrMap - Query예제 001</title>
</head>
<body>
	<h1>Dept List</h1>
	<table border="1">
		<tr>
			<th>deptNo</th>
			<th>dname</th>
			<th>loc</th>
		</tr>
		<%
			for(Dept d : deptList) {
		%>
				<tr>
					<td><%=d.deptNo%></td>
					<td><%=d.dname%></td>
					<td><%=d.loc%></td>
				</tr>
		<%		
			}
		%>
	</table>
	
	<h1>Emp List</h1>
	<table border="1">
		<tr>
			<th>empNo</th>
			<th>ename</th>
			<th>sal</th>
		</tr>
		<%
			for(Emp e : empList) {
		%>
				<tr>
					<td><%=e.empNo%></td>
					<td><%=e.ename%></td>
					<td><%=e.sal%></td>
				</tr>
		<%		
			}
		%>
	</table>
	
	<h1>Dept + onOff List</h1>
		<table border="1">
		<tr>
			<th>deptNo</th>
			<th>dname</th>
			<th>loc</th>
			<th>onOff</th>
		</tr>
		<%
			// map단점 : 형변환이 필요 할 수도 있고 IDE자동완성기능을 사용할 수 없다
			for(HashMap<String, Object> don : deptOnOffList) {
		%>
				<tr>
					<td><%=(Integer)(don.get("deptNo"))%></td>
					<td><%=(String)(don.get("dname"))%></td>
					<td><%=(String)(don.get("loc"))%></td>
					<td><%=(String)(don.get("onOff"))%></td>
				</tr>
		<%		
			}
		%>
	</table>
	
	<h1>Emp INNER JOIN Dept List</h1>
		<table border="1">
		<tr>
			<th>empNo</th>
			<th>ename</th>
			<th>deptNo</th>
			<th>dname</th>
		</tr>
		<%
			// map단점 : 형변환이 필요 할 수도 있고 IDE자동완성기능을 사용할 수 없다
			for(HashMap<String, Object> ed : empAndDeptList) {
		%>
				<tr>
					<td><%=(Integer)(ed.get("empNo"))%></td>
					<td><%=(String)(ed.get("ename"))%></td>
					<td><%=(Integer)(ed.get("deptNo"))%></td>
					<td><%=(String)(ed.get("dname"))%></td>
				</tr>
		<%		
			}
		%>
	</table>
</body>
</html>
