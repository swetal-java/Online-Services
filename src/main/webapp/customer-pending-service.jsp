<%@page import="dao.BookingServicesDao"%>
<%@page import="model.BookingServicesModel"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookingServicesDao" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="customer-navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<h2>Pending Services</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Booking Price</th>
					<th>Booking Status</th>
					<th>Payment Status</th>

				</tr>
			</thead>
			<tbody>
			
				<%
				int i = c.getId();
				
				%>
				<%
				List<BookingServicesModel> list = BookingServicesDao.getallpendingservices(i);
				%>
				<%
				for (BookingServicesModel b1 : list) {
				%>
				<tr>
					<td><%=b1.getSprice()%></td>
					<td><%=b1.getBooking_status()%></td>
					<td><%=b1.getPayment_status()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>