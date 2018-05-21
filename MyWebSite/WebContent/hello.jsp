<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		request.setCharacterEncoding("utf-8");
		String name = null;
		if (request.getMethod().equals("POST"))
			name = request.getParameter("userName");
		if (name == null || name.trim().isEmpty())
			name = "";
	%>
	<h1>
	<%
		if (name.isEmpty())
			out.print("Привет!");
		else
			out.print(String.format("Привет, %s!", name));
	
	%>
	</h1>
	<form method="POST">
		<input type="text" name="userName" value="<%= name %>">
		<input type="submit" value="Hello">
	</form>	

</body>
</html>