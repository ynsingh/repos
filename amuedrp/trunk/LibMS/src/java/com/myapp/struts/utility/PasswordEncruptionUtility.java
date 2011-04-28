/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Properties;


/**
 *
 * @author EDRP-AMU-06   Kedar Kumar
 * Date :09 Feb 2010
 * Class to Change Password in Encrupted Mode "MD5"
 */
public class PasswordEncruptionUtility {

public static StringBuffer sb;


/*Method to get String and return String in Encrupted Mode*/

    public static String password_encrupt(String password)
    {

        try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());

                byte byteData[] = md.digest();

                //convert the byte to hex format method 1
                 sb= new StringBuffer();
                for (int i = 0; i < byteData.length; i++)
                {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }

                System.out.println("Digest(in hex format):: " + sb.toString());

        
           }
            catch(Exception e)
            {
            System.out.println(e);
           }
        return sb.toString();
    }

    /**
         * Used to generate random password password
         * @return String
         */

	public static String randmPass(){
		byte[] pass=new byte[8];

               /**
               * Random is a function for
               * generating random numbers.
               */

               	Random rnd=new Random();
               	for(int i=0;i<8;i++)
               	{
  	        	int inPass=rnd.nextInt(26);
                        int p1;
                        if((inPass%2)==0)
                     	{
                                p1=inPass%10;
                                pass[i]=(byte)(p1+48);
                        }
                        else
                        {
                        	pass[i]=(byte)(inPass+97);
                        }
                }
		String password=new String(pass);
		return password;
	}

}
