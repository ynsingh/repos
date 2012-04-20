package db;

import bus.Directory;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.core.SqlParameter;

public class DirectoryManagerDaoJdbc {

    	/** Logger for this class and subclasses */
    	protected final Log logger = LogFactory.getLog(getClass());

    	private DataSource ds;
	public  DataSource getDataSourse() {
                return this.ds;
        }
	
    	public void setDataSource(DataSource ds) {
		logger.info("data source");
        	this.ds = ds;
    	}
}


