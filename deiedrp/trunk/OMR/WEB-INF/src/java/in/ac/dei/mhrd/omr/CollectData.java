package in.ac.dei.mhrd.omr;

import java.util.ArrayList;

public class CollectData {
	int sectionNo;
	int questionNo;
	String status;
	public CollectData(int sectionNo,int questionNo,String status) {
		this.questionNo=questionNo;
		this.sectionNo=sectionNo;
		this.status=status;
		// TODO Auto-generated constructor stub
	}
	public int getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(int sectionNo) {
		this.sectionNo = sectionNo;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
