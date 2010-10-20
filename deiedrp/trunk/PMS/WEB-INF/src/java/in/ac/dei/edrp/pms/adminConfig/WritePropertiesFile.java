package in.ac.dei.edrp.pms.adminConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class WritePropertiesFile {
	static final Logger logger = Logger.getLogger(WritePropertiesFile.class);
	public static void mailConfig(String filePath,String smtpServerName,
			String smtpServerPort,String mailFrom,String userPassword) {
		try {
						
			Properties properties = new Properties();
			properties.setProperty("smtpServerName", smtpServerName);
			properties.setProperty("smtpServerPort", smtpServerPort);
			properties.setProperty("mailFrom", mailFrom);
			properties.setProperty("userPassword", userPassword);
			
			File file = new File(filePath+"MailConfig.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut,"<!--@author Anil Kumar Tiwari mailto:aniltiwari08@gmail.com -->\n#This is mail configuration file created by super admin");
			fileOut.close();
		} catch (FileNotFoundException e) {
			logger.error("File creation failed.", e);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}





