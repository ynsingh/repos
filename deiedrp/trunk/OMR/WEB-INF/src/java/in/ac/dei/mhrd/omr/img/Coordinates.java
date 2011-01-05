package in.ac.dei.mhrd.omr.img;


import java.util.*;

/**
 * @ Author Anshul Agarwal
 * calculates the coordinates of the blocks 
 */
public class Coordinates {
	
	int x;
	int y;
	
	private Coordinates()
	{
	}
	
	public Coordinates(int xc, int yc)
	{
		x=  xc;
		y=  yc;
		
	}
	
	
	/**
	 * This function computes coordinates of top and bottom coordinates of the block
	 * @param ip
	 * @param xstart
	 * @param xend
	 * @return
	 */
   public static ArrayList<Coordinates> computeCorners(ij.process.ImageProcessor ip, int xstart, int xend){
         
          
	  
     ArrayList<Coordinates> arr = new ArrayList<Coordinates>();
     //int block_height= 50;
	 int block_height = (int)((ip.getHeight()*1.5)/100);
	// System.out.println("block height : " + block_height);
	// int block_height = 25;
	  int x=0,y=0, x1=0,y1=0, i=0;
	  int block_dist=(int)((ip.getHeight()*2.5)/100);
	  //System.out.println("block dist : " + block_dist);
	 //int block_dist = 95;
	//  int block_dist = 75;
	  int block_width = (int)((ip.getWidth()*1.55)/100);
	  //System.out.println("block width : " + block_width);
	  //int block_width = 20;
	 // xstart = 2320;
	 // xend = 2460;
	  /*
	   * xcoord, ycoord, y1 are the coordinates of the top and bottom of the block x vl remain same
	   */
	  
	 // position from where processing will start
	 
	int ystart = (int)(ip.getHeight()*10)/100;
	System.out.println("ystart : " + ystart);
	 
	  int yend = ip.getHeight(); // position where processing will end
	  
	   	int xcoord=-1, ycoord=0 , mp_y=-1,hx=0, hy=0;
	   	boolean xlimit = false;
	 int total_blocks = 23;
	
	
	   for(y=ystart;y<ip.getHeight();y++){ //this looop is executing up to the length of the page
		
		   if(i==total_blocks) // break the loop if all blocks found
		   {
			   break;
		   }
		   if(y >= yend) 
		   {
			   break;
		   }
		
		   for(x=xstart;x<xend;x++) //horizontal position where processing starts and ends
		   {
			   
			   //ip.drawPixel(x, y);
			   if(x>=ip.getWidth()-1)
			   {
				   break;
			   }
			  if(y>=yend || i==total_blocks)
			  {
				  break;
			  }
			   hy=0; hx=0; // height and width of the sheet
			  
			   x1=x;
			   if(ip.getPixelValue(x1, y)==0) // check if black pixel found			
			   {  
				 
				   xcoord = x1;
				   
				  int tempy = y+10; // y coord move due to tilt
				   while(ip.getPixelValue(x1, tempy)==0) //compute how many black pixels in sequence horizontally
				   {
					 //  System.out.println("inside while x1 : " +x1 + " tempy " + tempy);
					   hx++;
					   x1++;
					 
					   if(x1>=xend){
						   break;
					   }
					   if(hx==block_width)  //check if black pixels upto the width of block found
					   {
						  // System.out.println("hx : " +hx + " " + tempy);
						   xlimit = true; 
						   
						   hx=0;
						  
						   break;
					   }
				   }
					if(xlimit) // check black pixels vertically
					{
						 //System.out.println("checking vertically");
				   ycoord =y;
						   y1 = y;
						   if(y1>yend){
							   break;
						   }
						   int tempx = x+12; // to overcome the tilt
						   while(ip.getPixelValue(tempx,y1)==0)
						   {
							   //System.out.println("inside vertical loop x : " + tempx + " y: " + y1);
							   hy++;
							   y1++;
							   if(y1>yend){
								   break;
							   }
							  
						   } 
								  if(hy > block_height){
							    
								   i++;
								
								   /*System.out.println(i+" block found at top x:" +xcoord + "y:  "+ ycoord);
								   System.out.println("Bottom Left x: " +xcoord + "y: "+y1);
								   System.out.println("HEight :" +hy);*/
								  
								   y=y+block_dist; // distance between top corner of block
								   
								   
								   Coordinates obj=new Coordinates(xcoord, ycoord);
								   arr.add(obj);
								   Coordinates obj1=new Coordinates(xcoord, y1);
								   arr.add(obj1);
								 
								   break;
							   } //end if
						   }//end if
					   }
			   
			         if(xlimit){
			        	 hy=0;
						   xlimit=false;
						   break;
			         }
				   }//end xloop
			   }//end yloop
		   
	   return arr;
   }

}
