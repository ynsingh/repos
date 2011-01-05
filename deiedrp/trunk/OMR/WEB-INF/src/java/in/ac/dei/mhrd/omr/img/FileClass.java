package in.ac.dei.mhrd.omr.img;

import in.ac.dei.mhrd.omr.ProcessSheetAction;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


/**
 *
 * @author Anshul Agarwal
 * This class retrieves the path of all the bitmap files in the folder
 */
public class FileClass {
   
	/**
     *This method finds all the Bmp files in the specified folder and add them to an array list and return this list to the calling function
     *to the calling function
	 * @param rejectedFolderPath 
     * @param p :path of the folder
     * @return
     * 
     */
	private static Logger log = Logger.getLogger(FileClass.class);

    public ArrayList<String> pathfunc(String filepath, int testid, String rejectedFolderPath) {
    	Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

        File f1 = new File(filepath);

        FilenameFilter only = new OnlyBmpExt("BMP", rejectedFolderPath, testid);

        File[] s = f1.listFiles(only); // return all the files with bmp extension
        ArrayList<String> path = new ArrayList<String>();

        for (int i = 0; i < s.length; i++) {
          //  System.out.println("path :" + s[i].getAbsolutePath().toString());
             
              if((s[i].length()/1048576) >3){
            	  System.out.println("File is too large " + s[i].getAbsolutePath() + " size : " + (s[i].length()/1048576) );
            	  LogEntry.insert_Log(testid, s[i].getName(),
            	            message.getString("code.E103"), message.getString("msg.E103"));
            	  log.info(message.getString("msg.E103"));

            	 /* LogEntry.insert_Log(testid, s[i].getName(),
                  "improper resolution");*/
            	  
            	 /** Move the file to rejected folder **/
            	   
 	        	 boolean flag =s[i].renameTo(new File(rejectedFolderPath, s[i].getName()));
 	      	    System.out.println("move file status in file class :  "+ flag);
            	  continue;
              }
              else{
        	path.add(s[i].getAbsolutePath().toString()); // add absolute path of each file into the arraylist
              }
        }

        return path;
    }
}
