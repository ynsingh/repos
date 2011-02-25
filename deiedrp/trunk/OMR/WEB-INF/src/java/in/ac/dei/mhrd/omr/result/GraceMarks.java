/*
 * @(#) GraceMarks.java
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

package in.ac.dei.mhrd.omr.result;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

import org.apache.log4j.Logger;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.testSetUp.UpdateTestSetupAction;

/**
 * This class defines the methods that computes the marks of the misprinted
 * questions Creation Date: 23-10-2010
 * 
 * @version 1.0
 * @author Anshul Agarwal
 * 
 */

public class GraceMarks {

	private static Logger log = Logger.getLogger(GraceMarks.class);

	/**
	 * This method calculates the graceMarks to be awarded to each candidate
	 * appeared in the test according to the status of the mis printed questions
	 * 
	 * @param testId
	 *            id of the test
	 * @return float
	 */
	public float computeGraceMarks(int testId) {
		PreparedStatement ps = null;
		ResultSet rsGraceMarks = null;
		ArrayList<Integer> wrongQues = new ArrayList<Integer>();
		int totalSection;
		int quesSection = 0;
		float marks = 0;
		Connection con = null;
		try {
			con = Connect.prepareConnection();
			/*
			 * Retrieves the list of misprinted questions for which grace marks
			 * are to be awarded
			 * 
			 */
			ps = con
					.prepareStatement("select WrongQuestionNo from wrongquestion where TestId=? and status='G'");
			ps.setInt(1, testId);
			rsGraceMarks = ps.executeQuery();

			while (rsGraceMarks.next()) {
				wrongQues.add(rsGraceMarks.getInt(1));
			}
			/*
			 * if such wrong questions exist for which grace marks are to be
			 * provided, then compute grace marks
			 */
			if (!wrongQues.isEmpty()) {
				rsGraceMarks = null;
				ps = con
						.prepareStatement("select Total_section from testheader where TestId=?");
				ps.setInt(1, testId);
				rsGraceMarks = ps.executeQuery();
				rsGraceMarks.next();
				totalSection = rsGraceMarks.getInt(1);
				System.out.println("ts " + totalSection);
				rsGraceMarks = null;
				ps = con
						.prepareStatement("select Section_number, No_of_question, Neg_Marks, Marks_each_question from testsectiondetail "
								+ "where TestId=?");
				ps.setInt(1, testId);
				rsGraceMarks = ps.executeQuery();
				while (rsGraceMarks.next()) {
					for (Integer wrong : wrongQues) {
						if (wrong <= (quesSection + rsGraceMarks.getInt(2))
								&& wrong > quesSection) {
							marks = marks + rsGraceMarks.getFloat(4);
						}
					}
					quesSection = quesSection + rsGraceMarks.getInt(2);
				}
				System.out.println("GraceMarks marks " + marks);
				rsGraceMarks.close();

			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Exception in computing grace Marks " + e);
		} finally {
			Connect.freeConnection(con);

		}

		return marks;
	}

	/**
	 * This method computes the marks of the questions that are to be discarded
	 * 
	 * @param testId
	 *            discarding questions as per testId
	 * @return ArrayList<Float>
	 */
	public ArrayList<Float> computeDiscardMarks(int testId) {

		int quesSection = 0;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rsDiscard = null;
		ArrayList<Integer> discardQues = new ArrayList<Integer>();
		ArrayList<Float> discardMarks = new ArrayList<Float>();
		float marks = 0;
		int totalSection = 0;
		System.out.println("in discard");
		try {
			con = Connect.prepareConnection();
			ps = con
					.prepareStatement("select WrongQuestionNo from wrongquestion where TestId=? and status='D'");
			ps.setInt(1, testId);
			rsDiscard = ps.executeQuery();
			while (rsDiscard.next()) {
				discardQues.add(rsDiscard.getInt(1));
			}
			for (Integer wrong : discardQues) {
				System.out.println("ques " + wrong);
			}
			rsDiscard = null;
			if (!discardQues.isEmpty()) {
				ps = con
						.prepareStatement("select Total_section from testheader where TestId=?");
				ps.setInt(1, testId);
				rsDiscard = ps.executeQuery();
				rsDiscard.next();
				totalSection = rsDiscard.getInt(1);
				System.out.println("ts" + totalSection);
				rsDiscard = null;
				ps = con
						.prepareStatement("select Section_number, No_of_question, Neg_Marks, Marks_each_question from testsectiondetail "
								+ "where TestId=?");
				ps.setInt(1, testId);
				rsDiscard = ps.executeQuery();
				while (rsDiscard.next()) {
					System.out.println("no of ques " + rsDiscard.getInt(2));
					System.out.println("Meq " + rsDiscard.getFloat(4));
					for (Integer wrong : discardQues) {
						if (wrong <= (quesSection + rsDiscard.getInt(2))
								&& wrong > quesSection) {
							System.out.println("for wrong " + wrong);							
							marks = marks + rsDiscard.getFloat(4);
						}
					}
					System.out.println("marks sec " + marks);
					discardMarks.add(marks);
					marks = 0;
					quesSection = quesSection + rsDiscard.getInt(2);
				}

			}
		} catch (Exception e) {
			log.error("Exception in computing Discarded ques Marks " + e.getMessage());
		} finally {
			Connect.freeConnection(con);

		}

		return discardMarks;
	}
}
