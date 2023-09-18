<%@page import="dao.BookingServicesDao"%>
<%@page import="model.BookingServicesModel"%>
<%@page import="dao.ServicemanServicesDao"%>
<%@page import="model.ServicemanServices"%>
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
	<%
	int id = Integer.parseInt(request.getParameter("sid"));
	%>
	<%
	ServicemanServices s1 = ServicemanServicesDao.getservicesbyid(id);
	%>



	<!-- Contact Start -->
	<div class="container-xxl py-5">
		<div class="container">
			<div class="row g-12">
				<div class="col-md-8 wow fadeInUp" data-wow-delay="0.1s">
					<div class="bg-light p-5 h-100 d-flex align-items-center">
						<%
						boolean flag = BookingServicesDao.chkpendingstatus(c.getId(), id);
						%>
						<%
						if (flag == false) {
						%>
						<form action="BookingServicesController" method="post">
							<div class="row g-3">
								<input type="hidden" name="cid" value="<%=c.getId()%>">
								<input type="hidden" name="sid" value="<%=s1.getS_id()%>">
								<input type="hidden" name="ser_id"
									value="<%=s1.getServiceman_id()%>">
								<div class="col-md-12">
									<div class="form-floating">
										<input type="text" class="form-control" id="name"
											name="sprice" value="<%=s1.getS_price()%>"> <label
											for="name"></label>
									</div>
								</div>
								<div class="col-12">
									<button class="btn btn-primary w-100 py-3" type="submit"
										name="action" value="book">Book</button>

								</div>
							</div>
						</form>
						<%
						}else{
							%>
							<label>Already the Service has been Booked..</label>
							<%} %>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Contact End -->

</body>
</html>