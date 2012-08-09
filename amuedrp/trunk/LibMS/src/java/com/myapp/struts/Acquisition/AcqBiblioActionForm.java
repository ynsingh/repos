/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

/**
 *
 * @author maqbool
 */
public class AcqBiblioActionForm extends org.apache.struts.action.ActionForm {

           
    private String title;
    private String sub_title;
    private String author;
    private String sub_author;
    private String publisher_name;
    private String publication_place;
    private String publishing_year;
    private String lcc_no;
    private String isbn;
    private String edition;
    private String library_id;
    private String sub_library_id;
    private String search_keyword;
    private String search_by;
    private String sort_by;
    private String document_type;
    private int control_no;
    private String status;
    private String volume;
    private String primary_budget;
    private String unit_price;
    private String requested_date;
    private String requested_by;
    private int demand_id;

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }
     private String mem_id;

    public int getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(int demand_id) {
        this.demand_id = demand_id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    private String exchange;

    public String getVolume_no() {
        return volume_no;
    }

    public void setVolume_no(String volume_no) {
        this.volume_no = volume_no;
    }
    private String volume_no;
    private String no_of_volume;
    private int no_of_copies;
    private String vendor;
    private String acq_mode;
    private String approval_no;
    private String recommended_by;
     private String date;
     private String approved_by;
     private String button;
    private String list;
     private int title_id;
     private String list1;
     private String list2;
     private String list3;
     private String sub_author0;
     private String sub_author1;
     private String sub_author2;

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
    
     


    public String getList3() {
        return list3;
    }

    public void setList3(String list3) {
        this.list3 = list3;
    }

    public String getList2() {
        return list2;
    }

    public void setList2(String list2) {
        this.list2 = list2;
    }


    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
    private String approval_status;
     private String approval_date;

    public String getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(String approval_date) {
        this.approval_date = approval_date;
    }


    public String getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(String approval_status) {
        this.approval_status = approval_status;
    }


    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecommended_by() {
        return recommended_by;
    }

    public void setRecommended_by(String recommended_by) {
        this.recommended_by = recommended_by;
    }
    public String getApproval_no() {
        return approval_no;
    }

    public void setApproval_no(String approval_no) {
        this.approval_no = approval_no;
    }
   

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public String getAcq_mode() {
        return acq_mode;
    }

    public void setAcq_mode(String acq_mode) {
        this.acq_mode = acq_mode;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(int no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public String getNo_of_volume() {
        return no_of_volume;
    }

    public void setNo_of_volume(String no_of_volume) {
        this.no_of_volume = no_of_volume;
    }

    public int getControl_no() {
        return control_no;
    }

    public void setControl_no(int control_no) {
        this.control_no = control_no;
    }

    public String getPrimary_budget() {
        return primary_budget;
    }

    public void setPrimary_budget(String primary_budget) {
        this.primary_budget = primary_budget;
    }

    public String getRequested_by() {
        return requested_by;
    }

    public void setRequested_by(String requested_by) {
        this.requested_by = requested_by;
    }

    public String getRequested_date() {
        return requested_date;
    }

    public void setRequested_date(String requested_date) {
        this.requested_date = requested_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }




      

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getSearch_by() {
        return search_by;
    }

    public void setSearch_by(String search_by) {
        this.search_by = search_by;
    }

    public String getSearch_keyword() {
        return search_keyword;
    }

    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }
   
    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getSub_library_id() {
        return sub_library_id;
    }

    public void setSub_library_id(String sub_library_id) {
        this.sub_library_id = sub_library_id;
    }

    

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLcc_no() {
        return lcc_no;
    }

    public void setLcc_no(String lcc_no) {
        this.lcc_no = lcc_no;
    }

    public String getPublication_place() {
        return publication_place;
    }

    public void setPublication_place(String publication_place) {
        this.publication_place = publication_place;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(String publishing_year) {
        this.publishing_year = publishing_year;
    }

    public String getSub_author() {
        return sub_author;
    }

    public void setSub_author(String sub_author) {
        this.sub_author = sub_author;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

      public String getList1() {
        return list1;
    }

    public void setList1(String list1) {
        this.list1 = list1;
    }

    

    /**
     * @return
     */
   
    /**
     *
     */
    public AcqBiblioActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

  
}
