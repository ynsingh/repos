package in.ac.dei.edrp.pms.task;

public class TaskFields {
	//properties
	private String taskName;
	private String resourceName;
	private String no_of_days;
	private String schedule_start_date;
	private String schedule_end_date;
	private String actual_start_date;
	private String actual_end_date;
	private String gchart_color;
	private String per_completed;
	private String task_status;
	private String dependency;
	private String taskid;
	private String project_code;
	private String editauthority;
	private String assign_task_permission;
	//Parameterised constructor
	public TaskFields(String taskName, String resourceName,String no_of_days ,String schedule_start_date,
			String schedule_end_date, String actual_start_date,String actual_end_date,String gchart_color,
			String per_completed, String task_status,String dependency,String taskid,
			String project_code,String editauthority,String assign_task_permission){
		this.taskName=taskName;
		 this.resourceName=resourceName;
		 this.no_of_days=no_of_days;
		this.schedule_start_date=schedule_start_date;
		this.schedule_end_date=schedule_end_date;
		this.actual_start_date=actual_start_date;
		this.actual_end_date=actual_end_date;
		this.gchart_color=gchart_color;
		this.per_completed=per_completed;
		this.task_status=task_status;
		this.dependency=dependency;
		this.taskid=taskid;
		this.project_code=project_code;
		this.editauthority=editauthority;//to hold authorities Allow/Not Allow
		this.assign_task_permission=assign_task_permission;
			}

	//getter and setter methods
	
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(String no_of_days) {
		this.no_of_days = no_of_days;
	}

	public String getSchedule_start_date() {
		return schedule_start_date;
	}

	public void setSchedule_start_date(String schedule_start_date) {
		this.schedule_start_date = schedule_start_date;
	}

	public String getSchedule_end_date() {
		return schedule_end_date;
	}

	public void setSchedule_end_date(String schedule_end_date) {
		this.schedule_end_date = schedule_end_date;
	}

	public String getActual_start_date() {
		return actual_start_date;
	}

	public void setActual_start_date(String actual_start_date) {
		this.actual_start_date = actual_start_date;
	}

	public String getActual_end_date() {
		return actual_end_date;
	}

	public void setActual_end_date(String actual_end_date) {
		this.actual_end_date = actual_end_date;
	}

	public String getGchart_color() {
		return gchart_color;
	}

	public void setGchart_color(String gchart_color) {
		this.gchart_color = gchart_color;
	}

	public String getPer_completed() {
		return per_completed;
	}

	public void setPer_completed(String per_completed) {
		this.per_completed = per_completed;
	}

	public String getTask_status() {
		return task_status;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getEditauthority() {
		return editauthority;
	}

	public void setEditauthority(String editauthority) {
		this.editauthority = editauthority;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getAssign_task_permission() {
		return assign_task_permission;
	}

	public void setAssign_task_permission(String assign_task_permission) {
		this.assign_task_permission = assign_task_permission;
	}


}
