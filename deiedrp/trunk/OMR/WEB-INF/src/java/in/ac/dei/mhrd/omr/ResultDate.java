package in.ac.dei.mhrd.omr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import in.ac.dei.mhrd.omr.img.*;

/**
 * Creation date: 12-21-2010
 * @author Anshul Agarwal
 * This class is accessed by the ManageResult jsp via AJAX to determine the time period
 * for which result of the selected test can be viewed 
 *
 */
public class ResultDate {
	
	private static Logger log = Logger.getLogger(ResultDate.class);
	
	/**
	 * This function accepts the testname and then retrieves the Time period for which result can be viewed
	 * @param testname
	 * @return
	 */
	
	public String[] getResultDate(String testname) {
		
		int i=0; //index of the array
		String[] resultDate=new String[4];
	
	try {
		
        Connection con = Connect.prepareConnection();
        ResultSet rs;
       
        /*
         * This section of code retrieves the date at which the processing of test completes and the timeperiod for which results can be viewed
         */
        PreparedStatement ps = con.prepareStatement(
        "select date(ProcessEndDate), date(ResultDisplayedFrom), date(ResultDisplayedTo) from testHeader where Test_name=?");
        ps.setString(1, testname);
        rs = ps.executeQuery();
        rs.next();
        System.out.println("time stamp : " + rs.getTimestamp(1));
        System.out.println("count inside name list: " + rs.getString(2));

        resultDate[0]=rs.getTimestamp(1).toString(); //ProcessEndDate 
        resultDate[1]=rs.getString(2);              //ResultDisplayedFrom  
        resultDate[2]=rs.getString(3);             // ResultDisplayedTo
       
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
       /*Date ResultDisplayedFrom = sdf.parse(rs.getString(2)); 
       Date ResultDisplayedTo = sdf.parse(rs.getString(3));
       
       resultDate[1]= ResultDisplayedFrom.getTime();
       resultDate[2] = ResultDisplayedTo.toString();
       */
       //ResultDisplayedFrom.getMonth();
       
		/*java.util.Date processdate = sdf.parse(resultDate[0]); 
		java.sql.Timestamp timest = new java.sql.Timestamp(processdate.getTime()); 
		System.out.println("time Stamp value" + timest);
        */
		
		/*
		 * Result can be displayed maximum for one month after processing of the test
		 */
        ps = con.prepareStatement(
        "select date(Date_Add(?, interval 1 Month));");   // return the last date of displaying result
        ps.setTimestamp(1, rs.getTimestamp(1));
        ResultSet rsDate = ps.executeQuery();
        rsDate.next();
            resultDate[3]=rsDate.getString(1); // last date of displaying result
        }
        catch(Exception e){
        	log.error("error in try of resukt date " + e);
        }
        return resultDate;
    }


}
