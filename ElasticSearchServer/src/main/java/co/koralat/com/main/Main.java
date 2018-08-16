package co.koralat.com.main;

import javax.xml.ws.Endpoint;

import co.koralat.com.ws.CrudContactoImpl;

public class Main 
{
		 public static void main(String[]a)
		  {
			   try
			   {
				   Endpoint.publish("http://localhost:8086/ws/CrudContactoImpl", new CrudContactoImpl());
			   }catch(Exception e)
			   {
				   e.printStackTrace();
			   }
		   }
}
