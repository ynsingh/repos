/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;
import org.displaytag.decorator.TableDecorator;
import pojo.hibernate.Institutionroleprivileges;

/**
 *
 * @author sknaqvi
 */
public class ManageInstitutionUserRoleDecorator  extends TableDecorator {

    public String getiupCanAdd()
    {
       Institutionroleprivileges irp = (Institutionroleprivileges)getCurrentRowObject();
       Boolean CanAdd = irp.getIupCanAdd();
       if (CanAdd ==  false)
           return "No";
       else
           return "Yes";
    }

    public String getiupCanEdit()
    {
       Institutionroleprivileges irp = (Institutionroleprivileges)getCurrentRowObject();
       Boolean CanEdit = irp.getIupCanEdit();
       if (CanEdit == false)
           return "No";
       else
           return "Yes";
    }

    public String getiupCanDelete()
    {
       Institutionroleprivileges irp = (Institutionroleprivileges)getCurrentRowObject();
       Boolean CanDelete = irp.getIupCanDelete();
       if (CanDelete == false)
           return "No";
       else
           return "Yes";
    }

    public String getiupCanView()
    {
       Institutionroleprivileges irp = (Institutionroleprivileges)getCurrentRowObject();
       Boolean CanView = irp.getIupCanView();
       if (CanView == false)
           return "No";
       else
           return "Yes";
    }



}
