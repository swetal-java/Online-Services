package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import connection.DbConnection;
import model.ServicemanServices;

public class ServicemanServicesDao {
	// to upload services
	public static void insertservices(ServicemanServices services) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "insert into services_tbl (serviceman_id,s_name,s_category,s_duration,s_price) values (?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, services.getServiceman_id());
			pStatement.setString(2, services.getS_name());
			pStatement.setString(3, services.getS_category());
			pStatement.setString(4, services.getS_duration());
			pStatement.setInt(5, services.getS_price());
			pStatement.executeUpdate();
			System.out.println("Services are Added..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to manage services
	public static List<ServicemanServices> getservices(int ser_id) {
		List<ServicemanServices> list = new ArrayList<ServicemanServices>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from services_tbl where serviceman_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, ser_id);
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				ServicemanServices services = new ServicemanServices();
				services.setS_id(rSet.getInt("s_id"));
				services.setServiceman_id(rSet.getInt("serviceman_id"));
				services.setS_name(rSet.getString("s_name"));
				services.setS_category(rSet.getString("s_category"));
				services.setS_duration(rSet.getString("s_duration"));
				services.setS_price(rSet.getInt("s_price"));
				list.add(services);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	// 14-07-2023
	// to get services by id
	public static ServicemanServices getservicesbyid(int id) {
		ServicemanServices services = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from services_tbl where s_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next()) {
				services = new ServicemanServices();
				services.setS_id(rSet.getInt("s_id"));
				services.setServiceman_id(rSet.getInt("serviceman_id"));
				services.setS_name(rSet.getString("s_name"));
				services.setS_category(rSet.getString("s_category"));
				services.setS_duration(rSet.getString("s_duration"));
				services.setS_price(rSet.getInt("s_price"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return services;
	}

	// to update services
	public static void updateservices(ServicemanServices services) {
		try {
			Connection conncetion = DbConnection.connectionmethod();
			String sql = "update services_tbl set s_name=? , s_category=? , s_duration=? , s_price=? where s_id= ?";
			PreparedStatement pStatement = conncetion.prepareStatement(sql);
			pStatement.setString(1, services.getS_name());
			pStatement.setString(2, services.getS_category());
			pStatement.setString(3, services.getS_duration());
			pStatement.setInt(4, services.getS_price());
			pStatement.setInt(5, services.getS_id());
			pStatement.executeUpdate();
			System.out.println("Services are Updated..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to delete services
	public static void deleteservice(int id) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "delete from services_tbl where s_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
			System.out.println("Service is Deleted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to show all services to customer
	public static List<ServicemanServices> getallservices() {
		List<ServicemanServices> list = new ArrayList<ServicemanServices>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from services_tbl";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rSet = pst.executeQuery();
			while (rSet.next()) {
				ServicemanServices services = new ServicemanServices();
				services.setS_id(rSet.getInt("s_id"));
				services.setServiceman_id(rSet.getInt("serviceman_id"));
				services.setS_name(rSet.getString("s_name"));
				services.setS_category(rSet.getString("s_category"));
				services.setS_duration(rSet.getString("s_duration"));
				services.setS_price(rSet.getInt("s_price"));
				list.add(services);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;

	}
	
	//get service data on on admin side to edit
	public static ServicemanServices getservicesdata(int ser_id) {
		ServicemanServices services = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from services_tbl where s_id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, ser_id);
			ResultSet rSet = pStatement.executeQuery();
			if(rSet.next()) {
				services = new ServicemanServices();
				services.setS_id(rSet.getInt("s_id"));
				services.setS_name(rSet.getString("s_name"));
				services.setS_duration(rSet.getString("s_duration"));
				services.setS_price(rSet.getInt("s_price"));
				services.setS_category(rSet.getString("s_category"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return services;
	}
}
