package org.iitk.brihaspati.modules.utils;
/*
 * @(#)AutoSave.java
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. 
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 *
 */
import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;

public class AutoSave {

    /**
     * Create a simple Hashtable and serialize it to a file called
     * 
     */
    public static void doSave(String Id, String msg) {

        Hashtable h = new Hashtable();
        h.put("msg", msg);
	String Path=TurbineServlet.getRealPath("/tmp");

        try {

            FileOutputStream fileOut = new FileOutputStream(Path+"/"+Id);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(h);
            out.close();
            fileOut.close();
           
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Loads the contents of a previously serialized object from a file called
     * 
     */
    public static String doLoad(String Id) {

        Hashtable h = null;
	String Path=TurbineServlet.getRealPath("/tmp");


        try {
            FileInputStream fileIn = new FileInputStream(Path+"/"+Id);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            h = (Hashtable)in.readObject();
            in.close();
            fileIn.close();
           
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	Object obj=new Object();
        for (Enumeration e = h.keys(); e.hasMoreElements(); ) {
            obj = e.nextElement();
        }
	return h.get(obj).toString();

    }


    /**
     * Delete the file which is temporary stored in tmp folder
     * 
     */
    public static void doDelete(String Id) {

	String Path=TurbineServlet.getRealPath("/tmp");
	try{
		File file = new File(Path+"/"+Id);
		file.delete();
	}catch(Exception e){}

    }
}

