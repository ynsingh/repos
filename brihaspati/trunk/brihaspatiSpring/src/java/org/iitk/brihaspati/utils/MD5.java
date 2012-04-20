package org.iitk.brihaspati.utils;


import java.util.Vector;
import java.security.MessageDigest;
import java.math.*;

public class MD5 {

	public static String getMD5(String s)throws Exception{
        	try{
	                MessageDigest m=MessageDigest.getInstance("MD5");
        	        m.update(s.getBytes(),0,s.length());
                	String s1=new BigInteger(1,m.digest()).toString(16);
               		return s1;
         	} catch(Exception e){return null;}
         }


}

