package com;

import java.sql.Date;
import java.text.SimpleDateFormat;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.OnlineAppointment;

@Path("/Appointments")

public class OnlineAppointmentService {

	OnlineAppointment App1 = new OnlineAppointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadAppointment() {

		return App1.ReadAppointment();

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAppointment(@FormParam("date") Date date, @FormParam("time") String time,
			@FormParam("apmnt_desc") String desc, @FormParam("patient_id") int pid, @FormParam("doctor_id") int did,
			@FormParam("hospital_id") int hid) {
		String output = App1.addAppointment(date, time, desc, pid, did, hid);
		return output;
	}

	@PUT
	@Path("/App1/{apmnt_id}/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateAppointment(

			@FormParam("date") Date date, @FormParam("time") String time, @FormParam("apmnt_desc") String desc,
			@PathParam("apmnt_id") int AppID) {
		String output = App1.UpdateAppointment(date, time, desc, AppID);
		System.out.println(AppID);
		return output;
	}

	@DELETE
	@Path("/delete/App1/{apmnt_id}/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteAppointment(@PathParam("apmnt_id") int AppID) {

		// Read the value from the element <AppID>
		String output = App1.DeleteAppointment(AppID);
		return output;

	}

}
