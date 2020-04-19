package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital_Model {

	public Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremanagement?serverTimezone=UTC",
					"root", "");
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String view_hospital() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = " <table border=\"1\"><tr><th>ID</th><th>Hospital Name</th><th>Hospital Address</th>"
					+ "<th>Hospital Email</th><th>Hospital Phone</th><th>Hospital Charge</th></tr>";

			String query = "select * from hospital";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String id = Integer.toString(rs.getInt("hospital_id"));
				String name = rs.getString("hospital_name");
				String address = rs.getString("hospital_address");
				String email = rs.getString("hospital_email");
				String phone = rs.getString("hospital_phone");
				String charge = Double.toString(rs.getDouble("hospital_charge"));

				
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + charge + "</td></tr>";
	
			}
			con.close();
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insert_hospital(String name, String address, String email, String phone, String charge) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
	
			String query = " insert into hospital(hospital_id,hospital_name,hospital_address,hospital_email,hospital_phone,hospital_charge) values(?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, phone);
			preparedStmt.setDouble(6, Double.parseDouble(charge));

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String update_hospital(String id, String name, String address, String email, String phone, String charge) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE hospital SET hospital_name=?,hospital_address=?,hospital_email=?,hospital_phone=?,hospital_charge=? WHERE hospital_id=?";
			
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setString(1, name);
			preparedStmnt.setString(2, address);
			preparedStmnt.setString(3, email);
			preparedStmnt.setString(4, phone);
			preparedStmnt.setDouble(5, Double.parseDouble(charge));
			
			preparedStmnt.setInt(6, Integer.parseInt(id));
			
			preparedStmnt.execute();
			con.close();
			
			output="Updated Successfully";
		}catch(Exception e) {
			output="error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String delete_hospital(String id){
		String output = "";
	try{
			Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for deleting.";
		 }
		
		 String query = "delete from hospital where hospital_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 preparedStmt.setInt(1, Integer.parseInt(id));
	
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
	catch (Exception e)
	 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
}
