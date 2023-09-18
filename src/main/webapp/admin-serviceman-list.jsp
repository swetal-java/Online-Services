<%@page import="dao.AdminDao"%>
<%@page import="model.ServiceManModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="admin-navsidebar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="main">
		<header class="mb-3">
			<a href="#" class="burger-btn d-block d-xl-none"> <i
				class="bi bi-justify fs-3"></i>
				
			</a>
		</header>

		<div class="page-heading">
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3>Service-man's</h3>
						<p class="text-subtitle text-muted"></p>
					</div>
					<div class="col-12 col-md-6 order-md-2 order-first"></div>
				</div>
			</div>

			<!-- Basic Tables start -->
			<section class="section">
				<div class="card">
					<div class="card-header"></div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table" id="table1">
								<thead>
									<tr>
										<th>Name</th>
										<th>Email</th>
										<th>Address</th>
										<th>Contact</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
								<% List<ServiceManModel> list = AdminDao.getallserviceman(); %>
								<%for (ServiceManModel ss : list){ %>
									<tr>
										<td><%=ss.getName() %></td>
										<td><%=ss.getEmail() %></td>
										<td><%=ss.getAddress() %></td>
										<td><%=ss.getContact() %></td>
										<td><a
											href="admin-serviceman-edit.jsp?sid=<%= ss.getId()%>">Edit</a></td>
										<td><a
											href="admin-serviceman-delete.jsp?sid=<%= ss.getId() %>">Delete</a></td>
									</tr>
									<%} %>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
			<!-- Basic Tables end -->
		</div>
	</div>
</body>
</html>