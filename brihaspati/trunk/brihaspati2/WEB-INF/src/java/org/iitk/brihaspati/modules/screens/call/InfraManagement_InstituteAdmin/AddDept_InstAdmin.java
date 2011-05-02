package org.iitk.brihaspati.modules.screens.call.InfraManagement_InstituteAdmin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import org.apache.turbine.util.RunData;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.InfraManagementUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.om.Department;
import org.iitk.brihaspati.om.DepartmentPeer;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.Room;
import org.iitk.brihaspati.om.RoomPeer;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;


public class AddDept_InstAdmin extends SecureScreen_Institute_Admin
{
	public void doBuildTemplate( RunData data, Context context )
    {
	/* Get the mode for which part executed of the screen.
	 * get the counter for colour the tag in browser and 
	 * set mode and counter in context.
	 */
	String mode=data.getParameters().getString("mode","");
	String count=data.getParameters().getString("count","");
	context.put("count",count);
	context.put("mode",mode);
	context.put("utils", new InfraManagementUtil());
	
	
	if(count.equals("2"))
	{
		List<Department> departments = null;
		try {
			departments = DepartmentPeer.doSelect(new Criteria());
			context.put("departments", departments);
		} catch (TorqueException e) {
			e.printStackTrace();
		}
    } else if(count.equals("5"))
	{
		List<Department> departments = null;
		try {
			departments = DepartmentPeer.doSelect(new Criteria());
			context.put("departments", departments);
		} catch (TorqueException e) {
			e.printStackTrace();
		}
	} else if(count.equals("6"))
	{
		List<Room> rooms = null;
		try {
			rooms = RoomPeer.doSelect(new Criteria());
			context.put("rooms", rooms);
		} catch (TorqueException e) {
			e.printStackTrace();
		}
	}

    }

}
