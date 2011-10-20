///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.myapp.struts.admin;
//import java.io.File;
//import org.iso_relax.verifier.Schema;
//import org.iso_relax.verifier.Verifier;
//import org.iso_relax.verifier.VerifierFactory;
//import org.iso_relax.verifier.VerifierHandler;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.SAXWriter;
//import org.xml.sax.ErrorHandler;
//import org.xml.sax.SAXParseException;
//public class XMLCheckXSD {
//private String schemaURI;
//private Document document;
//public XMLCheckXSD(Document document, String schemaURI) {
//this.schemaURI = schemaURI;
//this.document = document;
//}
//public boolean validate() throws Exception {
//VerifierFactory factory = new com.sun.msv.verifier.jarv.TheFactoryImpl();
//Schema schema = factory.compileSchema(schemaURI);
//Verifier verifier = schema.newVerifier();
//verifier.setErrorHandler(new ErrorHandler() {
//public void error(SAXParseException saxParseEx) {
//System.out.println("ERROR :: ");
//saxParseEx.printStackTrace();
//}
//public void fatalError(SAXParseException saxParseEx) {
//System.out.println("FATAL ERROR :: ");
//saxParseEx.printStackTrace();
//}
//public void warning(SAXParseException saxParseEx) {
//System.out.println("WARNING :: ");
//saxParseEx.printStackTrace();
//}
//});
//VerifierHandler handler = verifier.getVerifierHandler();
//SAXWriter writer = new SAXWriter(handler);
//writer.write(document);
//return handler.isValid();
//}
//public static void main(String []args) throws DocumentException{
//SAXReader reader=new SAXReader();
//Document document=reader.read(new File("src\\holiday-request.xml"));
//ValidateXMLUsingXSD val=new ValidateXMLUsingXSD(document,"src\\hr.xsd");
//try {
//System.out.println("Result :: "+val.validate());
//if(val.validate()){
//System.out.println("XML IS COMPATIBLE WITH XSD SCHEMA");
//}
//} catch (Exception e) {
//System.out.println("0");
//e.printStackTrace();
//}
//}
//}
