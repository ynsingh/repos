/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.UserOperationBeans;

import java.security.MessageDigest;

/**
 *
 * @author ERP
 */
public class EncryPassword {
public String encryptPass(String pass)
    {
    try
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + sb.toString());
        StringBuilder hexString = new StringBuilder();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
        return hexString.toString();
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
        return null;
    }
}
}
