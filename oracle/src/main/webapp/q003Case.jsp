<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<!-- Model -->
<%
	ArrayList<HashMap<String, String>> list = EmpDAO.selectJobCaseList();
	
%>	


<!-- View --> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>case활용</h1>
	<%
		for(HashMap<String, String> m : list) {
	%>
		<%=m.get("ename")%>
		<%=m.get("job")%>
		<%=m.get("color")%>
	
	<%

		}
	%>

</body>
</html>