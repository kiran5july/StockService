package com.km.stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

//import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.Client;
//import org.glassfish.jersey.client.ClientConfig;

import com.sun.jersey.api.client.config.DefaultClientConfig;

public class HelloWorldRESTClient {

        public static void main(String[] args) {
        	
        	//Method#1
        	DefaultClientConfig clientConfig = new DefaultClientConfig();
        	com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(clientConfig);
            WebResource resource = client.resource("http://localhost:8080/StockService/resources/hello/mkclient1");
            // lets get the XML as a String
            String text = resource.accept("application/xml").get(String.class);
            System.out.println("Resp 11111: "+text);

            //javax.ws.rs.client.Client cl2 = ClientBuilder.newClient();
            //WebTarget target = cl2.target("http://localhost:8080");
            //Response response = target.path("StockService").path("resources").path("hello").path("mkclient11").request(MediaType.TEXT_PLAIN_TYPE).get();
            //System.out.println("Resp: " + response.getStatus() + " - " + response.getEntity().toString());   
                
            
                //JAX-RS 2.0 Client API
                //Client client = ClientBuilder.newBuilder().newClient();
                //WebTarget target = client.target("http://localhost:8080/rs");
                //target = target.path("service").queryParam("a", "avalue");
                 
                //Invocation.Builder builder = target.request();
                //Response response = builder.get();
                //Stock stk = builder.get(Stock.class);
                
                //Using Java standard API
            	URL url; 
            	HttpURLConnection httpConn;
                try { 
                	url = new URL("http://localhost:8080/StockService/resources/hello/mkclient2");
                	httpConn = (HttpURLConnection) url.openConnection();
                	httpConn.setDoInput(true); 
                	httpConn.setInstanceFollowRedirects(false); 
                	httpConn.setRequestMethod("GET"); 
                	httpConn.setRequestProperty("Content-Type", "application/xml"); 

                    InputStream ins = httpConn.getInputStream(); 
                    System.out.println("Resp 33333: " + convertStreamToString(ins));
                    
                    httpConn.getResponseCode(); 
                    httpConn.disconnect();
                } catch(Exception e) { 
                    throw new RuntimeException(e); 
                }finally{
               			
                	httpConn = null;
                }
                
                //Rest client API
                WebResource resource2 = client.resource("http://localhost:8080/StockService/resources/hello/mkclient3"); 
                ClientResponse resp = resource2.type("application/xml").get(ClientResponse.class); 
                System.out.println("Resp 44444: "+ resp.getEntity(String.class));
                //System.out.println("Resp: "+ resp.readEntity(String.class));
        }

        static String convertStreamToString(java.io.InputStream is) {
        	String sReturn = "";
        	//java.io.StringWriter writer = new java.io.StringWriter();
        	//org.apache.commons.io.IOUtils.copy(is, writer, encoding);
        	//String theString = writer.toString();
        	
        	//Using scanner *WORKS*
            //java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            //sReturn = s.hasNext() ? s.next() : "";
            
            //BufferedReader *WORKS*
        	BufferedReader br = null;
        	try{
        		br = new BufferedReader( new java.io.InputStreamReader( is ) );
        		StringBuffer text = new StringBuffer();
        		for ( String line; (line = br.readLine()) != null; )
        			text.append( line );
        		sReturn = text.toString();
        	}catch(IOException e){
        		System.out.println("Can't read from internet: "+e.toString());
        	}finally{
        		try{
        			br.close();
        		}catch(IOException e){
        			System.out.println("Can't close the stream:"+e.toString());
        		}
        	}
        	return sReturn;
        }
        
        private static URI getBaseURI() {
                return UriBuilder.fromUri("http://localhost:8080/com.vogella.jersey.first").build();
        }
}