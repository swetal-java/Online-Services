package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DbConnection;
import model.CustomerModel;

public class CustomerDao {
	public static void insertuser(CustomerModel cModel) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "insert into customer_tbl (name,email,address,contact,password) values (?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, cModel.getName());
			pStatement.setString(2, cModel.getEmail());
			pStatement.setString(3, cModel.getAddress());
			pStatement.setLong(4, cModel.getContact());
			pStatement.setString(5, cModel.getPassword());
			pStatement.executeUpdate();
			System.out.println("Customer Data Inserted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to check email whether registered or not
	public static boolean checkemail(String email) {
		boolean flag = false;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from customer_tbl where email = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, email);
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	// to perform login

	public static CustomerModel login(CustomerModel cModel) {
		CustomerModel cm = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from customer_tbl where email = ? and password = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, cModel.getEmail());
			pStatement.setString(2, cModel.getPassword());
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next()) {
				cm = new CustomerModel();
				cm.setId(rSet.getInt("id"));
				cm.setName(rSet.getString("name"));
				cm.setAddress(rSet.getString("address"));
				cm.setContact(rSet.getLong("contact"));
				cm.setEmail(rSet.getString("email"));
				cm.setPassword(rSet.getString("password"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cm;

	}

	// to update user profile
	public static void updateprofile(CustomerModel cModel) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "update customer_tbl set name=?,email=?,contact=?,address=? where id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, cModel.getName());
			pStatement.setString(2, cModel.getEmail());
			pStatement.setLong(3, cModel.getContact());
			pStatement.setString(4, cModel.getAddress());
			pStatement.setInt(5, cModel.getId());
			pStatement.executeUpdate();
			System.out.println("Customer data is upadted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to check old-password exists
	public static boolean checkoldpassword(String email, String np) {
		boolean flag = false;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from customer_tbl where email=? and password = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, email);
			pStatement.setString(2, np);
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	// to update password
	public static void updatepassword(String email, String np) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "update customer_tbl set password = ? where email = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, np);
			pStatement.setString(2, email);
			pStatement.executeUpdate();
			System.out.println("Customer Password is updated..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to send email on confirmation of services to customer
	public static CustomerModel getEmail(int cid) {
		CustomerModel customerModel = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from customer_tbl where id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rSet = pst.executeQuery();
			if (rSet.next()) {
				customerModel = new CustomerModel();
				customerModel.setId(rSet.getInt("id"));
				customerModel.setEmail(rSet.getString("email"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerModel;
	}

	// get info of customer to edit
	public static CustomerModel getdatacustomer(int cid) {
		CustomerModel cModel = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from customer_tbl where id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rset = pst.executeQuery();
			if (rset.next()) {
				cModel = new CustomerModel();
				cModel.setId(rset.getInt("id"));
				cModel.setName(rset.getString("name"));
				cModel.setEmail(rset.getString("email"));
				cModel.setAddress(rset.getString("address"));
				cModel.setContact(rset.getLong("contact"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cModel;
	}
	
	//to delete customer from admin side
	public static void deletecustomer(int cid) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "delete from customer_tbl where id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, cid);
			pStatement.executeUpdate();
			System.out.println("Customer is deleted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
