package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ServiceManDao;
import model.ServiceManModel;
import services.Servicess;

/**
 * Servlet implementation class ServiceManController
 */
@WebServlet("/ServiceManController")
public class ServiceManController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceManController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("register")) {
			ServiceManModel sModel = new ServiceManModel();
			sModel.setName(request.getParameter("name"));
			sModel.setAddress(request.getParameter("address"));
			sModel.setContact(Long.parseLong(request.getParameter("contact")));
			sModel.setEmail(request.getParameter("email"));
			sModel.setPassword(request.getParameter("password"));
			ServiceManDao.insertdata(sModel);
			response.sendRedirect("serviceman-login.jsp");
		} else if (action.equalsIgnoreCase("login")) {
			ServiceManModel serviceManModel = new ServiceManModel();
			String email = request.getParameter("email");
			serviceManModel.setEmail(request.getParameter("email"));
			serviceManModel.setPassword(request.getParameter("password"));
			boolean flag = ServiceManDao.checkEmail(email);
			if (flag == true) {
				ServiceManModel s = ServiceManDao.login(serviceManModel);
				if (s == null) {
					request.setAttribute("passwordmsg", "Password Is Not Correct..");
					request.getRequestDispatcher("servicemans-login.jsp").forward(request, response);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("data", s);
					request.getRequestDispatcher("serviceman-home.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errormsg", "You are not a Registered User..");
				request.getRequestDispatcher("serviceman-login.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("update")) {
			ServiceManModel serviceManModel = new ServiceManModel();
			serviceManModel.setId(Integer.parseInt(request.getParameter("id")));
			serviceManModel.setName(request.getParameter("name"));
			serviceManModel.setEmail(request.getParameter("email"));
			serviceManModel.setContact(Long.parseLong(request.getParameter("contact")));
			serviceManModel.setAddress(request.getParameter("address"));
			ServiceManDao.updatadata(serviceManModel);
			HttpSession session = request.getSession();
			session.setAttribute("data", serviceManModel);
			request.getRequestDispatcher("serviceman-home.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("get otp")) {
			ServiceManModel sModel = new ServiceManModel();
			String email = request.getParameter("email");
			boolean flag = ServiceManDao.checkEmail(email);
			if (flag == true) {
				Servicess servicess = new Servicess();
				Random r = new Random();
				int num = r.nextInt(999999);
				System.out.println(num);
				servicess.sendMail(email, num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("serviceman-otp-verify.jsp").forward(request, response);
				;
			} else {
				request.setAttribute("emailmsg", "You are not a registered user..");
				request.getRequestDispatcher("serviceman-emailsendotp.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("change password")) {
			String email = request.getParameter("email");
			String op = request.getParameter("op");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			boolean flag = ServiceManDao.chkoldpaasword(email, op);
			if (flag == true) {
				if (np.equals(cnp)) {
					ServiceManDao.updatepassword(email, np);
					response.sendRedirect("serviceman-home.jsp");
				}
			} else {
				request.setAttribute("ermsg", "Incorrect PAssword..");
				request.getRequestDispatcher("serviceman-change-password.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("verify")) {
			String email = request.getParameter("email");
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			System.out.println(email + otp1 + otp2);
			if (otp1 == otp2) {
				request.setAttribute("email", email);
				request.getRequestDispatcher("serviceman-new-password.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("new password")) {
			String email = request.getParameter("email");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			System.out.println(email + np + cnp);
			if (np.equals(cnp)) {
				ServiceManDao.updatepassword(email, np);
				response.sendRedirect("serviceman-login.jsp");
			}
		}
	}

}
