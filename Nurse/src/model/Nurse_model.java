package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Nurse_model {
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

	
	public String view_nurse() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = " <table border=\"1\"><tr><th>ID</th><th>Hospital Name</th><th>Hospital Address</th>"
					+ "<th>Hospital Email</th><th>Hospital Phone</th><th>Hospital Charge</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from nurse";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("n_id"));
				String f_name = rs.getString("n_fName");
				String l_name = rs.getString("n_lName");
				String nic = rs.getString("n_NIC");
				String address = rs.getString("n_address");
				String email = rs.getString("n_email");
				String phone = rs.getString("n_phone");
				

				output += "<tr><td>" + id + "</td>";
				output += "<td>" + f_name + "</td>";
				output += "<td>" + l_name + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";

		
				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\">" + "<input name=\"itemID\" type=\"hidden\" " + " value=\""
						+ id + "\">" + "</form></td></tr>";
			}

			con.close();
		
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String add_nurse(String f_name, String l_name, String nic, String address, String email, String phone) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
		
			String query = " insert into nurse(n_id, n_fName,n_lName,n_NIC,n_address,n_email,n_phone) values(?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, f_name);
			preparedStmt.setString(3, l_name);
			preparedStmt.setString(4, nic);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, phone);
		
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String update_nurse(String id, String f_name, String l_name, String nic, String address, String email, String phone) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE nurse SET n_fName=?,n_lName=?,n_NIC=?,n_address=?,n_email=?,n_phone=? WHERE n_id=?";
			
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setString(1, f_name);
			preparedStmnt.setString(2, l_name);
			preparedStmnt.setString(3, nic);
			preparedStmnt.setString(4, address);
			preparedStmnt.setString(5, email);
			preparedStmnt.setString(6, phone);
			
			
			preparedStmnt.setInt(7, Integer.parseInt(id));
			
			preparedStmnt.execute();
			con.close();
			
			output="Updated Successfully";
		}catch(Exception e) {
			output="error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String remove_nurse(String id){
		String output = "";
	try{
			Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for deleting.";
		 }
		 
		 String query = "delete from nurse where n_id=?"; 
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
