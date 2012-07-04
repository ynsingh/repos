/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;

public class GenerateXml {
    
    
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        
        ArrayList<Master> rowdata = new ArrayList<Master>();

		// create books
		Master row1 = new Master();
		row1.setLmsid("1");
                row1.setAction("action1");
                row1.setCoursename("Chemistry");
                row1.setDate("12-12-2012");
                row1.setModulename("Chemistry1");
                row1.setUsername("user1");
                row1.setInstid("INST1");
                row1.setUsertype("admin1");
		rowdata.add(row1);

		Master row2 = new Master();
		row2.setLmsid("1");
                row2.setAction("action1");
                row2.setCoursename("Physics");
                row2.setDate("12-12-2012");
                row2.setModulename("Chemistry1");
                row2.setUsername("user1");
                row2.setInstid("INST1");
                row2.setUsertype("admin1");
		rowdata.add(row1);

		
		Table allrows = new Table();	
		allrows.setRowData(rowdata);

		// create JAXB context and instantiate marshaller
                try
                {
                    JAXBContext context = JAXBContext.newInstance(Table.class);
                    Marshaller m = context.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);	
                    StringWriter oWriter = new StringWriter();
                    m.marshal(allrows,oWriter) ;
                    System.out.println(oWriter.toString());
                }
                catch(Exception e)
                {
                    
                }
	
		
    }
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;

public class GenerateXml {
    
    
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        
        ArrayList<Master> rowdata = new ArrayList<Master>();

		// create books
		Master row1 = new Master();
		row1.setLmsid("1");
                row1.setAction("action1");
                row1.setCoursename("Chemistry");
                row1.setDate("12-12-2012");
                row1.setModulename("Chemistry1");
                row1.setUsername("user1");
                row1.setInstid("INST1");
                row1.setUsertype("admin1");
		rowdata.add(row1);

		Master row2 = new Master();
		row2.setLmsid("1");
                row2.setAction("action1");
                row2.setCoursename("Physics");
                row2.setDate("12-12-2012");
                row2.setModulename("Chemistry1");
                row2.setUsername("user1");
                row2.setInstid("INST1");
                row2.setUsertype("admin1");
		rowdata.add(row1);

		
		Table allrows = new Table();	
		allrows.setRowData(rowdata);

		// create JAXB context and instantiate marshaller
                try
                {
                    JAXBContext context = JAXBContext.newInstance(Table.class);
                    Marshaller m = context.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);	
                    StringWriter oWriter = new StringWriter();
                    m.marshal(allrows,oWriter) ;
                    System.out.println(oWriter.toString());
                }
                catch(Exception e)
                {
                    
                }
	
		
    }
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;

public class GenerateXml {
    
    
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        
        ArrayList<Master> rowdata = new ArrayList<Master>();

		// create books
		Master row1 = new Master();
		row1.setLmsid("1");
                row1.setAction("action1");
                row1.setCoursename("Chemistry");
                row1.setDate("12-12-2012");
                row1.setModulename("Chemistry1");
                row1.setUsername("user1");
                row1.setInstid("INST1");
                row1.setUsertype("admin1");
		rowdata.add(row1);

		Master row2 = new Master();
		row2.setLmsid("1");
                row2.setAction("action1");
                row2.setCoursename("Physics");
                row2.setDate("12-12-2012");
                row2.setModulename("Chemistry1");
                row2.setUsername("user1");
                row2.setInstid("INST1");
                row2.setUsertype("admin1");
		rowdata.add(row1);

		
		Table allrows = new Table();	
		allrows.setRowData(rowdata);

		// create JAXB context and instantiate marshaller
                try
                {
                    JAXBContext context = JAXBContext.newInstance(Table.class);
                    Marshaller m = context.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);	
                    StringWriter oWriter = new StringWriter();
                    m.marshal(allrows,oWriter) ;
                    System.out.println(oWriter.toString());
                }
                catch(Exception e)
                {
                    
                }
	
		
    }
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;

public class GenerateXml {
    
    
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        
        ArrayList<Master> rowdata = new ArrayList<Master>();

		// create books
		Master row1 = new Master();
		row1.setLmsid("1");
                row1.setAction("action1");
                row1.setCoursename("Chemistry");
                row1.setDate("12-12-2012");
                row1.setModulename("Chemistry1");
                row1.setUsername("user1");
                row1.setInstid("INST1");
                row1.setUsertype("admin1");
		rowdata.add(row1);

		Master row2 = new Master();
		row2.setLmsid("1");
                row2.setAction("action1");
                row2.setCoursename("Physics");
                row2.setDate("12-12-2012");
                row2.setModulename("Chemistry1");
                row2.setUsername("user1");
                row2.setInstid("INST1");
                row2.setUsertype("admin1");
		rowdata.add(row1);

		
		Table allrows = new Table();	
		allrows.setRowData(rowdata);

		// create JAXB context and instantiate marshaller
                try
                {
                    JAXBContext context = JAXBContext.newInstance(Table.class);
                    Marshaller m = context.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);	
                    StringWriter oWriter = new StringWriter();
                    m.marshal(allrows,oWriter) ;
                    System.out.println(oWriter.toString());
                }
                catch(Exception e)
                {
                    
                }
	
		
    }
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;

public class GenerateXml {
    
    
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        
        ArrayList<Master> rowdata = new ArrayList<Master>();

		// create books
		Master row1 = new Master();
		row1.setLmsid("1");
                row1.setAction("action1");
                row1.setCoursename("Chemistry");
                row1.setDate("12-12-2012");
                row1.setModulename("Chemistry1");
                row1.setUsername("user1");
                row1.setInstid("INST1");
                row1.setUsertype("admin1");
		rowdata.add(row1);

		Master row2 = new Master();
		row2.setLmsid("1");
                row2.setAction("action1");
                row2.setCoursename("Physics");
                row2.setDate("12-12-2012");
                row2.setModulename("Chemistry1");
                row2.setUsername("user1");
                row2.setInstid("INST1");
                row2.setUsertype("admin1");
		rowdata.add(row1);

		
		Table allrows = new Table();	
		allrows.setRowData(rowdata);

		// create JAXB context and instantiate marshaller
                try
                {
                    JAXBContext context = JAXBContext.newInstance(Table.class);
                    Marshaller m = context.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);	
                    StringWriter oWriter = new StringWriter();
                    m.marshal(allrows,oWriter) ;
                    System.out.println(oWriter.toString());
                }
                catch(Exception e)
                {
                    
                }
	
		
    }
    
}
