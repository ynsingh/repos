package in.ac.dei.edrp.cms.utility;

public class PreviousSemesterDetail {

	private String previousSemesterStartDate = "";
	private String previousSemesterEndDate = "";

	private String entityId;
	private String programCourseKey;
	private String rollNumber;

	private String status;
	
	//Added for result processing:
	private double sgpa;
	private double theoryWeighted;
	private double practicalWeighted;
	private double theorySGPAPoint;
	private double practicalSGPAPoint;
	private double theoryEarnedCredit;
	private double practicalEarnedCredit;
	private double theoryCGPAPoint;
	private double practicalCGPAPoint;
	private double theoryEarnedCgpaCredit;
	private double practicalEarnedCgpaCredit;
	private String programId;
	private String branchId; 
	private String specializationId;
	private String semesterCode;
		
	private int numberOfRemedials;
	
	private double cumulativeWeighted;

	public String getPreviousSemesterStartDate() {
		return previousSemesterStartDate;
	}

	public void setPreviousSemesterStartDate(String previousSemesterStartDate) {
		this.previousSemesterStartDate = previousSemesterStartDate;
	}

	public String getPreviousSemesterEndDate() {
		return previousSemesterEndDate;
	}

	public void setPreviousSemesterEndDate(String previousSemesterEndDate) {
		this.previousSemesterEndDate = previousSemesterEndDate;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public double getSgpa() {
		return sgpa;
	}

	public void setSgpa(double sgpa) {
		this.sgpa = sgpa;
	}

	public double getTheoryWeighted() {
		return theoryWeighted;
	}

	public void setTheoryWeighted(double theoryWeighted) {
		this.theoryWeighted = theoryWeighted;
	}

	public double getPracticalWeighted() {
		return practicalWeighted;
	}

	public void setPracticalWeighted(double practicalWeighted) {
		this.practicalWeighted = practicalWeighted;
	}

	public double getTheorySGPAPoint() {
		return theorySGPAPoint;
	}

	public void setTheorySGPAPoint(double theorySGPAPoint) {
		this.theorySGPAPoint = theorySGPAPoint;
	}

	public double getPracticalSGPAPoint() {
		return practicalSGPAPoint;
	}

	public void setPracticalSGPAPoint(double practicalSGPAPoint) {
		this.practicalSGPAPoint = practicalSGPAPoint;
	}

	public double getTheoryEarnedCredit() {
		return theoryEarnedCredit;
	}

	public void setTheoryEarnedCredit(double theoryEarnedCredit) {
		this.theoryEarnedCredit = theoryEarnedCredit;
	}

	public double getPracticalEarnedCredit() {
		return practicalEarnedCredit;
	}

	public void setPracticalEarnedCredit(double practicalEarnedCredit) {
		this.practicalEarnedCredit = practicalEarnedCredit;
	}

	public double getTheoryCGPAPoint() {
		return theoryCGPAPoint;
	}

	public void setTheoryCGPAPoint(double theoryCGPAPoint) {
		this.theoryCGPAPoint = theoryCGPAPoint;
	}

	public double getPracticalCGPAPoint() {
		return practicalCGPAPoint;
	}

	public void setPracticalCGPAPoint(double practicalCGPAPoint) {
		this.practicalCGPAPoint = practicalCGPAPoint;
	}

	public double getTheoryEarnedCgpaCredit() {
		return theoryEarnedCgpaCredit;
	}

	public void setTheoryEarnedCgpaCredit(double theoryEarnedCgpaCredit) {
		this.theoryEarnedCgpaCredit = theoryEarnedCgpaCredit;
	}

	public double getPracticalEarnedCgpaCredit() {
		return practicalEarnedCgpaCredit;
	}

	public void setPracticalEarnedCgpaCredit(double practicalEarnedCgpaCredit) {
		this.practicalEarnedCgpaCredit = practicalEarnedCgpaCredit;
	}

	public int getNumberOfRemedials() {
		return numberOfRemedials;
	}

	public void setNumberOfRemedials(int numberOfRemedials) {
		this.numberOfRemedials = numberOfRemedials;
	}
	
	public double getCumulativeWeighted() {
		return cumulativeWeighted;
	}

	public void setCumulativeWeighted(double cumulativeWeighted) {
		this.cumulativeWeighted = cumulativeWeighted;
	}

	
	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	public PreviousSemesterDetail(String previousSemesterStartDate,
			String previosSemesterEndDate) {
		// TODO Auto-generated constructor stub

		this.previousSemesterStartDate = previousSemesterStartDate;
		this.previousSemesterEndDate = previosSemesterEndDate;

	}

	public PreviousSemesterDetail(String rollNumber, String entityId,
			String programCourseKey) {
		super();
		this.rollNumber = rollNumber;
		this.entityId = entityId;
		this.programCourseKey = programCourseKey;

	}
	//Added by Dheeraj for Switch Logic
	public PreviousSemesterDetail(String rollNumber, String entityId,
			String programCourseKey, String previousSemesterStartDate, String previosSemesterEndDate) {
		super();
		this.rollNumber = rollNumber;
		this.entityId = entityId;
		this.programCourseKey = programCourseKey;
		this.previousSemesterStartDate = previousSemesterStartDate;
		this.previousSemesterEndDate = previosSemesterEndDate;
	}

	public PreviousSemesterDetail() {
		super();
	}

	//Added Constructor for getting details: Grading System
	public PreviousSemesterDetail(String rollNumber, String programCourseKey,
			int numberOfRemedials, double sgpa, double theorySGPAPoint,
			double practicalSGPAPoint,double theoryEarnedCredit,double practicalEarnedCredit,
			double theoryCGPAPoint,double practicalCGPAPoint,
			double theoryEarnedCgpaCredit,double practicalEarnedCgpaCredit) {
		// TODO Auto-generated constructor stub
		this.rollNumber=rollNumber;
		this.programCourseKey=programCourseKey;
		this.sgpa=sgpa;
		this.theorySGPAPoint=theorySGPAPoint;
		this.practicalSGPAPoint=practicalSGPAPoint;
		this.theoryEarnedCredit=theoryEarnedCredit;
		this.practicalEarnedCredit=practicalEarnedCredit;
		this.theoryCGPAPoint=theoryCGPAPoint;
		this.practicalCGPAPoint=practicalCGPAPoint;
		this.theoryEarnedCgpaCredit=theoryEarnedCgpaCredit;
		this.practicalEarnedCgpaCredit=practicalEarnedCgpaCredit;
	}

	//Added Constructor for getting details: Marks System
	public PreviousSemesterDetail(String rollNumber, String programCourseKey,
			int numberOfRemedials, double theoryWeighted,double practicalWeighted,
			double theoryEarnedCredit,double practicalEarnedCredit) {
		// TODO Auto-generated constructor stub
		this.rollNumber=rollNumber;
		this.programCourseKey =programCourseKey;
		this.numberOfRemedials=numberOfRemedials;
		this.theoryWeighted=theoryWeighted;
		this.practicalWeighted=practicalWeighted;
		this.theoryEarnedCredit=theoryEarnedCredit;
		this.practicalEarnedCredit=practicalEarnedCredit;
	}

	

}
