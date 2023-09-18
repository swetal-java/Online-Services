package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingServicesDao;
import dao.CustomerDao;
import model.BookingServicesModel;
import model.CustomerModel;
import services.Servicess;

/**
 * Servlet implementation class BookingServicesController
 */
@WebServlet("/BookingServicesController")
public class BookingServicesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingServicesController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("confirm")) {
			Servicess servicess = new Servicess();
			int cid = Integer.parseInt(request.getParameter("cid"));
			int bid = Integer.parseInt(request.getParameter("bid"));
			CustomerModel cm = CustomerDao.getEmail(cid);
			if (cm != null) {
				String email = cm.getEmail();
				System.out.println(email);
			    servicess.sendConfrmationMail(email);
				BookingServicesDao.updatepedingstatus(bid);
				System.out.println(bid);
				response.sendRedirect("serviceman-home.jsp");
			}

		} else if (action.equalsIgnoreCase("reject")) {
			int bid = Integer.parseInt(request.getParameter("bid"));
			BookingServicesDao.rejectservices(bid);
			response.sendRedirect("serviceman-home.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("book")) {
			BookingServicesModel bsmodel = new BookingServicesModel();
			bsmodel.setCid(Integer.parseInt(request.getParameter("cid")));
			bsmodel.setSer_id(Integer.parseInt(request.getParameter("ser_id")));
			bsmodel.setSid(Integer.parseInt(request.getParameter("sid")));
			bsmodel.setSprice(Integer.parseInt(request.getParameter("sprice")));
			bsmodel.setBooking_status("pending");
			bsmodel.setPayment_status("pending");
			BookingServicesDao.bookservices(bsmodel);
			response.sendRedirect("customer-home.jsp");
		}
	}

}
