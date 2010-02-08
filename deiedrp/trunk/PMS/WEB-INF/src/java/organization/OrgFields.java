package organization;
/**
 * It is responsible for getting and setting the organisation details in a list
 * This is widely used by OrgList.
 * It has parametric Constructor with all fields of organisation 
 */
public class OrgFields {


	private String iname;	//for holding organisation name.
	private String iaddress;	//for holding organisation address.
	private String ieid;	//for holding email_id of an organisation.
	private String iphoneno;	//for holding organisation phone number.
	private String description;	//for holding description about an organisation.
	private String icity;	//for holding organisation city.
	private String istate;	//for holding organisation state.
	private String ipin;	//for holding organisation pin code.
	private String ifax;	//for holding organisation fax number.
	private String iurl;	//for holding organisation URL.
	private String ihead;
	private String id;
	
	//constructors
	public OrgFields(){}
	public OrgFields(String iname, String iaddress, String icity,String istate, String ipin,String iphoneno, String ifax, String iurl,String ihead,String ieid,String description,String id){
		this.iname = iname;
		this.iaddress = iaddress;
		this.icity = icity;
		this.istate = istate;
		this.ipin = ipin;
		this.iphoneno = iphoneno;
		this.ifax = ifax;
		this.ihead = ihead;
		this.iurl=iurl;
		this.ieid = ieid;
		this.description = description;
		this.id=id;
	}

	//getter and setter methods

	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	
	public String getIaddress() {
		return iaddress;
	}
	public void setIaddress(String iaddress) {
		this.iaddress = iaddress;
	}
	
	public String getIeid() {
		return ieid;
	}
	public void setIeid(String ieid) {
		this.ieid = ieid;
	}
	
	public String getIphoneno() {
		return iphoneno;
	}
	public void setIphoneno(String iphoneno) {
		this.iphoneno = iphoneno;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public String getIcity() {
		return icity;
	}
	public void setIcity(String icity) {
		this.icity = icity;
	}
	
	public String getIstate() {
		return istate;
	}
	public void setIstate(String istate) {
		this.istate = istate;
	}
	
	public String getIpin() {
		return ipin;
	}
	public void setIpin(String ipin) {
		this.ipin = ipin;
	}
	
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
	}
	
	public String getIhead() {
		return ihead;
	}
	public void setIhead(String ihead) {
		this.ihead = ihead;
	}
	
	public String getIfax() {
		return ifax;
	}
	public void setIfax(String ifax) {
		this.ifax = ifax;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
