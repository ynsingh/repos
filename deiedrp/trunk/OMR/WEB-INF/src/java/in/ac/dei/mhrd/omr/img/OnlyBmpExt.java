package in.ac.dei.mhrd.omr.img;

import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import in.ac.dei.mhrd.omr.*;

/**
*
* @author Anshul Agarwal
* This class extract path of BMP files only
*
*/
public class OnlyBmpExt implements FilenameFilter {
   String ext;
   String rejectedFolderPath;
   int testid;

   /**
    *
    * @param ext ; extension of the file
    */
	public OnlyBmpExt(String ext, String rejcteFolderPath, int testid){
		this.ext = ext;
		this.rejectedFolderPath = rejectedFolderPath;
		this.testid = testid;
	}
	private static Logger log = Logger.getLogger(OnlyBmpExt.class);

	public boolean accept(File dir, String name){
		
		Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

		
		System.out.println("in accept name : "+name);
		 boolean b = false;
	        b = (name.endsWith(ext)||name.endsWith(ext.toLowerCase()));

	        if (b == false) {
	        	  File source = new File(dir, name);
	        	  System.out.println("rejected folder in extr : "+ rejectedFolderPath);
	        	  System.out.println("File name in ext : " + ext);
	        	  LogEntry.insert_Log(testid, name,
          	            message.getString("code.E104"), message.getString("msg.E104"));
          	  log.info(message.getString("msg.E104"));

	  	        boolean flag = source.renameTo(new File (ProcessSheetAction.RejectedFolderPath, name));
	  	        log.info("filer move to rejected folder in ext " + flag);
	            
	        }
	        
	      

	        return b;
	}

}
