package com;

import model.Hospital_Model;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Hospital")
public class HospitalService
{
	Hospital_Model hospitalObj = new Hospital_Model();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String view_hospital()
	{
	return hospitalObj.view_hospital();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insert_hospital(@FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("email") String email,
	 @FormParam("phone") String phone,
	 @FormParam("charge") String charge)
	{
	 String output = hospitalObj.insert_hospital(name, address, email, phone, charge);
	return output;
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update_hospital(String hospitalData)
	{
	 JsonObject hospitalObject = new JsonParser().parse(hospitalData).getAsJsonObject();

	 String id = hospitalObject.get("id").getAsString();
	 String name = hospitalObject.get("name").getAsString();
	 String address = hospitalObject.get("address").getAsString();
	 String email = hospitalObject.get("email").getAsString();
	 String phone = hospitalObject.get("phone").getAsString();
	 String charge = hospitalObject.get("charge").getAsString();
	
	 String output = hospitalObj.update_hospital(id, name, address, email, phone, charge);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete_hospital(String hospitalData)
	{

	 Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());

	 String id = doc.select("id").text();
	 String output = hospitalObj.delete_hospital(id);
	return output;
	}


}
