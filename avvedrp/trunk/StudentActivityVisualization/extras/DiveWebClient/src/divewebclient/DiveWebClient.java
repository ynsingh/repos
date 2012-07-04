/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebclient;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DiveWebClient {
   
    public static void main(String[] args) throws Exception 
    {
        Properties properties = new Properties();
	properties.load(new FileInputStream("/home/sathyaraj/server.properties"));
	String sname = properties.getProperty("sname");
	System.out.println(sname);
        RPCServiceClient client = new RPCServiceClient();
        Options opts = new Options();
        opts.setAction("ns:doBinaryWithByteArray");
        EndpointReference to = new EndpointReference("http://"+sname+"/axis2/services/ImportData");     
        opts.setTo(to);
        EndpointReference targetEPR = new EndpointReference("http://"+sname+"/axis2/services/ImportData.ImportDataHttpEndpoint/");
        client.setOptions(opts);
        DataHandler dh = new DataHandler(new FileDataSource("/home/sathyaraj/books.xml"));
        client.invokeRobust(new QName("http://divewebservice", "doBinaryWithDataHandler"),new Object[] { dh });
     }    
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebclient;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DiveWebClient {
   
    public static void main(String[] args) throws Exception 
    {
        Properties properties = new Properties();
	properties.load(new FileInputStream("/home/sathyaraj/server.properties"));
	String sname = properties.getProperty("sname");
	System.out.println(sname);
        RPCServiceClient client = new RPCServiceClient();
        Options opts = new Options();
        opts.setAction("ns:doBinaryWithByteArray");
        EndpointReference to = new EndpointReference("http://"+sname+"/axis2/services/ImportData");     
        opts.setTo(to);
        EndpointReference targetEPR = new EndpointReference("http://"+sname+"/axis2/services/ImportData.ImportDataHttpEndpoint/");
        client.setOptions(opts);
        DataHandler dh = new DataHandler(new FileDataSource("/home/sathyaraj/books.xml"));
        client.invokeRobust(new QName("http://divewebservice", "doBinaryWithDataHandler"),new Object[] { dh });
     }    
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebclient;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DiveWebClient {
   
    public static void main(String[] args) throws Exception 
    {
        Properties properties = new Properties();
	properties.load(new FileInputStream("/home/sathyaraj/server.properties"));
	String sname = properties.getProperty("sname");
	System.out.println(sname);
        RPCServiceClient client = new RPCServiceClient();
        Options opts = new Options();
        opts.setAction("ns:doBinaryWithByteArray");
        EndpointReference to = new EndpointReference("http://"+sname+"/axis2/services/ImportData");     
        opts.setTo(to);
        EndpointReference targetEPR = new EndpointReference("http://"+sname+"/axis2/services/ImportData.ImportDataHttpEndpoint/");
        client.setOptions(opts);
        DataHandler dh = new DataHandler(new FileDataSource("/home/sathyaraj/books.xml"));
        client.invokeRobust(new QName("http://divewebservice", "doBinaryWithDataHandler"),new Object[] { dh });
     }    
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebclient;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DiveWebClient {
   
    public static void main(String[] args) throws Exception 
    {
        Properties properties = new Properties();
	properties.load(new FileInputStream("/home/sathyaraj/server.properties"));
	String sname = properties.getProperty("sname");
	System.out.println(sname);
        RPCServiceClient client = new RPCServiceClient();
        Options opts = new Options();
        opts.setAction("ns:doBinaryWithByteArray");
        EndpointReference to = new EndpointReference("http://"+sname+"/axis2/services/ImportData");     
        opts.setTo(to);
        EndpointReference targetEPR = new EndpointReference("http://"+sname+"/axis2/services/ImportData.ImportDataHttpEndpoint/");
        client.setOptions(opts);
        DataHandler dh = new DataHandler(new FileDataSource("/home/sathyaraj/books.xml"));
        client.invokeRobust(new QName("http://divewebservice", "doBinaryWithDataHandler"),new Object[] { dh });
     }    
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebclient;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DiveWebClient {
   
    public static void main(String[] args) throws Exception 
    {
        Properties properties = new Properties();
	properties.load(new FileInputStream("/home/sathyaraj/server.properties"));
	String sname = properties.getProperty("sname");
	System.out.println(sname);
        RPCServiceClient client = new RPCServiceClient();
        Options opts = new Options();
        opts.setAction("ns:doBinaryWithByteArray");
        EndpointReference to = new EndpointReference("http://"+sname+"/axis2/services/ImportData");     
        opts.setTo(to);
        EndpointReference targetEPR = new EndpointReference("http://"+sname+"/axis2/services/ImportData.ImportDataHttpEndpoint/");
        client.setOptions(opts);
        DataHandler dh = new DataHandler(new FileDataSource("/home/sathyaraj/books.xml"));
        client.invokeRobust(new QName("http://divewebservice", "doBinaryWithDataHandler"),new Object[] { dh });
     }    
    
}