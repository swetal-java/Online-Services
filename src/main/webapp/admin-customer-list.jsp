<%@page import="dao.AdminDao"%>
<%@page import="model.CustomerModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file = "admin-navsidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="main">
        <header class="mb-3">
          <a href="#" class="burger-btn d-block d-xl-none">
            <i class="bi bi-justify fs-3"></i>
          </a>
        </header>

        <div class="page-heading">
          <div class="page-title">
            <div class="row">
              <div class="col-12 col-md-6 order-md-1 order-last">
                <h3>Customer's</h3>
                <p class="text-subtitle text-muted">
                  
                </p>
              </div>
              <div class="col-12 col-md-6 order-md-2 order-first">
            
              </div>
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
                    <%List<CustomerModel> list = AdminDao.getallcustomer(); %>
                    <%for(CustomerModel cc : list){ %>
                      <tr>
                        <td><%=cc.getName() %></td>
                        <td><%=cc.getEmail() %></td>
                        <td><%=cc.getAddress() %>8</td>
                        <td><%=cc.getContact() %></td>
                        <td><a href="admin-customer-edit.jsp?cid=<%= cc.getId()%>">Edit</a></td>
                        <td><a href="admin-customer-delete.jsp?cid=<%= cc.getId() %>">Delete</a></td>
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