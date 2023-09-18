package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DbConnection;
import model.BookingServicesModel;
import model.CustomerModel;
import model.ServiceManModel;
import model.ServicemanServices;

public class AdminDao {
	
	//getting all customers list on admin side
	public static List<CustomerModel> getallcustomer(){
		List<CustomerModel> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from customer_tbl";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				CustomerModel cModel = new CustomerModel();
				cModel.setId(resultSet.getInt("id"));
				cModel.setName(resultSet.getString("name"));
				cModel.setEmail(resultSet.getString("email"));
				cModel.setAddress(resultSet.getString("address"));
				cModel.setContact(resultSet.getLong("contact"));
				cModel.setPassword(resultSet.getString("password"));
				list.add(cModel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//getting all service-man list on Admin side
	public static List<ServiceManModel> getallserviceman(){
		List<ServiceManModel> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from seviceman_tbl";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				ServiceManModel cModel = new ServiceManModel();
				cModel.setId(resultSet.getInt("id"));
				cModel.setName(resultSet.getString("name"));
				cModel.setEmail(resultSet.getString("email"));
				cModel.setAddress(resultSet.getString("address"));
				cModel.setContact(resultSet.getLong("contact"));
				cModel.setPassword(resultSet.getString("password"));
				list.add(cModel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//getting all services list on Admin side
	public static List<ServicemanServices> getallservices(){
		List<ServicemanServices> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from services_tbl";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				ServicemanServices cModel = new ServicemanServices();
				cModel.setS_id(resultSet.getInt("s_id"));
				cModel.setServiceman_id(resultSet.getInt("serviceman_id"));
				cModel.setS_name(resultSet.getString("s_name"));
				cModel.setS_category(resultSet.getString("s_category"));
				cModel.setS_price(resultSet.getInt("s_price"));
				cModel.setS_duration(resultSet.getString("s_duration"));
				list.add(cModel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//getting all booked services list on Admin side
		public static List<BookingServicesModel> getallbookedservices(){
			List<BookingServicesModel> list = new ArrayList<>();
			try {
				Connection connection = DbConnection.connectionmethod();
				String sql = "select * from services_tbl";
				PreparedStatement pStatement = connection.prepareStatement(sql);
				ResultSet resultSet = pStatement.executeQuery();
				while(resultSet.next()) {
					BookingServicesModel cModel = new BookingServicesModel();
					cModel.setBid(resultSet.getInt("bid"));
					cModel.setSer_id(resultSet.getInt("ser_id"));
					cModel.setCid(resultSet.getInt("cid"));
					cModel.setSid(resultSet.getInt("sid"));
					cModel.setBooking_status(resultSet.getString("booking_status"));
					cModel.setPayment_status(resultSet.getString("payment_status"));
					cModel.setSprice(resultSet.getInt("booking_price"));
					list.add(cModel);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
		}
		
		
}
