<%@page import="dao.ServiceManDao"%>
<%@page import="model.ServicemanServices"%>
<%@page import="dao.ServicemanServicesDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file = "serviceman-navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<h2>Your Services</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Service Name</th>
					<th>Service Price</th>
					<th>Service Duration</th>
					<th>Service Category</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<ServicemanServices> list = ServicemanServicesDao.getservices(s.getId());
				%>
				<%
				for (ServicemanServices s1 : list) {
				%>
				<tr>
					<td><%= s1.getS_name() %></td>
					<td><%= s1.getS_price() %></td>
					<td><%= s1.getS_duration() %></td>
					<td><%= s1.getS_category() %></td>
					<td><a href="ServicemanServicesController?action=edit&sid=<%= s1.getS_id() %>">Edit</a></td>
					<td><a href="serviceman-delete-service.jsp?sid=<%= s1.getS_id() %>">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>