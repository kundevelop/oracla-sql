<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>   
<!-- 컨트롤러 -->

<%
	ArrayList<HashMap<String, Object>> list 
		= EmpDAO.selectEmpJoin();


%>   




<!--  뷰 --> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>조인</h1>
	<table border="1">
		<tr>
			<th>EMPNO</th>
			<th>ENAME</th>
			<th>grade</th>
			<th>mgrName</th>
			<th>mgrGrade</th>
			
			<%
				for(HashMap<String, Object> m : list) {
			%>
				<tr>
					<td><%=m.get("empno")%></td>
					<td><%=m.get("ename")%></td>
					<td>
						<% 
							for(int i=0; i<(Integer)(m.get("grade")); i=i+1) {
								
						%>		
						
							&#128151;
						<%
						
							}
						%>
					
					</td>
					
					<td><%=m.get("mgrName")%></td>
					
					<td>
						<% 
							for(int i=0; i<((Integer)m.get("mgrGrade")); i=i+1) {
								
						%>		
						
							&#128154;
						<%
						
							}
						%>
					
					</td>
				
				</tr>
			
		
			<%
				}
			
			%>

	
		</table>

</body>
</html>