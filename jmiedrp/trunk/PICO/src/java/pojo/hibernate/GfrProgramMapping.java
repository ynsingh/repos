package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA



/**
 * GfrProgramMapping generated by hbm2java
 */
public class GfrProgramMapping  implements java.io.Serializable {


     private Integer gpmId;
     private GfrMaster gfrMaster;
     private Erpmprogram erpmprogram;

    public GfrProgramMapping() {
    }

    public GfrProgramMapping(GfrMaster gfrMaster, Erpmprogram erpmprogram) {
       this.gfrMaster = gfrMaster;
       this.erpmprogram = erpmprogram;
    }
   
    public Integer getGpmId() {
        return this.gpmId;
    }
    
    public void setGpmId(Integer gpmId) {
        this.gpmId = gpmId;
    }
    public GfrMaster getGfrMaster() {
        return this.gfrMaster;
    }
    
    public void setGfrMaster(GfrMaster gfrMaster) {
        this.gfrMaster = gfrMaster;
    }
    public Erpmprogram getErpmprogram() {
        return this.erpmprogram;
    }
    
    public void setErpmprogram(Erpmprogram erpmprogram) {
        this.erpmprogram = erpmprogram;
    }




}


