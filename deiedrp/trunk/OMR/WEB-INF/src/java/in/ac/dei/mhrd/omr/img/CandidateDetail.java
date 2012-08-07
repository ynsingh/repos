/*
 * @(#) CandidateDetailDetail.java
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

import java.util.*;
import java.sql.*;

import org.apache.log4j.Logger;

/**
 * This class defines the methods that insert the position of each circle in the
 * student info. part of the sheet into the database and match the positions of
 * the circles marked by the student from the database and retrieve the
 * corresponding data
 * 
 * Creation date: 12-06-2010
 * 
 * @author Anshul Agarwal
 * @version 1.0
 */

public class CandidateDetail {

	double xstart;
	double xend;
	double blockUpperratio;
	double blockMidRatio;
	double blockLowerRatio; /*
							 * starting and ending ratio of the circle in the y
							 * dir.
							 */
	int value;
	String field;
	private static Logger log = Logger.getLogger(CandidateDetail.class);

	/**
	 * This method should be called only for base sheet. It inserts the details
	 * regarding position of each circle in the database
	 * 
	 * @param detail
	 */

	public static void inserStudentInfo(ArrayList<CandidateDetail> detail) {
		Connection con = null;
	
		try {
			con = Connect.prepareConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into student_info(xstart, xend, y_ratio1, y_ratio2, field, value ) values (?,?,?,?,?,?)");

			for (CandidateDetail ob : detail) {
				ps.setDouble(1, ob.xstart);
				ps.setDouble(2, ob.xend);
				ps.setDouble(3, ob.blockUpperratio);
				ps.setDouble(4, ob.blockLowerRatio);
				ps.setString(5, ob.field);
				ps.setInt(6, ob.value);
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
		} catch (Exception e) {
			log
					.error("error while inserting bubble position in stuent info part : "
							+ e);
		} finally {
			Connect.freeConnection(con);
		}
	}

	/**
	 * This method returns true if xpos and ypos lies in the range stored in the
	 * database and retrieve corresponding values from the database
	 * 
	 * @param xpos
	 * @param ypos
	 * @param obj
	 * @param y
	 * @return
	 */

	public static boolean matchCandidateInfo(double xpos, double ypos,
			EachCandidateInfo obj, int y ,int countgroup) {
		boolean b = false;
		Connection con = null;
		String statusFlag;
		try {
			if(countgroup>0){
				statusFlag="GRC";
			}
			else statusFlag="NGC";
			con = Connect.prepareConnection();
			/*
			 * System.out.println("pos in match :" + xpos);
			 * System.out.println("mid point in match :" + ypos);
			 */
			ResultSet rs;
			PreparedStatement psXcomponent = con
					.prepareStatement("select  field, value from student_info where (xstart <= ? And xend >=?) and status_flag=?");
			psXcomponent.setDouble(1, xpos);
			psXcomponent.setDouble(2, xpos);
			psXcomponent.setString(3, statusFlag);
			ResultSet rsXcomponent = psXcomponent.executeQuery();

			if (rsXcomponent.next()) {
				/*
				 * System.out.println("data retrieved from x component pos : " +
				 * xpos);
				 */

				PreparedStatement ps = con
						.prepareStatement("select  field, value from student_info where (xstart <= ? And xend >= ?) And (y_ratio2 <= ? AND y_ratio1 >= ?) and status_flag=?");
				ps.setDouble(1, xpos);
				ps.setDouble(2, xpos);
				ps.setDouble(3, ypos);
				ps.setDouble(4, ypos);
				ps.setString(5, statusFlag);
				rs = ps.executeQuery();

				boolean val = rs.next();
				// System.out.println("resultset status : " + val);

				if (!val) {
					/*
					 * System.out.println("data not retrieved from y component : " +
					 * ypos);
					 */
					int counter = 0;
					int tempy;

					for (int i = 1; i <= 10; i++) {
						counter = 0;

						/*
						 * ypos = (double) (RotateImg.AvgSheetHeight /
						 * (RotateImg.originY - i));
						 */
						tempy = y + i;
						do {
							ypos = (double) (RotateImg.leftBlocks
									.get(RotateImg.leftBlocks.size() - 1).y - tempy)
									/ (double) (tempy - (RotateImg.leftBlocks
											.get(0)).y);
							
							
							PreparedStatement psYComponent = con
									.prepareStatement("select  field, value from student_info where (xstart <= ? And xend >= ?) And (y_ratio2 <= ? AND y_ratio1 >= ?) and status_flag=?");
							psYComponent.setDouble(1, xpos);
							psYComponent.setDouble(2, xpos);
							psYComponent.setDouble(3, ypos);
							psYComponent.setDouble(4, ypos);
							psYComponent.setString(5, statusFlag);
							rs = psYComponent.executeQuery();
							val = rs.next();

							if (val) {
								// System.out.println(" match found ");

								break;
							}
							tempy = y + (i * -1);
							counter++;
						} while (counter <= 1); // end while

						if (val) {
							break;
						}

					} // end for
				} // end if(!rs.next())

				while (val) {
					// System.out.println("inside val : ");
					b = true;
					if (rs.getString(1).startsWith("C")) {
						String loc = rs.getString(1).substring(
								rs.getString(1).length() - 1);
						// System.out.println("location in roll: " + loc);

						if (obj.rno.get(Integer.parseInt(loc) - 1).equals("NA")) {
							obj.rno.set(Integer.parseInt(loc) - 1, rs
									.getString(2));
						} else {
							
							obj.rno.set(Integer.parseInt(loc) - 1, "error");

							// System.out.println("error in roll");
						}
					} else if (rs.getString(1).startsWith("T")) {
						String loc = rs.getString(1).substring(
								rs.getString(1).length() - 1);

						// System.out.println("location in roll: " + loc);

						if (obj.tid.get(Integer.parseInt(loc) - 1).equals("NA")) {
							obj.tid.set(Integer.parseInt(loc) - 1, rs
									.getString(2));
						} else {
							// System.out.println("error in roll");

							// System.out.println("previous value of tid :" +
							// obj.tid.get(Integer.parseInt(loc) - 1));
							// System.out.println("error in tid: sec value " +
							// rs.getString(2));
							obj.tid.set(Integer.parseInt(loc) - 1, "error");

							// System.out.println("error in roll");
						}
					}
					val = rs.next();
				}
			}
		} catch (Exception e) {
			log.error("Error " + e);
		} finally {
			Connect.freeConnection(con);
		}
		return b;
	}
}
