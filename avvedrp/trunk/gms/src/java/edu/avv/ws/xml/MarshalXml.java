package edu.avv.ws.xml;

import java.io.OutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import edu.avv.util.GetConsole;
import edu.avv.ws.xml.request.WSRequest;
import edu.avv.ws.xml.response.WSResponse;

public class MarshalXml {
	
  public static void marshalRequest(WSRequest wsrequest,OutputStream out){
    try {
	  SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	  Schema schema = factory.newSchema(GetSchema.getFile("edu/avv/ws/schema/WSRequest.xsd"));
	  JAXBContext context = JAXBContext.newInstance(WSRequest.class);
	  Marshaller m = context.createMarshaller();
	  m.setSchema(schema);
	  m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	  GetConsole.info("Marshalling XML Request");
	  m.marshal(wsrequest,out);
	} catch (SAXException e) {
	  GetConsole.info("XML Parsing Error");
	  e.printStackTrace();
	} catch (JAXBException e) {
	  GetConsole.info("XML Request Fails To Satisfy WSRequest.xsd");
	  e.printStackTrace();
	}
  }
  
  public static void marshalResponse(WSResponse wsresponse,OutputStream out){
    try { 	
	  SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	  Schema schema = factory.newSchema(GetSchema.getFile("edu/avv/ws/schema/WSResponse.xsd"));
	  JAXBContext context = JAXBContext.newInstance(WSResponse.class);
	  Marshaller m = context.createMarshaller();
	  m.setSchema(schema);
	  m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	  GetConsole.info("Marshalling XML Response");
	  m.marshal(wsresponse,out);
    } catch (SAXException e) {
      GetConsole.info("XML Parsing Error");
      e.printStackTrace();
	} catch (JAXBException e) {
	  GetConsole.info("XML Response Fails To Satisfy WSResponse.xsd");	
	  e.printStackTrace();
	}  
  }
  
}
