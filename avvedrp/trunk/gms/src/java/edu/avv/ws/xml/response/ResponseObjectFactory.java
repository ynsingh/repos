//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.01.25 at 10:19:26 AM IST 
//


package edu.avv.ws.xml.response;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.avv.ws.xml.response package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ResponseObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.avv.ws.xml.response
     * 
     */
    public ResponseObjectFactory() {
    }

    /**
     * Create an instance of {@link WSResponse.Header }
     * 
     */
    public WSResponse.Header createWSResponseHeader() {
        return new WSResponse.Header();
    }

    /**
     * Create an instance of {@link WSResponse.Body }
     * 
     */
    public WSResponse.Body createWSResponseBody() {
        return new WSResponse.Body();
    }

    /**
     * Create an instance of {@link WSResponse.Body.Message }
     * 
     */
    public WSResponse.Body.Message createWSResponseBodyMessage() {
        return new WSResponse.Body.Message();
    }

    /**
     * Create an instance of {@link WSResponse }
     * 
     */
    public WSResponse createWSResponse() {
        return new WSResponse();
    }

}
