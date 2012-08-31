package in.ac.dei.edrp.client;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataBean
    implements IsSerializable
{

    private String registrationNumber;
    private double computedMarks;
    private double totalMarks;
    private String testNumber;
    private String percentage;
    private String compData[][];
    private String detail[];
    int slnum;
    private String category;
    private String student_name;
    private double sum_cm;
    private String status;
    private String reason;
    
    private String[] paper;
    private String gender;//Add by Devendra
     private List<ReportInfoGetter>componentDetail;//Add by Devendra June 2
    
    
    
    /**
	 * @return the componentDetail
	 */
	public List<ReportInfoGetter> getComponentDetail() {
		return componentDetail;
	}

	/**
	 * @param componentDetail the componentDetail to set
	 */
	public void setComponentDetail(List<ReportInfoGetter> componentDetail) {
		this.componentDetail = componentDetail;
	}
    
    /**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public DataBean()
    {
    }

    public DataBean(String registrationNumber, double totalMarks, String testNumber)
    {
        this.registrationNumber = registrationNumber;
        this.totalMarks = totalMarks;
        this.testNumber = testNumber;
    }

    public DataBean(String registrationNumber, String compData[][], double totalMarks)
    {
        this.registrationNumber = registrationNumber;
        this.compData = compData;
        this.totalMarks = totalMarks;
    }

    public DataBean(String registrationNumber, String detail[], String compData[][], double totalMarks)
    {
        this.registrationNumber = registrationNumber;
        this.compData = compData;
        this.totalMarks = totalMarks;
        this.detail = detail;
    }

    public DataBean(String registrationNumber, String testNumber, String detail[], Double totalMarks)
    {
        this.registrationNumber = registrationNumber;
        this.totalMarks = totalMarks.doubleValue();
        this.testNumber = testNumber;
        this.detail = detail;
    }

    public DataBean(int slnum, String registrationNumber, String testNumber, String percentage, String detail[], Double totalMarks)
    {
        this.slnum = slnum;
        this.registrationNumber = registrationNumber;
        this.totalMarks = totalMarks.doubleValue();
        this.testNumber = testNumber;
        this.detail = detail;
        this.percentage = percentage;
    }
    
    public DataBean(int slnum, String registrationNumber, String testNumber, String detail[], Double totalMarks)
    {
        this.slnum = slnum;
        this.registrationNumber = registrationNumber;
        this.totalMarks = totalMarks.doubleValue();
        this.testNumber = testNumber;
        this.detail = detail;
        
    }

    public DataBean(int slnum, String registrationNumber, String category, String detail[], String compData[][], double totalMarks)
    {
        this.registrationNumber = registrationNumber;
        this.compData = compData;
        this.totalMarks = totalMarks;
        this.category = category;
        this.detail = detail;
        this.slnum = slnum;
    }
    /**
     * To add paper code
     * @param slnum
     * @param registrationNumber
     * @param detail
     * @param compData
     * @param totalMarks
     */
    public DataBean(int slnum, String registrationNumber, String category, String detail[], String compData[][], double totalMarks,String[] paper)
    {
        this.registrationNumber = registrationNumber;
        this.compData = compData;
        this.totalMarks = totalMarks;
        this.category = category;
        this.detail = detail;
        this.slnum = slnum;
        this.paper=paper;
    }

    public DataBean(int slnum, String registrationNumber, String detail[], String compData[][], double totalMarks)
    {
        this.registrationNumber = registrationNumber;
        this.compData = compData;
        this.totalMarks = totalMarks;
        this.detail = detail;
        this.slnum = slnum;
    }

    public DataBean(int slnum, String registrationNumber, String testNumber, String detail[], Double totalMarks,String category)
    {
    	this.slnum = slnum;
        this.registrationNumber = registrationNumber;
        this.totalMarks = totalMarks.doubleValue();
        this.testNumber = testNumber;
        this.detail = detail;
        
        this.category=category;
    }

	public DataBean(int slnum, String registrationNumber, String testNumber, String category, String student_name, String percentage, List<ReportInfoGetter>list, 
            Double totalMarks,String gender)
    {
        this.slnum = slnum;
        this.registrationNumber = registrationNumber;
        this.totalMarks = totalMarks.doubleValue();
        this.testNumber = testNumber;
        this.componentDetail = list;
        this.percentage = percentage;
        this.category = category;
        this.student_name = student_name;
        this.gender=gender;
    }

    public DataBean(String registrationNumber, String detail[], String compData[][], double sum_cm, String status, String reason)
    {
        this.registrationNumber = registrationNumber;
        this.detail = detail;
        this.compData = compData;
        this.sum_cm = sum_cm;
        this.status = status;
        this.reason = reason;
    }

    public int getSlnum()
    {
        return slnum;
    }

    public void setSlnum(int slnum)
    {
        this.slnum = slnum;
    }

    public String[] getDetail()
    {
        return detail;
    }

    public void setDetail(String detail[])
    {
        this.detail = detail;
    }

    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    public double getComputedMarks()
    {
        return computedMarks;
    }

    public void setComputedMarks(double computedMarks)
    {
        this.computedMarks = computedMarks;
    }

    public double getTotalMarks()
    {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks)
    {
        this.totalMarks = totalMarks;
    }

    public String getTestNumber()
    {
        return testNumber;
    }

    public void setTestNumber(String testNumber)
    {
        this.testNumber = testNumber;
    }

    public String getPercentage()
    {
        return percentage;
    }

    public void setPercentage(String percentage)
    {
        this.percentage = percentage;
    }

    public String[][] getCompData()
    {
        return compData;
    }

    public void setCompData(String compData[][])
    {
        this.compData = compData;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getStudent_name()
    {
        return student_name;
    }

    public void setStudent_name(String student_name)
    {
        this.student_name = student_name;
    }

    public double getSum_cm()
    {
        return sum_cm;
    }

    public void setSum_cm(double sum_cm)
    {
        this.sum_cm = sum_cm;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

	public String[] getPaper() {
		return paper;
	}

	public void setPaper(String[] paper) {
		this.paper = paper;
	}
    
}
