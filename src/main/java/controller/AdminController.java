package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dao.ServiceManDao;
import model.CustomerModel;
import model.ServiceManModel;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("login")) {
			String adminemail = "admin@gmail.com";
			String adminpassword = "admin123";
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if(adminemail.equals(email) && adminpassword.equals(password)) {
				request.setAttribute("data", email);
				request.getRequestDispatcher("admin-home.jsp").forward(request, response);
			}else {
				request.setAttribute("pasmsg", "Incorrect Password..");
				request.getRequestDispatcher("admin-login.jsp").forward(request, response);
			}
			
		}else if(action.equalsIgnoreCase("update")) {
			CustomerModel customerModel = new CustomerModel();
			customerModel.setId(Integer.parseInt(request.getParameter("id")));
			customerModel.setName(request.getParameter("name"));
			customerModel.setEmail(request.getParameter("email"));
			customerModel.setAddress(request.getParameter("address"));
			customerModel.setContact(Long.parseLong(request.getParameter("contact")));
			CustomerDao.updateprofile(customerModel);
			HttpSession session = request.getSession();
			session.setAttribute("data", customerModel);
			request.getRequestDispatcher("admin-customer-list.jsp").forward(request, response);
			
		}else if(action.equalsIgnoreCase("updateserviceman")) {
			ServiceManModel sManModel = new ServiceManModel();
			sManModel.setId(Integer.parseInt(request.getParameter("id")));
			sManModel.setName(request.getParameter("name"));
			sManModel.setEmail(request.getParameter("email"));
			sManModel.setAddress(request.getParameter("address"));
			sManModel.setContact(Long.parseLong(request.getParameter("contact")));
			ServiceManDao.updatadata(sManModel);
			HttpSession session = request.getSession();
			session.setAttribute("data",sManModel);
			request.getRequestDispatcher("admin-customer-list.jsp").forward(request, response);
		}
	}

}
