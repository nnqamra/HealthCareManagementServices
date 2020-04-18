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


}
