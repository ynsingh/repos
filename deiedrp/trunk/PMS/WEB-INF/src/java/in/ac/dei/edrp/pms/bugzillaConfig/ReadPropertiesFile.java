package in.ac.dei.edrp.pms.bugzillaConfig;

//reading urlConfig.properties file

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
	public static Map<String, String> urlConfig(String filePath) {
		Map<String, String> urlData=null;
		try {
			urlData = new HashMap<String, String>();
			File file = new File(filePath+"UrlConfig.properties");
			if(file.exists()){
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();								
			urlData.put( "url", properties.getProperty("url"));
			}else{
				urlData.put( "url","not Exist");
			}
				
		} catch (FileNotFoundException e) {
			logger.error("UrlConfig.properties file not found in the WEB-INF folder," +
					"please give the URL to Bugzilla again or " +
					"paste the existing file into WEB-INF folder.", e);
		} catch (IOException e) {
			logger.error(e);
		}
		return urlData;
	}
}

