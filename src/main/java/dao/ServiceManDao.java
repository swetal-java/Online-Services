package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DbConnection;
import connection.DbConnection;
import model.ServiceManModel;

public class ServiceManDao {
	public static void insertdata(ServiceManModel sm) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "insert into seviceman_tbl (name,email,contact,address,password) values (?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, sm.getName());
			pst.setString(2, sm.getEmail());
			pst.setLong(3, sm.getContact());
			pst.setString(4, sm.getAddress());
			pst.setString(5, sm.getPassword());
			pst.executeUpdate();
			System.out.println("Serviceman Data Inserted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from seviceman_tbl where email = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rSet = pst.executeQuery();
			if (rSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	public static ServiceManModel login(ServiceManModel manModel) {
		ServiceManModel smodel = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from seviceman_tbl where email = ? and password = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, manModel.getEmail());
			pst.setString(2, manModel.getPassword());
			ResultSet rSet = pst.executeQuery();
			if (rSet.next()) {
				smodel = new ServiceManModel();
				smodel.setId(rSet.getInt("id"));
				smodel.setName(rSet.getString("name"));
				smodel.setEmail(rSet.getString("email"));
				smodel.setAddress(rSet.getString("address"));
				smodel.setPassword(rSet.getString("password"));
				smodel.setContact(rSet.getLong("contact"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return smodel;
	}

	// update data of user profile
	public static void updatadata(ServiceManModel s) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "update seviceman_tbl set name=?,email=?,contact=?,address=? where id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setString(2, s.getEmail());
			pst.setLong(3, s.getContact());
			pst.setString(4, s.getAddress());
			pst.setInt(5, s.getId());
			pst.executeUpdate();
			System.out.println("Data is updated..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to check old passsword
	public static boolean chkoldpaasword(String email, String np) {
		boolean flag = false;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from seviceman_tbl where email = ? and password = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, np);
			ResultSet rSet = pst.executeQuery();
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
			String sql = "update seviceman_tbl set password = ? where email = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, np);
			pst.setString(2, email);
			pst.executeUpdate();
			System.out.println("Serviceman password is updated..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//to get data for edit on admin-side
	public static ServiceManModel getdataserviceman(int sid) {
		ServiceManModel sManModel = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from seviceman_tbl where id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, sid);
			ResultSet rSet = pst.executeQuery();
			if(rSet.next()) {
				sManModel = new ServiceManModel();
				sManModel.setId(rSet.getInt("id"));
				sManModel.setName(rSet.getString("name"));
				sManModel.setAddress(rSet.getString("address"));
				sManModel.setEmail(rSet.getString("email"));
				sManModel.setContact(rSet.getLong("contact"));
			}
 		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sManModel;
	}
	
	//to delete serviceman from admin-side
	public static void deleteserviceman(int sid) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "delete from seviceman_tbl where id = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, sid);
			pStatement.executeUpdate();
			System.out.println("Serviceman deleted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
