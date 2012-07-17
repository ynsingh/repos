package in.ac.dei.edrp.cms.domain.utility;

public class ErrorReason {

	private String reasonCode;
	private String description;
	private boolean validRecord;

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isValidRecord() {
		return validRecord;
	}

	public void setValidRecord(boolean validRecord) {
		this.validRecord = validRecord;
	}

	public ErrorReason(String reasonCode, String description,
			boolean validRecord) {
		super();
		this.reasonCode = reasonCode;
		this.description = description;
		this.validRecord = validRecord;
	}

	public ErrorReason() {
		super();
		// TODO Auto-generated constructor stub
	}

}
