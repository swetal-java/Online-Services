<%@page import="dao.BookingServicesDao"%>
<%@page import="model.BookingServicesModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="serviceman-navbar.jsp"%>
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
				List<BookingServicesModel> list = BookingServicesDao.getallpendingservicemanservices(s.getId());
				%>
				<%
				for (BookingServicesModel book : list) {
				%>

				<tr>
					<td><%=book.getSprice() %></td>
					<td><%=book.getBooking_status() %></td>
					<td><%=book.getPayment_status() %></td>
					<td><a href="BookingServicesController?action=confirm&bid=<%=book.getBid() %>&cid=<%=book.getCid()%>">CONFIRM</a></td>
					<td><a href="BookingServicesController?action=reject&bid=<%=book.getBid() %>">REJECT</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>