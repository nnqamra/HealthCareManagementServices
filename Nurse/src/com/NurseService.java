package com;

import model.Nurse_model;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/nurse")
public class NurseService
{
	Nurse_model n_obj = new Nurse_model();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String view_nurse()
	{
	return n_obj.view_nurse();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String add_nurse(@FormParam("f_name") String f_name,
	 @FormParam("l_name") String l_name,
	 @FormParam("nic") String nic,
	 @FormParam("address") String address,
	 @FormParam("email") String email,
	 @FormParam("phone") String phone)
	{
	 String output = n_obj.add_nurse(f_name, l_name, nic, address, email, phone);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update_nurse(String data)
	{
	//Convert the input string to a JSON object
	 JsonObject n_object = new JsonParser().parse(data).getAsJsonObject();
	//Read the values from the JSON object
	 String id = n_object.get("id").getAsString();
	 String f_name = n_object.get("f_name").getAsString();
	 String l_name = n_object.get("l_name").getAsString();
	 String nic = n_object.get("nic").getAsString();
	 String address = n_object.get("address").getAsString();
	 String email = n_object.get("email").getAsString();
	 String phone = n_object.get("phone").getAsString();
	
	 String output = n_obj.update_nurse(id, f_name, l_name, nic, address, email, phone);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String remove_nurse(String data)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(data, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = n_obj.remove_nurse(id);
	return output;
	}
	

}
