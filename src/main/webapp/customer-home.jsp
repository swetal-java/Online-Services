<%@page import="dao.ServicemanServicesDao"%>
<%@page import="model.ServicemanServices"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="customer-navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container-xxl py-5">
		<div class="container">
			<div class="text-center wow fadeInUp" data-wow-delay="0.1s">
				<h6 class="text-secondary text-uppercase">Our Technicians</h6>
				<h1 class="mb-5">Our Services</h1>
			</div>
			<div class="row g-4">
				<%
				List<ServicemanServices> list = ServicemanServicesDao.getallservices();
				%>
				<%
				for (ServicemanServices ser : list) {
				%>
				<div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
					<div class="team-item">
						<div class="position-relative overflow-hidden">
							<img class="img-fluid" src="img/team-1.jpg" alt="">
						</div>
						<div class="team-text">
							<div class="bg-light">
								<h5 class="fw-bold mb-0"><%= ser.getS_name() %></h5>
								<small><%= ser.getS_price() %></small>
							</div>
							<div class="bg-primary">
								<a class="btn btn-square mx-1" href="customer-booksingle-services.jsp?sid=<%= ser.getS_id() %>">Book</a>
							</div>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>
</body>
</html>