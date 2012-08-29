
package org.iitk.brihaspati.modules.utils.security;

/*@(#)EncrptDecrpt.java
 *  Copyright (c) 2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  
 */


import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.SecretKey;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.lang.StringUtils;

/**
 * This class provide the encription and ecription of String
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */
public class EncrptDecrpt{

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private static KeySpec myKeySpec;
	private static SecretKeyFactory mySecretKeyFactory;
	private static Cipher cipher;
	static	byte[] keyAsBytes;
	private static String myEncryptionKey;
	private static String myEncryptionScheme;
	static SecretKey key;
	/**
  	 * Method To Encrypt The String
  	 * @param unencryptedString String
  	 * @param srcid String
  	 * @return String base 64 encoder encripted string
  	 */
	public static String encrypt(String unencryptedString, String srcid) {
		String hdir=System.getProperty("user.home");
		String osnme=System.getProperty("os.name");
		String path="";
                if (osnme.startsWith("Win")){
                        path=hdir+"\\remote_auth\\brihaspati3-remote-access.properties";
                }
                else{
                        path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                }

                //String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
		//String path=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati3-remote-access.properties");
                // write code for if path is null then it get from home
		String line=ReadNWriteInTxt.readLin(path,srcid);
                myEncryptionKey=StringUtils.substringBetween(line,";",";");
/*
                try{
                        myEncryptionKey = RemoteAuthProperties.getValue(path,"security_key");
                }
                catch(Exception ex){
                }
*/
		try{
          //      ErrorDumpUtil.ErrorLog("The my cipher key is  "+ myEncryptionKey);
		myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
                keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        //        ErrorDumpUtil.ErrorLog("The key byte array is  "+ keyAsBytes);
                myKeySpec = new DESedeKeySpec(keyAsBytes);
      //          ErrorDumpUtil.ErrorLog("The cipher is 1  "+ myKeySpec);
                mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
    //            ErrorDumpUtil.ErrorLog("The cipher is  2"+ mySecretKeyFactory);
                cipher = Cipher.getInstance(myEncryptionScheme);
  //              ErrorDumpUtil.ErrorLog("The cipher is 3 "+ cipher);
                key = mySecretKeyFactory.generateSecret(myKeySpec);
//                ErrorDumpUtil.ErrorLog("The key byte array key is  "+ key);
		}
		catch(Exception ex){
		System.out.println("The error in try block2 encript method"+ex);
                }

		String encryptedString = null;
		try {
	//		ErrorDumpUtil.ErrorLog("The key is "+ key);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			BASE64Encoder base64encoder = new BASE64Encoder();
			encryptedString = base64encoder.encode(encryptedText);
	//		ErrorDumpUtil.ErrorLog("The encrypted string is "+encryptedString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	/**
  	 * Method To Encrypt The String
  	 * @param unencryptedString String
  	 * @param srcid String
  	 * @param path String absolute location where the propeties file stored
  	 * @return String base 64 encoder encripted string
  	 */
	public static String encrypt(String unencryptedString, String srcid, String path) {
//		String hdir=System.getProperty("user.home");
  //              String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
		//String path=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati3-remote-access.properties");
                // write code for if path is null then it get from home
		String line=ReadNWriteInTxt.readLin(path,srcid);
                myEncryptionKey=StringUtils.substringBetween(line,";",";");
/*
                try{
                        myEncryptionKey = RemoteAuthProperties.getValue(path,"security_key");
                }
                catch(Exception ex){
                }
*/
		try{
          //      ErrorDumpUtil.ErrorLog("The my cipher key is  "+ myEncryptionKey);
		myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
                keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        //        ErrorDumpUtil.ErrorLog("The key byte array is  "+ keyAsBytes);
                myKeySpec = new DESedeKeySpec(keyAsBytes);
      //          ErrorDumpUtil.ErrorLog("The cipher is 1  "+ myKeySpec);
                mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
    //            ErrorDumpUtil.ErrorLog("The cipher is  2"+ mySecretKeyFactory);
                cipher = Cipher.getInstance(myEncryptionScheme);
  //              ErrorDumpUtil.ErrorLog("The cipher is 3 "+ cipher);
                key = mySecretKeyFactory.generateSecret(myKeySpec);
//                ErrorDumpUtil.ErrorLog("The key byte array key is  "+ key);
		}
		catch(Exception ex){
		System.out.println("The error in try block2 encript method"+ex);
                }

		String encryptedString = null;
		try {
	//		ErrorDumpUtil.ErrorLog("The key is "+ key);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			BASE64Encoder base64encoder = new BASE64Encoder();
			encryptedString = base64encoder.encode(encryptedText);
	//		ErrorDumpUtil.ErrorLog("The encrypted string is "+encryptedString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	/**
  	 * Method To Decrypt An Ecrypted String
  	 * @param encryptedString String
  	 * @param srcid String
  	 * @return String 
  	 */
	public static String decrypt(String encryptedString, String srcid) {
		String hdir=System.getProperty("user.home");
		String osnme=System.getProperty("os.name");
                String path="";
                if (osnme.startsWith("Win")){
                        path=hdir+"\\remote_auth\\brihaspati3-remote-access.properties";
                }
                else{
                        path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                }

             //   String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
		//String path=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati3-remote-access.properties");
                // write code for if path is null then it get from home
		String line=ReadNWriteInTxt.readLin(path,srcid);
                myEncryptionKey=StringUtils.substringBetween(line,";",";");
               /* try{
                        myEncryptionKey = RemoteAuthProperties.getValue(path,"security_key");
                }
                catch(Exception ex){
                }*/       
		try{
                myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
                keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
           //     ErrorDumpUtil.ErrorLog("The key byte array is  "+ keyAsBytes);
                myKeySpec = new DESedeKeySpec(keyAsBytes);
                mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
                cipher = Cipher.getInstance(myEncryptionScheme);
                key = mySecretKeyFactory.generateSecret(myKeySpec);
		}
		catch(Exception ex){
                }
	
		String decryptedText=null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText= bytes2String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
	/**
  	 * Method To Decrypt An Ecrypted String
  	 * @param encryptedString String
  	 * @param srcid String
  	 * @param path String absolute location where the propeties file stored
  	 * @return String 
  	 */
	public static String decrypt(String encryptedString, String srcid, String path) {
//		String hdir=System.getProperty("user.home");
  //              String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
		//String path=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati3-remote-access.properties");
                // write code for if path is null then it get from home
		String line=ReadNWriteInTxt.readLin(path,srcid);
                myEncryptionKey=StringUtils.substringBetween(line,";",";");
               /* try{
                        myEncryptionKey = RemoteAuthProperties.getValue(path,"security_key");
                }
                catch(Exception ex){
                }*/       
		try{
                myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
                keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
           //     ErrorDumpUtil.ErrorLog("The key byte array is  "+ keyAsBytes);
                myKeySpec = new DESedeKeySpec(keyAsBytes);
                mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
                cipher = Cipher.getInstance(myEncryptionScheme);
                key = mySecretKeyFactory.generateSecret(myKeySpec);
		}
		catch(Exception ex){
                }
	
		String decryptedText=null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText= bytes2String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
	/**
  	 * Returns String From An Array Of Bytes
  	 */
	private static String bytes2String(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i<bytes.length; i++) {
			stringBuffer.append((char) bytes[i]);
		}
		return stringBuffer.toString();
	}
	/**
 	 * Method to generate keyed hash
 	 */
	public static String keyedHash(String email, String random, String key) {
                String hkeyN="email="+email+";random="+random+";secret="+key+";";
                String hashcode="";
                try{
                        hashcode = EncryptionUtil.createDigest("MD5",hkeyN);
                }
                catch(Exception ex){
                        System.out.println("The problem in generating keyed hash code");
                }
        return hashcode;
        }

}
