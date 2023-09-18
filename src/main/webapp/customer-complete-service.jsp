<%@page import="dao.BookingServicesDao"%>
<%@page import="model.BookingServicesModel"%>
<%@page import="java.util.List"%>
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
					<th>Pay Now</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<BookingServicesModel> list = BookingServicesDao.getconfirmservicesbycid(c.getId());
				%>
				<%
				for (BookingServicesModel book : list) {
				%>
				<tr>
					<td><%=book.getSprice()%></td>
					<td><%=book.getBooking_status()%></td>
					<td><%=book.getPayment_status()%></td>
					<%
					boolean flag = BookingServicesDao.chkdonepaymentstatus(book.getBid());
					%>
					<%
					if (flag == false) {
					%>
					<td><form method="post"
							action="payment.jsp?amount=<%=book.getSprice()%>&cid=<%=book.getBid()%>">
							<table border="1">
								<tbody>
									<tr>
										<td><label>Amount</label></td>
										<td><input title="TXN_AMOUNT" tabindex="10" type="text"
											name="TXN_AMOUNT" value="<%=book.getSprice()%>"></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td><input value="CheckOut" type="submit" onclick=""></td>
									</tr>
								</tbody>
							</table>
							* - Mandatory Fields
						</form></td>
					<%
					} else {
					%>
					<td><label>Your Payment is Successfully Done..</label></td>

					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>