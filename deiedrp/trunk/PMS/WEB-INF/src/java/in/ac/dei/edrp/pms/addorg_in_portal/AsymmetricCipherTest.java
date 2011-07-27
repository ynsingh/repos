package in.ac.dei.edrp.pms.addorg_in_portal;
//File: src\jsbook\ch3\AsymmetricCipherTest.java

import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AsymmetricCipherTest {
	
  private static byte[] encrypt(byte[] inpBytes, PublicKey key,
      String xform) throws Exception {
    Cipher cipher = Cipher.getInstance(xform);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return cipher.doFinal(inpBytes);
  }
  private static byte[] decrypt(byte[] inpBytes, PrivateKey key,
      String xform) throws Exception{
    Cipher cipher = Cipher.getInstance(xform);
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher.doFinal(inpBytes);
  }

  private static KeyPair keyGenerator()throws Exception{
	  
	    // Generate a key-pair
	    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
	    SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
	 // Initialize the key generator to generate 1024-bit keys,
        // using random as the source of random bytes.

	    kpg.initialize(1024,random); // this is the keysize.
	 // Generate the key. This call will return a KeyPair object
        // containing a RSA key pair. 
	    KeyPair kp = kpg.generateKeyPair();
	    return kp;
  }
  private static void insertPass(String userid,String pass,String xform) throws Exception{
	 Connection con=getConnection();
	  KeyPair kp=keyGenerator();
	  
	    PublicKey pubk = kp.getPublic();
	    PrivateKey prvk = kp.getPrivate();
	   
	  byte[] dataBytes=pass.getBytes();
	  byte[] encBytes = encrypt(dataBytes, pubk, xform);
	    PreparedStatement ps = con
		.prepareStatement("insert into storePassword values(?,?,?)");
	    ps.setString(1,userid);
	    ps.setBytes(2,encBytes);
	    ps.setBytes(3,prvk.getEncoded());
	    ps.executeUpdate();
  }
  private static void checkPass(String userid,String pass,String xform) throws Exception{
	Connection  con=getConnection();
	  PreparedStatement ps = con
		.prepareStatement("select pass,prvk from storePassword where userid=?");
	    ps.setString(1,userid);
	    ResultSet rs=ps.executeQuery();
	    rs.next();
	    byte[] encBytes=rs.getBytes(1);
	    byte[] s=rs.getBytes(2);
	    System.out.println(s);
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    PKCS8EncodedKeySpec priKeySpec 
        = new PKCS8EncodedKeySpec(s);
     PrivateKey prvk = keyFactory.generatePrivate(priKeySpec);
System.out.println(prvk);
	    byte[] decBytes = decrypt(encBytes, prvk, xform);
	    byte[] dataBytes=pass.getBytes();
	    String s2= new String(decBytes);
	    System.out.println("your password="+s2);
	    
	  boolean expected = java.util.Arrays.equals(dataBytes, decBytes);
	  System.out.println("pass comparision="+expected);
  }
  private static Connection getConnection() throws ClassNotFoundException, SQLException{
	  Class.forName("com.mysql.jdbc.Driver");
	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pmsdatabase",
		        "root", "mysql");
return con;
  }
  public static void main(String[] unused) throws Exception {
   
    
	  String xform = "RSA/ECB/PKCS1PADDING"; //(PKCS)Public Key Cryptography Standards
//	  KeyPair kp=keyGenerator();
//	  
//    PublicKey pubk = kp.getPublic();
//    PrivateKey prvk = kp.getPrivate();
//System.out.println("public key="+pubk.toString()+" private key="+prvk.toString());
//    byte[] dataBytes =null;
//    dataBytes="anilsoft123".getBytes();
//    byte[] dataBytes1 =null;
//    dataBytes1=  "anilsoft123".getBytes();
//    System.out.println("databytes="+dataBytes+" databytes1="+dataBytes1);
    
//    byte[] encBytes = encrypt(dataBytes, pubk, xform);
//    byte[] decBytes = decrypt(encBytes, prvk, xform);
//  insertPass("anil@gmail.com","anilsoft",xform);
    checkPass("anil@gmail.com","anilsoft",xform);
//    String s1= new String(dataBytes);
//    String s2= new String(decBytes);
//    
//    System.out.println("dataBytes="+dataBytes+", decBytes="+decBytes);
//    System.out.println("encBytes="+encBytes.toString()+", decBytes="+decBytes);
//System.out.println("s1="+s1+", s2="+s2);
//
//    boolean expected = java.util.Arrays.equals(dataBytes, decBytes);
//    System.out.println(expected);
//    System.out.println("Test " + (expected ? "SUCCEEDED!" : "FAILED!"));
  }
}
