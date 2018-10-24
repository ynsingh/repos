package com.ehelpy.brihaspati4.authenticate ; 

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import sun.security.x509.X500Name;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
/*import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509Key;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.math.BigInteger;
import java.security.cert.Certificate;
import java.security.SecureRandom;
import javax.swing.JOptionPane;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X509CertInfo;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.security.KeyStore;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import sun.security.x509.X500Name;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;*/

public class GenerateCertificate {
	
	
	// taking user inputs for various fields of X509 certificate using GUI class
	private static final int keysize = 1024;
	
	//private static final long validity = 1096; // 3 years
	private static final long validity = 365; // 1 year
	
    //private static final String commonName = "IDENTITY SERVER";
	private static final String commonName = Gui.getcommonname();
	
    //private static final String organizationalUnit = "IIT KANPUR";
	private static final String organizationalUnit = Gui.getorganizationalunit();
	
    //private static final String organization = "EE DEPT";
    private static final String organization = Gui.getorganization();
    
    //private static final String city = "KANPUR";
    private static final String city = Gui.getcity();
    
    //private static final String state = "UTTAR PRADESH";
    private static final String state = Gui.getstate();
    
    //private static final String country = "IN";
    private static final String country = Gui.getcountry();
    
    //private static final String alias = "tomcat";
    static final String alias = Gui.getaliasname();
    
    static final char[] keyPass = Gui.getkeystorepass().toCharArray();
	public static Key privKey;
	
	// method to generate self signed certificate
    static X509Certificate createSelfSignedCert() throws GeneralSecurityException, IOException {
    
    	
       KeyStore keyStore = KeyStore.getInstance("JKS");
       keyStore.load(null, null);
       //KeyStore keyStore = KeyStore.getInstance("KEYSTORE");       
       //keyStore.load(null,keyPass );

       
       //public CertAndKeyGen(String keyType, String sigAlg, String providerName) throws NoSuchAlgorithmException, NoSuchProviderException 
       //Creates a CertAndKeyGen object for a particular key type, signature algorithm, and provider. 
       //sun.security.tools.keytool.CertAndKeyGen keypair = new sun.security.tools.keytool.CertAndKeyGen("RSA", "SHA1WithRSA", null);
       
       // OPTIONS OF USING DIFFERENT ALGORITHM BELOW.USE ONE AND COMMENT OTHERS.       
       //sun.security.tools.keytool.CertAndKeyGen keypair = new sun.security.tools.keytool.CertAndKeyGen("RSA", "MD5WithRSA", null);
       sun.security.tools.keytool.CertAndKeyGen keypair = new sun.security.tools.keytool.CertAndKeyGen("RSA", "SHA1WithRSA", null);
       
       X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);
       keypair.generate(keysize);
       System.out.println("key pair is:		" + keypair);
        
        PrivateKey privKey = keypair.getPrivateKey();
        System.out.println("private key  is:	" + privKey);
        
        PublicKey pubKey = keypair.getPublicKey();
        System.out.println("public key  is:		 " + pubKey); 
        System.out.println("alias  is:		" + alias); 

        X509Certificate[] chain = new X509Certificate[1];

        chain[0] = keypair.getSelfCertificate(x500Name, new Date(), (long) validity * 24 * 60 * 60);
     
        keyStore.setKeyEntry(alias, privKey, keyPass, chain);
        /*public final void setKeyEntry(String alias,byte[] key,Certificate[] chain) throws KeyStoreException
		Assigns the given key (that has already been protected) to the given alias.
		Parameters:
			alias - the alias name
			key - the key (in protected format) to be associated with the alias
			chain - the certificate chain for the corresponding public key (only useful if the protected key is of type java.security.PrivateKey).*/

              
        //String algorithm="MD5withRSA" ;
        
      FileOutputStream fos = new FileOutputStream("KeyStore.JKS");
      keyStore.store(fos, keyPass);
        
        saveKeyStore(keyStore,"KeyStore.JKS", "123456");
        System.out.println("new cert saved to keystore");
        return chain[0];		
     }
        
	 static KeyStore createEmptyKeyStore() 
        throws GeneralSecurityException, IOException { 
	       KeyStore ks = KeyStore.getInstance("JKS"); 
		   ks.load(null, null); // initialize 
		   return ks; 
	 } 
		 
    static void saveKeyStore(KeyStore ks, String filename, String password) 
		    throws GeneralSecurityException, IOException { 
		    FileOutputStream out = new FileOutputStream(filename); 
		    try { 
		      ks.store(out, password.toCharArray()); 
		    } finally { 
		      out.close(); 
		    } 
		  } 
		 
    public static void createKeyStore(String filename, String password, String alias, Key privateKey, Certificate cert) 
		    throws GeneralSecurityException, IOException { 
		    KeyStore ks = createEmptyKeyStore(); 
		    ks.setKeyEntry(alias, privateKey, password.toCharArray(), 
		                   new Certificate[]{cert}); 
		    saveKeyStore(ks, filename, password); 
    } 
		 
		  /**
		   * Creates a keystore with a single key and saves it to a file. 
		   *  
		   * @param filename String file to save 
		   * @param password String store password to set on keystore 
		   * @param keyPassword String key password to set on key 
		   * @param alias String alias to use for the key 
		   * @param privateKey Key to save in keystore 
		   * @param cert Certificate to use as certificate chain associated to key 
		   * @throws GeneralSecurityException for any error with the security APIs 
		   * @throws IOException if there is an I/O error saving the file 
		   */ 
		 public static void createKeyStore(String filename, 
		                                    String password, String keyPassword, String alias, 
		                                    Key privateKey, Certificate cert) 
		    throws GeneralSecurityException, IOException { 
		    KeyStore ks = createEmptyKeyStore(); 
		    ks.setKeyEntry(alias, privateKey, keyPassword.toCharArray(), 
		                   new Certificate[]{cert}); 
		    saveKeyStore(ks, filename, password); 
		  } 
}

