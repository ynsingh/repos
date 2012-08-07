/*
 * @(#) AnsDetail.java
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

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.uploadSheet.UploadResponseAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class defines the method that inserts the position of the bubbles in answer section of the OMR
 * sheet into the database and matches answer details from the database and
 * retrieves the associated information 
 * Creation date: 12-06-2010
 * 
 * @Author Anshul Agarwal
 * @version 1.0
 */
public class AnsDetail {
	static byte option_no = 0;
	double xstart; // ratio of x coordinate
	double xend; // ratio of x coordinate
	double ymidr1; // ratio of y coordinates
	double ymidr; // ratio of y coordinates
	double ymidr2; // ratio of y coordinates
	int qno;
	int sec;
	char option;
	private static Logger log = Logger.getLogger(AnsDetail.class);

	/**
	 * This method inserts the position of the circles in the answer detail part
	 * of the sheet into the database
	 * 
	 * @param detail :
	 *            contains xstartratio, xendratio, midpoint ratio of each circle
	 *            in the answer part
	 */
	public static void insertAnsCirclePosition(ArrayList<AnsDetail> detail) {
		/*
		 * Execute only once for base answer sheet
		 */
		Connection con = null;
		System.out.println("insertAnsCirclePosition");
		try {
			con = Connect.prepareConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into ans_info(q_no, ans, xstart, xend, y_ratio1, y_ratio2, sec) values (?,?,?,?,?,?,?)");

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
		} catch (Exception e) {
			log.error("error while insert bubble position into the database " +
					"in the answer section of the sheet: "
							+ e);
		} finally {
			Connect.freeConnection(con);
		}
	}

	/**
	 * This method returns true if xpos and ypos lies in the range stored in the
	 * database and retrieve corresponding values from the database
	 * 
	 * @param xpos: ratio of position of the circle w.r.t. x- axis  
	 * @param ypos: ratio of position of the circlew.r.t. y-axis
	 * @param obj
	 * @param y 
	 * @return
	 */

	public static boolean matchAns(double xpos, double ypos,
			EachCandidateAns obj, int y) {
		option_no = 0;

		boolean b = false;
		ResultSet rsXcomponent = null;
		
		Connection con = null;
		try {
			con = Connect.prepareConnection();
			ResultSet rs;
			try {
				// System.out.println("inside match Ans: " + " xpos :" + xpos +
				// " ypos : " + ypos );
				/*
				 * comparing positions, w.r.t. x-axis, of the filled circles
				 */
				PreparedStatement psXcomponent = con
						.prepareStatement("select  q_no, ans from ans_info where (xstart <= ? And xend >= ?)");
				psXcomponent.setDouble(1, xpos);
				psXcomponent.setDouble(2, xpos);
				rsXcomponent = psXcomponent.executeQuery();
			} catch (Exception e) {
				log.error("error retrieving data according to the x component "
						+ e);
			}
			/*
			 * if x position match then compare y position and retrieve required
			 * information
			 */
			if (rsXcomponent.next()) {
				
				PreparedStatement ps = con
						.prepareStatement("select  q_no, ans from ans_info where (xstart <= ? And xend >= ?) And (y_ratio2 <= ? AND y_ratio1 >= ?)");
				ps.setDouble(1, xpos);
				ps.setDouble(2, xpos);
				ps.setDouble(3, ypos);
				ps.setDouble(4, ypos);
				rs = ps.executeQuery();
				boolean val = rs.next();
				if (!val) {
					/*
					 * If x position matches but y position does not matches,
					 * than increment and decrement the y position by one pixel
					 * alternatively, compute the ratio and than compare again
					 */

					int counter = 0;
					int tempy;

					for (int i = 1; i <= 12; i++) {
						counter = 0;

						tempy = y + i;
						do {

							ypos = (double) (RotateImg.leftBlocks
									.get(RotateImg.leftBlocks.size() - 1).y - tempy)
									/ (double) (tempy - (RotateImg.leftBlocks
											.get(0)).y);

							PreparedStatement psYComponent = con
									.prepareStatement("select  q_no, ans from ans_info where (xstart <= ? And xend >= ?) And (y_ratio2 <= ? AND y_ratio1 >= ?)");
							psYComponent.setDouble(1, xpos);
							psYComponent.setDouble(2, xpos);
							psYComponent.setDouble(3, ypos);
							psYComponent.setDouble(4, ypos);
							rs = psYComponent.executeQuery();
							val = rs.next();
							if (val) {
								break;
							}
							tempy = y + (i * -1);
							counter++;
						} while (counter <= 1); // end while

						if (val) {
							break;
						}

					}// end for
				} // end if(!rs.next())

				while (val) {

					b = true;
					int pos = rs.getInt(1);

					if (pos < obj.response.length) {
						// array
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
							obj.response[pos] = (byte) (obj.response[pos] + option_no);

						}
					} else {
						break;
					}
					val = rs.next();
				}
			}// end if(rsXcomponent)...

		} catch (Exception e) {
			log
					.error("Error while matching position of bubbles in the answer section "
							+ e);
		} finally {
			Connect.freeConnection(con);
		}

		return b;
	}

}
