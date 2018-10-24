package server ; 

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import dao.DatabaseConnection;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import sun.security.x509.X500Name;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class GenerateCertificate {
	
	
	//private static final long validity = 1096; // 3 years
	private static final long validity = 365; // 1 year
	
    //private static final String commonName = "IDENTITY SERVER";
	//private static final String Emailid = Gui.getemailid();
	private static final String Emailid = "b4server@iitk.ac.in";
		
    private static final String organizationalUnit = "IIT KANPUR";
	//private static final String organizationalUnit = Gui.getorganizationalunit();
	
    private static final String organization = "EE DEPT";
    //private static final String organization = Gui.getorganization();
    
    private static final String city = "KANPUR";
    //private static final String city = Gui.getcity();
    
    private static final String state = "UTTAR PRADESH";
    //private static final String state = Gui.getstate();
    
    private static final String country = "IN";
    //private static final String country = Gui.getcountry();
     
    private static final String alias1 = "tomcat";
    //static final String alias1 = Gui.getaliasname();
    static final String password	=	Gui.getkeystorepass();
    static final char[] keyPass = password.toCharArray();
	public static Key privKey;
	//static X509V1CertificateGenerator  v1CertGen = new X509V1CertificateGenerator();
	//static { Security.addProvider(new BouncyCastleProvider());  }
	// method to generate self signed certificate
	
    static X509Certificate createSelfSignedCert(){
   
    try{
    
        KeyStore keyStore = null;
	try {
		keyStore = KeyStore.getInstance("JKS");
		//keyStore = KeyStore.getInstance("KEYSTORE");
	} catch (KeyStoreException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       //keyStore.load(null, null);
              
       try {
		keyStore.load(null,keyPass );
	} catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (CertificateException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      
            //public CertAndKeyGen(String keyType, String sigAlg, String providerName) throws NoSuchAlgorithmException, NoSuchProviderException 
       // OPTIONS OF USING DIFFERENT ALGORITHM BELOW.USE ONE AND COMMENT OTHERS.       
       //sun.security.tools.keytool.CertAndKeyGen keypair = new sun.security.tools.keytool.CertAndKeyGen("RSA", "MD5WithRSA", null);
       
       sun.security.tools.keytool.CertAndKeyGen keypair = null;
	try {
		keypair = new sun.security.tools.keytool.CertAndKeyGen("RSA", "SHA1WithRSA", null);
	} catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (NoSuchProviderException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       //CertAndKeyGen keypair = new CertAndKeyGen("RSA", "SHAWithRSA", null);
       //System.out.println(keypair);
       X500Name x500Name = new X500Name(Emailid, organizationalUnit, organization, city, state, country);
       
       try {
		keypair.generate(2048);
	} catch (InvalidKeyException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       //KeyPair keyPair = keyGen.generateKeyPair();
       PrivateKey privKey = keypair.getPrivateKey();
       byte[] bprivkey = privKey.getEncoded();
       String sprivate	=	new String(Base64.getEncoder().encode(bprivkey));
       System.out.println("binary private key =" + bprivkey );
       PublicKey pubKey = keypair.getPublicKey();
       
       keystore_save adam = new keystore_save();
       String path = properties_access.read_property("server.properties","home" );
       adam.dumpKeyPair(keypair);
       adam.SaveKeyPair(path, keypair);
       //keypair.generate(1024);
       System.out.println("key pair is:		" + keypair);
        
        System.out.println("private key  is:	" + privKey);
        
        System.out.println("public key  is:		 " + pubKey); 
        
        System.out.println("alias  is:		" + alias1); 
       
        try {
			DatabaseConnection.keytoDb(null, Emailid, sprivate, pubKey, validity ,organizationalUnit, organization, city, state, country,alias1, keyPass);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
       
        X509Certificate[] chain = new X509Certificate[1];

        try {
			chain[0] = keypair.getSelfCertificate(x500Name, new Date(), (long) validity * 24 * 60 * 60);
			System.out.println(" get self chain"+chain[0]);
        } catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CertificateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SignatureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchProviderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

     
       try {
		keyStore.setKeyEntry(alias1, privKey, keyPass, chain);
		System.out.println(" setkey chain"+chain[0]);
	} catch (KeyStoreException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
        
        /*public final void setKeyEntry(String alias,byte[] key,Certificate[] chain) throws KeyStoreException
		Assigns the given key (that has already been protected) to the given alias.
		Parameters:
			alias - the alias name
			key - the key (in protected format) to be associated with the alias
			chain - the certificate chain for the corresponding public key (only useful if the protected key is of type java.security.PrivateKey).*/
         
      FileOutputStream fos = new FileOutputStream("ServerKeyStore.JKS");
     
		try {
			keyStore.store(fos, keyPass);
			System.out.println(" store chain"+chain[0]);
		} catch (KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CertificateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
        
        try {
			saveKeyStore(keyStore,"KeyStore.JKS", password);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("new cert saved to keystore");
        return chain[0];
    } catch (IOException e)
    	{e.printStackTrace();}
		return null;
        		
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
		    ks.setKeyEntry(alias1, privateKey, password.toCharArray(), 
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

