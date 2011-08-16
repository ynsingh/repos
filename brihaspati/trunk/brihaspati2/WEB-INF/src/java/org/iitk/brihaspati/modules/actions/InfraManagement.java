package org.iitk.brihaspati.modules.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.fileupload.FileItem;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.InfraManagementUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.om.BaseDepartmentPeer;
import org.iitk.brihaspati.om.BaseRoomPeer;
import org.iitk.brihaspati.om.Department;
import org.iitk.brihaspati.om.DepartmentPeer;
import org.iitk.brihaspati.om.Room;
import org.iitk.brihaspati.om.RoomPeer;

public class InfraManagement extends SecureAction_Institute_Admin
{
	public void doAddDept(RunData data, Context context) throws Exception {
		ParameterParser  pp = data.getParameters();
		StringBuilder errorMsgs = new StringBuilder();
		try {
			int instituteId=Integer.parseInt((data.getUser().getTemp("Institute_id")).toString());
		
			String departmentName = pp.getString("DEPARTMENTNAME", "").trim();
			if((StringUtil.checkString(departmentName) != -1) || departmentName.equals(""))
			{
				throw new Exception("Invalid department name: " + departmentName);
			}
		
			String departmentCode = pp.getString("DEPARTMENTCODE", "").trim();
			if((StringUtil.checkString(departmentCode) != -1) || departmentCode.equals(""))
			{
				throw new Exception("Invalid department code: " + departmentCode);
			}
		
			int floorsCount = pp.getInt("FLOORSCOUNT");
			if(floorsCount == 0)
			{
				throw new Exception("Invalid department code: " + floorsCount);
			}

			try
			{
				Department department = new Department();
				department.setInstituteId(instituteId);
				department.setDepartmentCode(departmentCode);
				department.setName(departmentName);
				department.setFloorsCount(floorsCount);
				department.save();
				System.out.println("Successfully added Department: " + department.getDepartmentId());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.append("Error: " + e.getMessage());
		}
		if(errorMsgs.length() > 0)
		{
			context.put("err", errorMsgs);
		}
	}

	public void doAddRoom(RunData data, Context context) throws Exception {
		ParameterParser pp = data.getParameters();
		StringBuilder errorMsgs = new StringBuilder();
		int instituteId=Integer.parseInt((data.getUser().getTemp("Institute_id")).toString());
		try 
		{
			String roomCode = pp.getString("ROOMCODE", "").trim();
			if((StringUtil.checkString(roomCode) != -1) || roomCode.equals(""))
			{
				throw new Exception("Invalid room code: " + roomCode);
			}
			
			int departmentId = pp.getInt("DEPARTMENTID", -1);
			if(departmentId == -1)
			{
				throw new Exception("Invalid department");
			}
		
			int capacity = pp.getInt("CAPACITY", 0);
			if(capacity == 0)
			{
				throw new Exception("Invalid capacity: " + capacity);
			}
	
			String lab = pp.getString("LAB", "").trim();
			String projector = pp.getString("PROJECTOR", "").trim();
			String deptOnly = pp.getString("DEPTONLY", "").trim();
			
			int floor = pp.getInt("FLOOR", -1);
			if(floor == -1)
			{
				throw new Exception("Invalid floor No.");
			}

			byte deptOnlyByte = 0;
			byte labByte = 0;
			byte projectorByte = 0;
			if(deptOnly.equals("Y"))
			{
				deptOnlyByte = 1;
			}
			if(lab.equals("Y"))
			{
				labByte = 1;
			}
			if(projector.equals("Y"))
			{
				projectorByte = 1;
			}
			try
			{
				Room room = new Room();
				room.setInstituteId(instituteId);
				room.setCapacity(capacity);
				room.setDepartmentId(departmentId);
				room.setDeptonly(deptOnlyByte);
				room.setLab(labByte);
				room.setProjector(projectorByte);
				room.setRoomCode(roomCode);
				room.setFloor(floor);
				room.save();
				System.out.println("Successfully added Room: " + room.getRoomId());
			} catch(Exception e)
			{
				System.out.println(e);
				throw e;
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			errorMsgs.append("Error: " + e.getMessage());
		}
		if(errorMsgs.length() > 0)
		{
			context.put("err", errorMsgs);
		}
	}

	public void doDeleteDepartment(RunData data, Context context) throws Exception {
		ParameterParser  pp = data.getParameters();
		String[] def = new String[0];
		String[] departmentIds = pp.getStrings("departments", def);
		StringBuilder errorMsgs = new StringBuilder();
		try
		{
			for(int i = 0; i < departmentIds.length; i++)
			{
				Criteria criteria = new Criteria();
				Criteria criteriaRooms = new Criteria();
				criteriaRooms.add(BaseRoomPeer.DEPARTMENT_ID, departmentIds[i]);
				criteria.add(BaseDepartmentPeer.DEPARTMENT_ID, departmentIds[i]);
				RoomPeer.doDelete(criteriaRooms);
				DepartmentPeer.doDelete(criteria);
			}
			System.out.println("Deleted departments!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			errorMsgs.append("Error: " + e.getMessage());
		}
		if(errorMsgs.length() > 0)
		{
			context.put("err", errorMsgs);
		}
	}

	public void doDeleteRoom(RunData data, Context context) throws Exception {
		ParameterParser  pp = data.getParameters();
		String[] def = new String[0];
		String[] roomIds = pp.getStrings("rooms", def);
		StringBuilder errorMsgs = new StringBuilder();
		try
		{
			Criteria criteria = new Criteria();
			for(int i = 0; i < roomIds.length; i++)
			{
				criteria.add(BaseRoomPeer.ROOM_ID, roomIds[i]);
				RoomPeer.doDelete(criteria);
			}
			System.out.println("Deleted rooms!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			errorMsgs.append("Error: " + e.getMessage());
		}
		if(errorMsgs.length() > 0)
		{
			context.put("err", errorMsgs);
		}
	}

	public void doAddMultipleDepartments(RunData data, Context context) throws Exception {
		ParameterParser  pp = data.getParameters();
		int instituteId=Integer.parseInt((data.getUser().getTemp("Institute_id")).toString());
		FileItem file = pp.getFileItem("file");
		String fileName=file.getName();
		System.out.println(fileName);
		String role=pp.getString("Role","");
		Date date=new Date();
		StringBuilder errorMsgs = new StringBuilder();
		try {
			File f=new File(TurbineServlet.getRealPath("/tmp")+"/"+fileName+role+date.toString()+".txt");
			f.createNewFile();
			FileReader fr=new FileReader(f);
			file.write(f);
			f.deleteOnExit();
			BufferedReader br=new BufferedReader(fr);
			String line;
			int lineNo = 0;
			while((line=br.readLine()) != null)
			{
				lineNo++;
				System.out.println(line);
				try {
					StringTokenizer st = new StringTokenizer(line,";",false);
					if(st.countTokens() < 2)
					{
						throw new Exception("Invalid no of tokens.");
					}
					String departmentName = st.nextToken().trim();
					String departmentCode = st.nextToken().trim();
					System.out.println(departmentName + "-" + departmentCode);
					if((StringUtil.checkString(departmentName) != -1) || departmentName.equals(""))
					{
						throw new Exception("Invalid department name: " + departmentName);
					}
					if((StringUtil.checkString(departmentCode) != -1) || departmentCode.equals(""))
					{
						throw new Exception("Invalid department code: " + departmentCode);
					}
					try
					{
						Department department = new Department();
						department.setInstituteId(instituteId);
						department.setDepartmentCode(departmentCode);
						department.setName(departmentName);
						department.save();
						System.out.println("Successfully added Department: " + department.getDepartmentId());
					}
					catch(Exception e)
					{
						throw e;
					}
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.append("<b><u>Line No: " + lineNo + "</u></b>: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.append("Error: " + e.getMessage());
		}
		if(errorMsgs.length() > 0)
		{
			context.put("err", errorMsgs);
		}
	}

	public void doAddMultipleRooms(RunData data, Context context) throws Exception {
		ParameterParser  pp = data.getParameters();
		int instituteId=Integer.parseInt((data.getUser().getTemp("Institute_id")).toString());
		FileItem file = pp.getFileItem("file");
		String fileName=file.getName();
		System.out.println(fileName);
		String role=pp.getString("Role","");
		Date date=new Date();
		StringBuilder errorMsgs = new StringBuilder();
		try {
			File f=new File(TurbineServlet.getRealPath("/tmp")+"/"+fileName+role+date.toString()+".txt");
			f.createNewFile();
			FileReader fr=new FileReader(f);
			file.write(f);
			f.deleteOnExit();
			BufferedReader br=new BufferedReader(fr);
			String line;
			int lineNo = 0;
			while((line=br.readLine()) != null)
			{
				lineNo++;
				System.out.println(line);
				try {
					StringTokenizer st = new StringTokenizer(line,";",false);
					if(st.countTokens() < 6)
					{
						throw new Exception("Invalid no of tokens.");
					}
					String roomCode = st.nextToken().trim();
					int capacity = Integer.parseInt(st.nextToken().trim());
					String departmentCode = st.nextToken().trim();
					System.out.println(roomCode+"-"+capacity+"-"+departmentCode);
					List<Department> d = InfraManagementUtil.getDepartmentByCode(departmentCode);
					if(d == null || d.size() <= 0)
					{
						throw new Exception("Department: " + departmentCode + " not found.");
					}
					int departmentId = d.get(0).getDepartmentId();
					String lab = st.nextToken().trim();
					String projector = st.nextToken().trim();
					String deptOnly = st.nextToken().trim();
					System.out.println(departmentId+"-"+lab+"-"+projector+"-"+deptOnly);
					if((StringUtil.checkString(roomCode) != -1)  || roomCode.equals(""))
					{
						throw new Exception("Invalid room code: " + roomCode);
					}
					byte deptOnlyByte = 0;
					byte labByte = 0;
					byte projectorByte = 0;
					if(deptOnly.equals("Y"))
					{
						deptOnlyByte = 1;
					}
					if(lab.equals("Y"))
					{
						labByte = 1;
					}
					if(projector.equals("Y"))
					{
						projectorByte = 1;
					}
					try
					{
						Room room = new Room();
						room.setInstituteId(instituteId);
						room.setCapacity(capacity);
						room.setDepartmentId(departmentId);
						room.setDeptonly(deptOnlyByte);
						room.setLab(labByte);
						room.setProjector(projectorByte);
						room.setRoomCode(roomCode);
						room.save();
						System.out.println("Successfully added Room: " + room.getRoomId());
					}
					catch(Exception e)
					{
						throw e;
					}
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.append("<b><u>Line No: " + lineNo + "</u></b>: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.append("Error: " + e.getMessage());
		}
		if(errorMsgs.length() > 0)
		{
			context.put("err", errorMsgs);
		}
	}

	public void doPerform(RunData data,Context context) throws Exception
	{
		String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_doAddDept"))
		{
			doAddDept(data,context);
		} else if(action.equals("eventSubmit_doAddRoom"))
		{
			doAddRoom(data,context);
		} else if(action.equals("eventSubmit_doDeleteDepartment"))
		{
			doDeleteDepartment(data,context);
		} else if(action.equals("eventSubmit_doDeleteRoom"))
		{
			doDeleteRoom(data,context);
		} else if(action.equals("eventSubmit_doAddMultipleDepartments"))
		{
			doAddMultipleDepartments(data, context);
		} else if(action.equals("eventSubmit_doAddMultipleRooms"))
		{
			doAddMultipleRooms(data, context);
		}
	}

}
