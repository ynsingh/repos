package org.iitk.livetv.util;

/*
 * @(#)EncryptionUtil.java
 *  Copyright (c) 2012-13 ETRG,IIT Kanpur. 
 */

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

/**
 *  @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>
 */

public class EncryptionUtil
{

	public static String createDigest(String encryption,String input) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance(encryption);
		byte[] digest = md.digest(input.getBytes());
		return toHexString(digest);
	}

	public static String toHexString(byte[] byteDigest)
	{
		String temp="";
		int i,len=byteDigest.length;
		for(i=0;i<len;i++)
		{
			String byteString=Integer.toHexString(byteDigest[i]);
		
			int iniIndex=byteString.length();
			if(iniIndex==2)
				temp=temp+byteString;
			else if(iniIndex==1)
				temp=temp+"0"+byteString;
			else if(iniIndex==0)
				temp=temp+"00";
			else if(iniIndex>2)
				temp=temp + byteString.substring(iniIndex-2,iniIndex);
		}
		return temp;
	}
}
