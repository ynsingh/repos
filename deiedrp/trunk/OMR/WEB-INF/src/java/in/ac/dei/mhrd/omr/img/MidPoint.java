/*
 * @(#) MidPoint.java
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

import java.util.ArrayList;
import ij.*;
import ij.process.ImageProcessor;

import org.apache.log4j.Logger;


/**
 *This class defines the method that calculates the mid point
 * of the blocks and 30% above and below it
 *
 *@author Anshul Agarwal
 *@Creation date:09-27-2010
 *@version 1.0
 */
public class MidPoint {
	private static Logger log = Logger.getLogger(MidPoint.class);

    public int mp1; //stores 30% above mid point
    public int xmp;
    public int mp2; // stores 30% below mid point
    public int mp; // mid point
    public double yr1; // ratio of 30% above midpoint
    public double yr2; // ratio of 30% below midpoint
    public double mp_ratio; // ratio of midpoint

    /**
     * This method calculates the mid point of blocks in the sheet
     * @param RBlocks
     * @param start
     * @param end
     * @return
     */
    public static ArrayList<MidPoint> compute_block_midPoint(ArrayList<Coordinates> RBlocks,
        int start, int end, ImageProcessor ip) {
        /* create an arraylist that stores object of type yclass
         * This list will contain midpoint of each block
         */
        ArrayList<MidPoint> yobj = new ArrayList<MidPoint>();
        int block_height = (int)((ip.getHeight()*1.5)/100);
        
  try{
        for (int i = start; i <= end; i++) {
            MidPoint yc = new MidPoint();
            Coordinates obj1 = RBlocks.get(i - 1);
            Coordinates obj2 = RBlocks.get(i);

            //compute the mid point of 10th block  
            if (i == 10) {
                yc.mp = obj1.y + (block_height / 2);
                yc.xmp = obj1.x;
            } else if(i<10){
               yc.mp = obj1.y + ((int) ((obj2.y - obj1.y) * 1) / 2); //compute the y coordinate of mipoint of other blocks
               yc.xmp = obj1.x; 
               /*
               System.out.println("obj1.y : " + obj1.y);
                System.out.println("obj2.y : " + obj2.y);
                System.out.println("y  mp : y: " + yc.mp +" x: " + obj1.x);
                */
                yc.xmp = obj1.x;
            }
            else{
            	 yc.mp = obj1.y + ((int) ((obj2.y - obj1.y) * 70) / 100); //compute the y coordinate of mipoint of other blocks
            	 yc.xmp = obj1.x;
            	 /*
            	 System.out.println("obj1.y : " + obj1.y);
                 System.out.println("obj2.y : " + obj2.y);
                 System.out.println("y  mp : y: " + yc.mp +" x: " + obj1.x);
                 */
            }

            //compute the ratio of the midpoint
            yc.mp_ratio = (double) (RBlocks.get(RBlocks.size() - 1).y - yc.mp) / (double) (yc.mp -
                (RBlocks.get(0)).y);

            yobj.add(yc);
        } // end for
  }catch (Exception e) {
	  log.error("Error while computing midpoint of blocks.");
	//System.out.println("mp :" + e ); // TODO: handle exception
}
        //System.out.println("blocks height" + ( RBlocks.get(RBlocks.size() - 1).y - RBlocks.get(0).y ));
        return yobj;
    }

    /**
     * This method calculates the mid point and  30% above and below
     * execute only for base sheet
     * @param RBlocks
     * @param start
     * @param end
     * @return
     */
    public static ArrayList<MidPoint> compute_midPoint_basesheet_blocks(
        ArrayList<Coordinates> RBlocks, int start, int end, ImageProcessor ip) {
        double above_mp_percent = 20 / 100;
        double below_mp_percent = 80 / 100;
        double mp_percent = 50 / 100;
        int block_height = (int)((ip.getHeight()*1.5)/100);
        ArrayList<MidPoint> yobj = new ArrayList<MidPoint>();

        for (int i = start; i <= end; i++) {
            MidPoint yc = new MidPoint();
            Coordinates obj1 = RBlocks.get(i - 1);
            Coordinates obj2 = RBlocks.get(i);

            if (i == 10) {
                yc.mp1 = obj1.y + ((block_height * 20) / 100);
                yc.mp2 = obj1.y + ((block_height * 80) / 100);
                yc.mp = obj1.y + ((yc.mp1 - obj1.y) + ((yc.mp2 - yc.mp1) / 2));
                yc.xmp = obj1.x;
            } else if(i<10){
            	yc.mp1 = obj1.y + (int) (((obj2.y - obj1.y) * 20)/100);

                // 30% below midpoint
                yc.mp2 = obj1.y + (int) (((obj2.y - obj1.y) * 80)/100);
                yc.mp = obj1.y +
                    (int) ((yc.mp1 - obj1.y) +
                    (((yc.mp2 - yc.mp1) * 60)/100));
                
               /* System.out.println("obj1.y : " + obj1.y);
                System.out.println("obj2.y : " + obj2.y);
                System.out.println("y  mp : y: " + yc.mp +" x: " + obj1.x);
                               */
                yc.xmp = obj1.x;
            }
                       	
            	else {
                // 30% above mid point
                yc.mp1 = obj1.y + (int) (((obj2.y - obj1.y) * 20)/100);

                // 30% below midpoint
                yc.mp2 = obj1.y + (int) (((obj2.y - obj1.y) * 95)/100);
                yc.mp = obj1.y +
                    (int) ((yc.mp1 - obj1.y) +
                    (((yc.mp2 - yc.mp1) * 70)/100));
                
               /* System.out.println("obj1.y : " + obj1.y);
                System.out.println("obj2.y : " + obj2.y);
                System.out.println("y  mp : y: " + yc.mp +" x: " + obj1.x);*/
                
                
                yc.xmp = obj1.x;
            }

            //compute ratio
            yc.yr1 = (double) (RBlocks.get(RBlocks.size() - 1).y - yc.mp1) / (double) (yc.mp1 -
                (RBlocks.get(0)).y);
            yc.yr2 = (double) (RBlocks.get(RBlocks.size() - 1).y - yc.mp2) / (double) (yc.mp2 -
                (RBlocks.get(0)).y);
            yc.mp_ratio = (double) (RBlocks.get(RBlocks.size() - 1).y - yc.mp) / (double) (yc.mp -
                (RBlocks.get(0)).y);

            yobj.add(yc);
        }//end for
        return yobj;
    }
}
