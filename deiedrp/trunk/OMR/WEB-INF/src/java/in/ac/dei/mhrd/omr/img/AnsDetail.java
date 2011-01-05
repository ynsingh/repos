package in.ac.dei.mhrd.omr.img;

import in.ac.dei.mhrd.omr.UploadResponseAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Creation date: 12-06-2010
 * @Author Anshul Agarwal
 * @This class inserts the position of the 
 * bubbles in answer section of the OMR sheet into the database and matches  
 * answer details from the database
 */
public class AnsDetail {
    static byte option_no = 0;
    double xstart; //ratio of x coordinate
    double xend; //ratio of x coordinate
    double ymidr1; //ratio of y coordinates
    double ymidr; //ratio of y coordinates
    double ymidr2; //ratio of y coordinates
    int qno;
    int sec;
    char option;
	private static Logger log = Logger.getLogger(AnsDetail.class);

    /**
     * This function inserts the position of the circles in the answer detail part of the sheet into the database
     * @param detail : contains xstartratio, xendratio, midpoint ratio of each circle in the answer part
     */
    public static void insertAnsCirclePosition(ArrayList<AnsDetail> detail) {
        /*
         * Execute only once for base answer sheet
         */
        try {
            Connection con = Connect.prepareConnection();
            PreparedStatement ps = con.prepareStatement(
            "insert into ans_info(q_no, ans, xstart, xend, y_ratio1, y_ratio2, sec) values (?,?,?,?,?,?,?)");
   
            for (AnsDetail ob : detail) {
                 ps.setInt(1, ob.qno);
                ps.setString(2, "" + ob.option);
                ps.setDouble(3, ob.xstart);
                ps.setDouble(4, ob.xend);
                ps.setDouble(5, ob.ymidr1);
                ps.setDouble(6, ob.ymidr2);
                ps.setInt(7, ob.sec);
                ps.addBatch();
                }

              ps.executeBatch();
              con.commit();
            con.close();
        } catch (Exception e) {
            log.error("error while insert bubble position into the database in the answer section of the sheet: " + e);
        }
    }

    /**
     * This function returns true if xpos and ypos lies in the range stored in the database and retrieve corresponding values from the database
     * @param xpos
     * @param ypos
     * @param obj
     * @param sec
     * @return
     */
       
    public static boolean matchAns(double xpos, double ypos,
            EachCandidateAns obj, int y) {
            option_no = 0;

            //obj.init_response_to_zero();
            boolean b = false;
            ResultSet rsXcomponent=null;
            try {
                Connection con = Connect.prepareConnection();
                ResultSet rs;
                try{
                //	System.out.println("inside match Ans: " + " xpos :" + xpos + " ypos : " + ypos );
                	/*
                	 * comparing positions, w.r.t. x-axis, of the filled circles
                	 */
                PreparedStatement psXcomponent = con.prepareStatement(
                        "select  q_no, ans from ans_info where (xstart <= ? And xend >= ?)");
                psXcomponent.setDouble(1, xpos);
                psXcomponent.setDouble(2, xpos);
                
                 rsXcomponent = psXcomponent.executeQuery();
                }
                catch(Exception e){
                	System.out.println("error in try " + e);
                }
              /*
               * if x position match then compare y position and retrieve required information
               */
                if (rsXcomponent.next()) {
                	//System.out.println("data retrieved from x component pos : " + xpos);
                    PreparedStatement ps = con.prepareStatement(
                            "select  q_no, ans from ans_info where (xstart <= ? And xend >= ?) And (y_ratio2 <= ? AND y_ratio1 >= ?)");
                    ps.setDouble(1, xpos);
                    ps.setDouble(2, xpos);
                    ps.setDouble(3, ypos);
                    ps.setDouble(4, ypos);
                    rs = ps.executeQuery();
                    boolean val = rs.next();
                   // System.out.println("resultset status : " + val);
                    if (!val) {
                    	/*
                    	 * If x position matches but y position does not matches, than increment and decrement the y position by one pixel alternatively,
                    	 * compute the ratio and than compare again
                    	 */
                    	//System.out.println("data not retrieved from y component : " + ypos);
                    	 
                    	int counter =0; int tempy;

                    	for (int i = 1; i<=15; i++) {
		                    	counter =0;
		                    	
                        	tempy = y+i;
                        	do{
                        		
                        	 ypos = (double) (RotateImg.leftBlocks.get(RotateImg.leftBlocks.size() -
                                     1).y - tempy) / (double) (tempy -
                                 (RotateImg.leftBlocks.get(0)).y);
                          
                            PreparedStatement psYComponent = con.prepareStatement(
                                    "select  q_no, ans from ans_info where (xstart <= ? And xend >= ?) And (y_ratio2 <= ? AND y_ratio1 >= ?)");
                            psYComponent.setDouble(1, xpos);
                            psYComponent.setDouble(2, xpos);
                            psYComponent.setDouble(3, ypos);
                            psYComponent.setDouble(4, ypos);
                            rs = psYComponent.executeQuery();
                            val = rs.next();
                            if (val) {
                            	// System.out.println(" match found ");
                                break;
                            }
                            tempy=y+(i* -1);
	                        counter++;
	                    }while(counter<=1); // end while
	                        
	                        if(val)
	                        {
	                        	break;
	                        }
                            
                        }// end for
                    }  // end if(!rs.next())
               

                while (val) {
                    
                    b = true;
                    int pos = rs.getInt(1);
                    
                    if(pos<obj.response.length)
                    {
                                       //array
                    if ((char) rs.getString(2).charAt(0) == 'A') {
                        option_no = (byte) (option_no + (byte) 1);
                                                
                    }

                    if ((char) rs.getString(2).charAt(0) == 'B') {
                        option_no = (byte) (option_no + (byte) 2);
                    }

                    if ((char) rs.getString(2).charAt(0) == 'C') {
                        option_no = (byte) (option_no + (byte) 4);
                    }

                    if ((char) rs.getString(2).charAt(0) == 'D') {
                        option_no = (byte) (option_no + (byte) 8);
                    }

                    if (obj.response[pos] == 0) {
                        obj.response[pos] = option_no;
                    } else {
                        obj.response[pos] = (byte) (obj.response[pos] +
                            option_no);

                    }
                    }
                    else{
                    	break;
                    }
                    val = rs.next();
                                    }
                }// end if(rsXcomponent)...
                
            } catch (Exception e) {
                log.error("Error while matching position of bubbles in the answer section " + e);
            }

            return b;
        }
    
    
}
