/*
 * @(#) BaseSheet.java
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
import java.util.Iterator;

/**
 * This class process the base answer sheet computes the position of circles and
 * their corresponding values
 * 
 * Creation Date : 05-20-2010
 * 
 * @author Anshul Agarwal
 * @version 1.0 Execute only once
 * 
 * 
 */
class BaseSheet {
	/**
	 * This method process student detail part of the base sheet.
	 * 
	 * @param ip
	 * @param infoLeft
	 * @param infoRight
	 * @param xrtAvg
	 * @param xleftAvg
	 * @param xstart
	 * @param xend
	 */
	public static void computeInfoCirclePosition(ij.process.ImageProcessor ip,
			ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight,
			float xrtAvg, float xleftAvg, int xstart, int xend) {
		int count = 0;
		int c = 0;
		int total_circle = 0;
		int circle_width = (int) (ip.getWidth() * 0.81) / 100;
		System.out.println("computeInfoCirclePosition: " );
		float posStartratio = 0; // ratio of starting position of the circle
		float posEndratio = 0; // ratio of ending position of the circle
		ArrayList<CandidateDetail> detail = new ArrayList<CandidateDetail>();
		int x1; // coordinates of midpoint of left block
		int y1; // coordinates of midpoint of left block
		int x2; // coordinates of midpoint of right block
		int y2; // coordinates of midpoint of right block
		int y; // y coordinate for each value of x

		int circle_dist = (int) (ip.getWidth() * 1.41) / 100;

		// MidPoint contains the midpoint of each block
		for (int j = 0; j < infoLeft.size(); j++) {
			MidPoint ymidLeft = infoLeft.get(j); // retrieve mid point of
			// left blocks
			MidPoint ymidRight = infoRight.get(j); // retrieve mid point of
			// left blocks
			/*
			 * variables for straight line equation bw mid points of left & rt
			 * side blocks.
			 */
			x1 = ymidLeft.xmp;
			y1 = ymidLeft.mp;
			x2 = ymidRight.xmp;
			y2 = ymidRight.mp;

			count++; // count the no. of rows

			c = 0; // no. of circles in one row

			for (int i = xstart; i < xend; i++) {
				// compute y for each value of x
				y = (int) ((((y2 - y1) * (i - x1)) / (x2 - x1)) + y1);

				ip.drawPixel(i, y);
				if (ip.getPixelValue(i, y) == 0) { // search for black pixel
					if (checkPixelGroup(ip, i, y)) // search for a group of
					// black pixel
					{

						int sp = i; // starting and ending position of the
						// circle

						int starting_point = sp;

						// compute the ratio of starting point of the circle
						posStartratio = ((starting_point - xleftAvg) / (xrtAvg - starting_point));

						i++;

						while (ip.getPixelValue(i, y) == 0) // find the end
						// point of the
						// circle
						{

							ip.drawPixel(i, y);
							if (checkPixelGroup(ip, i, y)) {

								sp++;
							}

							i++;
						} // end while

						int end_point = sp;
						if ((end_point - starting_point) > circle_width) {
							// System.out.println("starting point : " +
							// starting_point + " end point : "+ end_point
							// +"circle width : " + (sp-starting_point));

							// computes the ratio of end point
							posEndratio = ((end_point - xleftAvg) / (xrtAvg - end_point)); // computes
							// the
							// ratio
							// of
							// end
							// position
							// of
							// the
							// circle

							total_circle++;
							c++; // counts the no of circles

							i = i + circle_dist; // distance between circles

							// CandidateDetail stores the details of student
							// info. part into the database
							CandidateDetail obj = new CandidateDetail();

							obj.xstart = posStartratio;
							obj.xend = posEndratio;
							obj.blockUpperratio = ymidLeft.yr1;
							obj.blockMidRatio = ymidLeft.mp_ratio;
							obj.blockLowerRatio = ymidLeft.yr2;

							// no of circles in a row are 14
							if (((c % 15) > 0) && ((c % 15) <= 8)) // first 6
							// circles
							// for rno
							{
								/*
								 * obj.value = ""+count;
								 * System.out.println("count :" +count);
								 * 
								 * obj.field = "Rno" +count+" "+ c%8;
								 * System.out.println("field: " + obj.field);
								 */
								if (count == 10) {
									int temp = 0;
									obj.value = temp;

									obj.field = "CID" + temp + " " + (c % 15);
								} else {
									obj.value = count;

									obj.field = "CID" + count + " " + (c % 15);
								}
								if (c % 8 == 6) {
									// i=i+350;
									// i= i+(15*ip.getWidth())/100;

								}
							} else {
								/*
								 * System.out.println("inside else Count: " +
								 * count + " c%15 : " + (c % 15));
								 */
								if (count == 10) {
									int temp = 0;
									obj.value = temp;

									obj.field = "TID" + temp + " "
											+ ((c % 15) - 8);
								} else {
									obj.value = count;

									obj.field = "TID" + count + " "
											+ ((c % 15) - 8);
								}

							}// end else

							detail.add(obj);
						}
					}
				}
			}// end if(circle width)

		}

		// for (CandidateDetail db : detail) {
		// System.out.println("field: " + db.field + "value:" + db.value);
		/*
		 * System.out.println("xend:" + db.xend); System.out.println("xstart:" +
		 * db.xstart); System.out.println("blockMidRatio:" + db.blockMidRatio);
		 * System.out.println("blockUpperratio: " + db.blockUpperratio);
		 * System.out.println("blockLowerRatio: " + db.blockLowerRatio);
		 */
		// }
		// call thtfuction to insert all the details in the database
		CandidateDetail.inserStudentInfo(detail);
	}

	// --------------------------------------AnswerDetails-------------------------------------------------------//
	/**
	 * This method process answer detail part of the base sheet
	 * 
	 * @param ip
	 * @param infoLeft
	 * @param infoRight
	 * @param xrtAvg
	 * @param xleftAvg
	 * @param xstart
	 * @param xend
	 */
	public static void computeAnsCirclePosition(ij.process.ImageProcessor ip,
			ArrayList<MidPoint> infoLeft, ArrayList<MidPoint> infoRight,
			float xrtAvg, float xleftAvg, int xstart, int xend) {
		int count = -1; // count no of rows
		int c = 0; // count no of circles
		int circles_in_row = 0;
		int counter = 0;
		System.out.println("computeAnsCirclePosition: " );
		// int xend = (ip.getWidth()*48)/100;
		int ques = 1;
		float posStartratio = 0; // xratio1
		float posEndratio = 0; // xratio2

		int x1; // coordinates of midpoint of left block
		int y1; // coordinates of midpoint of left block
		int x2; // coordinates of midpoint of right block
		int y2; // coordinates of midpoint of right block
		int y; // y coordinate for each value of x
		int circle_width = (int) (ip.getWidth() * 0.81) / 100;

		int circle_dist = (int) (ip.getWidth() * 1.412) / 100;
		ArrayList<AnsDetail> detail = new ArrayList<AnsDetail>();
		System.out.println("x left avg  " + xleftAvg + " x right Avg : "
				+ xrtAvg);
		// MidPoint contains the midpoint of each block
		for (int j = 0; j < infoLeft.size(); j++) {
			MidPoint ymidLeft = infoLeft.get(j);
			MidPoint ymidRight = infoRight.get(j);
			/*
			 * variables for straight line equation bw mid points of left & rt
			 * side blocks.
			 */
			x1 = ymidLeft.xmp;
			y1 = ymidLeft.mp;
			x2 = ymidRight.xmp;
			y2 = ymidRight.mp;
			circles_in_row = 0;
			count++;
			counter = 0;

			System.out.println();
			for (int i = xstart; i < xend; i++) {
				// System.out.println("row : " +count);
				y = (((y2 - y1) * (i - x1)) / (x2 - x1)) + y1;
				ip.drawPixel(i, y);

				if (ip.getPixelValue(i, y) == 0) {
					// System.out.println("y :" + y + " y mid: " + ymidLeft.mp);
					ip.drawPixel(i, y);

					if (checkPixelGroup(ip, i, y)) {
						int sp = i;
						ip.drawPixel(i, y);

						// Starting pint of circle
						int starting_point = sp;

						counter = 1;
						// computes the ratio of starting point
						posStartratio = ((starting_point - xleftAvg) / (xrtAvg - starting_point));

						i++;

						while (ip.getPixelValue(i, y) == 0) {
							if (checkPixelGroup(ip, i, y)) {
								sp++; // ending point of circle
							}

							i++;
						}

						int end_point = sp; // end point of each circle
						if ((end_point - starting_point) > circle_width) {
							System.out.print("    ");
							// ratio of end point of the circle
							posEndratio = ((end_point - xleftAvg) / (xrtAvg - end_point));
							System.out.print("row :" + count + " c : "
									+ circles_in_row + " st : "
									+ starting_point + " END: " + end_point
									+ "w: " + (end_point - starting_point)
									+ "start ratio+ : " + posStartratio
									+ "end ratio : " + posEndratio);

							c++;
							circles_in_row++;

							i = i + circle_dist;

							// stores all the information in Ans_db object to
							// insert into database
							AnsDetail obj = new AnsDetail();
							obj.xstart = posStartratio;
							obj.xend = posEndratio;
							obj.ymidr1 = ymidLeft.yr1;
							obj.ymidr = ymidLeft.mp_ratio;
							obj.ymidr2 = ymidLeft.yr2;

							if ((c % 4) == 0) {
								obj.option = 'D';
								obj.qno = ques;
							} else if ((c % 4) == 1) {
								obj.option = 'A';
								obj.qno = ques;
							} else if ((c % 4) == 2) {
								obj.option = 'B';
								obj.qno = ques;
							} else if ((c % 4) == 3) {
								obj.option = 'C';
								obj.qno = ques;
							}

							detail.add(obj);

							if ((c % 4) == 0) {
								if ((c % 16) == 0) {
									ques = ques - 90;
									ques++;
								} else {
									ques = (ques + 30);
								}
							}
						}
					}
				}// end if(circle width)w

			}// end i loop
			System.out.println("No. of circles in " + count + " row : "
					+ circles_in_row);
		}// end j loop

		System.out.println("total circles found : " + c);
		// THIS FUNCTION INSERTS THE ANSWER DEATIL INTO THE DATABASE
		AnsDetail.insertAnsCirclePosition(detail);
	}

	/**
	 * This function returns true if a group of 20 pixels found
	 * 
	 * @param ip
	 * @param xc
	 * @param yc
	 * @return
	 */

	static boolean checkPixelGroup(ij.process.ImageProcessor ip, int xc, int yc) {
		int connect = 0;
		int i = 1;
		int j = 1;
		int pixel_group = 20;
		boolean found = false;
		System.out.println("checkPixelGroup: " );
		while ((i <= 10) && (j <= 10)) {
			j += 2;
			i += 2;

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

			if (connect > pixel_group) {
				found = true;
				if (i > 1720 && i < 1749) {
					System.out.println("grop find");
				}
				break;
			}
		}

		return found;
	}
}
