package in.ac.dei.edrp.server.SharedFiles;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 * Log4JInitServlet
 *
 * This class should get loaded first (based on the web.xml),
 * so it can init the logger.
 *
 * @author Manpreet Kaur
 */

//@RemoteServiceRelativePath("log4jMap")
public class Log4JInitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public final Logger logger = Logger.getLogger(Log4JInitServlet.class);

    public void init() throws ServletException {
        String log4jfile = getInitParameter("log4j-properties");

        if (log4jfile != null) {
            logger.error("                                          ");
            logger.info("******** " + new java.util.Date() +
                " ********************************************************************************************");

            String propertiesFilename = getServletContext()
                                            .getRealPath(log4jfile);
            PropertyConfigurator.configure(propertiesFilename);
        } else {
            System.out.println("Error setting up logger.");
        }
    }
}
