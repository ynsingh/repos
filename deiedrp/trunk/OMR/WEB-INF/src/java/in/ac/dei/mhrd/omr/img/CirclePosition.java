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

import java.awt.Color;

import java.util.*;

import org.apache.log4j.Logger;


/**
 * This class finds the position of the circles marked by the student and 
 * the associated information
 *
 * @author Anshul Agarwal
 * Creation date:08-29-2010
 * @version 1.0
 **/
public class CirclePosition {
	private static Logger log = Logger.getLogger(Coordinates.class);

	
   /**
	* This method detects marked circles in the candidate's information part of the sheet and
    * 
	* @param ip :reference of image
    * @param infoLeft : mid points of left side  blocks
    * @param infoRight : mid  points of right side blocks
    * @param xrtAvg : Average of x coordinates of the blocks on the right side
    * @param xleftAvg : Average of x coordinates of the blocks on the left side
    * @param xstart : where the processing begins
    * @param xend : where the processing ends
    * @param filename
	* @param testid
	* @return
	*/
	public static String getCandidateInfo(ij.process.ImageProcessor ip,
        ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight, double xrtAvg,
        double xleftAvg, int xstart, int xend, String filename, int testid, String instructorTestNo) {
        int count = -1; //count the no. of rows
        int c = 0; // count the no. of circles
        
        Double posStart;// ratio of the starting position of the circle
        int x1; //coordinates of midpoint of left block
        int y1; //coordinates of midpoint of left block
        int x2; //coordinates of midpoint of right block
        int y2; //coordinates of midpoint of right block
        int y; // y coordinate for each value of x
        int circle_width = 50;
        String tno;
        
        EachCandidateInfo Infoobj = new EachCandidateInfo();
         MidPoint ymidLeft;
         MidPoint ymidRight;

        for (int j = 0; j < infoLeft.size(); j++) {
            ymidLeft = infoLeft.get(j);
            ymidRight = infoRight.get(j);
            /**
             * variables for straight line equation bw mid points of left & rt side blocks.
             */
            x1 = ymidLeft.xmp;
            y1 = ymidLeft.mp;
            x2 = ymidRight.xmp;
            y2 = ymidRight.mp;
            count++;
             
            
            for (int i = xstart; i < xend; i++) {
                y = (((y2 - y1) * (i - x1)) / (x2 - x1)) + y1; // compute y for each value of x using straight line equation
                  ip.drawPixel(i, y);
                
               // if (ip.getPixelValue(i, y) == 0) { // search black pixel 

                	
                  //  if (checkPixelGroup(ip, i, y)) { // search group of black pixel

                       /* int start_point = i;

                        if (start_point > xrtAvg) {
                            break;
                        }
                        */
                         while(ip.getPixelValue(i, y) == 0) {
                           

                            if (checkPixelGroup(ip, i, y)) {
                            	 int start_point = i;
                                 ip.drawPixel(i, y);    
                                 if (start_point > xrtAvg) {
                                     break;
                                 }
                            	ip.drawPixel(i, y);
                                /*
                                 * if group of black pixel found, compute its ratio
                                 */
                                posStart = ((start_point - xleftAvg) / (xrtAvg -
                                    start_point));
                                /*System.out.println(" connected row :" + j + " x: " + i+ " y : " + y);*/
                                if (posStart.isInfinite() || posStart.isNaN()) {
                                    break;
                                }

                                //check whether posstart lies in the database or not
                                boolean b = CandidateDetail.matchCandidateInfo((double) posStart,
                                         ymidLeft.mp_ratio, Infoobj, y, 0);

                                if (b) {
                                    i = i + circle_width; // distance between two circles

                                    c++; // increment if circle found

                                    break;
                                }
                            }

                            i++;
                        }//while (ip.getPixelValue(i, y) == 0); //end while
                  //  } //end if(connected..)
               // }// end if (getPixel..)
            }
        }

       String roll =  Infoobj.getCandidateId(filename, testid, instructorTestNo);
        //System.out.println("total circle found : " + c);
       // System.out.println("tno in c p " + tno);
        //if r pno no is not -1 , insert into the database
       /* if (tno != "-1") {
            obj.insert_position(filename);
        }
*/
        return roll;
            }

   /**
    *
    * @param ip :reference of image
    * @param infoLeft : mid points of left side  blocks
    * @param infoRight : mid  points of right side blocks
    * @param xrtAvg : Average of x coordinates of the blocks on the right side
    * @param xleftAvg : Average of x coordinates of the blocks on the left side
    * @param xstart : where the processing begins
    * @param xend : where the processing ends
    * @param noOfQues : no of questions
    * @return
    */
    public static byte[] getAns(ij.process.ImageProcessor ip,
        ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight, double xrtAvg,
        double xleftAvg, int xstart, int xend, int no_of_ques) {
        int count = -1;
        int c = 0;
        int circle_width = 50;
        
        Double posStart = (double) 0.0;
        EachCandidateAns obj = new EachCandidateAns(no_of_ques);
        int x1; // x coordinates of midpoint of left block
        int y1; // y coordinates of midpoint of left block
        int x2; // x coordinates of midpoint of right block
        int y2; // x coordinates of midpoint of right block
        int y; // y coordinate for each value of x

        
        for (int j = 0; j < infoLeft.size(); j++) {
        	
            MidPoint ymidLeft = infoLeft.get(j); //retrieve midpoints of leftblocks
            MidPoint ymidRight = infoRight.get(j); // retrieve midpoint of right blocks
            /*
             * variables for straight line equation between mid points of left & right side blocks.
             */
            x1 = ymidLeft.xmp;
            y1 = ymidLeft.mp;
            x2 = ymidRight.xmp;
            y2 = ymidRight.mp;
            count++;
            for (int i = xstart; i < xend; i++) {
            	/*
            	System.out.println("inside x block of ans : ");
            	System.out.println("x1 : " + x1);
            	System.out.println("x2 : " + x2);
            	System.out.println(" y1 : " + y1);
            	System.out.println(" y2 : " + y2);
            	
            	System.out.println("(y2 - y1) * (i - x1)" + ((y2 - y1) * (i - x1)));
            	System.out.println(" x2 - x1 : " + (x2-x1));
            	*/
            	
            	/* computes y for each value of x using straight line equation */
                y = (((y2 - y1) * (i - x1)) / (x2 - x1)) + y1; 

                 ip.drawPixel(i, y);
                 
                if (ip.getPixelValue(i, y) == 0) {             /*search for black pixel*/

                	
                    if (checkPixelGroup(ip, i, y)) {            /* search for group of pixels */
                    	
                        // ip.drawPixel(i, y);
                        int circleStartPoint = i;

                        while (ip.getPixelValue(i, y) == 0) { // continues searching group of black pixel until matches with database
                        	  if(circleStartPoint>x2){
                              	break;
                              }
                        	circleStartPoint = i;
                            ip.drawPixel(i, y);
                            if (checkPixelGroup(ip, i, y)) {
                            //	System.out.println("row : " + j + "connecte pixel found at x : " + i + " y : " + y);
                            	
                                posStart = (double) ((circleStartPoint - xleftAvg) / (xrtAvg -
                                    circleStartPoint));
                                
                                if (posStart.isInfinite() || posStart.isNaN()) {
                                    break;
                                }

                                /* Match ratio with the database and retrieve correcircleStartPointonding information  */
                                boolean b = AnsDetail.matchAns((double) posStart,
                                        (double) ymidLeft.mp_ratio, obj, ymidLeft.mp);

                                if (b) {
                                    i = i + circle_width;
                                    c++; // increment when circle found
                                    break;
                                }
                            }

                            i++;
                        } // end while

                       
                    } // end if(connected.)
                } // end if(getPixelValue..)
            } //end x loop
        } // end y loop

      //  obj.displayAns();

        return obj.response;
    } // end method

    /**
     * This method returns true if group of pixel finds around a pixel
     * @param ip
     * @param xc
     * @param yc
     * @return
     */
    static boolean checkPixelGroup(ij.process.ImageProcessor ip, int xc, int yc) {
        int connect = 0;
        int i = 1;
        int j = 1;
        boolean found = false;
        int pixelGroup=(int)(ip.getWidth()*1.5)/100;
       // System.out.println("checkPixelGroup CPositions: " );
        while ((i <= 8) && (j <=8)) {
            j++;
            i++;

            if (ip.getPixelValue(xc + i, yc) == 0) {
                connect++;
                
            }

            if (ip.getPixelValue(xc - i, yc) == 0) {
                connect++;
               
            }

            if (ip.getPixelValue(xc, yc + j) == 0) {
                connect++;
               
            }

            if (ip.getPixelValue(xc, yc - j) == 0) {
                connect++;
               
            }

            if (ip.getPixelValue(xc + i, yc + j) == 0) {
                connect++;
               
            }

            if (ip.getPixelValue(xc - i, yc - j) == 0) {
                connect++;
                
            }

            if (ip.getPixelValue(xc + i, yc - j) == 0) {
                connect++;
               
            }

            if (ip.getPixelValue(xc - i, yc + j) == 0) {
                connect++;
               
            }
            
            if (connect > pixelGroup) {
                found = true;
               break;
            } 
        }// end while

       
      
        return found;
    }
	
	/**
     * To get the group of Correct Answer Sheet
     * @param ip
     * @param infoLeft
     * @param infoRight
     * @param xrtAvg
     * @param xleftAvg
     * @param xstart
     * @param xend
     * @param countgroup
     * @return
     */
    public static String getGroupInfo(ij.process.ImageProcessor ip,
            ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight, double xrtAvg,
            double xleftAvg, int xstart, int xend, int countgroup) {
        int count = -1; //count the no. of rows
        int c = 0; // count the no. of circles
        Double posStart;// ratio of the starting position of the circle
        int x1; //coordinates of midpoint of left block
        int y1; //coordinates of midpoint of left block
        int x2; //coordinates of midpoint of right block
        int y2; //coordinates of midpoint of right block
        int y; // y coordinate for each value of x
        int circle_width = 50;
        
        EachCandidateInfo Infoobj = new EachCandidateInfo();
        MidPoint ymidLeft;
        MidPoint ymidRight;

        for (int j = 0; j < infoLeft.size(); j++) {
            ymidLeft = infoLeft.get(j);
            ymidRight = infoRight.get(j);
            /**
             * variables for straight line equation bw mid points of left & rt side blocks.
             */
            x1 = ymidLeft.xmp;
            y1 = ymidLeft.mp;
            x2 = ymidRight.xmp;
            y2 = ymidRight.mp;
            count++;
            for (int i = xstart; i < xend; i++) {
                y = (((y2 - y1) * (i - x1)) / (x2 - x1)) + y1; // compute y for each value of x using straight line equation
                ip.drawPixel(i, y);
            	while(ip.getPixelValue(i, y) == 0) {
            		if (checkPixelGroup(ip, i, y)) {
            			int start_point = i;
            			ip.drawPixel(i, y);    
            			if (start_point > xrtAvg) {
            				break;
            			}
            			ip.drawPixel(i, y);
                        /*
                         * if group of black pixel found, compute its ratio
                         */
                        posStart = ((start_point - xleftAvg) / (xrtAvg -start_point));
                        if (posStart.isInfinite() || posStart.isNaN()) {
                        	break;
                        }
                        boolean b;	
                        //check whether posstart lies in the database or not
                   		//if(countgroup>0){
                            b = CandidateDetail.matchCandidateInfo((double) posStart,ymidLeft.mp_ratio, Infoobj, y, countgroup);
                        //}
                   		//else b = CandidateDetail.matchCandidateInfoNoGroup((double) posStart,ymidLeft.mp_ratio, Infoobj, y);
                   		
                   		if (b) {
                   			i = i + circle_width; // distance between two circles
                   			c++; // increment if circle found
                   			break;
                        }
                   	}
            		i++;
            	}//while (ip.getPixelValue(i, y) == 0); //end while
            }
        }
        String gCode;
        //if(countgroup>0){
        	gCode =  Infoobj.getGroupCode();
        //}
        /*else  gCode =  Infoobj.getCode();*/
        return gCode;
    }
    
    /**
     * To Get The Candidate Id for group Sheet
     * @param ip
     * @param infoLeft
     * @param infoRight
     * @param xrtAvg
     * @param xleftAvg
     * @param xstart
     * @param xend
     * @param filename
     * @param testid
     * @param instructorTestNo
     * @param countGroup
     * @return
     */
	public static String getCandidateInfoGroup(ij.process.ImageProcessor ip,
	        ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight, double xrtAvg,
	        double xleftAvg, int xstart, int xend, String filename, int testid, String instructorTestNo, int countGroup){
		int count = -1; //count the no. of rows
		int c = 0; // count the no. of circles
		Double posStart;// ratio of the starting position of the circle
		int x1; //coordinates of midpoint of left block
		int y1; //coordinates of midpoint of left block
		int x2; //coordinates of midpoint of right block
		int y2; //coordinates of midpoint of right block
		int y; // y coordinate for each value of x
		int circle_width = 50;
		String tno;
		
		EachCandidateInfo Infoobj = new EachCandidateInfo();
		MidPoint ymidLeft;
		MidPoint ymidRight;
		for (int j = 0; j < infoLeft.size(); j++) {
			ymidLeft = infoLeft.get(j);
			ymidRight = infoRight.get(j);
			/**
			 * variables for straight line equation bw mid points of left & rt side blocks.
			 */
			x1 = ymidLeft.xmp;
			y1 = ymidLeft.mp;
			x2 = ymidRight.xmp;
			y2 = ymidRight.mp;
			count++;
			
			for (int i = xstart; i < xend; i++) 
			{
				y = (((y2 - y1) * (i - x1)) / (x2 - x1)) + y1; // compute y for each value of x using straight line equation
				ip.drawPixel(i, y);
				while(ip.getPixelValue(i, y) == 0) {
					if (checkPixelGroup(ip, i, y)) {
						int start_point = i;
						ip.drawPixel(i, y);    
						if (start_point > xrtAvg) {
							break;
						}
						ip.drawPixel(i, y);
						/*
						 * if group of black pixel found, compute its ratio
						 */
						posStart = ((start_point - xleftAvg) / (xrtAvg -start_point));
						if (posStart.isInfinite() || posStart.isNaN()) {
							break;
						}
						boolean b;
						
						//check whether posstart lies in the database or not
						//if(countGroup>0){
							b = CandidateDetail.matchCandidateInfo((double) posStart,ymidLeft.mp_ratio, Infoobj, y,countGroup);
						//}
						//else b = CandidateDetail.matchCandidateInfo((double) posStart,ymidLeft.mp_ratio, Infoobj, y,"NGC");

						if (b) {
							i = i + circle_width; // distance between two circles
							c++; // increment if circle found
							break;
						}
					}
					i++;
				}
			}
		}
		String roll;
		if(countGroup>0){
			roll =  Infoobj.getCandidateIdGroup(filename, testid, instructorTestNo);
		}
		else  roll = Infoobj.getCandidateId(filename, testid, instructorTestNo);
		return roll;
	}
	
	/**
	 * To get Group Code of the Candidates
	 * @param ip
	 * @param infoLeft
	 * @param infoRight
	 * @param xrtAvg
	 * @param xleftAvg
	 * @param xstart
	 * @param xend
	 * @param filename
	 * @param testid
	 * @param instructorTestNo
	 * @param countGroup
	 * @return
	 */
	public static String getGroupCodes(ij.process.ImageProcessor ip,
	        ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight, double xrtAvg,
	        double xleftAvg, int xstart, int xend, String filename, int testid, String instructorTestNo, int countGroup) {
	        int count = -1; //count the no. of rows
	        int c = 0; // count the no. of circles
	        Double posStart;// ratio of the starting position of the circle
	        int x1; //coordinates of midpoint of left block
	        int y1; //coordinates of midpoint of left block
	        int x2; //coordinates of midpoint of right block
	        int y2; //coordinates of midpoint of right block
	        int y; // y coordinate for each value of x
	        int circle_width = 50;
	        
	        EachCandidateInfo Infoobj = new EachCandidateInfo();
	        MidPoint ymidLeft;
	        MidPoint ymidRight;

	        for (int j = 0; j < infoLeft.size(); j++) {
	            ymidLeft = infoLeft.get(j);
	            ymidRight = infoRight.get(j);
	            /**
	             * variables for straight line equation bw mid points of left & rt side blocks.
	             */
	            x1 = ymidLeft.xmp;
	            y1 = ymidLeft.mp;
	            x2 = ymidRight.xmp;
	            y2 = ymidRight.mp;
	            count++;
	            for (int i = xstart; i < xend; i++) {
	                y = (((y2 - y1) * (i - x1)) / (x2 - x1)) + y1; // compute y for each value of x using straight line equation
	                ip.drawPixel(i, y);

	                while(ip.getPixelValue(i, y) == 0) {
	                	if (checkPixelGroup(ip, i, y)) {
	                		int start_point = i;
	                		ip.drawPixel(i, y);    
	                		if (start_point > xrtAvg) {
	                			break;
	                		}
	                		ip.drawPixel(i, y);
	                		/*
	                		 * if group of black pixel found, compute its ratio
	                		 */
	                		posStart = ((start_point - xleftAvg) / (xrtAvg -start_point));
	                		if (posStart.isInfinite() || posStart.isNaN()) {
	                			break;
	                		}
	                		//check whether posstart lies in the database or not
	                		boolean    b = CandidateDetail.matchCandidateInfo((double) posStart,ymidLeft.mp_ratio, Infoobj, y,countGroup);
	                		if (b) {
	                			i = i + circle_width; // distance between two circles
	                			c++; // increment if circle found
	                			break;
	                		}
	                	}
	                	i++;
	                }
	            }
	        }
	        String group =  Infoobj.getCandidateGroupCodes(filename, testid, instructorTestNo);
	        return group;
	}
}
   