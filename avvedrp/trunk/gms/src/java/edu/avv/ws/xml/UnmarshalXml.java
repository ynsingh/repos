package edu.avv.ws.xml;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import edu.avv.util.GetConsole;
import edu.avv.ws.xml.request.WSRequest;
import edu.avv.ws.xml.response.WSResponse;

public class UnmarshalXml {
  
  private static WSRequest wsrequest;
  private static WSResponse wsresponse;
	
  public static WSRequest unmarshalRequest(InputStream in){
   	try {
	  SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	  Schema schema = factory.newSchema(GetSchema.getFile("edu/avv/ws/schema/WSRequest.xsd"));
	  JAXBContext context = JAXBContext.newInstance(WSRequest.class);
	  Unmarshaller um = context.createUnmarshaller();
	  um.setSchema(schema);
	  GetConsole.info("Unmarshalling XML Request");	
	  wsrequest = (WSRequest) um.unmarshal(in);
	} catch (SAXException e) {
	  GetConsole.info("XML Parsing Error");	
	  e.printStackTrace();
	} catch (JAXBException e) {
	  GetConsole.info("XML Request Fails To Satisfy WSRequest.xsd");	
	  e.printStackTrace();
	}
    return wsrequest;
  }
  
  public static WSResponse unmarshalResponse(InputStream in){
    try {
	  SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	  Schema schema = factory.newSchema(GetSchema.getFile("edu/avv/ws/schema/WSResponse.xsd"));
	  JAXBContext context = JAXBContext.newInstance(WSResponse.class);
	  Unmarshaller um = context.createUnmarshaller();
	  um.setSchema(schema);
	  GetConsole.info("Unmarshalling XML Response");
	  wsresponse = (WSResponse) um.unmarshal(in);
	} catch (SAXException e) {
	  GetConsole.info("XML Parsing Error");	
	  e.printStackTrace();
	} catch (JAXBException e) {
	  GetConsole.info("XML Response Fails To Satisfy WSResponse.xsd");	
	  e.printStackTrace();
	} 
    return wsresponse;
  }

}
