<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
String name1 = request.getParameter("val");
if (name1 == null || name1.trim().equals("")) {
	out.print("<p>Please Enter EMail..</p>");
} else {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/project", "root", "");
		PreparedStatement ps = conn.prepareStatement("select * from seviceman_tbl where email = ?");
		ps.setString(1, name1);
		ResultSet rset = ps.executeQuery();
		while (rset.next()) {
	      out.println("<p>Email Id Already Registered..</p>");
		}
		conn.close();
	} catch (Exception e) {
		
	}
}
%>

<%
						String e = request.getParameter("val");
						if (e == null && e.trim().equals("")) {
							out.print("<p>Please Enter Email..</p>");
						} else {
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/project", "root", "");
								PreparedStatement ps = conn.prepareStatement("select * from seviceman_tbl where email = ?");
								ps.setString(1, e);
								ResultSet rset = ps.executeQuery();
								if (rset.next()) {
							out.println("<p>Email Id Already Registered..</p>");
								} else {
							out.println("<p>Email Id Is Not Registered..</p>");
								}
								conn.close();
							} catch (Exception e1) {

							}
						}
						%>
