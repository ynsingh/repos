/*
 * @(#) SectionDetail.java
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


package in.ac.dei.mhrd.omr.testSetUp;
import in.ac.dei.mhrd.omr.img.*;
import java.sql.*;

/**
 * This class retrieves section detail from the database
 * @author Ashutosh 
 *
 */
public class SectionDetail {
	
		private int sectionNo;
		private int noOfQuestion;
		private float marksEachQuestion;
		private float negMarks;
		
		public SectionDetail(int sectionNo,int noOfQuestion,float marksEachQuestion,float negMarks)
		{
			this.sectionNo=sectionNo;
			this.noOfQuestion=noOfQuestion;
			this.marksEachQuestion=marksEachQuestion;
			this.negMarks=negMarks;			
		}
		  
		public int getSectionNo() {
			return sectionNo;
		}
		public void setSectionNo(int sectionNo) {
			this.sectionNo = sectionNo;
		}
		public int getNoOfQuestion() {
			return noOfQuestion;
		}
		public void setNoOfQuestion(int noOfQuestion) {
			this.noOfQuestion = noOfQuestion;
		}
		public float getMarksEachQuestion() {
			return marksEachQuestion;
		}
		public void setMarksEachQuestion(float marksEachQuestion) {
			this.marksEachQuestion = marksEachQuestion;
		}
		public float getNegMarks() {
			return negMarks;
		}
		public void setNegMarks(float negMarks) {
			this.negMarks = negMarks;
		}
		
		}

