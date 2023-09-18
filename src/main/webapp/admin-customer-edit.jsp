<%@page import="dao.AdminDao"%>
<%@page import="dao.CustomerDao"%>
<%@page import="model.CustomerModel"%>
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
		<div class="page-heading">
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3></h3>
						<p class="text-subtitle text-muted"></p>
					</div>
					<div class="col-12 col-md-6 order-md-2 order-first">
						<nav aria-label="breadcrumb"
							class="breadcrumb-header float-start float-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="index.html"></a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Form Layout</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>

			<!-- Basic Horizontal form layout section start -->
			<section id="basic-horizontal-layouts">
				<div class="row match-height">
					<div class="col-md-6 col-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">Horizontal Form</h4>
							</div>
							<div class="card-content">
								<div class="card-body">
									<form class="form form-horizontal" method="post" action="AdminController">
									<%int cid = Integer.parseInt(request.getParameter("cid")); %>
									<% CustomerModel cm = CustomerDao.getdatacustomer(cid); %>
										<div class="form-body">
											<div class="row">
											<input type="hidden" name="id" value="<%= cid%>">
												<div class="col-md-4">
													<label>Name</label>
												</div>
												<div class="col-md-8 form-group">
													<input type="text" id="first-name" class="form-control"
														name="name" placeholder="First Name" value="<%= cm.getName()%>">
												</div>
												<div class="col-md-4">
													<label>Email</label>
												</div>
												<div class="col-md-8 form-group">
													<input type="email" id="email-id" class="form-control"
														name="email" placeholder="Email" value="<%= cm.getEmail()%>">
												</div>
												<div class="col-md-4">
													<label>Contact</label>
												</div>
												<div class="col-md-8 form-group">
													<input type="text" id="contact-info" class="form-control"
														name="contact" placeholder="Mobile" value="<%= cm.getContact()%>"/>
												</div>
												<div class="col-md-4">
													<label>Address</label>
												</div>
												<div class="col-md-8 form-group">
													<input type="text" id="password" class="form-control"
														name="address" placeholder="Address" value="<%= cm.getAddress()%>"/>
												</div>
												<div class="col-12 col-md-8 offset-md-4 form-group">
													<div class="form-check"></div>
												</div>
											</div>
											<div class="col-sm-12 d-flex justify-content-end">
												<button type="submit"
													class="btn btn-light-secondary me-1 mb-1" name="action" value="update">
													Update</button>
											</div>
										</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
		</div>
		</section>
		
	</div>
	</div>
</body>
</html>