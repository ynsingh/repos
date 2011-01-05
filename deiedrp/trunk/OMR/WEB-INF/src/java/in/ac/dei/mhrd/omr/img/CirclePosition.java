package in.ac.dei.mhrd.omr.img;

import java.awt.Color;

import java.util.*;


/**
 * @author Anshul Agarwal
 *
 * This class finds the position of the circles marked by the student and the associated information
 **/
public class CirclePosition {
	
	/**
    * This function detects marked circles in the candidate's information part of the sheet and
    *  
    *
    * @param ip :reference of image
    * @param infoLeft : mid points of left side  blocks
    * @param infoRight : mid  points of right side blocks
    * @param xrtAvg : Average of x coordinates of the blocks on the right side
    * @param xleftAvg : Average of x coordinates of the blocks on the left side
    * @param xstart : where the processing begins
    * @param xend : where the processing ends
    * @return
    */
    public static String getCandidateInfo(ij.process.ImageProcessor ip,
        ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight, double xrtAvg,
        double xleftAvg, int xstart, int xend, String filename, int testid) {
        int count = -1; //count the no. of rows
        int c = 0; // count the no. of circles

        Double posStart;
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
                                         ymidLeft.mp_ratio, Infoobj, y);

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

       String roll =  Infoobj.display(filename, testid);
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
      @param ip :reference of image
    * @param infoLeft : mid points of left side  blocks
    * @param infoRight : mid  points of right side blocks
    * @param xrtAvg : Average of x coordinates of the blocks on the right side
    * @param xleftAvg : Average of x coordinates of the blocks on the left side
    * @param xstart : where the processing begins
    * @param xend : where the processing ends
     * @param noOfQues : no of ques in each sec
     * @param sec : section number
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
            /**
             * variables for straight line equation bw mid points of left & rt side blocks.
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
                          //  ip.drawPixel(i, y);
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

        obj.displayAns();

        return obj.response;
    } // end function

    /**
     * This function returns true if group of 40 pixels found around a pixel
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
        int pixelGroup=(int)(ip.getWidth()*1.1)/100;
        
        while ((i <= 6) && (j <= 6)) {
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
}
   