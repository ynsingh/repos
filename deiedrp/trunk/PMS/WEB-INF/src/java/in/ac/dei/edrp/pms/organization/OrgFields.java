package in.ac.dei.edrp.pms.organization;
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
	private String icity;	//for holding organisation city.
	private String istate;	//for holding organisation state.
	private String ifax;	//for holding organisation fax number.
	private String iurl;	//for holding organisation URL.
	private String id;
	
	//constructors
	
	public OrgFields(String iname, String iaddress, String icity,String istate,String iphoneno, String ifax, String iurl,String id){
		this.iname = iname;
		this.iaddress = iaddress;
		this.icity = icity;
		this.istate = istate;
		this.iphoneno = iphoneno;
		this.ifax = ifax;
		this.iurl=iurl;
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
	
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
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
