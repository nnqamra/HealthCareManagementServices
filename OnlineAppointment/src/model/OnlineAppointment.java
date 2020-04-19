package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import config.DBConnector;

public class OnlineAppointment {

	public String addAppointment(Date date, String time, String desc, int pid, int did, int hid) {

		try (Connection con = DBConnector.getConnection()) {

			String addAppQuery = " insert into appointment (`apmnt_id`,`date`,`time`,`apmnt_desc`,`patient_id`,`doctor_id`,`hospital_id`)"
					+ "values(?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmnt = con.prepareStatement(addAppQuery);

			pstmnt.setInt(1, 0);
			pstmnt.setDate(2, date);
			pstmnt.setString(3, time);
			pstmnt.setString(4, desc);
			pstmnt.setInt(5, pid);
			pstmnt.setInt(6, did);
			pstmnt.setInt(7, hid);

			pstmnt.execute();
			return "Appointment added successfully";

		} catch (SQLException e) {
			return "Error occured during adding an Appointment\n" + e.getMessage();
		}

	}

	public String ReadAppointment() {

		try (Connection con = DBConnector.getConnection()) {

			LocalDate prvPaymentDate = null;
			String readQuery = "select * from appointment";

			PreparedStatement pstmt = con.prepareStatement(readQuery);

			String output = "<table border=\"1\"><tr><th>Appointment ID</th>" + "<th>Appointment Date</th> "
					+ "<th>Appointment Time</th>" + "<th>Appointment Description</th>" + "<th>Patient ID</th>"
					+ "<th>Doctor ID</th>" + "<th>Hospital ID</th>" + "<th>Update</th><th>Remove</th></tr>";

			ResultSet rs = pstmt.executeQuery(readQuery);

			while (rs.next()) {
				int AppID = rs.getInt("apmnt_id");
				Date date = rs.getDate("date");
				String time = rs.getString("time");
				String desc = rs.getString("apmnt_desc");
				int pid = rs.getInt("patient_id");
				int did = rs.getInt("doctor_id");
				int hid = rs.getInt("hospital_id");

				output += "<tr><td>" + AppID + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + desc + "</td>";
				output += "<td>" + pid + "</td>";
				output += "<td>" + did + "</td>";
				output += "<td>" + hid + "</td>";

				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + AppID + "\">" + "</form></td></tr>";

			}

			output += "</table>";
			return output;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Error occured during retrieving data";
		}
	}

	public String UpdateAppointment(Date date, String time, String desc, int AppID) {

		try (Connection con = DBConnector.getConnection()) {

			String updateAppQuery = "UPDATE appointment SET date=?,time=?,desc=? WHERE apmnt_id=?";
			PreparedStatement pstmnt = con.prepareStatement(updateAppQuery);
			pstmnt.setDate(1, date);
			pstmnt.setString(2, time);
			pstmnt.setString(3, desc);
			pstmnt.setInt(4, AppID);

			System.out.println(pstmnt.toString());
			pstmnt.execute();
			return "Appointment Updated successfully";

		} catch (SQLException e) {
			return "Error occured during Updating an Appointment\n" + e.getMessage();
		}

	}

	public String DeleteAppointment(int AppID) {

		try (Connection con = DBConnector.getConnection()) {

			// create a prepared statement
			String Deletequery = "delete from appointment where apmnt_id=?";

			PreparedStatement pstmnt = con.prepareStatement(Deletequery);
			pstmnt.setInt(1, AppID);
			pstmnt.execute();
			return "Appoinment Deleted successfully";

		} catch (SQLException e) {

			return "Error occurrd during Deleting\n" + e.getMessage();
		}

	}

}
