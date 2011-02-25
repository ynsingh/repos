/*
 * @(#) CirclePosition.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */


package in.ac.dei.mhrd.omr.img;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import ij.ImagePlus;

import ij.plugin.BMP_Writer;
import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.processSheet.ProcessSheetAction;

import java.awt.Color;
import java.awt.Image;

import java.io.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import org.apache.log4j.Logger;



/**
 * This class skew the tilted image and then process the image further
 * Creation Date: 05-05-2010
 * @Author Anshul Agarwal
 * @version 1.0
 * 
 */
public class RotateImg {
	
	private static Logger log = Logger.getLogger(RotateImg.class);

	public  static ArrayList<String> AnsList; 
    public static ArrayList<Coordinates> leftBlocks; // coordinates of left
														// block
    public static ArrayList<Coordinates> RightBlocks; // coordinates of right
														// blocks
    static ImagePlus implus;
   static ij.process.ImageProcessor ip;
   static int xstartL; // starting point of processing in x dir. for left
						// blocks
   static int xendL; // ending point of processing in x dir. for left blocks
   static int xstartR; // starting point of processing in x dir. for right
						// blocks
   static int xendR; // ending point of processing in x dir. for left blocks
   static int noOfQues; // NO OF QUES IN EACH SECTION
                        /* sum of x coordinates of blocks on left side */
   static float xLtotal = 0;

    /* sum of y coordinates of blocks on left side */
   static float yLtotal = 0;

    /* x and y average of left side block */
   static float[] xyLeftAvg = new float[2];

    /* x and y average of right side block */
   static float[] xyRtAvg = new float[2];

    /* sum of x coordinates of blocks on right side */
   static float xRtotal = 0;

    /* sum of y coordinates of blocks on right side */
   static float yRtotal = 0;
   static byte[] attemptAns;
  public static byte[] correctAns; // CORRECT ANSWERS IN EACH SECTION
   
   public static boolean infoFlag=true;

    /**
	 * This function accepts object of imageProcessor class and rotate the image if
	 * required
	 */
    private void rotateImg(ij.process.ImageProcessor ip) {
        float xdiff;
        float ydiff;
        double angle;

        xstartL = (int) (ip.getWidth() * 1) / 100;  /*
													 * starting point of
													 * processing in x dir. for
													 * left blocks
													 */

        xendL = (int) (ip.getWidth() * 10) / 100; // ending point of
													// processing in x dir. for
													// left blocks.

        xstartR = (int) (ip.getWidth() * 91) / 100; // starting point of
													// processing in x dir. for
													// right blocks

        xendR = ip.getWidth(); // ending point of processing in x dir. for left
								// blocks

        /*
		 * computes the coordinates of left side blocks, accept image object, x
		 * coordinate of starting point and ending point of processing
		 */

        // System.out.println("left blocs b4 rotation");
        leftBlocks = Coordinates.computeCorners(ip, xstartL, xendL);

        computeAvg(leftBlocks, xyLeftAvg, xLtotal, yLtotal); // computes the
																// average of
																// coordinates
																// of leftside
																// blocks

        /*
		 * computes the coordinates of right side blocks, accept image object, x
		 * coordinate of starting point and ending point of processing
		 */

        // System.out.println("Rt block b4 rotation xstartR : " + xstartR + "
		// xendr : " + xendR);
        RightBlocks = Coordinates.computeCorners(ip, xstartR, xendR);

        computeAvg(RightBlocks, xyRtAvg, xRtotal, yRtotal); // computes the
															// average of
															// coordinates of
															// rightside blocks

        xdiff = xyRtAvg[0] - xyLeftAvg[0]; /*
											 * difference b/w the avg of x
											 * coordinates on the left side and
											 * the right side
											 */

        ydiff = xyLeftAvg[1] - xyRtAvg[1];  /*
											 * difference between the average of
											 * y coordinates on the leftside and
											 * right side blocks
											 */

        // System.out.println("y1-y2 : " + ydiff);

        /* compute angle of rotation in radians */
        angle = (double) Math.atan2(ydiff, xdiff);
        // System.out.println("image rotated by " + ang1);

        /* convert the angle of rotation to degrees & rotate the image */
        ip.rotate(Math.toDegrees(angle));

        // System.out.println(" lft block after rotation");
        leftBlocks = Coordinates.computeCorners(ip, xstartL, xendL);
        computeAvg(leftBlocks, xyLeftAvg, xLtotal, yLtotal);

        // System.out.println("Right block aftr rotation");
        RightBlocks = Coordinates.computeCorners(ip, xstartR, xendR);
        computeAvg(RightBlocks, xyRtAvg, xRtotal, yRtotal);
        ydiff = xyLeftAvg[1] - xyRtAvg[1]; // difference between the average of
											// y coordinates on the leftside and
											// right side blocks

        // System.out.println("y1-y2 : " + ydiff);
    }

    /**
	 * This function process the correct answer sheet
	 * 
	 * @param str
	 *            :path of the image
	 * @throws IOException
	 */
    public byte[] processCorrectSheet(String str, int testid, int noOfQues) throws IOException {
    	correctAns = new byte[noOfQues+1];
    	
    	implus = new ImagePlus(str);

        ip = implus.getChannelProcessor(); // loads the image

        ip.dilate(); // to fill the gaps in the marked circles

        ip.erode(); // to remove extra boundary pixels added during dilation

        
        rotateImg(ip);

        // "leftBlocks" contains the top and bottom coordinates of each block on
		// the left side

        // stores the mid point of the left & right side blocks in arraylist
        ArrayList<MidPoint> ansDetail_mpLeft = MidPoint.compute_block_midPoint(leftBlocks,
                11, leftBlocks.size() - 1, ip); // other sheets
        ArrayList<MidPoint> ansDetail_mpRight = MidPoint.compute_block_midPoint(RightBlocks,
                11, RightBlocks.size() - 1, ip); // other sheets
        ip.erode();
        ip.erode(); /* to erode the unfilled circles & othet text */
       // ip.erode();

        ip.dilate(); // to make the marks visible

        /*
		 * These functions return the correct answers in each section
		 */
        this.correctAns = CirclePosition.getAns(ip, ansDetail_mpLeft,
                ansDetail_mpRight, xyRtAvg[0], xyLeftAvg[0],
                (int) (ip.getWidth() * 7) / 100,
                (int) (ip.getWidth() * 94) / 100, noOfQues);
        System.out.println("b4 correct ans");
       // convertToString(correctAns);
       return correctAns;

        /*
		 * These functions inserts the correct answers of easch section in the
		 * database
		 */
        /*
        int i=1;
        System.out.println(" inside rotaete size" +EachCandidateAns.confirmCorrrectAns.size);
        for(String s:EachCandidateAns.confirmCorrrectAns){
        	
        	System.out.println(i+" "+ s);
        	i++;
        }
*/
              // CorrectAns.insertCorrectAns(correctAns, testid);
        
    }

    public ArrayList<String> convertToString(byte[] correctAns ){
    	
    	ArrayList<String> AnsList = new ArrayList<String>(correctAns.length);
    	//System.out.println("inside " + AnsList.size());
    	for(int i=0;i<correctAns.length;i++){
    		
    		switch(correctAns[i]){
    		case 1:
    			   AnsList.add("A");
    			   break;
    		case 2:
    			  AnsList.add("B");
    	          break;
    		case 4:
    			
    			AnsList.add("C");
    			break;
    		case 8:
    			AnsList.add("D");
    			break;
    		case 3:
 			   AnsList.add("A+B");
 			   
 			   break;
 		case 5:
 			  AnsList.add("A+C");
 	          break;
 		case 6:
 			AnsList.add("B+C");
 			break;
 		case 7:
 			AnsList.add("A+B+C");
 			break;
 		case 9:
			   AnsList.add("A+D");
			   break;
		case 10:
			  AnsList.add(i,"B+D");
	          break;
		case 11:
			AnsList.add("A+B+D");
			break;
		case 12:
			AnsList.add("D+C");
			break;
		case 13:
			AnsList.add("A+C+D");
			break;
		case 14:
			AnsList.add("B+C+D");
			break;
		case 15:	
		     AnsList.add("A+B+C+D");
		     break;
		    default:
		    	AnsList.add("x");
		 		    			
    		
    		}
    		
    	}
    	/*
    	System.out.println("size" + AnsList.size());
    	
    	for(int k=1;k<AnsList.size();k++)
    	{
        	System.out.println("get : " + AnsList.get(k));
    	}
    	System.out.println("get : " + AnsList.get(1));
    	*/
    	return AnsList;
    }
    /**
     * This function process the response sheets of the candidate
     * @param str
     * @param testid
     * @param noOfQues
     */
    
    public void processSheet(String imgPath, int testid, int noOfQues) {
        String rno="-1"; // holds the rno no. obtained after processing the
							// sheet
        attemptAns = new byte[noOfQues+1]; 
        String fileName = new File(imgPath).getName();
        try {
        	
        	
            implus = new ImagePlus(imgPath);

            ip = implus.getChannelProcessor(); // load the image
           
        } catch (Exception e) {
           System.out.println("error in loading mage");
           log.error("error in loading mage");

            return;
        }

        ip.dilate(); // to fill the gaps in the marked circles

        ip.erode(); // to remove the extra boundary pixels add during dilation

        System.out.println("1");
        rotateImg(ip); // rotate to correct the tilt

        /*
		 * stores the mid points of the blocks in the student info. part of the
		 * sheet(first 10 blocks)
		 */
        System.out.println("2");
        ArrayList<MidPoint> studentDetail_mpLeft = MidPoint.compute_block_midPoint(leftBlocks,
                1, 10, ip); // other sheets
        ArrayList<MidPoint> studentDetail_mpRight = MidPoint.compute_block_midPoint(RightBlocks,
                1, 10, ip); // other sheets

        /*
		 * stores the coordinates of mid point of the blocks in left & right
		 * side of the sheet(last 13 blocks)
		 */
        System.out.println("3");
        ArrayList<MidPoint> ansDetail_mpLeft = MidPoint.compute_block_midPoint(leftBlocks,
                11, leftBlocks.size() - 1, ip); // other sheets
        ArrayList<MidPoint> ansDetail_mpRight = MidPoint.compute_block_midPoint(RightBlocks,
                11, RightBlocks.size() - 1, ip); // other sheets

        System.out.println("3");
        ip.erode();
        ip.erode();

        ip.dilate();
        //ip.dilate();
        RotateImg.infoFlag=true;

        // This function returns the roll no of the candidate
        
        rno= CirclePosition.getCandidateInfo(ip, studentDetail_mpLeft,
                studentDetail_mpRight, (double)xyRtAvg[0], (double)xyLeftAvg[0],
                (int) (ip.getWidth() * 7) / 100,
                (int) (ip.getWidth() * 93) / 100, imgPath, testid);
        System.out.println("rno: " + rno);
        attemptAns = CirclePosition.getAns(ip, ansDetail_mpLeft,
                ansDetail_mpRight, xyRtAvg[0], xyLeftAvg[0],
                (int) (ip.getWidth() * 7) / 100,
                (int) (ip.getWidth() * 94) / 100, noOfQues);
        System.out.println("size of attempt" + attemptAns.length);
        Response.insert_response(attemptAns, testid,
                Integer.parseInt(rno), noOfQues, imgPath);
        //System.out.println("calling evaluate");
        evaluateSheet(Integer.parseInt(rno), testid, noOfQues, fileName);

       
		  /*try { 
			  Image img = implus.getImage(); implus.updateChannelAndDraw();
		  implus.show();
		  
		  BMP_Writer bw = new BMP_Writer();
		  
		  bw.run("");
		  
		 // bw.saveBitmap("C:/bmpImg.bmp", img, 3000, 2400);
		  } catch (Exception
		  e) { System.out.println("Exception : " + e); }*/
		 
          implus.flush();
             
    }
    
  /**
   * This method computes the number of correct attempts, wrong attempts and unattempted 
   * questions and insert this information into the database
   * @param rno
   * @param testid
   * @param noOfQues
   * @param fileName
   */  
    
    private void evaluateSheet(int rno, int testid, int noOfQues, String fileName){
    	int[] correctAttempt=new int[5];
    	int[] wrongAttempt=new int[5];
    	for(int i=0; i<5;i++){
    		correctAttempt[i]=0;
    		wrongAttempt[i]=0;
    	}
    	int responseCounter;
    	int sectionNumber=1;
    	//System.out.println("rno" + rno + "testid" +testid + " ques " +noOfQues);
    	ResultSet correctAttRs=null;
    	ResultSet wrongAttRs = null;
    	ResultSet sectionDetailRs=null;
    	Connection con =null;
    	  try {
               con = Connect.prepareConnection();
              con.setAutoCommit(false);
             // System.out.println("inside eva");
              
              PreparedStatement ps = con.prepareStatement(
              "SELECT Section_number, No_of_question FROM testsectiondetail t where TestId=?");
              ps.setInt(1, testid);

              sectionDetailRs = ps.executeQuery();
                 
              ps = con.prepareStatement(
              "Select count(*), c.SectionNumber from correctans c, response r where ((c.Ques_no=r.ques_no)AND (c.TestId=?)AND (c.Answer = r.ans)AND(c.TestId=r.TestId)AND (r.RollNo=?) AND (r.FileName=?)AND (c.Ques_no NOT IN (select WrongQuestionNo from wrongquestion where TestId=?))) group by c.SectionNumber");
           ps.setInt(1, testid);
           ps.setInt(2, rno);
           ps.setString(3, fileName);
           ps.setInt(4, testid); 
      correctAttRs = ps.executeQuery();
     // System.out.println("Aftr exe corr");
     
      while(correctAttRs.next()){
    	  correctAttempt[correctAttRs.getInt(2)]= correctAttRs.getInt(1);
    	  System.out.println(" correct :  " + correctAttRs.getInt(2) + " "+ correctAttRs.getInt(1) + "  file : "+ fileName);
    	 
      }
      /*correctAttRs.next();
      System.out.println("correct Ans : " + correctAttRs.getInt(1));
      */
      ps = con.prepareStatement(
      "Select count(*), c.SectionNumber from correctans c, response r where ((c.Ques_no=r.ques_no)AND (c.TestId=?)AND (c.Answer != r.ans)AND(c.TestId=r.TestId)AND (r.RollNo=?) AND (r.FileName=?) AND (c.Ques_no NOT IN (select WrongQuestionNo from wrongquestion where TestId=?))) group by c.SectionNumber");
   ps.setInt(1, testid);
   ps.setInt(2, rno);
   ps.setString(3, fileName);
   ps.setInt(4,testid);
 wrongAttRs = ps.executeQuery();
 
 //System.out.println("After executing wrong");
 while(wrongAttRs.next()){
	  wrongAttempt[wrongAttRs.getInt(2)]= wrongAttRs.getInt(1);
	 // System.out.println(" wrong :  " + wrongAttRs.getInt(2) + " "+ wrongAttRs.getInt(1) + "  file : "+ fileName);
	 
	  
 }
 
   //wrongAttempt = rs.getInt(1); 
 
 

  
  /* System.out.println("correct Attempt : " + correctAttempt);
   System.out.println("wrong Attempt : " + wrongAttempt);
   System.out.println("unattempt : " + (noOfQues- (correctAttempt + wrongAttempt)));*/
    
 System.out.println("1");
 int secCount=1;
 while(sectionDetailRs.next()){
	 
	 
	// System.out.println("sec num : " + sectionDetailRs.getInt(1) + " ques : "+ sectionDetailRs.getInt(2) + " file : "+ fileName);
	// System.out.println("unatttempt :" + (sectionDetailRs.getInt(2)-(correctAttempt[secCount] + wrongAttempt[secCount])));
                ps = con.prepareStatement(
                          "insert into attempt_info(TestId, RollNo, Correct_attempt, Wrong_attempt, Unattempt, SectionNumber, FileName) values (?,?,?,?,?,?,?)");
                  ps.setInt(1, testid);
                  ps.setInt(2, rno);
                  ps.setInt(3, correctAttempt[secCount]);
                  ps.setInt(4, wrongAttempt[secCount]);
                  ps.setInt(5, (sectionDetailRs.getInt(2)-(correctAttempt[secCount] + wrongAttempt[secCount])));
                  ps.setInt(6, sectionDetailRs.getInt(1));
                  ps.setString(7, fileName);
                  int i = ps.executeUpdate();
                  secCount++;
                 // sectionNumber++;
   }
 con.commit();                 
          } catch (Exception e) {
             System.out.println("error while insert in attempt rotate img" + e);
          }
          finally{
        	  Connect.freeConnection(con);
          }

    }

    // ------------------------------Evaluate Answers
	// --------------------------------------------------------------//

    /**
	 * This function evaluates the answers and compute the marks of each section
	 * and then the total marks
	 * 
	 * @param Filepath
	 * @param testNo
	 */
    void evaluate() {
        int totalcorrectAttempt = 0;
        int unattempted = 0;

        int wrong = 0;
       // System.out.println("Correct Ans list :");
        for(int i=1;i<=120; i++){
        	System.out.println("correct Ans : " + " ques :  " + i + "  ans : " + correctAns[i]);
        }

        System.out.println("Attempt Ans list :");
        for(int i=1;i<=120; i++){
        	System.out.println("Attempt Ans : " + " ques :  " + i + "  ans : " + attemptAns[i]);
        }
        for (int i = 1; i < 121; i++) {
            if ((this.attemptAns[i]) == (this.correctAns[i])) {
            	System.out.println("ques :"+ i +  " attempted correct Ans " + correctAns[i]);
                totalcorrectAttempt++;
            }

            if ((this.attemptAns[i] != 0) && (this.attemptAns[i] != this.correctAns[i])) {
            	System.out.println("ques : "+ i+" wrong Ans , correct " + this.correctAns[i] + "atmpt" + this.attemptAns[i]);
                wrong++;
            }

            if (this.attemptAns[i] == 0) {
            	System.out.println("un attempted Ans : " + this.attemptAns[i]);
                unattempted++;
            }
        }
/*
        System.out.println("correct  Answer " + totalcorrectAttempt);
        System.out.println("wrong : " + wrong);
        System.out.println("unattempt " + unattempted);
        */
    }

    
    // ------------------------------process base
	// sheet-------------------------------------------------------------//
    /**
	 * 
	 * @param str
	 * @throws IOException
	 *             This method process base sheet to be executed only once
	 */
    public void processBaseSheet(String str) throws IOException {
        implus = new ImagePlus(str);

        ip = implus.getChannelProcessor(); // load the image

        ip.dilate(); // to fill the gaps in the marked circles
        ip.dilate();

        ip.erode(); // to erode the extra boundary pixels added during dilation
        ip.erode();

        rotateImg(ip);

        // stores the coordinates of mid points of the blocks in the student
		// info. part of the sheet (first 10 blocks)
        ArrayList<MidPoint> baseStudentDetail_mpLeft = MidPoint.compute_midPoint_basesheet_blocks(leftBlocks,
                1, 10, ip); // base sheet
        ArrayList<MidPoint> baseStudentDetail_mpRight = MidPoint.compute_midPoint_basesheet_blocks(RightBlocks,
                1, 10, ip); // base sheet

        // stores the coordinates of mid point of the blocks in the answer
		// detail part of the sheet(last 13 blocks)
        ArrayList<MidPoint> baseAnsDetail_mpLeft = MidPoint.compute_midPoint_basesheet_blocks(leftBlocks,
                11, leftBlocks.size() - 1, ip); // base sheets
        ArrayList<MidPoint> baseAnsDetail_mpRight = MidPoint.compute_midPoint_basesheet_blocks(RightBlocks,
                11, RightBlocks.size() - 1, ip); // base sheets

        ip.erode();
        ip.erode();

        ip.dilate();
        // ip.dilate();

        // process the student detail part of the sheet
        BaseSheet.computeInfoCirclePosition(ip, baseStudentDetail_mpLeft,
            baseStudentDetail_mpRight, xyRtAvg[0], xyLeftAvg[0],
            (int) (ip.getWidth() * 10) / 100, (int) (ip.getWidth() * 92) / 100);

        // process the ans. detail part of the sheet
        ///process the ans. detail part of the sheet
        
       BaseSheet.computeAnsCirclePosition(ip, baseAnsDetail_mpLeft,
            baseAnsDetail_mpRight, xyRtAvg[0], xyLeftAvg[0],
        
            (int) (ip.getWidth() * 7) / 100, (int) (ip.getWidth() * 95) / 100);
      
        
        try {
            Image img = implus.getImage();

            if (img == null) {
                System.out.println("image is null");
            }

            implus.updateAndDraw();
            implus.show();

            BMP_Writer bw = new BMP_Writer();

            bw.run("");
            bw.saveBitmap("C:/Img1.bmp", img, 2480, 3507);
        } catch (Exception e) {
            System.out.println("error in save : " + e);
        }

        implus.flush();
    }

    // ---------------------------compute Average of top left coordinates of
	// blocks-----------------------------//
    /**
	 * 
	 * @param Blocks
	 *            :contains the top left coordinates of each block
	 * @param XYAvg :
	 *            stores the x & y coordinate of each block
	 * @param xLtotal:
	 *            sum of x coordinate
	 * @param yLtotal:
	 *            sum of y coordinate
	 */
    public void computeAvg(ArrayList<Coordinates> Blocks, float[] XYAvg,
        float xtotal, float ytotal) {
        int i = 0;

        for (Coordinates obj : Blocks) {
            xtotal = xtotal + obj.x;
            ytotal = ytotal + obj.y;
            i++;

            // System.out.println("block : " + i + " y: " + obj.y + " ytotal " +
			// ytotal);
        } // end for

        XYAvg[0] = xtotal / Blocks.size();
        XYAvg[1] = ytotal / Blocks.size();

        // System.out.println("Avg" + XYAvg[1]);
    }
}
