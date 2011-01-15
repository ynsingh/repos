package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GridDataBean implements IsSerializable{

	private String regnum;
	private String call;
	private String[] comp;
	private String[][] comp1;
	private Integer[] total_marks;

	private String total;
	private String testNumber;
	private String att; 
	
	

	private String[][] data;
	
	public String[][] getData() {
		return data;
	}

	public String getAtt() {
		return att;
	}

	public void setAtt(String att) {
		this.att = att;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public GridDataBean() {

	}
	
	public GridDataBean(String regnum,String call,String[][] data, String att) {
		this.data=data;
		this.call = call;
		this.regnum=regnum;
		this.att=att;
		
	}

	public GridDataBean(String regnum, String testNumber,String call, String[] comp, String total) {

		this.regnum = regnum;
		this.testNumber=testNumber;
		this.call = call;
		this.comp = comp;
		this.total = total;
	
	}
	
	public GridDataBean(String regnum, String testNumber,String call, String[][] comp, String total) {

		this.regnum = regnum;
		this.testNumber=testNumber;
		this.call = call;
		this.comp1 = comp;
		this.total = total;
	
	}
	
	public GridDataBean(String regnum, String testNumber,String call, String[] comp,Integer[] total_marks,String total) {

		this.regnum = regnum;
		this.testNumber=testNumber;
		this.call = call;
		this.comp = comp;
		this.total_marks=total_marks;
		this.total = total;
	
	}

	public String getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(String testNumber) {
		this.testNumber = testNumber;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public void setComp(String[] comp) {
		this.comp = comp;
	}

	public String getRegnum() {
		return regnum;
	}

	public String getCall() {
		return call;
	}

	public String[] getComp() {
		return comp;
	}
	
	public String[][] getComp1() {
		return comp1;
	}

	public void setComp1(String[][] comp1) {
		this.comp1 = comp1;
	}

	public Integer[] getTotal_marks() {
		return total_marks;
	}

	public void setTotal_marks(Integer[] total_marks) {
		this.total_marks = total_marks;
	}

}
