/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Inventory;
import pojo.hibernate.ErpmIssueReturnMaster;
import org.displaytag.decorator.TableDecorator;
import pojo.hibernate.ErpmIssueDetail;
import pojo.hibernate.ErpmIssueMaster;
import pojo.hibernate.ErpmIssueReceive;


import utils.DateUtilities;

public class InventoryDecorator extends TableDecorator{
    //this method used to change formate of irmReturnDate (yyyy-mm-dd) to (dd-mm-yyyy) in brouse of returnIssuedItemAction class
     public String getformattedirmReturnDate() {
      ErpmIssueReturnMaster erpmirm = (ErpmIssueReturnMaster)getCurrentRowObject();
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(erpmirm.getIrmReturnDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }
       
}
         //this method used to change formate of irmReturnDate (yyyy-mm-dd) to (dd-mm-yyyy) in brouse of ErpmIssueMaster class

       public String getformattedismIssueDate() {
      ErpmIssueMaster eim = (ErpmIssueMaster)getCurrentRowObject();
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(eim.getIsmIssueDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}
                //this method used to change formate of irmReturnDate (yyyy-mm-dd) to (dd-mm-yyyy) in brouse of ErpmIssueReceive class

       
         public String getformattedisrReceiptDate() {
      ErpmIssueReceive eir = (ErpmIssueReceive)getCurrentRowObject();
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(eir.getIsrReceiptDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}

      public String getFormattedEdit() {
     
       try {
//           ErpmIssueDetail eid = (ErpmIssueDetail)getCurrentRowObject();
//           if(eid.getErpmItemMaster().getErpmimSerialNoApplicable()=='Y')
//               return "Edit/View SerialNo";
//           else
               return "Edit";
       }
       catch (Exception e) {
        return "Error in formatted Edit";
    }

}


}
