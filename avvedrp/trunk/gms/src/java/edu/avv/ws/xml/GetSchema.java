package edu.avv.ws.xml;

import javax.xml.transform.stream.StreamSource;

import edu.avv.util.GetConsole;

public class GetSchema {

  public static StreamSource getFile(String fileName){
	GetConsole.info("Reading XML Schema "+fileName);  
    return new StreamSource(GetSchema.class.getClassLoader().getResourceAsStream(fileName));	  
  }

}
