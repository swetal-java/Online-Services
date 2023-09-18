package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ServicemanServicesDao;
import model.ServicemanServices;

/**
 * Servlet implementation class ServicemanServicesController
 */
@WebServlet("/ServicemanServicesController")
public class ServicemanServicesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServicemanServicesController() {
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
		if (action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("sid"));
			ServicemanServices services = ServicemanServicesDao.getservicesbyid(id);
			request.setAttribute("data", services);
			request.getRequestDispatcher("serviceman-edit-service.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("upload")) {
			ServicemanServices services = new ServicemanServices();
			services.setServiceman_id(Integer.parseInt(request.getParameter("ser_id")));
			services.setS_name(request.getParameter("sname"));
			services.setS_category(request.getParameter("scategory"));
			services.setS_duration(request.getParameter("sduration"));
			services.setS_price(Integer.parseInt(request.getParameter("sprice")));
			ServicemanServicesDao.insertservices(services);
			response.sendRedirect("serviceman-home.jsp");
		} else if (action.equalsIgnoreCase("update")) {
			ServicemanServices services = new ServicemanServices();
			services.setS_id(Integer.parseInt(request.getParameter("sid")));
			services.setS_name(request.getParameter("sname"));
			services.setS_category(request.getParameter("scategory"));
			services.setS_duration(request.getParameter("sduration"));
			services.setS_price(Integer.parseInt(request.getParameter("sprice")));
			ServicemanServicesDao.updateservices(services);
			response.sendRedirect("serviceman-manage-service.jsp");
		}

	}

}
