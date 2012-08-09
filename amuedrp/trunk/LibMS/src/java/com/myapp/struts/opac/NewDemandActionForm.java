/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;


import org.apache.struts.validator.ValidatorForm;
public class NewDemandActionForm extends ValidatorForm {
    
 int demand_id;
        private String sub_author;
    private String sub_author0;
    private String sub_author1;
    private String sub_author2;
 private String mem_type;
    private String sub_member_type;

    public int getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(int demand_id) {
        this.demand_id = demand_id;
    }

    public String getLcc_no() {
        return lcc_no;
    }

    public void setLcc_no(String lcc_no) {
        this.lcc_no = lcc_no;
    }

    public String getMem_type() {
        return mem_type;
    }

    public void setMem_type(String mem_type) {
        this.mem_type = mem_type;
    }

    public String getPublication_place() {
        return publication_place;
    }

    public void setPublication_place(String publication_place) {
        this.publication_place = publication_place;
    }

    public String getSub_author() {
        return sub_author;
    }

    public void setSub_author(String sub_author) {
        this.sub_author = sub_author;
    }

    public String getSub_author0() {
        return sub_author0;
    }

    public void setSub_author0(String sub_author0) {
        this.sub_author0 = sub_author0;
    }

    public String getSub_author1() {
        return sub_author1;
    }

    public void setSub_author1(String sub_author1) {
        this.sub_author1 = sub_author1;
    }

    public String getSub_author2() {
        return sub_author2;
    }

    public void setSub_author2(String sub_author2) {
        this.sub_author2 = sub_author2;
    }

    public String getSub_member_type() {
        return sub_member_type;
    }

    public void setSub_member_type(String sub_member_type) {
        this.sub_member_type = sub_member_type;
    }


    private String TXTTITLE;
    private String CMBCAT;
    private String TXTAUTHOR;
    private String TXTISBN;
    private String issn;
    private String callno;
    private String TXTPUB;
    private String TXTPUBYR;
    private String TXTREMARK;
    private String lang;
    private String TXTCOPY;
    private String TXTVOL;
    private String TXTEDITION;
    private String CMBLib;
private String cmdSubLibary;
     private String publication_place;
    private String lcc_no;



    /**
     *
     */
    public NewDemandActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    /**
     * @return the TXTTITLE
     */
    public String getTXTTITLE() {
        return TXTTITLE;
    }

    /**
     * @param TXTTITLE the TXTTITLE to set
     */
    public void setTXTTITLE(String TXTTITLE) {
        this.TXTTITLE = TXTTITLE;
    }

    /**
     * @return the CMBCAT
     */
    public String getCMBCAT() {
        return CMBCAT;
    }

    /**
     * @param CMBCAT the CMBCAT to set
     */
    public void setCMBCAT(String CMBCAT) {
        this.CMBCAT = CMBCAT;
    }

    /**
     * @return the TXTAUTHOR
     */
    public String getTXTAUTHOR() {
        return TXTAUTHOR;
    }

    /**
     * @param TXTAUTHOR the TXTAUTHOR to set
     */
    public void setTXTAUTHOR(String TXTAUTHOR) {
        this.TXTAUTHOR = TXTAUTHOR;
    }

    /**
     * @return the TXTISBN
     */
    public String getTXTISBN() {
        return TXTISBN;
    }

    /**
     * @param TXTISBN the TXTISBN to set
     */
    public void setTXTISBN(String TXTISBN) {
        this.TXTISBN = TXTISBN;
    }

    /**
     * @return the issn
     */
    public String getIssn() {
        return issn;
    }

    /**
     * @param issn the issn to set
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**
     * @return the callno
     */
    public String getCallno() {
        return callno;
    }

    /**
     * @param callno the callno to set
     */
    public void setCallno(String callno) {
        this.callno = callno;
    }

    /**
     * @return the TXTPUB
     */
    public String getTXTPUB() {
        return TXTPUB;
    }

    /**
     * @param TXTPUB the TXTPUB to set
     */
    public void setTXTPUB(String TXTPUB) {
        this.TXTPUB = TXTPUB;
    }

    /**
     * @return the TXTPUBYR
     */
    public String getTXTPUBYR() {
        return TXTPUBYR;
    }

    /**
     * @param TXTPUBYR the TXTPUBYR to set
     */
    public void setTXTPUBYR(String TXTPUBYR) {
        this.TXTPUBYR = TXTPUBYR;
    }

    /**
     * @return the TXTREMARK
     */
    public String getTXTREMARK() {
        return TXTREMARK;
    }

    /**
     * @param TXTREMARK the TXTREMARK to set
     */
    public void setTXTREMARK(String TXTREMARK) {
        this.TXTREMARK = TXTREMARK;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the TXTCOPY
     */
    public String getTXTCOPY() {
        return TXTCOPY;
    }

    /**
     * @param TXTCOPY the TXTCOPY to set
     */
    public void setTXTCOPY(String TXTCOPY) {
        this.TXTCOPY = TXTCOPY;
    }

    /**
     * @return the TXTVOL
     */
    public String getTXTVOL() {
        return TXTVOL;
    }

    /**
     * @param TXTVOL the TXTVOL to set
     */
    public void setTXTVOL(String TXTVOL) {
        this.TXTVOL = TXTVOL;
    }

    /**
     * @return the TXTEDITION
     */
    public String getTXTEDITION() {
        return TXTEDITION;
    }

    /**
     * @param TXTEDITION the TXTEDITION to set
     */
    public void setTXTEDITION(String TXTEDITION) {
        this.TXTEDITION = TXTEDITION;
    }

    /**
     * @return the CMBLib
     */
    public String getCMBLib() {
        return CMBLib;
    }

    /**
     * @param CMBLib the CMBLib to set
     */
    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }

    /**
     * @return the cmdSubLibary
     */
    public String getCmdSubLibary() {
        return cmdSubLibary;
    }

    /**
     * @param cmdSubLibary the cmdSubLibary to set
     */
    public void setCmdSubLibary(String cmdSubLibary) {
        this.cmdSubLibary = cmdSubLibary;
    }

  
}
