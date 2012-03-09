/*
 * JangoMail_API.java
 * 
 * This example code shows how to use the JangoMail/JangoSMTP API from a java application.
 * 
 */

// these are used to create, send and receive soap requests
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.MimeHeaders;

// these are used to parse the response
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

public class JangoMail_API_Test {

	public static void main(String[] args) {
		try{
			
			// create the connection
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();

			//Next, create the actual message/request
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();
	         
			//Create objects for the request parts            
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPBody body = envelope.getBody();
		
			// set the SOAPAction Header to the desired request
			MimeHeaders headers = message.getMimeHeaders();
			headers.addHeader("SOAPAction", "http://api.jangomail.com/Groups_GetList_String");
			
			// Populate the body of the request
			// create the main element and namespace
			SOAPElement bodyElement = body.addChildElement(envelope.createName("Groups_GetList_String", "", "http://api.jangomail.com/"));
			
			// add the parameters
			bodyElement.addChildElement("Username").addTextNode("Your JangoMail/JangoSMTP Username");
			bodyElement.addChildElement("Password").addTextNode("Your JangoMail/JangoSMTP Password");
			bodyElement.addChildElement("RowDelimiter").addTextNode("\n");
			bodyElement.addChildElement("ColDelimiter").addTextNode(" - ");
			bodyElement.addChildElement("TextQualifier").addTextNode("");
			
			// save the message
			message.saveChanges();
			
			// check the input
			System.out.println("\nREQUEST:\n");
			message.writeTo(System.out);
			System.out.println();
			
			// Send the message and print the reply
			// set the destination
			String destination = "http://api.jangomail.com/api.asmx";
			// send the message
			SOAPMessage reply = connection.call(message, destination);

	        	//Check the output
		        System.out.println("\nRESPONSE:\n");

		        //Create the transformer
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer = transformerFactory.newTransformer();

		        //Extract the content of the reply
		        Source sourceContent = reply.getSOAPPart().getContent();

		        //Set the output for the transformation
		        StreamResult result = new StreamResult(System.out);
		        transformer.transform(sourceContent, result);
		        System.out.println();
			
			// close the connection
			connection.close();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
