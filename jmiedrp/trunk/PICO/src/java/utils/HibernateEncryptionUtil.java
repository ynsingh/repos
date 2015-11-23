package utils;


import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

/**
 *  @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>
 */

public class HibernateEncryptionUtil
{

	public static String createDigest(String encryption,String input)
	{
		byte[] digest = new byte[1000000];
		try{		
		MessageDigest md = MessageDigest.getInstance(encryption);
		 digest = md.digest(input.getBytes());
		}catch(NoSuchAlgorithmException ex){}
		return toHexString(digest);
	}

	public static String toHexString(byte[] byteDigest)
	{
		String temp="";
		try{
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
		}catch(Exception ex){}
		return temp;
	}
}
