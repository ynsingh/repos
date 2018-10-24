package server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class ConvertStringCertToX509 {
	public static X509Certificate[] convertToX509Certarray(String certEntry) throws IOException {
		 				
        InputStream in = null;
        X509Certificate cert = null;
        try {
        	
        	//System.out.println("tHE RECIEVED CERT FOR CONVERSION IS:   "+certEntry);
            byte[] certEntryBytes = Base64.getDecoder().decode(certEntry);
            //int i = certEntryBytes.length;
            //System.out.println("the byte length" + i);
            //for(int j =0; j<i; j++)
            //System.out.println("I am converting cert from string to X509 format. THe converted byte format cert is:   "+certEntryBytes[j]);
            
            in = new ByteArrayInputStream(certEntryBytes);
            //System.out.println("I am converting cert from string to X509 format. THe converted byte array stream  format cert is:   "+in);
            
            CertificateFactory certFactory = null;
			certFactory = CertificateFactory.getInstance("X.509"); 
            //System.out.println("I am converting cert from string to X509 format. THe converted certfactory format cert is:   "+certFactory);
            cert = (X509Certificate) certFactory.generateCertificate(in);
            System.out.println("I am converting cert from string to X509 format. THe converted cert is:   "+cert);
            PublicKey pubkey = cert.getPublicKey();
            //System.out.println(pubkey);
            
        } catch (CertificateException ex) {
        	ex.getMessage();
        	return new X509Certificate[]{};
 
        } finally {
            if (in != null) {
                    in.close();
            }
        }
        //System.out.println("I am converting cert from string to X509 format. THe converted cert is:   "+cert);
       // return cert;
        return new X509Certificate[]{cert};
    }
	public static X509Certificate convertToX509Cert(String certEntry) throws IOException {
			
        InputStream in = null;
        X509Certificate cert = null;
        try {
        	
        	//System.out.println("tHE RECIEVED CERT FOR CONVERSION IS:   "+certEntry);
        	CertificateFactory certFactory = null;
			certFactory = CertificateFactory.getInstance("X.509"); 
            byte[] certEntryBytes = Base64.getEncoder().encode(certEntry.getBytes());
            System.out.println("I am converting cert from string to X509 format. THe converted byte format cert is:   "+certEntryBytes);
            
            in = new ByteArrayInputStream(certEntryBytes);
            
            cert = (X509Certificate) certFactory.generateCertificate(in);
            //System.out.println("I am converting cert from string to X509 format. THe converted byte array stream  format cert is:   "+in);
            
            
            //System.out.println("I am converting cert from string to X509 format. THe converted certfactory format cert is:   "+certFactory);
            //cert = (X509Certificate) certFactory.generateCertificate(in);
            System.out.println("I am converting cert from string to X509 format. THe converted cert is:   "+cert);
            PublicKey pubkey = cert.getPublicKey();
            System.out.println(pubkey);
            return cert;
        } catch (CertificateException ex) {
        	ex.getMessage();
        	//return new X509Certificate[]{};
 
        } finally {
            if (in != null) {
                    in.close();
            }
        }
        //System.out.println("I am converting cert from string to X509 format. THe converted cert is:   "+cert);
       // return cert;
        return cert;
    }

	/*public X509Certificate[] getAcceptedIssuers() {
    try {
        X509Certificate scert;
        try (InputStream inStream = new FileInputStream("..\\server.crt")) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            scert = (X509Certificate) cf.generateCertificate(inStream);
        }                        
        return new X509Certificate[]{scert};
    } catch (Exception ex) {
        writeLogFile(ex.getMessage());
        return new X509Certificate[]{};
	}*/

}
