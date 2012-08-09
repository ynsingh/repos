/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

/**
 *
 * @author edrp01
 */
public class CirCheckInReportActionForm extends org.apache.struts.action.ActionForm {
    
    
    private String memid;
    private String starting_date;
    private String end_date;
    private double totalfine;
    private double paid;
     private double paid1;
      private double paid2;
       private double paid3;
        private double paid4;
    private String paymod;
    private double remain;
    private String chequeno;
    private String bankname;
    private String date;
    private String slipno;

    public String getSlipno() {
        return slipno;
    }

    public void setSlipno(String slipno) {
        this.slipno = slipno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getChequeno() {
        return chequeno;
    }

    public void setChequeno(String chequeno) {
        this.chequeno = chequeno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

    public String getPaymod() {
        return paymod;
    }

    public void setPaymod(String paymod) {
        this.paymod = paymod;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getRemain() {
        return remain;
    }

    public void setRemain(double remain) {
        this.remain = remain;
    }

    public double getTotalfine() {
        return totalfine;
    }

    public void setTotalfine(double totalfine) {
        this.totalfine = totalfine;
    }

    

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

    public String getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }

    public double getPaid1() {
        return paid1;
    }

    public void setPaid1(double paid1) {
        this.paid1 = paid1;
    }

    public double getPaid2() {
        return paid2;
    }

    public void setPaid2(double paid2) {
        this.paid2 = paid2;
    }

    public double getPaid3() {
        return paid3;
    }

    public void setPaid3(double paid3) {
        this.paid3 = paid3;
    }

    public double getPaid4() {
        return paid4;
    }

    public void setPaid4(double paid4) {
        this.paid4 = paid4;
    }

    
    /**
     * @return
     */
   
    /**
     *
     */
    public CirCheckInReportActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    
}
