/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import java.io.Serializable;

/**
 *
 * @author edrp02
 */
public class PlacedOrderList implements Serializable{



 String title,publisher_name,author,isbn,order_no,unit_price,acq_mode,vendor_id,recieved_copies,pending_copies;
 int no_of_copies,control_no,title_id;

   


    public String getAcq_mode() {
        return acq_mode;
    }

    public void setAcq_mode(String acq_mode) {
        this.acq_mode = acq_mode;
    }

    

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price =String.valueOf(unit_price) ;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }




    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    

    public String getPending_copies() {
        return pending_copies;
    }

    public void setPending_copies(String pending_copies) {
        if(pending_copies==null)
          this.pending_copies = "0";
        else
        this.pending_copies = pending_copies;

    }

//    public void setPending_copies(String pending_copies) {
//        if(pending_copies==null)
//          this.pending_copies = 0;
//        else
//          this.pending_copies =Integer.parseInt(pending_copies);
//    }


    public String getRecieved_copies() {
        return recieved_copies;
    }

//    public void setRecieved_copies(String recieved_copies) {
//
//        if(recieved_copies==null)
//         this.recieved_copies=0;
//        else
//         this.recieved_copies =Integer.parseInt(recieved_copies);
//    }

        public void setRecieved_copies(String recieved_copies) {

         if(recieved_copies==null)
           this.recieved_copies="0";
         else
           this.recieved_copies=recieved_copies;

    }




    public int getControl_no() {
        return control_no;
    }

    public void setControl_no(int control_no) {
        this.control_no = control_no;
    }



    public int getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(int no_of_copies) {
        this.no_of_copies = no_of_copies;
    }


    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

   
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

 

}
