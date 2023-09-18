<%@page import="dao.ServiceManDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% int sid = Integer.parseInt(request.getParameter("sid")); %>
<%ServiceManDao.deleteserviceman(sid); %>
<% response.sendRedirect("admin-serviceman-list.jsp"); %>
</body>
</html>