
package com.km.stock;

/* //Invocation
 * http://localhost:8080/StockService/resources/hello/mk
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldRESTService {

	@GET
	@Path("/{param}")
	public String sayHello(@PathParam("param") String msg) {
		String output = "Welcome to you " + msg +", our journey to RESTful service.";
		return output;
	
	}

  // This method is called if TEXT_PLAIN is request
  @GET
  @Path("/{param}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response sayPlainTextHello(@PathParam("param") String msg) {
	String output = "Welcome " + msg +", our journey to RESTful service.";
	return Response.status(200).entity(output).build();
  }

  // This method is called if XML is request
  @GET
  @Path("/{param}")
  @Produces(MediaType.TEXT_XML)
  public Response sayXMLHello(@PathParam("param") String msg) {
  String output = "<?xml version=\"1.0\"?>" + "<hello> Welcome " + msg +", our journey to RESTful service.</hello>";
	  return Response.status(200).entity(output).build();
  }

  // This method is called if HTML is request
  @GET
  @Path("/{param}")
  @Produces(MediaType.TEXT_HTML)
  public Response sayHtmlHello(@PathParam("param") String msg) {
  String output = "<html> " + "<title>" + "Hello Jersey" + "</title>"
+ "<body><h1>Welcome " + msg +", </h1> to our journey to RESTful service.</body>"+"</html>";
	  return Response.status(200).entity(output).build();
  }

	  
}