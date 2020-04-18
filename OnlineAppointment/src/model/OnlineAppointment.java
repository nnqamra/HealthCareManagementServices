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
					+ "values(NULL, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmnt = con.prepareStatement(addAppQuery);

			pstmnt.setInt(1, 0);
			pstmnt.setDate(1, date);
			pstmnt.setString(2, time);
			pstmnt.setString(3, desc);
			pstmnt.setInt(4, pid);
			pstmnt.setInt(5, did);
			pstmnt.setInt(6, hid);

			pstmnt.execute();
			return "Appointment added successfully";

		} catch (SQLException e) {
			return "Error occured during adding an Appointment\n" + e.getMessage();
		}

	}


}
