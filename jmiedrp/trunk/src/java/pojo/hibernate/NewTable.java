package pojo.hibernate;
// Generated 23 May, 2011 11:13:06 AM by Hibernate Tools 3.2.1.GA



/**
 * NewTable generated by hbm2java
 */
public class NewTable  implements java.io.Serializable {


     private Integer gtGtid;
     private short gtImId;
     private Integer gtEgmId;
     private String gtTermsDescription;

    public NewTable() {
    }

	
    public NewTable(short gtImId) {
        this.gtImId = gtImId;
    }
    public NewTable(short gtImId, Integer gtEgmId, String gtTermsDescription) {
       this.gtImId = gtImId;
       this.gtEgmId = gtEgmId;
       this.gtTermsDescription = gtTermsDescription;
    }
   
    public Integer getGtGtid() {
        return this.gtGtid;
    }
    
    public void setGtGtid(Integer gtGtid) {
        this.gtGtid = gtGtid;
    }
    public short getGtImId() {
        return this.gtImId;
    }
    
    public void setGtImId(short gtImId) {
        this.gtImId = gtImId;
    }
    public Integer getGtEgmId() {
        return this.gtEgmId;
    }
    
    public void setGtEgmId(Integer gtEgmId) {
        this.gtEgmId = gtEgmId;
    }
    public String getGtTermsDescription() {
        return this.gtTermsDescription;
    }
    
    public void setGtTermsDescription(String gtTermsDescription) {
        this.gtTermsDescription = gtTermsDescription;
    }




}


