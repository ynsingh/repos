/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.FeeHeads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.nmeict.smvdu.Beans.OtherFeeHeadMaster;

import org.nmeict.smvdu.Beans.db.CommonDB;

/**
 *
 * @author SHAISTA BANO
 */
@ManagedBean (name="feeHeadMaster")
@RequestScoped

public class FeeHeadMaster {
    public FeeHeadMaster (){}
    
    
    private String feeHeadName;
    private int feeHeadId;
    private double feeHeadValue;
    
    
    public String getFeeHeadName() {
        return this.feeHeadName;
    }
    
    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }
    public double getFeeHeadValue() {
        return this.feeHeadValue;
    }
    
    public void setFeeHeadValue(double feeHeadValue) {
        this.feeHeadValue = feeHeadValue;
    }
     
     public void setFeeHeadId(int feeHeadId) {
       
        this.feeHeadId = feeHeadId;
    }
    
    public int getFeeHeadId(){
       return feeHeadId; 
    }  
    List<OtherFeeHeadMaster> feeHeadList = new ArrayList<OtherFeeHeadMaster>();
    public List<OtherFeeHeadMaster> getFeesHeadList() {
        try
        {
           
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select * from feeHead_master");
            rst = pst.executeQuery();
            //System.err.println("\n\n\nrstttttttttt="+rst.next());
                
            while(rst.next())
            {
                OtherFeeHeadMaster otherFeeHeadMast = new OtherFeeHeadMaster();
                otherFeeHeadMast.setFeeHeadCode(rst.getInt(1));
                otherFeeHeadMast.setFeeHeadName(rst.getString(2));
                otherFeeHeadMast.setFeeHeadValue(rst.getDouble(3));
                otherFeeHeadMast.setFeeHeadId(rst.getInt(4));

                /*
                 otherFeeHeadMast.setDate(rst.getString(5));
                * */     

                feeHeadList.add(otherFeeHeadMast);
            }
            
            rst.close();
            pst.close();
            connection.close();
            return feeHeadList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void setFeeHeadList(List<OtherFeeHeadMaster> feeHeadList ) {
		this.feeHeadList = feeHeadList;
	}

    /*
     * Inserting New Fee Heads
     */
    
    public void saveMasterFeeHead(){
       
        try{
            //System.out.println("\n\nSave==============");
            
            String feeName;
            int Id = 0;

            feeName = this.getFeeHeadName();
            String code = getCodeFromGroups();
            code = getCodeForledgers(code);
            
            //System.out.println("code=="+code);
            
            Id = getLedgerId();
            
            //System.out.println("id=="+Id);
            
            saveFeeHeadInledgers(Id, code, feeName);
            saveFeeInFeeHeadMaster(feeName, Id);
            
        }
        catch(Exception e){}
        
        
    }
    
    /*
    * Making Connection from BGAS database.
    * Getting code from groups table of BGAS.
    */
    public String getCodeFromGroups(){
        PreparedStatement pst;
        ResultSet rst; 
        try{
            Connection bgasConnection = new CommonDB().getBGASConnection();
            pst = bgasConnection.prepareStatement("select code from groups where id = '"+125+"'");
            rst = pst.executeQuery();
            String code = "";
            while(rst.next()){
                code = rst.getString(1);
            }
            pst.close();
            rst.close();
            bgasConnection.close();
            return code;
        }
        catch(Exception e){
            return null;
        }
    }
    
    /*
    * Making Connection from BGAS database.
    * Counting  No of rows according to group_id.
    * Making code to insert new fee head in ledgers table.
    */
    
    public String getCodeForledgers(String code){
        try{
            PreparedStatement pst;
            ResultSet rst;
            
            Connection bgasConnection = new CommonDB().getBGASConnection();
            pst = bgasConnection.prepareStatement("select COUNT(code) as cp from ledgers where group_id = 125;");
            rst = pst.executeQuery();
            int rowCounter = 0;
            while(rst.next()){
                
                rowCounter = rst.getInt("cp");
            }
        
            //System.out.println("rowCounter=="+rowCounter);
            
            if(rowCounter <10){
                
                code = code+"0"+Integer.toString(rowCounter +1);
            }
            else{
                code = code+Integer.toString(rowCounter +1);
            }
            //System.out.println("code=="+code);
            pst.close();
            rst.close();
            bgasConnection.close();
            return code;
        }
        catch(Exception e){
            return null;
        }
    }
    
    /*
    * Making Connection from BGAS database.
    * Getting count of id from ledgers.
    */
    public int getLedgerId(){
        PreparedStatement pst;
        ResultSet rst;
        int Id = 0;
        try{
            Connection bgasConnection = new CommonDB().getBGASConnection();
            pst = bgasConnection.prepareStatement("select Max(id) from ledgers");
            //pst = bgasConnection.prepareStatement("select COUNT(id) from ledgers");
            rst=pst.executeQuery();
            
            while(rst.next()){
                Id = rst.getInt(1)+1;
                
            }
            pst.close();
            rst.close();
            bgasConnection.close();
            return Id;
        }
        catch(Exception e){
            return -1;
        }
    }
    
    /*
    * Making Connection from BGAS database.
    * Inserting new Fee heads in ledgers table.
    */
    
    public void saveFeeHeadInledgers(int Id, String code, String feeName){
        PreparedStatement pst;
               
        try{
            Connection bgasConnection = new CommonDB().getBGASConnection();
            pst = bgasConnection.prepareStatement("insert into ledgers (id,code,group_id,name,op_balance,op_balance_dc,type,reconciliation,lstatus) values('"+Id+"','"+code+"',125,'"+feeName+"',0,'D',0,0,0)" );
            pst.executeUpdate();
            pst.close();
            bgasConnection.close();
        }
        catch(Exception e){
        }
    }
    
    /*
    * Making Connection from BGAS database.
    * Inserting new Fee heads in feeHeadMaster table.
    */
    
    public void saveFeeInFeeHeadMaster(String feeName, int feeId){
        PreparedStatement pst;
        ResultSet rst;
        FacesContext fc = FacesContext.getCurrentInstance();
        try{
              
            Connection cn = new CommonDB().getConnection();
            List<OtherFeeHeadMaster> feeHeadList;
            feeHeadList = getFeesHeadList();
            for(OtherFeeHeadMaster fl : feeHeadList){
            
                if(fl.getFeeHeadName().equals(feeName)){
                   fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fee Head is Already Inserted.", "")); 
                   return;
                }
                    
            }
            if(!feeName.isEmpty()){
                
                pst = cn.prepareStatement("insert into feeHead_master(fee_head_name,fee_head_amt,fee_head_id) values('"+feeName+"',0,'"+feeId+"')" );
                pst.executeUpdate();
                cn.close();
               //new OtherFeeHeadMaster().setFeeHeadList(getFeesHeadList());
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fee Head is  Inserted Successfully.", ""));             
            } 
            else{
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fee Head Could not Inserted.", ""));
                
              //return "../setup/FeeHeadsDetails.xhtml?faces-redirect=true";
            }
            
            }catch(Exception e){ 
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fee Head Could not Inserted."+e, ""));
                return;
            }
    }
    
}