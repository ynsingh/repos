package in.ac.dei.edrp.pms.adminConfig;

//reading properties file

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadPropertiesFile {
	static final Logger logger = Logger.getLogger(ReadPropertiesFile.class);
	public static Map<String, String> mailConfig(String filePath) {
		Map<String, String> mailData=null;
		try {
			File file = new File(filePath+"MailConfig.properties");
			//System.out.println("path="+file.getAbsolutePath());
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
				
				mailData = new HashMap<String, String>();
				mailData.put( "smtpServerName", properties.getProperty("smtpServerName"));
				mailData.put( "smtpServerPort", properties.getProperty("smtpServerPort"));
				mailData.put( "mailFrom", properties.getProperty("mailFrom"));
				mailData.put( "userPassword", properties.getProperty("userPassword"));
				
		} catch (FileNotFoundException e) {
			logger.error("MailConfig.properties file not found in the WEB-INF folder," +
					"please reconfigured the mail server again or " +
					"paste the existing file into WEB-INF folder.", e);
		} catch (IOException e) {
			logger.error(e);
		}
		return mailData;
	}
}

