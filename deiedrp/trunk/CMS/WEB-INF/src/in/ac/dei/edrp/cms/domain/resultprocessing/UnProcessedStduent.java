package in.ac.dei.edrp.cms.domain.resultprocessing;

public class UnProcessedStduent {
	
	private String rollNumber;
	private String staus;
	private String processed;
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getStaus() {
		return staus;
	}
	public void setStaus(String staus) {
		this.staus = staus;
	}
	
	public String getProcessed() {
		return processed;
	}
	public void setProcessed(String processed) {
		this.processed = processed;
	}
	public UnProcessedStduent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UnProcessedStduent(String rollNumber, String staus,String processed) {
		super();
		this.rollNumber = rollNumber;
		this.staus = staus;
		this.processed=processed;
	}
	
	public UnProcessedStduent(String rollNumber,String processed) {
		super();
		this.rollNumber = rollNumber;
		this.processed=processed;
	}
	

}
