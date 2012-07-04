import java.util.Date;

class NotificationQuery {
	private	Date minDate;
	private	Date maxDate;
	private	Date minDate1;
	private Date maxDate1;
	private String title;

	public Date getMinDate() {
		return minDate;
		}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
		}
	public Date getMaxDate() {
		return maxDate;
		}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
		}
	public Date getMinDate1() {
		return minDate1;
		}
	public void setMinDate1(Date minDate1) {
		this.minDate1 = minDate1;
		}
	public Date getMaxDate1() {
		return maxDate1;
		}
	public void setMaxDate1(Date maxDate1) {
		this.maxDate1 = maxDate1;
		}
	
	public String getTitle() {
	return title;
	}
	public void setTitle(String title) {
	this.title = title;
	}
	
}
