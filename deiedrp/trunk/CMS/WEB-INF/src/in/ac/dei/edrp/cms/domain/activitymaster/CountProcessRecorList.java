package in.ac.dei.edrp.cms.domain.activitymaster;

import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;

import java.util.ArrayList;
import java.util.List;

public class CountProcessRecorList {

	private int totalRecords;
	private int correctProcessed;
	private int rejectedProcessed;
	private int inError;
	private boolean activityCompleted;
	private List<List<UnProcessedStduent>> studentList=new ArrayList<List<UnProcessedStduent>>();
	private List<UnProcessedStduent> rejStudentList=new ArrayList<UnProcessedStduent>();

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCorrectProcessed() {
		return correctProcessed;
	}

	public void setCorrectProcessed(int correctProcessed) {
		this.correctProcessed = correctProcessed;
	}

	public int getRejectedProcessed() {
		return rejectedProcessed;
	}

	public void setRejectedProcessed(int rejectedProcessed) {
		this.rejectedProcessed = rejectedProcessed;
	}

	public int getInError() {
		return inError;
	}

	public void setInError(int inError) {
		this.inError = inError;
	}

	public boolean isActivityCompleted() {
		return activityCompleted;
	}

	public void setActivityCompleted(boolean activityCompleted) {
		this.activityCompleted = activityCompleted;
	}

	
	public List<List<UnProcessedStduent>> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<List<UnProcessedStduent>> studentList) {
		this.studentList = studentList;
	}

	public CountProcessRecorList(int totalRecords, int correctProcessed,
			int rejectedProcessed, int inError, boolean activityCompleted) {
		super();
		this.totalRecords = totalRecords;
		this.correctProcessed = correctProcessed;
		this.rejectedProcessed = rejectedProcessed;
		this.inError = inError;
		this.activityCompleted = activityCompleted;
	}
	
	public CountProcessRecorList(int totalRecords, int correctProcessed,
			int rejectedProcessed, int inError, boolean activityCompleted,
			List<List<UnProcessedStduent>> studentList) {
		super();
		this.totalRecords = totalRecords;
		this.correctProcessed = correctProcessed;
		this.rejectedProcessed = rejectedProcessed;
		this.inError = inError;
		this.activityCompleted = activityCompleted;
		this.studentList=studentList;
	}
	
	public CountProcessRecorList(int totalRecords, int correctProcessed,
			 int inError ,boolean activityCompleted,
			List<UnProcessedStduent> rejStudentList) {
		super();
		this.totalRecords = totalRecords;
		this.correctProcessed = correctProcessed;
		this.inError = inError;
		this.activityCompleted = activityCompleted;
		this.setRejStudentList(rejStudentList);
	}

	public CountProcessRecorList() {

	}

	public void setRejStudentList(List<UnProcessedStduent> rejStudentList) {
		this.rejStudentList = rejStudentList;
	}

	public List<UnProcessedStduent> getRejStudentList() {
		return rejStudentList;
	}

}
