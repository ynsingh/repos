/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
* Copyright (c) 2010 - 2011 SMVDU, Katra.
* All Rights Reserved.
* Redistribution and use in source and binary forms, with or 
* without modification, are permitted provided that the following 
* conditions are met: 
* Redistributions of source code must retain the above copyright 
* notice, this  list of conditions and the following disclaimer. 
* 
* Redistribution in binary form must reproduce the above copyright
* notice, this list of conditions and the following disclaimer in 
* the documentation and/or other materials provided with the 
* distribution. 
* 
* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
* DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
* FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
* OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
* BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
* OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
* EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
* Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
* Date: 15 sep 2015, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
*/


public class EncryptionUtil {

    /**generating hash values from String.
     * @param encryption
     * @param input
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public String createDigest(String encryption,String input) throws NoSuchAlgorithmException
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
