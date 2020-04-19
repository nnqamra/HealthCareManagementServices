package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	/// Connect to DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/payment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readItems() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\\\"1\\\"><tr><th>Payment ID</th><th>Doctor Name</th><th>Patient Name</th><th>Doctor Charges</th><th>Booking Charges</th><th>Hospital Charges</th><th>Pharmeasy Bill</th><th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentId = Integer.toString(rs.getInt("paymentId"));
				String docName = rs.getString("docName");
				String patiName = rs.getString("patiName");
				String docCharges = Double.toString(rs.getDouble("docCharges"));
				String booknCharges = Double.toString(rs.getDouble("booknCharges"));
				String hosptlCharges = Double.toString(rs.getDouble("hosptlCharges"));
				String pharmBill = Double.toString(rs.getDouble("pharmeasyBill"));

				// Add into the html table
				// output += "<tr><td>" + itemID + "</td>";
				output += "<tr><td>" + paymentId + "</td>";
				output += "<td>" + docName + "</td>";
				output += "<td>" + patiName + "</td>";
				output += "<td>" + docCharges + "</td>";
				output += "<td>" + booknCharges + "</td>";
				output += "<td>" + hosptlCharges + "</td>";
				output += "<td>" + pharmBill + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\" btnUpdate btn btn-secondary\"></td> <td><form method=\"post\" action=\"docter.jsp\">  <input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">  <input name=\"hidItemIDDelete\" type=\"hidden\" value=\""
						+ paymentId + "\">" + "</form></td>      </tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}

		return output;

	}

	public String insertPayment(String docName, String patiName, String docCharges, String booknCharges,
			String hosptlCharges, String pharmBill) {

		String output = "";
		
		
		try {

			Connection con = connect();

			if (con == null) {

				return "DB Insert error.";
			}
			
			// create a prepared statement
			String query = "insert into payment values(?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, docName);
			preparedStmt.setString(3, patiName);
			preparedStmt.setDouble(4, Double.parseDouble(docCharges));
			preparedStmt.setDouble(5, Double.parseDouble(booknCharges));
			preparedStmt.setDouble(6, Double.parseDouble(hosptlCharges));
			preparedStmt.setDouble(7, Double.parseDouble(pharmBill));

			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
			System.out.println("not insert");
		}

		return output;

	}

	public String deletePayment(String paymentId) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where paymentId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the appointment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
