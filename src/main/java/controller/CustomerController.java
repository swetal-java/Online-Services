package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import model.CustomerModel;
import services.Servicess;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
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
		if (action.equalsIgnoreCase("register")) {
			try {
				CustomerModel cModel = new CustomerModel();
				cModel.setName(request.getParameter("name"));
				cModel.setAddress(request.getParameter("address"));
				cModel.setContact(Long.parseLong(request.getParameter("contact")));
				cModel.setEmail(request.getParameter("email"));
				cModel.setPassword(request.getParameter("password"));
				CustomerDao.insertuser(cModel);
				response.sendRedirect("customer-login.jsp");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} else if (action.equalsIgnoreCase("login")) {
			CustomerModel cModel = new CustomerModel();
			String email = request.getParameter("email");
			cModel.setEmail(request.getParameter("email"));
			cModel.setPassword(request.getParameter("password"));

			boolean flag = CustomerDao.checkemail(email);
			if (flag == true) {
				CustomerModel customerModel = CustomerDao.login(cModel);
				if (customerModel == null) {
					request.setAttribute("passwordmsg", "Your Password is Incorrect..");
					request.getRequestDispatcher("customer-login.jsp").forward(request, response);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("data", customerModel);
					request.getRequestDispatcher("customer-home.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "You are not a Registered User");
				request.getRequestDispatcher("customer-login.jsp").forward(request, response);
			}

		} else if (action.equalsIgnoreCase("update")) {
			CustomerModel customerModel = new CustomerModel();
			customerModel.setId(Integer.parseInt(request.getParameter("id")));
			customerModel.setName(request.getParameter("name"));
			customerModel.setEmail(request.getParameter("email"));
			customerModel.setAddress(request.getParameter("address"));
			customerModel.setPassword(request.getParameter("password"));
			customerModel.setContact(Long.parseLong(request.getParameter("contact")));
			CustomerDao.updateprofile(customerModel);
			HttpSession session = request.getSession();
			session.setAttribute("data", customerModel);
			request.getRequestDispatcher("customer-home.jsp").forward(request, response);
		}else if (action.equalsIgnoreCase("change password")) {
			String email = request.getParameter("email");
			String op = request.getParameter("op");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			boolean flag = CustomerDao.checkoldpassword(email, op);
			if(flag == true) {
				if(np.equals(cnp)) {
					CustomerDao.updatepassword(email, np);
					response.sendRedirect("customer-home.jsp");
				}
			}else {
				request.setAttribute("ermsg", "Incorrect Passsword..");
				request.getRequestDispatcher("customer-changepassword.jsp").forward(request, response);
			}
		}else if(action.equalsIgnoreCase("get otp")) {
			String email = request.getParameter("email");
			boolean flag = CustomerDao.checkemail(email);
			if(flag == true) {
				Servicess servicess = new Servicess();
				Random random = new Random();
				int num = random.nextInt(99999);
				System.out.println(num);
				servicess.sendMail(email, num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("customer-verify-otp.jsp").forward(request, response);
			}else {
				request.setAttribute("emailmsg","Your email is not Registered..");
				request.getRequestDispatcher("customer-forgot-password.jsp").forward(request, response);
			}
		}else if (action.equalsIgnoreCase("verify")) {
			String email = request.getParameter("email");
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			if(otp1 == otp2) {
				request.setAttribute("email",email);
				request.getRequestDispatcher("customer-new-password.jsp").forward(request, response);
			}
		}else if (action.equalsIgnoreCase("new password")) {
			String email = request.getParameter("email");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			if(np.equals(cnp)) {
				CustomerDao.updatepassword(email, np);
				response.sendRedirect("customer-login.jsp");
				System.out.println("Updated");
			}
		}
	}

}
