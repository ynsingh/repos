package org.iitk.brihaspati.modules.utils;

import java.util.List;

import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.BaseDepartmentPeer;
import org.iitk.brihaspati.om.BaseRoomPeer;
import org.iitk.brihaspati.om.Department;
import org.iitk.brihaspati.om.DepartmentPeer;
import org.iitk.brihaspati.om.Room;
import org.iitk.brihaspati.om.RoomPeer;

public class InfraManagementUtil {

	public static List<Department> getDepartmentList()
	{
		List<Department> departments = null;
		try {
			departments = DepartmentPeer.doSelect(new Criteria());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departments;
	}

	public static List<Room> getRoomList()
	{
		List<Room> rooms = null;
		try {
			rooms = RoomPeer.doSelect(new Criteria());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rooms;
	}

	public static List<Department> getDepartmentByName(String name)
	{
		List<Department> department = null;
		try {
			Criteria criteria = new Criteria();
			criteria.add(BaseDepartmentPeer.NAME, name);
			department = DepartmentPeer.doSelect(criteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department;
	}
	
	public static List<Department> getDepartmentByCode(String code)
	{
		List<Department> department = null;
		try {
			Criteria criteria = new Criteria();
			criteria.add(BaseDepartmentPeer.DEPARTMENT_CODE, code);
			department = DepartmentPeer.doSelect(criteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department;
	}

	public static Department getDepartmentById(int id)
	{
		Department department = null;
		try 
		{
			Criteria criteria = new Criteria();
			criteria.add(BaseDepartmentPeer.DEPARTMENT_ID, id);
			List<Department> deps = null;
			deps = DepartmentPeer.doSelect(criteria);
			if(deps == null || deps.size() < 1)
			{
				return null;
			} else
			{
				department = deps.get(0);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return department;
	}
	
	public static Room getRoomById(int id)
	{
		Room room = null;
		try
		{
			Criteria criteria = new Criteria();
			criteria.add(BaseRoomPeer.ROOM_ID, id);
			List<Room> rooms = null;
			rooms = RoomPeer.doSelect(criteria);
			if(rooms == null || rooms.size() < 1)
			{
				return null;
			} else
			{
				room = rooms.get(0);
			}

		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return room;
	}
	
	public static void deleteRoomsInDepartment(int departmentId)
	{
		try {
			Criteria criteriaRooms = new Criteria();
			criteriaRooms.add(BaseRoomPeer.DEPARTMENT_ID, departmentId);
			RoomPeer.doDelete(criteriaRooms);
		} catch (TorqueException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteRoomsInInstitute(int instituteId)
	{
		try {
			Criteria criteriaRooms = new Criteria();
			criteriaRooms.add(BaseRoomPeer.INSTITUTE_ID, instituteId);
			RoomPeer.doDelete(criteriaRooms);
		} catch (TorqueException e) {
			e.printStackTrace();
		}
	}
}
