package in.ac.dei.mhrd.omr.img;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import ij.ImagePlus;

import ij.plugin.BMP_Writer;

import java.awt.Color;
import java.awt.Image;

import java.io.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;



/**
 * @Author Anshul Agarwal
 * 
 * This class skew the tilted image and then process the image further
 */
public class RotateImg {
	
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

            return;
        }

        ip.dilate(); // to fill the gaps in the marked circles

        ip.erode(); // to remove the extra boundary pixels add during dilation

        rotateImg(ip); // rotate to correct the tilt

        /*
		 * stores the mid points of the blocks in the student info. part of the
		 * sheet(first 10 blocks)
		 */
        ArrayList<MidPoint> studentDetail_mpLeft = MidPoint.compute_block_midPoint(leftBlocks,
                1, 10, ip); // other sheets
        ArrayList<MidPoint> studentDetail_mpRight = MidPoint.compute_block_midPoint(RightBlocks,
                1, 10, ip); // other sheets

        /*
		 * stores the coordinates of mid point of the blocks in left & right
		 * side of the sheet(last 13 blocks)
		 */
        ArrayList<MidPoint> ansDetail_mpLeft = MidPoint.compute_block_midPoint(leftBlocks,
                11, leftBlocks.size() - 1, ip); // other sheets
        ArrayList<MidPoint> ansDetail_mpRight = MidPoint.compute_block_midPoint(RightBlocks,
                11, RightBlocks.size() - 1, ip); // other sheets

        ip.erode();
        ip.erode();

        ip.dilate();
        //ip.dilate();
        RotateImg.infoFlag=true;

        // This function returns the rno
        
        rno= CirclePosition.getCandidateInfo(ip, studentDetail_mpLeft,
                studentDetail_mpRight, (double)xyRtAvg[0], (double)xyLeftAvg[0],
                (int) (ip.getWidth() * 7) / 100,
                (int) (ip.getWidth() * 93) / 100, imgPath, testid);
        
        attemptAns = CirclePosition.getAns(ip, ansDetail_mpLeft,
                ansDetail_mpRight, xyRtAvg[0], xyLeftAvg[0],
                (int) (ip.getWidth() * 7) / 100,
                (int) (ip.getWidth() * 94) / 100, noOfQues);
        //System.out.println("size of attempt" + attemptAns.length);
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
        
                    // this function insert the responses in the database
                                     /*
										 * //this function evaluates the
										 * responses and compute the total marks
										 * EvaluateAns(str,
										 * Integer.parseInt(rno)); } else { //
										 * if there is error in the rno no,
										 * filename & description is inserted in
										 * the log table File filePath = new
										 * File(str);
										 * LogEntry.insert_Log(filePath.getName(),
										 * "error in rno no."); }
										 */
       
  
       
       
    }
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
    	
    	  try {
              Connection con = Connect.prepareConnection();
              con.setAutoCommit(false);
             // System.out.println("inside eva");
              
              PreparedStatement ps = con.prepareStatement(
              "SELECT Section_number, No_of_question FROM testsectiondetail t where TestId=?");
              ps.setInt(1, testid);

              sectionDetailRs = ps.executeQuery();
                 
              ps = con.prepareStatement(
              "Select count(*), c.sectionNumber from correctans c, response r where ((c.Ques_no=r.ques_no)AND (c.TestId=?)AND (c.Answer = r.ans)AND(c.TestId=r.TestId)AND (r.RollNo=?) AND (r.FileName=?)AND (c.Ques_no NOT IN (select WrongQuestionNo from wrongQuestion where testId=?))) group by c.sectionNumber");
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
      "Select count(*), c.sectionNumber from correctans c, response r where ((c.Ques_no=r.ques_no)AND (c.TestId=?)AND (c.Answer != r.ans)AND(c.TestId=r.TestId)AND (r.RollNo=?) AND (r.FileName=?) AND (c.Ques_no NOT IN (select WrongQuestionNo from wrongQuestion where testId=?))) group by c.sectionNumber");
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
 
 

  
  /* System.out.println("correctr Attempt : " + correctAttempt);
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
 con.close();
          } catch (Exception e) {
             System.out.println("error while insert in attempt rotate img" + e);
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

    /*
	 * void evaluateAns(String Filepath) { int correct_attempt = 0;
	 * 
	 * int unattempted = 0; int testNo; int total_marks = 0; int wrong = 0;
	 * 
	 * File filename = new File(Filepath); // to get the file name
	 * 
	 * try { total_marks = 0;
	 * 
	 * correct_attempt = 0; unattempted = 0; wrong = 0; Connection con1 =
	 * Connect.prepareConnection(); PreparedStatement ps1 =
	 * con1.prepareStatement( "select distinct(TestNo) from Response where
	 * Filename=?"); ps1.setString(1, "Scan10067.BMP"); ResultSet rs1 =
	 * ps1.executeQuery(); System.out.println("se 1 : "); while(rs1.next()){
	 * testNo = Integer.parseInt(rs1.getString(1)); System.out.println("testNo : " +
	 * testNo); for (int i = 1; i < noOfQues; i++) { Connection con =
	 * Connect.prepareConnection(); PreparedStatement ps = con.prepareStatement(
	 * "select ans from correctAns where ques_no=?"); ps.setInt(1, i);
	 * 
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * PreparedStatement ps2 = con.prepareStatement( "select ans from Response
	 * where ques_no=? AND TestNo = ?"); ps2.setInt(1, i); ps2.setInt(2,
	 * testNo);
	 * 
	 * 
	 * ResultSet rs2 = ps2.executeQuery();
	 * 
	 * if (rs.next() && rs2.next()) { if (rs.getInt(1) == rs2.getInt(1)) {
	 * correct_attempt++; System.out.println("correct ans : " + i + " option : "+
	 * rs2.getInt(1)); } else { wrong++; System.out.println("wrong : " + i + "
	 * option : "+ rs2.getInt(1)); } } else { System.out.println("unattempted : " +
	 * i); unattempted++; } }
	 * 
	 * sec1_marks = correct_attempt*4-wrong; total_marks = total_marks +
	 * sec1_marks; System.out.println("sec1 marks :" + sec1_marks);
	 * System.out.println("wtrong attempts :" + wrong);
	 * System.out.println("correct attempts : " + correct_attempt);
	 * correct_attempt = 0; unattempted = 0; wrong = 0;
	 * 
	 * System.out.println("sec 2 ");
	 *  // compute correct attempts in second section for (int i = 1; i <
	 * noOfQues; i++) { Connection con = Connect.prepareConnection();
	 * PreparedStatement ps = con.prepareStatement( "select ans from correctAns
	 * where ques_no=?"); ps.setInt(1, i);
	 * 
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * PreparedStatement ps2 = con.prepareStatement( "select ans from Response
	 * where ques_no=? AND TestNo=? AND sec=?"); ps2.setInt(1, i); ps2.setInt(2,
	 * testNo);
	 * 
	 * ResultSet rs2 = ps2.executeQuery();
	 * 
	 * if (rs.next() && rs2.next()) { if (rs.getInt(1) == rs2.getInt(1)) {
	 * 
	 * System.out.println("correct" + i + " option : "+ rs2.getInt(1));
	 * correct_attempt++; } else { System.out.println("wrong : " + i + " option : "+
	 * rs2.getInt(1));
	 * 
	 * wrong++; } } else { System.out.println("unattempt : " + i);
	 * unattempted++; } }
	 * 
	 * System.out.println("In sec section:"); sec2_marks =
	 * correct_attempt*4-wrong; total_marks = total_marks + sec2_marks;
	 * System.out.println("sec2 marks :" + sec2_marks);
	 * System.out.println("wtrong attempts :" + wrong);
	 * System.out.println("correct attempts : " + correct_attempt); /*
	 * Connection con = Connect.prepareConnection();
	 * 
	 * //inserts rno details in the database PreparedStatement ps =
	 * con.prepareStatement( "insert into marksinfo(TestNo, sec1, sec2, total,
	 * FileName) values (?,?,?,?,?)"); ps.setInt(1, testNo); ps.setInt(2,
	 * sec1_marks); ps.setInt(3, sec2_marks);
	 * 
	 * ps.setInt(4, total_marks); ps.setString(5, filename.getName());
	 * 
	 * int i = ps.executeUpdate(); System.out.println("row inserted: " + i);
	 * 
	 * sec1_marks = 0; sec2_marks = 0; wrong =0; correct_attempt=0; } }
	 * 
	 * 
	 * catch (Exception e) { System.out.println("error in evaluate : " +
	 * e.getMessage()); e.printStackTrace(); } }
	 */

    // ------------------------------process base
	// sheet-------------------------------------------------------------//
    /**
	 * 
	 * @param str
	 * @throws IOException
	 *             This function process base sheet to be executed only once
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
