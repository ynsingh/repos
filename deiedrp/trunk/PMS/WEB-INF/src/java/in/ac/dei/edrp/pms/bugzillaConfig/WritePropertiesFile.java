package in.ac.dei.edrp.pms.bugzillaConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
/*
 * this class is used to write urlConfig.properties file as per the url passed by the superadmin
 */
public class WritePropertiesFile {
	static final Logger logger = Logger.getLogger(WritePropertiesFile.class);
	static boolean success=true;
	public static boolean urlConfig(String filePath,String url) {
		try {
						
			Properties properties = new Properties();
			properties.setProperty("url", url);
			File file = new File(filePath+"UrlConfig.properties");		
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut,"<!--@author Nupur Dixit mailto:noopur.here@gmail.com -->\n#This is url configuration file created by super admin");
			fileOut.close();
		} catch (FileNotFoundException e) {
			success=false;
			logger.error("File creation failed.", e);
		} catch (IOException e) {
			success=false;
			e.printStackTrace();
		}
		return success;
	}
}





