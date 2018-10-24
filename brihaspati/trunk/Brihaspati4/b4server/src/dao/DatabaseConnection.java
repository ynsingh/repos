package dao;

import java.io.EOFException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import server.properties_access;
import sun.misc.BASE64Decoder;

public class DatabaseConnection {

	
	
	public static void toDb(String otp, String emailid, String ccert) throws ClassNotFoundException, SQLException  {
		// TODO Auto-generated method stub
		
		try
	    {
	      // create a mysql database connection
	      //String myDriver = properties_access.read_property("server.properties", "database_driver");
	      //String myUrl = properties_access.read_property("server.properties", "database_url");
	      //Class.forName(myDriver);
	      Connection conn = connect_database();
	      
	      Statement st = conn.createStatement();

	      // note that i'm leaving "date_created" out of this insert statement
	      String Query="INSERT INTO otp  (otp, emailid, ccert) VALUES ( '" + otp + "','" + emailid + "','" + ccert + "')" ;
	      System.out.println("The query goes to db "+Query);
	      st.executeUpdate("INSERT INTO otp  (otp, emailid, ccert) VALUES ( '" + otp + "','" + emailid + "','" + ccert + "')") ;

	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	}
		public static String fromDb(String emailid) throws ClassNotFoundException, SQLException  {
		// TODO Auto-generated method stub
			String OtpFromDb = null;
			try
		    {
		      // create a mysql database connection
		      //String myDriver = "com.mysql.jdbc.Driver";
		      //String myUrl = "jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false";
		      //Class.forName(myDriver);
		      //Connection conn = DriverManager.getConnection(myUrl, "root", "ic64276x");
				Connection conn = connect_database();
		      Statement st = conn.createStatement();


		      // to store otp to database
		      //System.out.println(sql);
		      ResultSet rs = st.executeQuery("SELECT otp FROM otp where emailid = '" + emailid + "'");
		      while(rs.next())	      
		      {
		    	  OtpFromDb=rs.getString("otp");
		    	  //System.out.println(OtpFromDb);
		    	  //conn.close();
		    	  
		      } 
		      conn.close(); 
		      
		      
		    }
		      catch (Exception e)
			    {
			      System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			    }
				return OtpFromDb;
		    }
		public static String CertfromDb(String emailid) throws ClassNotFoundException, SQLException  {
			// TODO Auto-generated method stub
				String CertFromDb = null;
				try
			    {
			      // create a mysql database connection
			     /* String myDriver = "com.mysql.jdbc.Driver";
			      String myUrl = "jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false";
			      Class.forName(myDriver);
			      Connection conn = DriverManager.getConnection(myUrl, "root", "ic64276x");
			     */
					Connection conn = connect_database();
			      Statement st = conn.createStatement();


			      // to store otp to database


			     // String sql;
			     // sql = "SELECT otp FROM otp where emailid = \" vijit@iitk.ac.in \" ";
			      //System.out.println(sql);
			      ResultSet rs = st.executeQuery("SELECT ccert FROM otp where emailid = '" + emailid + "'");
			      while(rs.next())	      
			      {
			    	  CertFromDb=rs.getString("ccert");
			    	  //System.out.println(OtpFromDb);
			    	  //conn.close();
			    	  
			      } 
			      conn.close(); 
			      
			      
			    }
			      catch (Exception e)
				    {
				      System.err.println("Got an exception!");
				      System.err.println(e.getMessage());
				    }
					return CertFromDb;
			    }
		public static void keytoDb(String nodeid, String emailid, String privkey, PublicKey pubkey,  long validity, String OrganisationalUnit, String Organisation, String City, String State, String Country, String Alias, char[] keypass) throws ClassNotFoundException, SQLException  {
			// TODO Auto-generated method stub
			
			try
		    {
		      // create a mysql database connection
		      /*String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "ic64276x");
		      */
				Connection conn = connect_database();
		      Statement st = conn.createStatement();

		      // note that i'm leaving "date_created" out of this insert statement
		      st.executeUpdate("INSERT INTO keystore VALUES ( '" + nodeid +"','" + emailid + "','" + privkey + "','" + pubkey +"','" +  validity + "','" + OrganisationalUnit +"','"+ Organisation + "','" + City + "','" + State +"','"+ Country + "','" + Alias + "','" + keypass +"')" ) ;

		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }
		}
		public static PrivateKey PrivkeyfromDb(String emailid) throws ClassNotFoundException, SQLException, EOFException  {
			// TODO Auto-generated method stub
				String privkeyFromDb ;
				PrivateKey priv = null;
				try
			    {
			      // create a mysql database connection
			     /* String myDriver = "com.mysql.jdbc.Driver";
			      String myUrl = "jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false";
			      Class.forName(myDriver);
			      Connection conn = DriverManager.getConnection(myUrl, "root", "ic64276x");
			     */
					Connection conn = connect_database();
			      Statement st = conn.createStatement();


			      // to store otp to database


			     // String sql;
			     // sql = "SELECT otp FROM otp where emailid = \" vijit@iitk.ac.in \" ";
			      //System.out.println(sql);
			      ResultSet rs = st.executeQuery("SELECT privkey FROM keystore where emailid = '" + emailid + "'");
			      while(rs.next())	      
			      {
			    	  privkeyFromDb=rs.getString("privkey");
			    	  System.out.println("privkeyFromDb" + privkeyFromDb);
			    	  //ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
			    	  //buffer.putLong(privkeyFromDb);
			    	  byte[] keybytes = Base64.getDecoder().decode(privkeyFromDb);
			    	 // int i = keybytes.length;
			    	  //System.out.println("byte length" + i);
			    	 // for (int j=0;j<i;j++)
			    	  //{System.out.println("byte data" + keybytes[j]);
			    	  
			    	  //}
			    	  //System.out.println("a1");
			    	  PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keybytes);
			    	 // System.out.println("a2");
			    	  KeyFactory fact = KeyFactory.getInstance("RSA");
			    	 // System.out.println("a3");
			    	  priv = fact.generatePrivate(keySpec);
			    	 // System.out.println("privkey data" + getHexString(priv.getEncoded()));
			    	  //Arrays.fill(clear, (byte) 0);
			    	    //return priv;
			    	 /* KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			    	  EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privkeyFromDb);
			    	  System.out.println("encodekeyspec = " + privateKeySpec);
			    	  privkey = keyFactory.generatePrivate(privateKeySpec);
			    	  System.out.println("privkey = " + privkey);*/
			    	  //System.out.println(OtpFromDb);
			    	  //conn.close();
			    	  
			      } 
			      conn.close(); 
			      
			      
			    }
			      catch (Exception e)
				    {
				      System.err.println("Got an exception in database");
				      e.printStackTrace(System.out);
				      System.err.println("M "+ e.getMessage());
				    }
					return priv;
			    }
		private static String getHexString(byte[] b) {
			String result = "";
			for (int i = 0; i < b.length; i++) {
				result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
			}
			return result;
		}
		private static Connection connect_database() {
		      String propfile= "/home/brihaspati/apache-tomcat-8.5.28/webapps/b4server/server.properties";
			System.out.println(propfile);
		      String myDriver = properties_access.read_property(propfile, "database_driver");
		      String myUrl = properties_access.read_property(propfile, "database_url");
		      try {
				Class.forName(myDriver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      Connection conn = null;
			try {
				conn = DriverManager.getConnection(myUrl, properties_access.read_property(propfile, "database_user"), properties_access.read_property(propfile, "database_password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      return conn;
		}

}



