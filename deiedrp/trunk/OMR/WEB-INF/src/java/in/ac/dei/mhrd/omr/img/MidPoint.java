package in.ac.dei.mhrd.omr.img;

import java.util.ArrayList;
import ij.*;
import ij.process.ImageProcessor;


/**
 *
 * @author Anshul Agarwal
 *This class calculates the mid point of the blocks and 30% above and below it
 */
public class MidPoint {
    public int mp1; //stores 25% above mid point
    public int xmp;
    public int mp2; // stores 25% below mid point
    public int mp; // mid point
    public double yr1; // ratio of 25% above midpoint
    public double yr2; // ratio of 25% below midpoint
    public double mp_ratio; // ratio of midpoint

    /**
     *  This function calculates the mid point of blocks in the sheet
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
	//System.out.println("mp :" + e ); // TODO: handle exception
}
        //System.out.println("blocks height" + ( RBlocks.get(RBlocks.size() - 1).y - RBlocks.get(0).y ));
        return yobj;
    }

    /**
      This function calculates the mid point and  30% above and below
       execute only for base sheet
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
        System.out.println("blocks height" + ( RBlocks.get(RBlocks.size() - 1).y - RBlocks.get(0).y ));
        return yobj;
    }
}
