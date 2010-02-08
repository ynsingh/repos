package task;

public class TaskFields {
	//properties
	private String taskName;
	private String resourceName;
	private String projectName;
	private String sdate;
	private String fdate;
	private String orgName;
	private String taskDependency;
	private String input_field;
	private String pcom;
	private String darea;
	private String taskid;
	
	//Parameterised constructor
	public TaskFields(String taskName, String resourceName,String orgName ,String projectName,
			String sdate, String fdate,String pcom,String gantcolor,
			String taskDependency, String description,String taskid){
		this.taskName=taskName;
		 this.resourceName=resourceName;
		 this.projectName=projectName;
		this.sdate=sdate;
		this.fdate=fdate;
		this.orgName=orgName;
		this.taskDependency=taskDependency;
		this.input_field=gantcolor;
		this.pcom=pcom;
		this.darea=description;
		this.taskid=taskid;
			}

	//getter and setter methods
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String rname) {
		this.resourceName = rname;
	}
	public String getDarea() {
		return darea;
	}
	public void setDarea(String darea) {
		this.darea = darea;
	}
	
	public String getInput_field() {
		return input_field;
	}
	public void setInput_field(String input_field) {
		this.input_field = input_field;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
	public String getPcom() {
		return pcom;
	}
	public void setPcom(String pcom) {
		this.pcom = pcom;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String pname) {
		this.projectName = pname;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String institute) {
		this.orgName = institute;
	}
	
	public String getTaskDependency() {
		return taskDependency;
	}
	public void setTaskDependency(String pdependency) {
		this.taskDependency= pdependency;
	}
	
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

	/** 
	 * Returns the taskname.
	 * @return String
	 */
	public String getTaskName() {
		return taskName;
	}

	/** 
	 * Set the tname.
	 * @param tname The tname to set
	 */
	public void setTaskName(String tname) {
		this.taskName = tname;
	}

}
