package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.RSAException;

import connection.DbConnection;
import model.BookingServicesModel;
import model.CustomerModel;

public class BookingServicesDao {

	// to book services
	public static void bookservices(BookingServicesModel bmodel) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "insert into bookservices_tbl (cid,ser_id,sid,booking_price,booking_status,payment_status) values (?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, bmodel.getCid());
			pst.setInt(2, bmodel.getSer_id());
			pst.setInt(3, bmodel.getSid());
			pst.setInt(4, bmodel.getSprice());
			pst.setString(5, bmodel.getBooking_status());
			pst.setString(6, bmodel.getPayment_status());
			pst.executeUpdate();
			System.out.println("Service Booked");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// check all pending services
	public static boolean chkpendingstatus(int cid, int sid) {
		boolean flag = false;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from bookservices_tbl where cid = ? and sid = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, sid);
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

	// all pending services on customer side
	public static List<BookingServicesModel> getallpendingservices(int id) {
		List<BookingServicesModel> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from bookservices_tbl where cid = ? and booking_status = 'pending'";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rSet = pst.executeQuery();
			while (rSet.next()) {
				BookingServicesModel bmodel = new BookingServicesModel();
				bmodel.setBid(rSet.getInt("bid"));
				bmodel.setCid(rSet.getInt("cid"));
				bmodel.setSid(rSet.getInt("sid"));
				bmodel.setSprice(rSet.getInt("booking_price"));
				bmodel.setBooking_status(rSet.getString("booking_status"));
				bmodel.setPayment_status(rSet.getString("payment_status"));
				list.add(bmodel);
			}
			System.out.println("Done");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	// all rejected services on customer side
	public static List<BookingServicesModel> getrejectedservices(int cid) {
		List<BookingServicesModel> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from bookservices_tbl where cid = ? and booking_status = 'reject'";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rSet = pst.executeQuery();
			while (rSet.next()) {
				BookingServicesModel bmodel = new BookingServicesModel();
				bmodel.setBid(rSet.getInt("bid"));
				bmodel.setCid(rSet.getInt("cid"));
				bmodel.setSid(rSet.getInt("sid"));
				bmodel.setSprice(rSet.getInt("booking_price"));
				bmodel.setBooking_status(rSet.getString("booking_status"));
				bmodel.setPayment_status(rSet.getString("payment_status"));
				list.add(bmodel);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	// all pending service on service-man side
	public static List<BookingServicesModel> getallpendingservicemanservices(int ser_id) {
		List<BookingServicesModel> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from bookservices_tbl where ser_id = ? and booking_status = 'pending'";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, ser_id);
			ResultSet rSet = pst.executeQuery();
			while (rSet.next()) {
				BookingServicesModel bmodel = new BookingServicesModel();
				bmodel.setBid(rSet.getInt("bid"));
				bmodel.setCid(rSet.getInt("cid"));
				bmodel.setSid(rSet.getInt("sid"));
				bmodel.setSprice(rSet.getInt("booking_price"));
				bmodel.setBooking_status(rSet.getString("booking_status"));
				bmodel.setPayment_status(rSet.getString("payment_status"));
				list.add(bmodel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	// to update pending status to confirm
	public static void updatepedingstatus(int bid) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "update bookservices_tbl set booking_status = 'confirm' where bid = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, bid);
			pst.executeUpdate();
			System.out.println("Status is Confirmed");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to reject services
	public static void rejectservices(int bid) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "update bookservices_tbl set booking_status = 'reject'";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, bid);
			pst.executeUpdate();
			System.out.println("Status is Rejected");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// get confirm service on customer-side
	public static List<BookingServicesModel> getconfirmservicesbycid(int cid){
		List<BookingServicesModel> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from bookservices_tbl where cid = ? and booking_status = 'confirm'";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rSet = pst.executeQuery();
			while(rSet.next()) {
				BookingServicesModel bModel = new BookingServicesModel();
				bModel.setBid(rSet.getInt("bid"));
				bModel.setCid(rSet.getInt("cid"));
				bModel.setSer_id(rSet.getInt("ser_id"));
				bModel.setSid(rSet.getInt("sid"));
				bModel.setSprice(rSet.getInt("booking_price"));
				bModel.setBooking_status(rSet.getString("booking_status"));
				bModel.setPayment_status(rSet.getString("payment_status"));
				list.add(bModel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	// update pending to done payment status
	public static void donepayment(int bid) {
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "update bookservices_tbl set payment_status = 'done' where bid = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, bid);
			pst.executeUpdate();
			System.out.println("Payment is Done");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//get customer-id for payment
	public static BookingServicesModel getcustomerid(int bid) {
		BookingServicesModel bmodel = null;
		try {
			Connection connection = DbConnection.connectionmethod();
			String sql = "select * from bookservices_tbl where bid = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, bid);
			ResultSet rSet = pst.executeQuery();
		    if(rSet.next()) {
		    	bmodel = new BookingServicesModel();
		    	bmodel.setBid(rSet.getInt("bid"));
				bmodel.setCid(rSet.getInt("cid"));
				bmodel.setSer_id(rSet.getInt("ser_id"));
				bmodel.setSid(rSet.getInt("sid"));
				bmodel.setSprice(rSet.getInt("booking_price"));
				bmodel.setBooking_status(rSet.getString("booking_status"));
				bmodel.setPayment_status(rSet.getString("payment_status"));
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bmodel;
	}
	
	//check done payment status
	public static boolean chkdonepaymentstatus(int bid) {
		boolean flag = false;
		try {
			Connection connection =DbConnection.connectionmethod();
			String sql = "select * from bookservices_tbl where bid = ? and payment_status = 'done'";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, bid);
			ResultSet rSet = pStatement.executeQuery();
			if(rSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	
}
